package xpath.classes;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import parser.HashMapParser;

public class HandleAbbr {
    public static String convert(Element element){

        StringBuilder stringBuilder = new StringBuilder();
        if (element.hasChildNodes()) {
            printNote(element.getChildNodes(),stringBuilder);
        } else {
            stringBuilder.append(element.getTextContent());
        }
        return stringBuilder.toString();
    }

    public static void printNote(NodeList nodeList, StringBuilder stringBuilder) {

        // StringBuilder stringBuilder = new StringBuilder();

        for (int count = 0; count < nodeList.getLength(); count++) {

            Node tempNode = nodeList.item(count);

            // make sure it's element node.
            if (tempNode.getNodeType() == Node.TEXT_NODE) {
               // System.out.println("Text = "+tempNode.getTextContent());
                stringBuilder.append(tempNode.getNodeValue());
                //stringBuilder.append(" ");
            }

            if (tempNode.getNodeType() == Node.ELEMENT_NODE) {

                Element eElement = (Element) tempNode;
                //System.out.println("Element name  = "+eElement.getTagName()+" data is "+tempNode.getTextContent());
                if (eElement.getTagName().equalsIgnoreCase("ABBR")) {
                    stringBuilder.append("<abbr title=\"");
                    stringBuilder.append(HashMapParser.getAbbrMap().get(eElement.getAttribute("REFID")) + "\">");
                    stringBuilder.append((eElement.getTextContent()) + "</abbr>");
                }
                if (eElement.getTagName().equalsIgnoreCase("XREF")) {
                    stringBuilder.append("<a href=\"https://www.worldwhoswho.com/views/entry.html?id=");
                    stringBuilder.append((eElement.getAttribute("SLTARGETID").toLowerCase())+"\">");
                    stringBuilder.append(eElement.getTextContent());
                    stringBuilder.append("</a>");
                }

                if (tempNode.hasChildNodes() && eElement.getTagName()!="ABBR" && eElement.getTagName()!="XREF") {
                    // loop again if has child nodes
                    printNote(tempNode.getChildNodes(), stringBuilder);
                }
            }

        }
    }
}
