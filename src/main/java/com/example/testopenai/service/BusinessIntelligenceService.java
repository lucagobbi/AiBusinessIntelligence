package com.example.testopenai.service;

import com.example.testopenai.client.OpenAIClient;
import com.example.testopenai.model.dao.Item;
import com.example.testopenai.model.dto.CustomResponse;
import com.example.testopenai.model.dto.OpenAiRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class BusinessIntelligenceService {

    @Autowired
    OpenAIClient client;

    @Autowired
    JdbcTemplate jdbcTemplate;

    public CustomResponse getResultSetFromSqlStatement(OpenAiRequest openAiRequest) {
        CustomResponse customResponse = new CustomResponse();
        String statement = client.getSqlStatement(openAiRequest);
        customResponse.setSqlStatement(statement);
        List<Map<String, Object>> resultSet = jdbcTemplate.queryForList(statement);
        List<Item> items = new ArrayList<>();
        for(Map<String, Object> row : resultSet) {
            Item item = new Item();
            item.setId((Long) row.get("id"));
            item.setName((String) row.get("name"));
            item.setPrice((Double) row.get("price"));
            items.add(item);
        }
        customResponse.setResultSet(items);
        return customResponse;
    }

}
