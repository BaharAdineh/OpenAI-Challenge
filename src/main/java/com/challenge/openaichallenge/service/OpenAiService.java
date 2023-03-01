package com.challenge.openaichallenge.service;

import com.theokanning.openai.api.*;
import com.theokanning.openai.api.Completion.CompletionRequest;
import com.theokanning.openai.api.Completion.CompletionResponse;
import com.theokanning.openai.api.Engine.EngineRequest;
import com.theokanning.openai.api.Engine.EngineResponse;
import com.theokanning.openai.api.Model.ModelRequest;
import com.theokanning.openai.api.Model.ModelResponse;

import org.springframework.stereotype.Service;

@Service
public class OpenAiService {

    private final String apiKey;

    public OpenAiService(Config config) {
        this.apiKey = config.get("openai.apiKey");
    }

    public String generateText(String prompt) throws APIException {
        // Retrieve the engine
        EngineRequest engineRequest = new EngineRequest();
        engineRequest.setApiKey(apiKey);
        engineRequest.setEngineId("davinci");

        EngineResponse engineResponse = Engine.retrieve(engineRequest);

        // Retrieve the model
        ModelRequest modelRequest = new ModelRequest();
        modelRequest.setApiKey(apiKey);
        modelRequest.setModelId(engineResponse.getDefaultModel());

        ModelResponse modelResponse = Model.retrieve(modelRequest);

        // Generate text
        CompletionRequest completionRequest = new CompletionRequest();
        completionRequest.setApiKey(apiKey);
        completionRequest.setEngine(engineResponse.getId());
        completionRequest.setModel(modelResponse.getId());
        completionRequest.setPrompt(prompt);
        completionRequest.setMaxTokens(128);

        CompletionResponse completionResponse = Completion.create(completionRequest);

        return completionResponse.getChoices().get(0).getText();
    }

}
