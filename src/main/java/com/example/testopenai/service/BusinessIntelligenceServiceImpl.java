package com.example.testopenai.service;

import com.example.testopenai.client.OpenAIClient;
import com.example.testopenai.mapper.ItemMapper;
import com.example.testopenai.model.dto.CustomResponse;
import com.example.testopenai.model.dto.OpenAiRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class BusinessIntelligenceServiceImpl implements BusinessIntelligenceService{

    @Autowired
    OpenAIClient client;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    ItemMapper itemMapper;

    public CustomResponse getResultSetFromSqlStatement(OpenAiRequest openAiRequest) {
        CustomResponse customResponse = new CustomResponse();
        String statement = client.getSqlStatement(openAiRequest);
        customResponse.setSqlStatement(statement);
        List<?> items = jdbcTemplate.query(statement, itemMapper);
        customResponse.setResultSet(items);
        return customResponse;
    }

}
