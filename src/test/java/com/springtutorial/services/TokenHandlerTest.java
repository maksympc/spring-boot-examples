package com.springtutorial.services;

import org.bson.types.ObjectId;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.Assert.*;

public class TokenHandlerTest {

    @Test
    public void generateToken() {
        TokenHandler tokenHandler = new TokenHandler();
        String token = tokenHandler.generateAccessToken(new ObjectId("5a887d175696d3063450bc1c"), LocalDateTime.now().plusDays(14));
        System.out.println(token);

        Optional<ObjectId> id = tokenHandler.extractUserId(token);
        System.out.println(id.get().toString());
    }

}