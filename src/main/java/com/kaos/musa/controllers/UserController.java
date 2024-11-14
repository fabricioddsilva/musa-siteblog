package com.kaos.musa.controllers;

import com.kaos.musa.entities.records.AuthDTO;
import com.kaos.musa.entities.records.RegisterDTO;
import com.kaos.musa.entities.User;
import com.kaos.musa.services.TokenService;
import com.kaos.musa.services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService service;

    @Autowired
    private TokenService tokenService;

    @GetMapping("/user/login")
    public String login(){
        return "pages/login";
    }

    @PostMapping("/user/login")
    public String login(AuthDTO data, HttpServletResponse response){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.username(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        // Criação do cookie com o token
        Cookie jwtCookie = new Cookie("jwt", token);
        jwtCookie.setHttpOnly(true);  // Impede acesso via JavaScript
        jwtCookie.setSecure(true);    // Usa somente em conexões HTTPS
        jwtCookie.setPath("/");       // Disponível em todas as rotas da aplicação
        jwtCookie.setMaxAge(2 * 60 * 60); // Define o tempo de expiração em 2 horas
        response.addCookie(jwtCookie);
        return "redirect:/blog";
    }

    @GetMapping("/user/register")
    public String register(){
        return "pages/register";
    }

    @PostMapping("/user/register")
    public ModelAndView register(RegisterDTO data){
        ModelAndView mv = new ModelAndView();
        if(service.isUsernameAlreadyRegistered(data.username())){
            mv.setViewName("pages/login");
            mv.addObject("msg", "Usuário já existe");
            return mv;
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(null, data.name(), data.username(), encryptedPassword, data.role());
        service.register(newUser);
        mv.setViewName("redirect:/blog");
        return mv;
    }
}
