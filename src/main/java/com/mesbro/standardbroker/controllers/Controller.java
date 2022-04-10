package com.mesbro.standardbroker.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/spring")
public class Controller {

    @GetMapping("/")
    public ResponseEntity<String> getTeste () {
        return ResponseEntity.ok().body("show de bola");
    }
    
}
