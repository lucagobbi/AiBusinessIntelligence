package com.example.testopenai.controller;

import com.example.testopenai.client.OpenAIClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {

    @Autowired
    OpenAIClient client;

    @GetMapping("/test")
    public String testSQL() {
        return client.getSQL();
    }

}
