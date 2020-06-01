package application;

import fooditem.FoodItem;
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
        for (Object o : fooditems) {
            System.out.println(o);
        }
    }
}
