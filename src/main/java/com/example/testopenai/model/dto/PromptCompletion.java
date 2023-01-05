package com.example.testopenai.model.dto;

import com.example.testopenai.utils.ExcelColumn;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PromptCompletion {

    @ExcelColumn(name = "prompt")
    private String prompt;

    @ExcelColumn(name = "completion")
    private String completion;

}
