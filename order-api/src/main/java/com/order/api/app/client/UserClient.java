package com.order.api.app.client;

import com.order.api.app.model.UserInfo;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "USER-API", url = "http://localhost:8002")
public interface UserClient {

    @PostMapping("/users/find-user-with-phone") 
    UserInfo getUserByPhoneNumber(@RequestBody Map<String, String> entity);
}
