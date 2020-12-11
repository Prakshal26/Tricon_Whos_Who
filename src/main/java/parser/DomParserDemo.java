package parser;
//CTRL+ALT+L to format code

import com.fasterxml.jackson.databind.ObjectMapper;

import database.PostgreSQLJDBC;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import xpath.classes.*;
import xpath.constants.XPathConstants;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DomParserDemo {

    public static void main(String[] args) throws SQLException {

        PostgreSQLJDBC postgreSQLJDBC = new PostgreSQLJDBC();
        Connection connection = null;
        try {
            HashMapParser.abbrConverter();
            connection = postgreSQLJDBC.connect();

          //  File inputFile = new File("C:\\Users\\lenovo\\IdeaProjects\\x_json\\WhosWho_IgnoreBadIWWList\\WhosWho_IgnoreBadIWWList\\iww1.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
           // Document doc = dBuilder.parse(inputFile);
//            doc.getDocumentElement().normalize();
//            Node entryNode =doc.getDocumentElement();
//            NodeList nodeList = entryNode.getChildNodes();

            File dir = new File("C:\\Users\\lenovo\\IdeaProjects\\x_json\\WhosWho_IgnoreBadIWWList\\Test");

            File [] files = dir.listFiles();
            int inserted_id = 0;
            for(File file : files) {
                if(file.isFile() && file.getName().endsWith(".xml")) {
                    Document doc = dBuilder.parse(file);
                    Person person1 = ElementParse.parseFiles(doc);

                    inserted_id = postgreSQLJDBC.insertPeople(connection, person1);
                    if (inserted_id != 0) {
                        postgreSQLJDBC.insertProfession(connection, person1.getProfession(),inserted_id);

                    }
                    else {
                        System.out.println("Issue with Insertion");
                    }


                    //REMOVE THIS AFTERWARDS
                    //CONVERtiNG TO JSON

                    ObjectMapper mapper = new ObjectMapper();
                    String jsonString = mapper.writeValueAsString(person1);
                    System.out.println(jsonString);

                    File filet = new File ("person.json");
                    mapper.writeValue(filet,person1);

                }
            }

//
//            for (int i=0; i<nodeList.getLength();i++) {
//                Node nNode = nodeList.item(i);
//
//                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
//                    Element element = (Element)nNode;
//                    match(element,person);
//                }
//            }

            //CONVERTING to JSON


        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            connection.close();
        }
    }
}

