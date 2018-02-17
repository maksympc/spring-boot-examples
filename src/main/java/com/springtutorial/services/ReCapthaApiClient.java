package com.springtutorial.services;

import com.springtutorial.model.ReCaptchaResponseDto;
import lombok.NonNull;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@Component
public class ReCapthaApiClient {
    @Value("${app.reCaptcha.apiUrl}")
    private String URL;
    @Value("${app.reCaptcha.key}")
    private String key;

    private HttpClient httpClient = HttpClientBuilder.create().build();
    private ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
    private RestTemplate restTemplate = new RestTemplate(requestFactory);

    public ReCaptchaResponseDto verify(@NonNull String recaptchaResponse) {
        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
        form.add("secret", key);
        form.add("response", recaptchaResponse);
        try {
            return restTemplate.postForObject(new URI(URL), form, ReCaptchaResponseDto.class);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
