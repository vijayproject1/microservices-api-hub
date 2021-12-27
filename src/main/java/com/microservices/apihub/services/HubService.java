package com.microservices.apihub.services;

import com.microservices.apihub.client.UserApiClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class HubService {

    @Value("${message.prefix:default}")
    String prefixMessage;

    @Value("${message.suffix:default}")
    String suffix;
    @Autowired
    UserApiClient userApiClient;

    @HystrixCommand(fallbackMethod = "defaultGreeting")
    public String getMessage(String userName) {
        String message = userApiClient.getMessageByUserName(userName);
        return prefixMessage + message + suffix;
    }
    

    private String defaultGreeting(String username) {
        return "Hello Default User!";
    }

    //@Retry(name = "hub-api", fallbackMethod = "defaultException")
    @CircuitBreaker(name = "hub-api", fallbackMethod = "defaultException")
    public String getResilience(String username) {
        System.out.println("RETRY HAPPENED");
        String message = userApiClient.getMessageByUserName(username);
        return prefixMessage + message + suffix;
    }
    private String defaultException(Exception e) {
        return "Hello Default User!";
    }

}
