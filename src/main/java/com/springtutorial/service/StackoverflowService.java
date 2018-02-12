package com.springtutorial.service;

import com.springtutorial.model.StackOverflowWebsite;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class StackoverflowService {
    private static List<StackOverflowWebsite> fakeItems = new ArrayList();

    static {
        fakeItems.add(new StackOverflowWebsite("stackoverflow", "https://cdn.sstatic.net/Sites/stackoverflow/img/favicon.ico", "https://stackoverflow.com/", "Stack Overflow (StackExchange)", "Learn, Share, Build\n" +
                "Stack Overflow is the largest online community for programmers to learn, share their knowledge and build their careers."));
        fakeItems.add(new StackOverflowWebsite("serverfault", "https://cdn.sstatic.net/Sites/serverfault/img/favicon.ico", "https://serverfault.com/", "Server Fault (StackExchange)", "Server Fault is a question and answer site for system and network administrators. Join them; it only takes a minute."));
    }

    public List<StackOverflowWebsite> findAll() {
        return fakeItems;
    }
}
