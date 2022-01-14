package presentstudent;

import java.sql.*;
import java.time.LocalDate;

public class Database extends Object {
    public static Connection connection;
    PreparedStatement preparedStatement = null;

    public static final String servername = "localhost";
    public static final String port = "3306";
    public static final String database = "presentstudent";
    public static final String username = "root";
    public static final String password = "hhs";
    public static final String url = "jdbc:mysql://" + servername +":"+ port +  "/" + database;

    private static Connection connect() {
        Connection conn = null;
        String driver = "com.mysql.cj.jdbc.Driver";
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return conn;
    }

    public static void startConnection() {
        connection = connect();
    }

    public void createStudent(String voornaam, String achternaam) {
        String query = "INSERT INTO leerling(voornaam, achternaam)"
                + "VALUES(?,?)";

        preparedStatement = null;
            try {
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, voornaam);
                preparedStatement.setString(2, achternaam);

                preparedStatement.executeUpdate();
                preparedStatement.clearParameters();

            } catch (Exception e){
                System.err.println(e.getMessage());
            }
    }

    public void createTeacher(String voornaam, String achternaam) {
        String query = "INSERT INTO docent(voornaam, achternaam)"
                + "VALUES(?,?)";


        preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, voornaam);
            preparedStatement.setString(2, achternaam);

            preparedStatement.executeUpdate();
            preparedStatement.clearParameters();

        } catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    public void createLesson(int lesnummer, int docent) {
        String query = "INSERT INTO les(lesnummer, tijdstip, docent)"
                + "VALUES(?,?,?)";


        preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, lesnummer);
            preparedStatement.setTime(2, getTime());
            preparedStatement.setInt(3, docent);

            preparedStatement.executeUpdate();
            preparedStatement.clearParameters();

        } catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    public void createMention(String reden, String soort, int leerlingnummer, int lesnummer) {

        String query = "INSERT INTO melding(reden, soort, datum, tijd, leerling, les)"
                + "VALUES(?,?,?,?,?,?)";


        preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, reden);
            preparedStatement.setString(2, soort);
            preparedStatement.setDate(3, getDate());
            preparedStatement.setTime(4, getTime());
            preparedStatement.setInt(5, leerlingnummer);
            preparedStatement.setInt(6, lesnummer);

            preparedStatement.executeUpdate();
            preparedStatement.clearParameters();

        } catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    public Date getDate() {
        Date date = Date.valueOf(LocalDate.now());
        return date;
    }
    public static Time getTime() {
        Time time = new Time(System.currentTimeMillis());
        return time;
    }

}