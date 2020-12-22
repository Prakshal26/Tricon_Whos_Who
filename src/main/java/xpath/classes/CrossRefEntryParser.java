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

        if (person.getGivenname() != null) {
            crossRefEntry.setGivenName(person.getGivenname());
        }
        if (person.getIndexedname() != null) {
            crossRefEntry.setIndexedName(person.getIndexedname());
        }
        if (person.getNobility() != null) {
            crossRefEntry.setNobility(person.getNobility());
        }
        if (person.getTitle() != null) {
            crossRefEntry.setTitle(person.getTitle());
        }
        if (person.getPseudonym() != null) {
            crossRefEntry.setPseudonym(person.getPseudonym());
        }

        referredNameBuilder.append("<a href=\"https://www.worldwhoswho.com/views/entry.html?id=");
        referredNameBuilder.append((element.getAttribute("SLTARGETID").toLowerCase()) + "\">");
        printNote(element.getChildNodes(),referredNameBuilder);
        referredNameBuilder.append("</a> ");
        crossRefEntry.setReferred_name(referredNameBuilder.toString());

        if (element.hasAttribute("SLTARGETID")) {
            crossRefEntry.setReferred_id(element.getAttribute("SLTARGETID"));
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
