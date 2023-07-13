package ro.oane.caloriescounter.service;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import ro.oane.caloriescounter.record.FoodPiece;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class XlsxConverter {

    public List<FoodPiece> extractData(final String filePath) {
        final List<FoodPiece> foodPieces = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {
            final Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                if (row.getRowNum() != 0) {
                    final FoodPiece foodPiece = new FoodPiece();
                    for (Cell cell : row) {
                        switch (cell.getColumnIndex()) {
                            case 0 -> foodPiece.setCode(BigDecimal.valueOf(cell.getNumericCellValue()).longValue());
                            case 1 -> foodPiece.setName(cell.getStringCellValue());
                            case 2 -> foodPiece.setPortionDefault(BigDecimal.valueOf(cell.getNumericCellValue()).intValue());
                            case 3 -> foodPiece.setPortionAmount(cell.getStringCellValue());
                            case 4 -> foodPiece.setPortionName(cell.getStringCellValue());
                            case 6 -> foodPiece.setIncrement(cell.getStringCellValue());
                            case 7 -> foodPiece.setMultiplier(cell.getStringCellValue());
                            case 24 -> foodPiece.setCalories(cell.getStringCellValue());
                        }
                    }
                    foodPieces.add(foodPiece);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return foodPieces;
    }
}
