package presentstudent;

import java.sql.*;
import java.time.LocalDate;

public class Database extends Object {

    Connection connection;
    PreparedStatement preparedStatement = null;

    private Connection connect() {
        Connection conn = null;
        String driver = "com.mysql.cj.jdbc.Driver";
        // MySQL connection string, pas zonodig het pad aan:
        String connection = "jdbc:mysql://localhost:3306/presentstudent";
        String user = "root";
        String password = "hhs";

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(connection, user, password);
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return conn;
    }

    public void main(String[] args) {
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

        } catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    public void createLesson(int lesnummer, String docent) {
        String query = "INSERT INTO les(lesnummer, docent)"
                + "VALUES(?,?)";


        preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, lesnummer);
            preparedStatement.setString(2, docent);

            preparedStatement.executeUpdate();

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

        } catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    public Date getDate() {
        Date date = Date.valueOf(LocalDate.now());
        return date;
    }
    public Time getTime() {
        Time time = new Time(System.currentTimeMillis());
        return time;
    }

}