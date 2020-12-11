package xpath.classes;//package xpath.classes;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import parser.HashMapParser;
import pojo.Person;

import java.util.LinkedList;
import java.util.List;

public class Name {

    public static void convert(Element element, Person person) {

        NodeList nodeList = element.getChildNodes();

        for (int i=0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element subElement = (Element) node;

                if (subElement.getTagName()=="INDEXEDNAME") {
                    StringBuilder surNameBuilder = new StringBuilder();
                    printNote(subElement.getChildNodes(),surNameBuilder);
                    person.setSurname(surNameBuilder.toString());
                }
                if (subElement.getTagName() == "NOBILITY") {
                    StringBuilder nobilityBuilder = new StringBuilder();
                    nobilityBuilder.append(", ");
                    printNoteNobility(subElement.getChildNodes(), nobilityBuilder);
                    person.setNobility(nobilityBuilder.toString());
                }
                if (subElement.getTagName() == "TITLES") {
                    StringBuilder titleBuilder = new StringBuilder();
                    titleBuilder.append(", ");
                    printNote(subElement.getChildNodes(), titleBuilder);
                    person.setTitle(titleBuilder.toString());
                }
                if (subElement.getTagName() == "GIVENNAME") {
                    StringBuilder firstNameBuilder = new StringBuilder();
                    printNote(subElement.getChildNodes(), firstNameBuilder);
                    person.setFirstname(firstNameBuilder.toString());
                }
                if (subElement.getTagName() == "PSEUDONYM") {
                    StringBuilder pseudonymBuilder = new StringBuilder();
                    pseudonymBuilder.append("(");
                    printNote(subElement.getChildNodes(), pseudonymBuilder);
                    pseudonymBuilder.append(")");
                    person.setPseudonym(pseudonymBuilder.toString());
                }
            }
        }
    }

    private static void printNote(NodeList nodeList, StringBuilder stringBuilder) {

        // StringBuilder stringBuilder = new StringBuilder();

        for (int count = 0; count < nodeList.getLength(); count++) {

            Node tempNode = nodeList.item(count);

            // make sure it's element node.
            if (tempNode.getNodeType() == Node.TEXT_NODE) {
                stringBuilder.append(tempNode.getTextContent().trim());
            }

            if (tempNode.getNodeType() == Node.ELEMENT_NODE) {

                Element eElement = (Element) tempNode;
                if (eElement.getTagName() == "ABBR") {
                    stringBuilder.append("<abbr title=\"");
                    stringBuilder.append(HashMapParser.getAbbrMap().get(eElement.getAttribute("REFID")) + "\">");
                    stringBuilder.append((eElement.getTextContent()) + "</abbr> ");
                }


                if (tempNode.hasChildNodes() && eElement.getTagName()!="ABBR") {
                    // loop again if has child nodes
                    printNote(tempNode.getChildNodes(), stringBuilder);
                }
            }

        }
    }
    private static void printNoteNobility(NodeList nodeList, StringBuilder stringBuilder) {

        // StringBuilder stringBuilder = new StringBuilder();

        for (int count = 0; count < nodeList.getLength(); count++) {

            Node tempNode = nodeList.item(count);

            // make sure it's element node.
            if (tempNode.getNodeType() == Node.TEXT_NODE) {
                stringBuilder.append(tempNode.getTextContent().trim());
                stringBuilder.append(" ");
            }

            if (tempNode.getNodeType() == Node.ELEMENT_NODE) {

                Element eElement = (Element) tempNode;
                if (eElement.getTagName() == "ABBR") {
                    stringBuilder.append(HashMapParser.getAbbrMap().get(eElement.getAttribute("REFID")));
                    stringBuilder.append(" ");
                }


                if (tempNode.hasChildNodes() && eElement.getTagName()!="ABBR") {
                    // loop again if has child nodes
                    printNote(tempNode.getChildNodes(), stringBuilder);
                }
            }

        }
    }
}
