package application;

import excelwriter.Excelwriter;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        Excelwriter excelwriter = new Excelwriter();
        excelwriter.excelWriter();
    }
}
