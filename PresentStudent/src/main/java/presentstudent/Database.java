package presentstudent;

import javafx.geometry.Pos;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

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

        if (reden == null || reden == "") {
            reden = "Geen reden opgegeven.";
        }

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

            notification(leerlingnummer + " is " + soort, reden, 10).showInformation();

        } catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    public void getStudentName(int studentNummer) {
        String query = "SELECT voornaam, achternaam FROM leerling WHERE leerlingnummer = studentNummer + VALUES(?)";

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, studentNummer);
            preparedStatement.executeUpdate();
            preparedStatement.clearParameters();
        }
        catch (SQLException e) {
            System.out.printf("Cannot create preparestatement");
        }

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String firstName = rs.getString("voornaam");
                String lastName = rs.getString("achternaam");
                String fullName = firstName + " " + lastName;

                System.out.println(fullName);
            }
        }
        catch (SQLException e){
            System.out.printf("Something wrong during the query");
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

    public Notifications notification(String title, String text, double showDuration) {
        Notifications notificationBuilder = Notifications.create()
                .title(title)
                .text(text)
                .hideAfter(Duration.millis(showDuration * 1000))
                .position(Pos.BOTTOM_RIGHT);
        return notificationBuilder;
    }

}