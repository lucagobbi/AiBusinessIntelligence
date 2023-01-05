package com.example.testopenai.service;

import com.example.testopenai.model.dto.CustomResponse;
import com.example.testopenai.model.dto.OpenAiRequest;

import java.io.IOException;

public interface BusinessIntelligenceService {

    public CustomResponse getResultSetFromSqlStatement(OpenAiRequest openAiRequest) throws Exception;

}
