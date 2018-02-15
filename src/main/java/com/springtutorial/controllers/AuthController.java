package com.springtutorial.controllers;

import com.springtutorial.model.ReCaptchaResponseDto;
import com.springtutorial.model.RegisterDto;
import com.springtutorial.service.ReCapthaApiClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
public class AuthController {
    @Autowired
    ReCapthaApiClient reCapthaApiClient;


    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public void register(RegisterDto registerDto) {
        log.info("{}", registerDto);

        ReCaptchaResponseDto reCaptchaResponse = reCapthaApiClient.verify(registerDto.getRecaptchaResponse());
        log.info("{}", reCaptchaResponse);

        // using RuntimeException just for example
        if (!reCaptchaResponse.isSuccess()) {
            throw new RuntimeException("The reCaptchaResponse after verifying contains success:false status!");
        }
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity handleRuntimeException(RuntimeException e) {
        log.info("RuntimeException:" + e.getMessage());
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

}
