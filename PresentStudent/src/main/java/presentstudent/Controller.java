package presentstudent;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {

    Database database = new Database();

    @FXML
    void goAbsent(ActionEvent event) throws IOException {
        changeScene(event, "absent", "Absentie aanmaken");
    }

    @FXML
    void goAbsentOverview(ActionEvent event) throws IOException {
        changeScene(event, "overzicht", "Absentie overzicht");
    }

    @FXML
    void goHome(ActionEvent event) throws IOException {
        changeScene(event, "home", "Hoofdmenu");
    }

    @FXML
    void goSchedule(ActionEvent event) throws IOException {
        changeScene(event, "rooster", "Rooster overzicht");
    }

    @FXML
    void lastWeek(ActionEvent event) throws IOException {

    }

    @FXML
    void thisWeek(ActionEvent event) throws IOException {

    }

    @FXML
    void nextWeek(ActionEvent event) throws IOException {

    }

    @FXML
    void sync(ActionEvent event) throws IOException {
        //sync
    }

    @FXML
    void scan() {
        //scan
    }

    @FXML
    void addStudent(String voornaam, String achternaam) {
        database.createStudent(voornaam, achternaam);
    }

    @FXML
    void addTeacher(String voornaam, String achternaam) {
        database.createTeacher(voornaam, achternaam);
    }

    @FXML
    void addLesson(int lesnummer, String docentnaam) {
        database.createLesson(lesnummer, docentnaam);
    }

    @FXML
    void addMention(String reden, String soort, int leerlingnummer, int lesnummer) {
        database.createMention(reden, soort, leerlingnummer, lesnummer);
    }

    @FXML
    void sendAbsent(ActionEvent event) throws IOException {
        // code voor verzenden hier



        // dit is om terug te gaan naar het vorige scherm.
        goAbsentOverview(event);
    }

    @FXML
    void changeScene(ActionEvent event, String filename, String titleName) throws IOException {

        Parent parent = FXMLLoader.load(getClass().getResource(filename + ".fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(titleName);
        stage.setScene(scene);
        stage.show();
    }

}
