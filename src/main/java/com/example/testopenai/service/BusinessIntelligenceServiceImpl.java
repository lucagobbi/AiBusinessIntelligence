package com.example.testopenai.service;

import com.example.testopenai.client.OpenAIClient;
import com.example.testopenai.mapper.ItemMapper;
import com.example.testopenai.model.dao.*;
import com.example.testopenai.model.dto.CustomResponse;
import com.example.testopenai.model.dto.OpenAiRequest;
import com.example.testopenai.repository.*;
import jakarta.annotation.PostConstruct;
import lombok.extern.log4j.Log4j2;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

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

    public CustomResponse getResultSetFromSqlStatement(OpenAiRequest openAiRequest) throws IOException {
        CustomResponse customResponse = new CustomResponse();
        String statement = client.getSqlStatement(openAiRequest);
        customResponse.setSqlStatement(statement);
        updateReport(openAiRequest.getPrompt(), statement);
        List<?> items = jdbcTemplate.query(statement, itemMapper);
        customResponse.setResultSet(items);
        return customResponse;
    }

    private void updateReport(String prompt, String completion) throws IOException {
        ClassPathResource reportResource = new ClassPathResource("report.xlsx");
        FileInputStream fileIn = new FileInputStream(reportResource.getFile());
        Workbook workbook  = new XSSFWorkbook(fileIn);
        fileIn.close();
        Sheet sheet = workbook.getSheetAt(0); // get the first sheet
        Row row = getFirstFreeRow(sheet);
        Cell promptCell = row.getCell(0); // get the first cell
        Cell completionCell = row.getCell(1); // get the first cell
        promptCell.setCellValue(prompt); // set the cell value
        completionCell.setCellValue(completion);
        FileOutputStream fileOut = new FileOutputStream("report.xlsx");
        workbook.write(fileOut);
        fileOut.close();
        workbook.close();
    }

    private Row getFirstFreeRow(Sheet sheet) {
        Row firstRow = sheet.getRow(0);
        for(Row row : sheet) {
            if(isRowEmpty(row)){
                return row;
            }
            firstRow = sheet.getRow(row.getRowNum());
        }
        return firstRow;
    }
    private boolean isRowEmpty(Row row) {
        for (int c = row.getFirstCellNum(); c < row.getLastCellNum(); c++) {
            Cell cell = row.getCell(c);
            if (cell != null && cell.getCellType() != CellType.BLANK)
                return false;
        }
        return true;
    }

}
