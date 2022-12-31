package com.example.testopenai.client;

import com.example.testopenai.model.dto.OpenAiRequest;
import com.example.testopenai.model.dto.OpenAiResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Component
@Log4j2
public class OpenAIClient {

    @Value("${apiKey}")
    private String apiKey;

    @Value("${openAIUrl}")
    private String openAIUrl;

    @Value("${dbSchemaDescription}")
    private String dbSchemaDescription;

    @Autowired
    private RestTemplate restTemplate;


    public String getSqlStatement(OpenAiRequest openAiRequest) {
        String prompt = dbSchemaDescription + openAiRequest.getPrompt();
        openAiRequest.setPrompt(prompt);
        openAiRequest.setStop(List.of("#", ";"));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<OpenAiRequest> request = new HttpEntity<>(openAiRequest, headers);
        headers.set(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey);
        ResponseEntity<OpenAiResponse> response = restTemplate.exchange(openAIUrl, HttpMethod.POST, request, OpenAiResponse.class);
        String rawResponse;
        if(response.getBody() == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No response from OpenAI");
        } else {
            rawResponse = response.getBody().getChoices().get(0).getText();
        }
        return cleanResult(rawResponse);
    }

    private String cleanResult(String result) {
        result = result.toLowerCase();
        int selectIndex = result.indexOf("select");
        if (selectIndex == -1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error! Invalid SQL statement");
        }
        return result.substring(selectIndex);
    }

}
