package com.challenge.openaichallenge.controller;


import com.theokanning.openai.api.APIException;
import com.challenge.openaichallenge.service.OpenAiService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OpenAiController {

    private final OpenAiService openAiService;

    @Autowired
    public OpenAiController(OpenAiService openAiService) {
        this.openAiService = openAiService;
    }

    @GetMapping("/generate-text")
    public String generateText(@RequestParam String prompt) {
        try {
            return openAiService.generateText(prompt);
        } catch (APIException e) {
            // Handle any API exceptions
            return "Error generating text: " + e.getMessage();
        }
    }
}
