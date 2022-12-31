package com.example.testopenai.model.dto;

import lombok.Data;

@Data
public class RequestPaginate {
    private String statement;
    private Integer page;
}
