package com.springtutorial.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class HealthController {
    @RequestMapping("/health")
    public ResponseEntity<String> getStatus() {
        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(10);
        if ((randomInt % 3) == 0) {
            return new ResponseEntity<>("INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Ok", HttpStatus.OK);
    }
}