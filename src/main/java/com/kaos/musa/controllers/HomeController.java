package com.kaos.musa.controllers;

import com.kaos.musa.entities.Reader;
import com.kaos.musa.services.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class HomeController {

    @Autowired
    private HomeService service;


    @GetMapping("/")
    public String index(){
        return "index";
    }

    @PostMapping("/newsletter")
    public ResponseEntity<String> insertReader(Reader reader) {
        if (service.isEmailAlreadyRegistered(reader.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email já cadastrado!");
        }
        service.insertReader(reader);
        return ResponseEntity.ok("Inscrição realizada com sucesso, Obrigado!!");
    }


}
