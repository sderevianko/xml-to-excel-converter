package application;

import excelwriter.ExcelWriter;
import fooditem.FoodItem;
import org.xml.sax.SAXException;
import parserxml.ParserXml;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        ParserXml parser = new ParserXml();
        parser.parserXml();

        ExcelWriter excelWriter = new ExcelWriter();
        excelWriter.tableConstructor();
        excelWriter.excelWriter(parser.getFoodItems());
        excelWriter.fileCreator();

    }
}
