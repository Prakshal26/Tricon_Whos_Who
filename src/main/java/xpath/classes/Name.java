package xpath.classes;//package xpath.classes;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import parser.HashMapParser;
import parser.Person;

import java.util.LinkedList;
import java.util.List;

public class Name {


    public static void convert(Element element, Person person) {

        List<String> listNames = convertNames(element);
        person.setSurname(listNames.get(0));
        person.setNobility(listNames.get(1));
        person.setTitle(listNames.get(2));
        person.setFirstname(listNames.get(3));
        person.setPseudonym(listNames.get(4));

    }


    public static List<String> convertNames(Element element){

        StringBuilder surNameBuilder = new StringBuilder();
        StringBuilder firstNameBuilder = new StringBuilder();
        StringBuilder titleBuilder = new StringBuilder();
        StringBuilder nobilityBuilder = new StringBuilder();
        StringBuilder pseudonymBuilder = new StringBuilder();


        NodeList nodeList = element.getChildNodes();

        for (int i=0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element subElement = (Element) node;

                if (subElement.getTagName()=="INDEXEDNAME") {
                    printNote(subElement.getChildNodes(),surNameBuilder);
                }
                if (subElement.getTagName() == "NOBILITY") {
                    nobilityBuilder.append(", ");
                    printNoteNobility(subElement.getChildNodes(), nobilityBuilder);
                }
                if (subElement.getTagName() == "TITLES") {
                    titleBuilder.append(", ");
                    printNote(subElement.getChildNodes(), titleBuilder);
                }
                if (subElement.getTagName() == "GIVENNAME") {
                    printNote(subElement.getChildNodes(), firstNameBuilder);
                }
                if (subElement.getTagName() == "PSEUDONYM") {
                    pseudonymBuilder.append("(");
                    printNote(subElement.getChildNodes(), pseudonymBuilder);
                    pseudonymBuilder.append(")");
                }
            }
        }

        List <String> list = new LinkedList<>();
        list.add(surNameBuilder.toString());
        list.add(nobilityBuilder.toString());
        list.add(titleBuilder.toString());
        list.add(firstNameBuilder.toString());
        list.add(pseudonymBuilder.toString());
        return list;
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
