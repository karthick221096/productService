package com.karthick.productservice.commons;

import com.karthick.productservice.dtos.FakeStoreProductResponseDto;
import com.karthick.productservice.dtos.UserDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class AuthenticationCommons {
    private RestTemplate restTemplate;
    public AuthenticationCommons(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public UserDto validateToken(String token){
        if(token == null){return null;}

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization",token);
        HttpEntity<String> entity = new HttpEntity<>(headers);


        try {
            ResponseEntity<UserDto> response = restTemplate.exchange(
                    "http://localhost:9091/users/validate",
                    HttpMethod.POST,
                    entity,
                    UserDto.class);
            return response.getBody();
        } catch (HttpClientErrorException e) {
            System.err.println("Response Body: " + e.getResponseBodyAsString());
            throw e; // Rethrow to propagate the error
        }

    }
}
