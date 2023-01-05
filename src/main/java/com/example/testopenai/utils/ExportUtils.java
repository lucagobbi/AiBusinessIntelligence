package com.example.testopenai.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExportUtils {

    public static Workbook getWorkbookFromExcel(InputStream inputStream) throws IOException {
        Workbook workbook = new XSSFWorkbook(inputStream);
        return workbook;
    }

    public static <T> void pojoToSheet(Sheet sheet, List<T> beans) throws Exception {

        if (beans.size() > 0) {
            Row row = null;
            Cell cell = null;
            int lastRowNum = sheet.getLastRowNum();
            int c = 0;
            int colCount = 0;
            DataFormat dataFormat = sheet.getWorkbook().createDataFormat();

            Class beanClass = beans.get(0).getClass();

            colCount = c;

            // contents
            for (T bean : beans) {
                c = 0;
                row = sheet.createRow(lastRowNum++);
                for (Field f : beanClass.getDeclaredFields()) {
                    cell = row.createCell(c++);
                    if (!f.isAnnotationPresent(ExcelColumn.class)) {
                        continue;
                    }
                    ExcelColumn ec = f.getAnnotation(ExcelColumn.class);
                    // do number formatting the contents
                    Map<String, Object> properties = new HashMap<>();
                    properties.put(CellUtil.DATA_FORMAT, dataFormat.getFormat("General"));
                    CellUtil.setCellStyleProperties(cell, properties);

                    f.setAccessible(true);
                    Object value = f.get(bean);
                    if (value != null) {
                        if (value instanceof String) {
                            cell.setCellValue((String)value);
                        } else if (value instanceof Double) {
                            cell.setCellValue((Double)value);
                        } else if (value instanceof Integer) {
                            cell.setCellValue((Integer)value);
                        } else if (value instanceof java.util.Date) {
                            cell.setCellValue((java.util.Date)value);
                        } else if (value instanceof Boolean) {
                            cell.setCellValue((Boolean)value);
                        }
                    }
                }
            }

            // auto size columns
            for (int col = 0; col < colCount; col++) {
                sheet.autoSizeColumn(col);
            }
        }

    }

}
