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

    @Autowired
    private OpenAiRequest openAiRequest;

    public String getSqlStatement(String query) {
        String prompt = dbSchemaDescription + query;
        log.info(prompt);
        openAiRequest.setPrompt(prompt);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<OpenAiRequest> request = new HttpEntity<>(openAiRequest, headers);
        headers.set(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey);
        ResponseEntity<OpenAiResponse> response = restTemplate.exchange(openAIUrl, HttpMethod.POST, request, OpenAiResponse.class);
        log.info(response.getBody());
        String rawResponse = response.getBody().getChoices().get(0).getText();
        return cleanResult(rawResponse);
    }

    private String cleanResult(String result) {
        result = result.toLowerCase();
        log.info(result);
        int selectIndex = result.indexOf("select");
        log.info(result.indexOf("select"));
        if (selectIndex == -1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error! Invalid SQL statement");
        }
        return result.substring(selectIndex);
    }

}
