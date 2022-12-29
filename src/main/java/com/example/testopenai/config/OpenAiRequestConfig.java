package com.example.testopenai.config;

import com.example.testopenai.model.dto.OpenAiRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAiRequestConfig {

    @Bean
    public OpenAiRequest openAiRequest() {
        OpenAiRequest openAiRequest = new OpenAiRequest();
        openAiRequest.setModel("code-davinci-002");
        openAiRequest.setMax_tokens(256);
        openAiRequest.setTemperature(0);
        openAiRequest.setTop_p(1);
        openAiRequest.setFrequency_penalty(0);
        openAiRequest.setPresence_penalty(0);
        openAiRequest.setStop(List.of("#", ";"));
        return openAiRequest;
    }

}
