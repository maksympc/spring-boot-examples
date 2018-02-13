package com.springtutorial.service;

import com.springtutorial.model.StackOverflowWebsite;
import com.springtutorial.persistence.StackoverflowWebsiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class StackoverflowService {
    @Autowired
    private StackoverflowWebsiteRepository repository;

    private static List<StackOverflowWebsite> items = new ArrayList();

    static {
        items.add(
                new StackOverflowWebsite("stackoverflow", "https://cdn.sstatic.net/Sites/stackoverflow/img/favicon.ico",
                        "https://stackoverflow.com", "Stack Overflow (StackExchange)",
                        "Learn, Share, Build Stack Overflow is the largest online community for programmers to learn, share their knowledge and build their careers."));
        items.add(
                new StackOverflowWebsite("serverfault", "https://cdn.sstatic.net/Sites/serverfault/img/favicon.ico",
                        "https://serverfault.com", "Server Fault (StackExchange)",
                        "Server Fault is a question and answer site for system and network administrators. Join them; it only takes a minute."));
    }

    @PostConstruct
    public void init() {
        if (repository.count() == 0) {
            repository.save(items);
        }
    }

    public List<StackOverflowWebsite> findAll() {
        return repository.findAll();
    }
}