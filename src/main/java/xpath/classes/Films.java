package xpath.classes;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import parser.HashMapParser;

public class Films {

    public static String convert(Element element){

        StringBuilder stringBuilder = new StringBuilder();

        NodeList nodeList = element.getChildNodes();

        for (int i=0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element subElement = (Element) node;
                stringBuilder.append("<p>");
                if (subElement.hasChildNodes()) {
                    printNote(subElement.getChildNodes(),stringBuilder);
                    stringBuilder.append("</p>");
                } else {
                    stringBuilder.append(subElement.getTextContent());
                }
            }
        }
        return stringBuilder.toString();
    }


    private static void printNote(NodeList nodeList, StringBuilder stringBuilder) {

        // StringBuilder stringBuilder = new StringBuilder();

        for (int count = 0; count < nodeList.getLength(); count++) {

            Node tempNode = nodeList.item(count);


            // make sure it's element node.
            if (tempNode.getNodeType() == Node.TEXT_NODE) {
                stringBuilder.append(tempNode.getNodeValue());
            }

            if (tempNode.getNodeType() == Node.ELEMENT_NODE) {

                Element eElement = (Element) tempNode;
                if (eElement.getTagName() == "ABBR") {
                    stringBuilder.append("<abbr title=\"");
                    stringBuilder.append(HashMapParser.getAbbrMap().get(eElement.getAttribute("REFID")) + "\">");
                    stringBuilder.append((eElement.getTextContent()) + "</abbr>");
                }

                if (tempNode.hasChildNodes() && eElement.getTagName()!="ABBR") {
                    // loop again if has child nodes
                    printNote(tempNode.getChildNodes(), stringBuilder);
                }
         }

        }
    }

}
