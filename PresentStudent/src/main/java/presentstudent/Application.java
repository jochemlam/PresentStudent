package presentstudent;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        ComPortSendReceive serial = new ComPortSendReceive();

        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("home.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 600);
        primaryStage.setTitle("Hoofdscherm");
        primaryStage.setScene(scene);
        primaryStage.show();

        Database.startConnection();
        serial.startSerial();
    }



    public static void main(String[] args) {
        launch();
    }
}