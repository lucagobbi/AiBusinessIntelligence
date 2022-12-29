package com.example.testopenai.service;

import com.example.testopenai.client.OpenAIClient;
import com.example.testopenai.model.dao.Category;
import com.example.testopenai.model.dao.Item;
import com.example.testopenai.model.dao.Supplier;
import com.example.testopenai.repository.CategoryRepository;
import com.example.testopenai.repository.ItemRepository;
import com.example.testopenai.repository.PurchaseRepository;
import com.example.testopenai.repository.SupplierRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class BusinessIntelligenceService {

    @Autowired
    OpenAIClient client;

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> getResultSetFromSqlStatement(String query) {
        String statement = client.getSqlStatement(query);
        List<Map<String, Object>> resultSet = jdbcTemplate.queryForList(statement);
        return resultSet;
    }

}
