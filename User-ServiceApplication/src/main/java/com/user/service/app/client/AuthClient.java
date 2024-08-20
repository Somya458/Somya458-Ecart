/*
 * package com.user.service.app.client;
 * 
 * import org.springframework.cloud.openfeign.FeignClient; import
 * org.springframework.stereotype.Component; import
 * org.springframework.web.bind.annotation.GetMapping; import
 * org.springframework.web.bind.annotation.PostMapping; import
 * org.springframework.web.bind.annotation.RequestBody; import
 * org.springframework.web.bind.annotation.RequestParam;
 * 
 * import com.user.service.app.model.AuthenticationRequest;
 * 
 * @FeignClient(name = "authentication-service", url = "http://localhost:8085")
 * 
 * @Component public interface AuthClient {
 * 
 * @PostMapping("/token") public String generateToken(@RequestBody
 * AuthenticationRequest authenticationRequest);
 * 
 * @GetMapping("/validate") boolean validateToken(@RequestParam("token") String
 * token);
 * 
 * }
 */