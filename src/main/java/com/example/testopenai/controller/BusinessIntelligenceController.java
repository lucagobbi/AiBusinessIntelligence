package com.example.testopenai.controller;

import com.example.testopenai.model.dto.CustomResponse;
import com.example.testopenai.model.dto.OpenAiRequest;
import com.example.testopenai.service.BusinessIntelligenceServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
@Log4j2
public class BusinessIntelligenceController {

    @Autowired
    BusinessIntelligenceServiceImpl service;

    @PostMapping("/query-raw")
    public ResponseEntity<CustomResponse> queryRaw(@RequestBody OpenAiRequest openAiRequest) {
        log.info("Inizio chiamata queryRaw - query: " + openAiRequest.getPrompt());
        CustomResponse customResponse = service.getResultSetFromSqlStatement(openAiRequest);
        log.info("Fine chiamata queryRaw");
        return ResponseEntity.ok(customResponse);
    }

}
