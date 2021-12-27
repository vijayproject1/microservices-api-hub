package com.microservices.apihub.controller;

import com.microservices.apihub.services.HubService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hub")
public class HubController {
    Logger logger = LoggerFactory.getLogger(HubController.class);

    @Autowired
    HubService hubService;

    @GetMapping("/message/{username}")
    public String getMessage(@PathVariable String username) {
        logger.info("Received API-HUB Request {} " , username);

        return hubService.getMessage(username);
    }


    @GetMapping("/resilience/{username}")
    public String getResilience(@PathVariable String username) {
        logger.info("Received API-HUB getResilience {} " , username);
        return hubService.getResilience(username);
    }

}
