package com.platform.apigateway.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gateway-services")
public class GatewayServices {

    @GetMapping(value = "/say-hello")
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Hello World from gateway!");
    }
}
