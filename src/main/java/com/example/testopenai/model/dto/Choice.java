package com.example.testopenai.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Choice {
    private String text;
    private Integer index;
    private String logprobs;
    private String finish_reason;
}
