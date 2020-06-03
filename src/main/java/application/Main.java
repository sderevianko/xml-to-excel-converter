package application;

import excelwriter.ExcelWriter;
import org.xml.sax.SAXException;
import parserxml.XmlParser;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        XmlParser parser = new XmlParser();
        parser.parseXml();

        ExcelWriter excelWriter = new ExcelWriter();
        excelWriter.constructTable();
        excelWriter.writeToExcel(parser.getFoodItems());
        excelWriter.сreateFile();
    }
}
