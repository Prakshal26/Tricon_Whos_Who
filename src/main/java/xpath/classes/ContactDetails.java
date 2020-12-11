package xpath.classes;

import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import parser.HashMapParser;

import java.util.LinkedList;
import java.util.List;

public class ContactDetails {

    public static List<String> convert(Element element){

        List<String> stringList = new LinkedList<>();

        StringBuilder stringBuilder = new StringBuilder();
        String contactType = element.getAttribute("TYPE");
        if (!contactType.equalsIgnoreCase("CONTACT")) {
            if (element.hasChildNodes()) {
                printNote(element.getChildNodes(),stringBuilder);
            } else {
                stringBuilder.append(element.getTextContent());
            }
        }
        stringList.add(contactType);
        stringList.add(stringBuilder.toString());
        return stringList;
    }

    private static void printNote(NodeList nodeList, StringBuilder stringBuilder) {


        for (int count = 0; count < nodeList.getLength(); count++) {

            Node tempNode = nodeList.item(count);

            if (tempNode.getNodeType() == Node.ELEMENT_NODE) {

                Element eElement = (Element) tempNode;
                if (eElement.getTagName().equalsIgnoreCase("ADDRESS")) {
                    stringBuilder.append("<b>Address:</b> ");
                    addressAbbrHandler(eElement.getChildNodes(),stringBuilder);
                  //  stringBuilder.append(eElement.getTextContent());
                    if(eElement.hasAttribute("TYPE"))
                    stringBuilder.append(" ("+(eElement.getAttribute("TYPE").toLowerCase())+"); ");
                }
                if (eElement.getTagName().equalsIgnoreCase("TELEPHONE")) {
                    stringBuilder.append("<b>Tel:</b> ");
                    stringBuilder.append(eElement.getTextContent());
                    if(eElement.hasAttribute("TYPE"))
                    stringBuilder.append(" ("+(eElement.getAttribute("TYPE").toLowerCase())+"); ");
                }
                if (eElement.getTagName().equalsIgnoreCase("FAX")) {
                    stringBuilder.append("<b>Fax:</b> ");
                    stringBuilder.append(eElement.getTextContent());
                    if(eElement.hasAttribute("TYPE"))
                    stringBuilder.append(" ("+(eElement.getAttribute("TYPE").toLowerCase())+"); ");
                }
                if (eElement.getTagName().equalsIgnoreCase("TELEX")) {
                    stringBuilder.append("<b>Email:</b> <a href=\"mailto:");
                    stringBuilder.append((eElement.getTextContent())+"\">");
                    stringBuilder.append((eElement.getTextContent())+"</a>");
                    if(eElement.hasAttribute("TYPE"))
                    stringBuilder.append(" ("+(eElement.getAttribute("TYPE").toLowerCase())+"); ");
                }
                if (eElement.getTagName().equalsIgnoreCase("EMAIL")) {
                    stringBuilder.append("<b>Email:</b> <a href=\"mailto:");
                    stringBuilder.append((eElement.getTextContent())+"\">");
                    stringBuilder.append((eElement.getTextContent())+"</a>");
                    if(eElement.hasAttribute("TYPE"))
                    stringBuilder.append(" ("+(eElement.getAttribute("TYPE").toLowerCase())+"); ");
                }
                if (eElement.getTagName().equalsIgnoreCase("WEBSITE")) {
                    stringBuilder.append("<b>Website:</b> <a href=\"");
                    stringBuilder.append((eElement.getTextContent())+"\">");
                    stringBuilder.append((eElement.getTextContent())+"</a>");
                    if(eElement.hasAttribute("TYPE"))
                    stringBuilder.append(" ("+(eElement.getAttribute("TYPE").toLowerCase())+").");
                }
            }
        }

    }
    private static void addressAbbrHandler(NodeList nodeList, StringBuilder stringBuilder) {

        // StringBuilder stringBuilder = new StringBuilder();

        for (int count = 0; count < nodeList.getLength(); count++) {

            Node tempNode = nodeList.item(count);
            // make sure it's element node.
            if (tempNode.getNodeType() == Node.TEXT_NODE) {
               // System.out.println("Text = "+tempNode.getTextContent());
                stringBuilder.append(tempNode.getTextContent().trim());
                stringBuilder.append(" ");
            }

            if (tempNode.getNodeType() == Node.ELEMENT_NODE) {

                Element eElement = (Element) tempNode;
//                System.out.println("Eleemnt name  = "+eElement.getTagName()+" data is "+tempNode.getTextContent());

                if (eElement.getTagName().equalsIgnoreCase("ABBR")) {
                    stringBuilder.append("<abbr title=\"");
                    stringBuilder.append(HashMapParser.getAbbrMap().get(eElement.getAttribute("REFID")) + "\">");
                    stringBuilder.append((eElement.getTextContent()) + "</abbr> ");
                }
                if (eElement.getTagName().equalsIgnoreCase("XREF")) {
                    stringBuilder.append("<a href=\"https://www.worldwhoswho.com/views/entry.html?id=");
                    stringBuilder.append((eElement.getAttribute("SLTARGETID").toLowerCase())+"\">");
                    stringBuilder.append(eElement.getTextContent());
                    stringBuilder.append("</a> ");
                }

                if (tempNode.hasChildNodes() && eElement.getTagName()!="ABBR" && eElement.getTagName()!="XREF") {
                    // loop again if has child nodes
                    addressAbbrHandler(tempNode.getChildNodes(), stringBuilder);
                }
            }

        }
    }
}
