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

public class XmlParser {

    private final List<FoodItem> foodItems = new ArrayList<>();

    public List<FoodItem> getFoodItems() {
        return foodItems;
    }

    public List<FoodItem> parseXml() throws IOException, SAXException, ParserConfigurationException {
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        final DocumentBuilder builder = factory.newDocumentBuilder();
        final ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        final Document document = builder.parse(new File(classLoader.getResource("xml/BreakfastMenu.xml").getFile()));

        document.getDocumentElement().normalize();

        final Element root = document.getDocumentElement();
        System.out.println(root.getNodeName());

        final NodeList nList = document.getElementsByTagName("food");

        int index = 0;
        for (int temp = 0; temp < nList.getLength(); temp++) {
            final Node node = nList.item(temp);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                final Element element = (Element) node;
                final FoodItem foodItem = new FoodItem(
                    element.getElementsByTagName("name").item(0).getTextContent(),
                    element.getElementsByTagName("price").item(0).getTextContent(),
                    element.getElementsByTagName("description").item(0).getTextContent(),
                    element.getElementsByTagName("calories").item(0).getTextContent());
                foodItems.add(index++, foodItem);
            }
        }
        return foodItems;
    }

    @Override
    public String toString() {
        return "ParserXml{" +
            "foodItems=" + foodItems +
            '}';
    }
}
