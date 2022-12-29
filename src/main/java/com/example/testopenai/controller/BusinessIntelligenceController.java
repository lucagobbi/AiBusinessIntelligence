package com.example.testopenai.controller;

import com.example.testopenai.service.BusinessIntelligenceService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@Log4j2
public class BusinessIntelligenceController {

    @Autowired
    BusinessIntelligenceService service;

    @PostMapping("/query-raw")
    public ResponseEntity<List<Map<String, Object>>> queryRaw(@RequestBody String query) {
        log.info("Inizio chiamata queryRaw - query: " + query);
        List<Map<String, Object>> resultSet = service.getResultSetFromSqlStatement(query);
        log.info("Fine chiamata queryRaw");
        return ResponseEntity.ok(resultSet);
    }

}
