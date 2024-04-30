package com.example.project.core;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class ExcelExporter {

    public static void exportToExcel(TableView tableView, Stage primaryStage) throws IOException {
        ObservableList<TableColumn> columns = tableView.getColumns();
        ObservableList data = tableView.getItems();
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Data");
        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < columns.size(); i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns.get(i).getText());
        }
        for (int i = 0; i < data.size(); i++) {
            Row row = sheet.createRow(i + 1);
            for (int j = 0; j < columns.size(); j++) {
                Cell cell = row.createCell(j);
                Object value = columns.get(j).getCellData(i);
                if (value != null) {
                    if (value instanceof String) {
                        cell.setCellValue((String) value);
                    } else if (value instanceof Integer) {
                        cell.setCellValue((Integer) value);
                    } else if (value instanceof Double) {
                        cell.setCellValue((Double) value);
                    } else if (value instanceof Boolean) {
                        cell.setCellValue((Boolean) value);
                    } else {
                        cell.setCellValue(value.toString());
                    }
                }
            }
        }
        for (int i = 0; i < columns.size(); i++) {
            sheet.autoSizeColumn(i);
        }

        // Mở cửa sổ FileChooser
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Excel File");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Excel files (*.xlsx)", "*.xlsx");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(primaryStage);

        if (file != null) {
            try (FileOutputStream fileOut = new FileOutputStream(file)) {
                workbook.write(fileOut);
            }
        }
        workbook.close();
    }
}
