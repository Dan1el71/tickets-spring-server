package com.microservice.gateway;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testController {

    @RequestMapping("/api/v1/test")
    public String test() {
        return "test";
    }
}
