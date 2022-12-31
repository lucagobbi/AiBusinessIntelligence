package com.example.testopenai.service;

import com.example.testopenai.model.dto.CustomResponse;
import com.example.testopenai.model.dto.OpenAiRequest;
import com.example.testopenai.model.dto.RequestPaginate;

import java.util.List;

public interface BusinessIntelligenceService {

    public CustomResponse getResultSetFromSqlStatement(OpenAiRequest openAiRequest);

    public List<?> getPaginate(RequestPaginate requestPaginate);

}
