package com.example.testopenai.service;

import com.example.testopenai.client.OpenAIClient;
import com.example.testopenai.mapper.ItemMapper;
import com.example.testopenai.model.dto.CustomResponse;
import com.example.testopenai.model.dto.OpenAiRequest;
import com.example.testopenai.model.dto.RequestPaginate;
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

    private static final String PAGINATION = " LIMIT ? OFFSET ?";
    private static final Integer LIMIT = 5;

    public CustomResponse getResultSetFromSqlStatement(OpenAiRequest openAiRequest) {
        CustomResponse customResponse = new CustomResponse();
        String statement = client.getSqlStatement(openAiRequest);
        customResponse.setSqlStatement(statement);
        List<?> items = jdbcTemplate.query(statement + PAGINATION, itemMapper, LIMIT, 0);
        customResponse.setResultSet(items);
        return customResponse;
    }

    @Override
    public List<?> getPaginate(RequestPaginate requestPaginate) {
        List<?> items = jdbcTemplate.query(
                requestPaginate.getStatement() + PAGINATION, itemMapper, LIMIT, requestPaginate.getPage() * 5
        );
        return items;
    }


}
