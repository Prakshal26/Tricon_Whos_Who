package parser;
//CTRL+ALT+L to format code



import database.PostgreSQLJDBC;
import org.w3c.dom.Document;
import pojo.Person;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

public class DomParserDemo {

    public static void main(String[] args) throws SQLException {

        PostgreSQLJDBC postgreSQLJDBC = new PostgreSQLJDBC();
        Connection connection = null;
        try {
            HashMapParser.abbrConverter();
            connection = postgreSQLJDBC.connect();
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            File dir = new File("C:\\Users\\Admin\\IdeaProjects\\x_json\\WhosWho_IgnoreBadIWWList\\WhosWho_IgnoreBadIWWList");

            File [] files = dir.listFiles();
            int inserted_id = 0;
            int file_count =1;
            for(File file : files) {
                if(file.isFile() && file.getName().endsWith(".xml")) {
                    System.out.println("File "+ file_count++ + file.getName());

                    Document doc = dBuilder.parse(file);
                    Person person = ElementParse.parseFiles(doc);

                    inserted_id = postgreSQLJDBC.insertPeople(connection, person);
                    if (inserted_id == 0) {
                        System.out.println("Issue with Insertion");
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


