import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.util.Properties;
import java.io.InputStream;
import java.io.FileInputStream;


public class Program {
    // Static variables which will hold the required information from the database queries for Window2 to read.
    public static List<String> MediaList = new ArrayList<>();
    public static List<String> DescriptionList = new ArrayList<>();
    public static List<String> TalentList = new ArrayList<>();

    public static void beginCodeExecution(String selectedClass, String selectedSpec) throws IOException, InterruptedException, SQLException {
        //Checks to make sure the static lists passed to the window classes are empty after initial run
        if (MediaList.size() != 0) {
            MediaList.clear();
            DescriptionList.clear();
            TalentList.clear();
        }
        // Initialize variables to be used to fetch configuration settings for the database
        String dburl = null;
        String dbName = null;
        String port = null;
        String username = null;
        String password = null;

        // Grabs config settings from "resources/config.properties"
        try (InputStream input = new FileInputStream("resources/config.properties")){
            Properties prop = new Properties();
            prop.load(input);
            dburl = prop.getProperty("dburl");
            dbName = prop.getProperty("dbName");
            port = prop.getProperty("port");
            username = prop.getProperty("username");
            password = prop.getProperty("password");

        }catch (IOException e){
            System.out.println("File could not be found/loaded");
        }

        // Formats the connection string to the database and connects
        String ConnectDB = "jdbc:mysql://" + dburl + ":" + port + "/" + dbName + "?user=" + username + "&password=" + password;
        Connection conn = DriverManager.getConnection(ConnectDB);

        // Prepares query to get the talent names for the selected Class and Specialization
        PreparedStatement talentNameStatement = conn.prepareStatement("SELECT Talent0, Talent1, Talent2, Talent3," +
                " Talent4, Talent5, Talent6 from CharacterTalents WHERE Class = '" + selectedClass + "' AND " +
                "Specialization = '" + selectedSpec + "'");

        // Runs the query, and stores them in a result set that can be iterated over
        ResultSet talentNameSet = talentNameStatement.executeQuery();

        // Iterates through the result set and saves the talent names in the static variable to pass to Window2
        while (talentNameSet.next()) {
            TalentList.add(talentNameSet.getString("Talent0"));
            TalentList.add(talentNameSet.getString("Talent1"));
            TalentList.add(talentNameSet.getString("Talent2"));
            TalentList.add(talentNameSet.getString("Talent3"));
            TalentList.add(talentNameSet.getString("Talent4"));
            TalentList.add(talentNameSet.getString("Talent5"));
            TalentList.add(talentNameSet.getString("Talent6"));
        }

        // Repeat the process below for the talent descriptions then the url for the talent images.
        PreparedStatement descriptionTalentStatement = conn.prepareStatement("SELECT TalentDescription0, TalentDescription1," +
                " TalentDescription2, TalentDescription3," + " TalentDescription4, TalentDescription5, TalentDescription6" +
                " from CharacterTalents WHERE Class = '" + selectedClass + "' AND " + "Specialization = '" + selectedSpec + "'");

        ResultSet descriptionTalentSet = descriptionTalentStatement.executeQuery();

        while (descriptionTalentSet.next()) {
            DescriptionList.add(descriptionTalentSet.getString("TalentDescription0"));
            DescriptionList.add(descriptionTalentSet.getString("TalentDescription1"));
            DescriptionList.add(descriptionTalentSet.getString("TalentDescription2"));
            DescriptionList.add(descriptionTalentSet.getString("TalentDescription3"));
            DescriptionList.add(descriptionTalentSet.getString("TalentDescription4"));
            DescriptionList.add(descriptionTalentSet.getString("TalentDescription5"));
            DescriptionList.add(descriptionTalentSet.getString("TalentDescription6"));
        }

        PreparedStatement talentURLStatement = conn.prepareStatement("SELECT TalentURL0, TalentURL1, TalentURL2, TalentURL3," +
                " TalentURL4, TalentURL5, TalentURL6 from CharacterTalents WHERE Class = '" + selectedClass + "' AND " +
                "Specialization = '" + selectedSpec + "'");

        ResultSet talentURLSet = talentURLStatement.executeQuery();

        while (talentURLSet.next()) {
            MediaList.add(talentURLSet.getString("TalentURL0"));
            MediaList.add(talentURLSet.getString("TalentURL1"));
            MediaList.add(talentURLSet.getString("TalentURL2"));
            MediaList.add(talentURLSet.getString("TalentURL3"));
            MediaList.add(talentURLSet.getString("TalentURL4"));
            MediaList.add(talentURLSet.getString("TalentURL5"));
            MediaList.add(talentURLSet.getString("TalentURL6"));

        }

    }
}