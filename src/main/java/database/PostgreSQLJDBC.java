
package database;

import pojo.CrossRefEntry;
import pojo.Person;

import java.sql.*;
import java.util.List;

public class PostgreSQLJDBC {

    public Connection connect() {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/ww_data_parsing_profession",
                            "postgres", "root");

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);

        }
        return connection;
    }

    public int insertPeople(Connection connection, Person person) {

        int id = 0;
        Statement stmt = null;
        try {


            PreparedStatement statement = connection.prepareStatement("INSERT into ww_people(xmlId,indexedName,nobility," +
                    "titles,givenName,pseudonym,portraitImage,qualifications,nationality,profession,presentPosition,nameAtBirth,dateOfBirth,placeOfBirth," +
                    "dateOfDeath,parentage,family,education,careerPara,honoursAwards," +
                    "films,plays,tv,music,dance,artExhibition,radio,achievements,publications,leisureInterests," +
                    "contactDetails,managementAddress,dead,gender,region,subRegion)" + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);

            int i = 1;
            statement.setString(i++, person.getXmlId());
            statement.setString(i++, person.getIndexedName());
            statement.setString(i++, person.getNobility());
            statement.setString(i++, person.getTitles());
            statement.setString(i++, person.getGivenName());
            statement.setString(i++, person.getPseudonym());

            statement.setString(i++, person.getPortraitImage());
            statement.setString(i++, person.getQualifications());
            statement.setString(i++, person.getNationality());

            Array array = connection.createArrayOf("VARCHAR", person.getProfession().toArray());
            statement.setArray(i++,array);

            statement.setString(i++, person.getPresentPosition());
            statement.setString(i++, person.getNameAtBirth());
            statement.setString(i++, person.getDateOfBirth());
            statement.setString(i++, person.getPlaceOfBirth());
            statement.setString(i++, person.getDateOfDeath());
            statement.setString(i++, person.getParentage());
            statement.setString(i++, person.getFamily());
            statement.setString(i++, person.getEducation());
            statement.setString(i++, person.getCareerPara());
            statement.setString(i++, person.getHonoursAwards());
            statement.setString(i++, person.getFilms());
            statement.setString(i++, person.getPlays());
            statement.setString(i++, person.getTv());
            statement.setString(i++, person.getMusic());
            statement.setString(i++, person.getDance());
            statement.setString(i++, person.getArtExhibition());
            statement.setString(i++, person.getRadio());
            statement.setString(i++, person.getAchievements());
            statement.setString(i++, person.getPublications());
            statement.setString(i++, person.getLeisureInterests());
            statement.setString(i++, person.getContactDetails());
            statement.setString(i++, person.getManagementAddress());
            statement.setInt(i++, person.getDead());
            statement.setString(i++, person.getGender());
            statement.setString(i++, person.getRegion());
            statement.setString(i++, person.getSubRegion());

            statement.execute();

            ResultSet rs = statement.getGeneratedKeys();

            if(rs.next()){
                id=rs.getInt(1);
            }
            return id;


        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return id;
    }

    public void insertCrossRefEntry(Connection connection, CrossRefEntry crossRefEntry) {


        try {
            PreparedStatement statement = connection.prepareStatement("INSERT into ww_crossrefentry(id, " +
                    "indexedname, givenname, nobility, title, pseudonym, referred_id,referred_name)" + "VALUES (?,?,?,?,?,?,?,?)");

            int i =1;
            statement.setString(i++, crossRefEntry.getId());
            statement.setString(i++, crossRefEntry.getIndexedName());
            statement.setString(i++, crossRefEntry.getGivenName());
            statement.setString(i++, crossRefEntry.getNobility());
            statement.setString(i++, crossRefEntry.getTitle());
            statement.setString(i++, crossRefEntry.getPseudonym());
            statement.setString(i++, crossRefEntry.getReferred_id());
            statement.setString(i++, crossRefEntry.getReferred_name());

            statement.execute();
            statement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
