package application;

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
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document document = builder.parse(new File("C:\\Users\\serya\\Desktop\\OOP\\gradle\\hello-gradle\\src\\main\\resources\\xml\\BreakfastMenu.xml"));

        document.getDocumentElement().normalize();

        Element root = document.getDocumentElement();
        System.out.println(root.getNodeName());

        NodeList nList = document.getElementsByTagName("food");
        System.out.println("============================");
        List<FoodItem> fooditems = new ArrayList<>();
        int index = 0;
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node node = nList.item(temp);
            System.out.print("");
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) node;
                FoodItem foodItem = new FoodItem(
                    eElement.getElementsByTagName("name").item(0).getTextContent(),
                    eElement.getElementsByTagName("price").item(0).getTextContent(),
                    eElement.getElementsByTagName("description").item(0).getTextContent(),
                    eElement.getElementsByTagName("calories").item(0).getTextContent());
                fooditems.add(index++, foodItem);
            }
        }

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
            cell.setCellValue(fooditems.get(i - 1).getName());
            cell.setCellStyle(style);

            cell = row.createCell(1);
            cell.setCellValue(fooditems.get(i - 1).getPrice());
            cell.setCellStyle(style);

            cell = row.createCell(2);
            cell.setCellValue(fooditems.get(i - 1).getDescription());
            cell.setCellStyle(style);

            cell = row.createCell(3);
            cell.setCellValue(fooditems.get(i - 1).getCalories());
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
