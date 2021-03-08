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

                if (subElement.getTagName().equalsIgnoreCase("INDEXEDNAME")) {
                    StringBuilder indexedNameBuilder = new StringBuilder();
                    printNote(subElement.getChildNodes(),indexedNameBuilder);
                    person.setIndexedName(indexedNameBuilder.toString());
                }
                if (subElement.getTagName().equalsIgnoreCase("NOBILITY")) {
                    StringBuilder nobilityBuilder = new StringBuilder();
                    //nobilityBuilder.append(", ");
                    printNoteNobility(subElement.getChildNodes(), nobilityBuilder);
                    person.setNobility(nobilityBuilder.toString());
                }
                if (subElement.getTagName().equalsIgnoreCase("TITLES") ) {
                    StringBuilder titleBuilder = new StringBuilder();
                    //titleBuilder.append(", ");
                    printNote(subElement.getChildNodes(), titleBuilder);
                    person.setTitles(titleBuilder.toString());
                }
                if (subElement.getTagName().equalsIgnoreCase("GIVENNAME")) {
                    StringBuilder givenNameBuilder = new StringBuilder();
                    printNote(subElement.getChildNodes(), givenNameBuilder);
                    person.setGivenName(givenNameBuilder.toString());
                }
                if (subElement.getTagName().equalsIgnoreCase("PSEUDONYM")) {
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
    private static void printNoteNobility(NodeList nodeList, StringBuilder stringBuilder) {

        // StringBuilder stringBuilder = new StringBuilder();

        for (int count = 0; count < nodeList.getLength(); count++) {

            Node tempNode = nodeList.item(count);

            // make sure it's element node.
            if (tempNode.getNodeType() == Node.TEXT_NODE) {
                stringBuilder.append(tempNode.getNodeValue());
              //  stringBuilder.append(" ");
            }

            if (tempNode.getNodeType() == Node.ELEMENT_NODE) {

                Element eElement = (Element) tempNode;
                if (eElement.getTagName() == "ABBR") {
                    stringBuilder.append(HashMapParser.getAbbrMap().get(eElement.getAttribute("REFID")));
                    //stringBuilder.append(" ");
                }

                if (tempNode.hasChildNodes() && eElement.getTagName()!="ABBR") {
                    // loop again if has child nodes
                    printNote(tempNode.getChildNodes(), stringBuilder);
                }
            }

        }
    }
}
