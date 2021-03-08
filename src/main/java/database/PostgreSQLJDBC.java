//First Commit to remove Profession Table
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
                    .getConnection("jdbc:postgresql://localhost:5432/ww_data_parsing",
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

            PreparedStatement statement = connection.prepareStatement("INSERT into ww_people(xml_id, indexedname, " +
                    "givenname,title,nobility,pseudonym,portrait_image,qualifications,nationality,presentposition,nameatbirth,dateofbirth,placeofbirth," +
                    "dateofdeath,parentage,family,education,careerpara,honoursawards," +
                    "films,plays,tv,music,dance,artexhibition,radio,achievements,publications,leisureinterests," +
                    "contactdetails,management_address,dead,gender,region,subRegion)" + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);

            int i = 1;
            statement.setString(i++, person.getId());
            statement.setString(i++, person.getIndexedname());
            statement.setString(i++, person.getGivenname());
            statement.setString(i++, person.getTitle());
            statement.setString(i++, person.getNobility());
            statement.setString(i++, person.getPseudonym());
            statement.setString(i++, person.getPortrait_image());
            statement.setString(i++, person.getQualifications());
            statement.setString(i++, person.getNationality());
            statement.setString(i++, person.getPresentPosition());
            statement.setString(i++, person.getNameAtBirth());
            statement.setString(i++, person.getDateOfBirth());
            statement.setString(i++, person.getPlaceOfbirth());
            statement.setString(i++, person.getDateOfDeath());
            statement.setString(i++, person.getParentage());
            statement.setString(i++, person.getFamily());
            statement.setString(i++, person.getEducation());
            statement.setString(i++, person.getCareer());
            statement.setString(i++, person.getHonourAndAwards());
            statement.setString(i++, person.getFilms());
            statement.setString(i++, person.getPlays());
            statement.setString(i++, person.getTv());
            statement.setString(i++, person.getMusic());
            statement.setString(i++, person.getDance());
            statement.setString(i++, person.getArtExhibition());
            statement.setString(i++, person.getRadio());
            statement.setString(i++, person.getAchievements());
            statement.setString(i++, person.getPublications());
            statement.setString(i++, person.getLeisure_interests());
            statement.setString(i++, person.getContact_details());
            statement.setString(i++, person.getManagement());
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

    public void insertProfession(Connection connection, List<String> profession, int id) {

        try {
            PreparedStatement statement = connection.prepareStatement("INSERT into ww_profession(people_id, " +
                    "profession_name)" + "VALUES (?,?)");

            for (String s : profession) {
                statement.setInt(1, id);
                statement.setString(2,s);

                statement.execute();
            }
            statement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

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
