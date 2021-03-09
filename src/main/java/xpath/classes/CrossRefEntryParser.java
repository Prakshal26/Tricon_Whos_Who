package xpath.classes;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import parser.HashMapParser;
import pojo.CrossRefEntry;
import pojo.Person;

public class CrossRefEntryParser {

    public static void convert(Element element, CrossRefEntry crossRefEntry, Person person) {

        StringBuilder referredNameBuilder = new StringBuilder();

        if (person.getGivenName() != null) {
            crossRefEntry.setGivenName(person.getGivenName());
        }
        if (person.getIndexedName() != null) {
            crossRefEntry.setIndexedName(person.getIndexedName());
        }
        if (person.getNobility() != null) {
            crossRefEntry.setNobility(person.getNobility());
        }
        if (person.getTitles() != null) {
            crossRefEntry.setTitles(person.getTitles());
        }
        if (person.getPseudonym() != null) {
            crossRefEntry.setPseudonym(person.getPseudonym());
        }

        referredNameBuilder.append("<a href=\"https://www.worldwhoswho.com/views/entry.html?id=");
        referredNameBuilder.append((element.getAttribute("SLTARGETID").toLowerCase()) + "\">");
        printNote(element.getChildNodes(),referredNameBuilder);
        referredNameBuilder.append("</a> ");
        crossRefEntry.setReferredName(referredNameBuilder.toString());

        if (element.hasAttribute("SLTARGETID")) {
            String starId = element.getAttribute("SLTARGETID");
            crossRefEntry.setReferredId(starId.toLowerCase());
        }
    }


    private static void printNote(NodeList nodeList, StringBuilder stringBuilder) {

        // StringBuilder stringBuilder = new StringBuilder();

        for (int count = 0; count < nodeList.getLength(); count++) {

            Node tempNode = nodeList.item(count);

            if (tempNode.getNodeType() == Node.TEXT_NODE) {
                // System.out.println("Text = "+tempNode.getTextContent());
                stringBuilder.append(tempNode.getTextContent().trim());
            }

            if (tempNode.getNodeType() == Node.ELEMENT_NODE) {

                Element eElement = (Element) tempNode;
                //System.out.println("Eleemnt name  = "+eElement.getTagName()+" data is "+tempNode.getTextContent());
                if (eElement.getTagName().equalsIgnoreCase("ABBR")) {
                    stringBuilder.append("<abbr title=\"");
                    stringBuilder.append(HashMapParser.getAbbrMap().get(eElement.getAttribute("REFID")) + "\">");
                    stringBuilder.append((eElement.getTextContent()) + "</abbr> ");
                }
                if (eElement.getTagName().equalsIgnoreCase("XREF")) {
                    stringBuilder.append("<a href=\"https://www.worldwhoswho.com/views/entry.html?id=");
                    stringBuilder.append((eElement.getAttribute("SLTARGETID").toLowerCase()) + "\">");
                    stringBuilder.append(eElement.getTextContent());
                    stringBuilder.append("</a> ");
                }

                if (tempNode.hasChildNodes() && eElement.getTagName() != "ABBR" && eElement.getTagName() != "XREF") {
                    // loop again if has child nodes
                    printNote(tempNode.getChildNodes(), stringBuilder);
                }
            }

        }
    }
}
