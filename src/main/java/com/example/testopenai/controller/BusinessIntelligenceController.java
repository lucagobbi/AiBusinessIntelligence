package com.example.testopenai.controller;

import com.example.testopenai.model.dto.CustomResponse;
import com.example.testopenai.model.dto.OpenAiRequest;
import com.example.testopenai.model.dto.RequestPaginate;
import com.example.testopenai.service.BusinessIntelligenceServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/get-paginate")
    public ResponseEntity<List<?>> getPaginate(HttpServletResponse response, @RequestBody RequestPaginate requestPaginate) {
        log.info("Inizio chiamata getPaginate");
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:8080");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        List<?> result = service.getPaginate(requestPaginate);
        log.info("Fine chiamata getPaginate");
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
