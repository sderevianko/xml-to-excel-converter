package excelwriter;

import fooditem.FoodItem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.xml.sax.SAXException;
import parserxml.Parserxml;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Excelwriter {

    public void excelWriter() throws IOException, ParserConfigurationException, SAXException {

        Parserxml parser = new Parserxml();
        List<FoodItem> fooditems2 = new ArrayList<>();
        fooditems2 = parser.parserXml();

        Workbook workbook = new XSSFWorkbook();

        Sheet sheet = workbook.createSheet("Food");
        sheet.setColumnWidth(0, 7000);
        sheet.setColumnWidth(1, 2000);
        sheet.setColumnWidth(2, 22000);
        sheet.setColumnWidth(3, 2000);

        Row header = sheet.createRow(0);

        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFFont font = ((XSSFWorkbook) workbook).createFont();
        font.setFontName("Calibri");
        font.setFontHeightInPoints((short) 11);
        font.setBold(true);
        headerStyle.setFont(font);

        Cell headerCell = header.createCell(0);
        headerCell.setCellValue("Name");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(1);
        headerCell.setCellValue("Price");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(2);
        headerCell.setCellValue("Description");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(3);
        headerCell.setCellValue("Calories");
        headerCell.setCellStyle(headerStyle);

        CellStyle style = workbook.createCellStyle();
        style.setWrapText(true);
        Row row;
        Cell cell;
        for (int i = 1; i <= 5; i++) {

            row = sheet.createRow(i);
            cell = row.createCell(0);
            cell.setCellValue(fooditems2.get(i - 1).getName());
            cell.setCellStyle(style);

            cell = row.createCell(1);
            cell.setCellValue(fooditems2.get(i - 1).getPrice());
            cell.setCellStyle(style);

            cell = row.createCell(2);
            cell.setCellValue(fooditems2.get(i - 1).getDescription());
            cell.setCellStyle(style);

            cell = row.createCell(3);
            cell.setCellValue(fooditems2.get(i - 1).getCalories());
            cell.setCellStyle(style);
        }

        File currDir = new File(".");
        String path = currDir.getAbsolutePath();
        String fileLocation = path.substring(0, path.length() - 1) + "breakfast_menu.xlsx";

        FileOutputStream outputStream = new FileOutputStream(fileLocation);
        workbook.write(outputStream);
        workbook.close();
    }
}
