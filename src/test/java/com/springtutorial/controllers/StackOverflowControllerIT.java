package com.springtutorial.controllers;

import com.google.common.collect.ImmutableList;
import com.springtutorial.model.StackOverflowWebsite;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StackOverflowControllerIT {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Before
    public void setUp() {
        mongoTemplate.dropCollection(StackOverflowWebsite.class);
        mongoTemplate.save(new StackOverflowWebsite("websiteId1", "imgId1", "website1", "title1", "description1"));
        mongoTemplate.save(new StackOverflowWebsite("websiteId2", "imgId2", "website2", "title2", "description2"));
    }

    @Test
    public void getListOfProviders() {
        ResponseEntity<List<StackOverflowWebsite>> response = restTemplate.exchange("/api/stackoverflow", HttpMethod.GET, null, new ParameterizedTypeReference<List<StackOverflowWebsite>>() {
        });

        List<StackOverflowWebsite> actualList = response.getBody();
        assertThat(actualList.size(), is(2));
        List<String> actualIds = actualList.stream()
                .map(stackOverflowWebsite -> stackOverflowWebsite.getId())
                .collect(collectingAndThen(toList(), ImmutableList::copyOf));
        assertThat(actualIds, containsInAnyOrder("websiteId1", "websiteId2"));
    }
}