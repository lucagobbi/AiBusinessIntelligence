package com.example.testopenai.service;

import com.example.testopenai.model.dto.CustomResponse;
import com.example.testopenai.model.dto.OpenAiRequest;

public interface BusinessIntelligenceService {

    public CustomResponse getResultSetFromSqlStatement(OpenAiRequest openAiRequest);

}
