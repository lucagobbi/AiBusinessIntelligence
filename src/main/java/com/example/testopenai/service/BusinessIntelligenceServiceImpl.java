package com.example.testopenai.service;

import com.example.testopenai.client.OpenAIClient;
import com.example.testopenai.mapper.ItemMapper;
import com.example.testopenai.model.dto.CustomResponse;
import com.example.testopenai.model.dto.OpenAiRequest;
import com.example.testopenai.model.dto.PromptCompletion;
import com.example.testopenai.repository.*;
import com.example.testopenai.utils.ExportUtils;
import lombok.extern.log4j.Log4j2;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Service
@Log4j2
public class BusinessIntelligenceServiceImpl implements BusinessIntelligenceService{

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    SupplierRepository supplierRepository;

    @Autowired
    PurchaseRepository purchaseRepository;

    @Autowired
    ItemPurchaseRepository itemPurchaseRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    OpenAIClient client;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    ItemMapper itemMapper;

    public CustomResponse getResultSetFromSqlStatement(OpenAiRequest openAiRequest) throws Exception {

        CustomResponse customResponse = new CustomResponse();
        String prompt = openAiRequest.getPrompt();
        String statement = client.getSqlStatement(openAiRequest);
        customResponse.setSqlStatement(statement);

        updateReport(PromptCompletion
                        .builder()
                        .prompt(prompt)
                        .completion(statement)
                        .build());

        List<?> items = jdbcTemplate.query(statement, itemMapper);
        customResponse.setResultSet(items);
        return customResponse;

    }

    private void updateReport(PromptCompletion promptCompletion) throws Exception {
        File file = new File("/home/luca/Documenti/ReportAi/report.xlsx");
        FileInputStream fileIn = new FileInputStream(file);
        Workbook workbook = ExportUtils.getWorkbookFromExcel(fileIn);
        ExportUtils.pojoToSheet(workbook.getSheetAt(0), List.of(promptCompletion));
        fileIn.close();
        FileOutputStream fileOut = new FileOutputStream("/home/luca/Documenti/ReportAi/report.xlsx");
        workbook.write(fileOut);
        fileOut.close();
        workbook.close();
    }

}
