package com.java.firebaseclient.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/application")
public class HealthController {

    @Value("${version}")
    private String version;

    @GetMapping("/health")
    public ResponseEntity<String> healthcheck(){
        return ResponseEntity.ok("Application is running!");
    }

    @GetMapping("/version")
    public ResponseEntity<String> version(){
        return ResponseEntity.ok(version);
    }
}
