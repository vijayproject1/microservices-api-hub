package com.microservices.apihub.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name = "user-api",url = "localhost:7101")
@FeignClient(name = "user-api")
public interface UserApiClient {

    @GetMapping("/user/message/{username}")
    public String getMessageByUserName(@PathVariable String username);
}
