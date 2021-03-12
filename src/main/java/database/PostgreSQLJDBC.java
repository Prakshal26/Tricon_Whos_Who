
package database;

import pojo.Person;

import java.sql.*;

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


            PreparedStatement statement = connection.prepareStatement("INSERT into ww_people(xml_id,indexed_name,nobility," +
                    "titles,given_name,pseudonym,portrait_image,qualifications,nationality,profession,present_position,name_at_birth,date_of_birth,place_of_birth," +
                    "date_of_death,parentage,family,education,career_para,honours_awards," +
                    "films,plays,tv,music,dance,art_exhibition,radio,achievements,publications,leisure_interests," +
                    "contact_details,management_address,dead,gender,region,sub_region,reference_id,reference_name)" + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);

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

            /*
            Un-Comment this to Convert Profession Into a Array.
             */
            //Array array = connection.createArrayOf("VARCHAR", person.getProfession().toArray());
            //statement.setArray(i++,array);

            /*
            Storing Profession as a text.
            If else is added because if the list is empty it was showing black instead of null in DB.
             */
            if (person.getProfession().isEmpty()) {
                statement.setString(i++,null);
            } else {
                String joinedProfession = String.join(", ",person.getProfession());
                statement.setString(i++, joinedProfession);
            }

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

            statement.setString(i++, person.getReferenceId());
            statement.setString(i++, person.getReferenceName());

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
}
