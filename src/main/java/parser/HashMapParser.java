package parser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.HashMap;

public class HashMapParser {

    public static HashMap<String,String> abbrMap = new HashMap<>();

    public static void abbrConverter() {

        try {
            File inputFile = new File("C:\\Users\\admin\\IdeaProjects\\x_json\\Dom\\src\\main\\resources\\abbreviations.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("ABBRITEM");
            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);

                // System.out.println("\nCurrent Element :" + nNode.getNodeName());
                Element abbrElement = (Element) nNode;

                NodeList cList = nNode.getChildNodes();
                Node node = cList.item(1);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element expansionElement = (Element) node;
                    // System.out.println(expansionElement.getNodeName() + "==> " +expansionElement.getTextContent());
                    if (!abbrMap.containsKey(abbrElement.getAttribute("ID"))) {
                        abbrMap.put(abbrElement.getAttribute("ID").toUpperCase(), expansionElement.getTextContent());
                    }
                }
            }
//            System.out.println(abbrMap.size());
//            for (HashMap.Entry entry:  abbrMap.entrySet()) {
//                System.out.println(entry.getKey() + " and "+ entry.getValue());
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static HashMap<String, String> getAbbrMap() {
        return abbrMap;
    }

    public static void setAbbrMap(HashMap<String, String> abbrMap) {
        HashMapParser.abbrMap = abbrMap;
    }
}

