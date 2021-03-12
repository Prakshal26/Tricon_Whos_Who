package xpath.classes;

import org.w3c.dom.Element;
import pojo.Person;

public class CrossRefEntryParser {

    public static void convert(Element element, Person person) {

        StringBuilder referredNameBuilder = new StringBuilder();

        HandleAbbr.printNote(element.getChildNodes(), referredNameBuilder);
        person.setReferenceName(referredNameBuilder.toString());

        if (element.hasAttribute("SLTARGETID")) {
            String starId = element.getAttribute("SLTARGETID");
            person.setReferenceId(starId.toLowerCase());
        }
    }
//
//
//    private static void printNote(NodeList nodeList, StringBuilder stringBuilder) {
//
//        // StringBuilder stringBuilder = new StringBuilder();
//
//        for (int count = 0; count < nodeList.getLength(); count++) {
//
//            Node tempNode = nodeList.item(count);
//
//            if (tempNode.getNodeType() == Node.TEXT_NODE) {
//                // System.out.println("Text = "+tempNode.getTextContent());
//                stringBuilder.append(tempNode.getNodeValue());
//            }
//
//            if (tempNode.getNodeType() == Node.ELEMENT_NODE) {
//
//                Element eElement = (Element) tempNode;
//
//                if (eElement.getTagName().equalsIgnoreCase("ABBR")) {
//                    stringBuilder.append("<abbr title=\"");
//                    stringBuilder.append(HashMapParser.getAbbrMap().get(eElement.getAttribute("REFID")) + "\">");
//                    stringBuilder.append((eElement.getTextContent()) + "</abbr>");
//                }
//                if (eElement.getTagName().equalsIgnoreCase("XREF")) {
//                    stringBuilder.append("<a href=\"https://www.worldwhoswho.com/views/entry.html?id=");
//                    stringBuilder.append((eElement.getAttribute("SLTARGETID").toLowerCase()) + "\">");
//                    stringBuilder.append(eElement.getTextContent());
//                    stringBuilder.append("</a>");
//                }
//
//                if (tempNode.hasChildNodes() && eElement.getTagName() != "ABBR" && eElement.getTagName() != "XREF") {
//                    // loop again if has child nodes
//                    printNote(tempNode.getChildNodes(), stringBuilder);
//                }
//            }
//
//        }
//    }
}
