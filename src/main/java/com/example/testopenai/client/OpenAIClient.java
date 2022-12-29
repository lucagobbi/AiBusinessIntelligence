package com.example.testopenai.client;

import com.example.testopenai.model.dto.OpenAiRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class OpenAIClient {

    @Value("${apiKey}")
    private String apiKey;

    @Value("${openAIUrl}")
    private String openAIUrl;

    @Autowired
    private RestTemplate restTemplate;

    public String getSQL() {

        String prompt = "### MySql tables, with their properties:\n" +
                "#\n" +
                "# Item(id, name, price, supplier_id)\n" +
                "# Supplier(id, company_name, category_id)\n" +
                "# Category(id, name)\n" +
                "#\n" +
                "### A query to list the names of the items in the category \"Hardware\" that costs less than two hundred dollars";

        OpenAiRequest openAiRequest = new OpenAiRequest();
        openAiRequest.setModel("code-davinci-002");
        openAiRequest.setPrompt(prompt);
        openAiRequest.setMax_tokens(256);
        openAiRequest.setTemperature(0);
        openAiRequest.setTop_p(1);
        openAiRequest.setFrequency_penalty(0);
        openAiRequest.setPresence_penalty(0);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<OpenAiRequest> request = new HttpEntity<>(openAiRequest, headers);
        headers.set(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey);
        return restTemplate.exchange(openAIUrl, HttpMethod.POST, request, String.class).getBody();
    }

}
