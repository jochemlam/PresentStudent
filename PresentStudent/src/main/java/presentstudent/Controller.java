package presentstudent;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.io.IOException;

public class Controller {

    Database database = new Database();
    ToggleGroup group = new ToggleGroup();


    @FXML
    TextArea textBox;

    @FXML
    RadioButton lateButton;

    @FXML
    RadioButton absentButton;

    @FXML
    String getButtonState() {
        if (lateButton.isSelected()) {
            return "telaat";
        }
        else if (absentButton.isSelected()) {
            return "absent";
        }
        return null;
    }


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
        database.getStudentName(55);
    }

    @FXML
    public void sendAbsent(ActionEvent event) throws IOException {
        // code voor verzenden hier
        String message = textBox.getText();
        database.createMention(message, getButtonState(), 55, 5);
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
