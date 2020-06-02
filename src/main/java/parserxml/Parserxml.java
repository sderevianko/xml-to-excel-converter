package parserxml;

import fooditem.FoodItem;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Parserxml {

    private List<FoodItem> fooditems = new ArrayList<>();

    public List<FoodItem> getFooditems() {
        return fooditems;
    }

    public List<FoodItem> parserXml() throws IOException, SAXException, ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document document = builder.parse(new File("C:\\Users\\serya\\Desktop\\OOP\\gradle\\hello-gradle\\src\\main\\resources\\xml\\BreakfastMenu.xml"));

        document.getDocumentElement().normalize();

        Element root = document.getDocumentElement();
        System.out.println(root.getNodeName());

        NodeList nList = document.getElementsByTagName("food");
        System.out.println("============================");

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
        return getFooditems();
    }

    @Override
    public String toString() {
        return "Parserxml{" +
            "fooditems=" + fooditems +
            '}';
    }
}
