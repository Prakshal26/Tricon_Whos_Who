package parser;
//CTRL+ALT+L to format code

import com.fasterxml.jackson.databind.ObjectMapper;

import database.PostgreSQLJDBC;
import org.w3c.dom.Document;
import pojo.CrossRefEntry;
import pojo.Person;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DomParserDemo {

    public static void main(String[] args) throws SQLException {

        PostgreSQLJDBC postgreSQLJDBC = new PostgreSQLJDBC();
        Connection connection = null;
        try {
            HashMapParser.abbrConverter();
            connection = postgreSQLJDBC.connect();
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            File dir = new File("C:\\Users\\lenovo\\IdeaProjects\\x_json\\WhosWho_IgnoreBadIWWList\\WhosWho_IgnoreBadIWWList");

            File [] files = dir.listFiles();
            int inserted_id = 0;
            for(File file : files) {
                if(file.isFile() && file.getName().endsWith(".xml")) {
                    Document doc = dBuilder.parse(file);
                    List<Object> objectList = ElementParse.parseFiles(doc);
                    Person person1 = (Person) objectList.get(0);
                    CrossRefEntry crossRefEntry = (CrossRefEntry) objectList.get(1);
                    if (crossRefEntry.getXml_surname() != null) {

                        postgreSQLJDBC.insertCrossRefEntry(connection,crossRefEntry);

                        ObjectMapper mapper = new ObjectMapper();
                        String jsonString = mapper.writeValueAsString(crossRefEntry);
                        System.out.println(jsonString);
                       // File filet = new File ("person.json");
                        //mapper.writeValue(filet,person1);
                    } else {
                        inserted_id = postgreSQLJDBC.insertPeople(connection, person1);
                        if (inserted_id != 0) {
                            postgreSQLJDBC.insertProfession(connection, person1.getProfession(),inserted_id);
                        }
                        else {
                            System.out.println("Issue with Insertion");
                        }
                        ObjectMapper mapper = new ObjectMapper();
                        String jsonString = mapper.writeValueAsString(person1);
                        System.out.println(jsonString);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            connection.close();
        }
    }
}

