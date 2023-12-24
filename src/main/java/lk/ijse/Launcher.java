package lk.ijse;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Launcher extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
//        Parent root = FXMLLoader.load(this.getClass().getResource("/view/userLogin.fxml"));
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/returnVehicle.fxml"));
        Scene scene= new Scene(root);

        stage.setTitle("User Login Form");
        stage.centerOnScreen();
        stage.setScene(scene);

        stage.show();
    }
}