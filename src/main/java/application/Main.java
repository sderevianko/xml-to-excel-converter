package application;

import excelwriter.ExcelWriter;
import org.xml.sax.SAXException;
import parserxml.XmlParser;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        final XmlParser parser = new XmlParser();
        parser.parseXml();

        final ExcelWriter excelWriter = new ExcelWriter();
        excelWriter.constructTable();
        excelWriter.writeToExcel(parser.getFoodItems());
        excelWriter.createFile();
    }
}
