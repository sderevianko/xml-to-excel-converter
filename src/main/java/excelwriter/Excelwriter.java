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

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelWriter {

    private Workbook workbook;
    private Sheet sheet;

    public void constructTable() {
        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet("Food");
        sheet.setColumnWidth(0, 7000);
        sheet.setColumnWidth(1, 2000);
        sheet.setColumnWidth(2, 22000);
        sheet.setColumnWidth(3, 2000);

        final Row header = sheet.createRow(0);

        final CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        final XSSFFont font = ((XSSFWorkbook) workbook).createFont();
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
    }

    public void writeToExcel(List<FoodItem> foodItems) throws IOException, ParserConfigurationException, SAXException {

        final CellStyle style = workbook.createCellStyle();
        style.setWrapText(true);
        Row row;
        Cell cell;

        for (int i = 1; i <= 5; i++) {
            FoodItem food = foodItems.get(i - 1);
            row = sheet.createRow(i);
            cell = row.createCell(0);
            cell.setCellValue(food.getName());
            cell.setCellStyle(style);

            cell = row.createCell(1);
            cell.setCellValue(food.getPrice());
            cell.setCellStyle(style);

            cell = row.createCell(2);
            cell.setCellValue(food.getDescription());
            cell.setCellStyle(style);

            cell = row.createCell(3);
            cell.setCellValue(food.getCalories());
            cell.setCellStyle(style);
        }
    }

    public void createFile() throws IOException {
        final File currDir = new File(".");
        final String path = currDir.getAbsolutePath();
        final String fileLocation = path.substring(0, path.length() - 1) + "breakfast_menu.xlsx";

        final FileOutputStream outputStream = new FileOutputStream(fileLocation);
        workbook.write(outputStream);
        workbook.close();
    }
}
