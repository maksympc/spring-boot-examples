package com.springtutorial.controllers;

import com.springtutorial.model.StackOverflowWebsite;
import com.springtutorial.services.StackoverflowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/stackoverflow")
public class StackOverflowController {
    @Autowired
    private StackoverflowService stackoverflowService;

    @RequestMapping
    public List<StackOverflowWebsite> getListOfProviders() {
        return stackoverflowService.findAll();
    }
}
