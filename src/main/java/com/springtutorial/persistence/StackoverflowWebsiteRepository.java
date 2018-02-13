package com.springtutorial.persistence;

import com.springtutorial.model.StackOverflowWebsite;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StackoverflowWebsiteRepository extends MongoRepository<StackOverflowWebsite, String> {
    List<StackOverflowWebsite> findByWebsite(String website);
}