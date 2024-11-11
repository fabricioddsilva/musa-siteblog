package com.kaos.musa.controllers;

import com.kaos.musa.entities.Lead;
import com.kaos.musa.entities.Reader;
import com.kaos.musa.services.BlogService;
import com.kaos.musa.services.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class HomeController {

    @Autowired
    private HomeService service;

    @Autowired
    private BlogService blogService;


    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("posts", blogService.recentPosts());
        return "index";
    }

    @PostMapping("/newsletter")
    public ResponseEntity<String> insertReader(Reader reader) {
        if (service.isEmailAlreadyRegistered(reader)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email já cadastrado!");
        }
        service.insertReader(reader);
        return ResponseEntity.ok("Inscrição realizada com sucesso, Obrigado!!");
    }

    @PostMapping("/proposal")
    public ResponseEntity<String> insertLead(Lead lead){
        if(service.isEmailAlreadyRegistered(lead)){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email já cadastrado!");
        }
        service.insertLead(lead);
        return ResponseEntity.ok("Tudo Certo. Muito obrigado por se interessar por nossa solução!!");
    }


}
