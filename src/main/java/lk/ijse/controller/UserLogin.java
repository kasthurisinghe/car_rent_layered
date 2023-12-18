package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class UserLogin {
    @FXML
    private AnchorPane rootPane;
    @FXML
    private Label createAccount;

    @FXML
    private Button login;

    @FXML
    private Label notificationMessage;

    @FXML
    private TextField txtUserId;

    @FXML
    private PasswordField txtUserPassword;

    @FXML
    void btnLoginOnAction(ActionEvent event) throws IOException {
        notificationMessage.setText("");
        String userID = txtUserId.getText();
        String password=txtUserPassword.getText();
//
//        if (checkUser(password,userID)){
//            Parent root= FXMLLoader.load(this.getClass().getResource("/view/mainMenu.fxml"));
//
//            Scene scene=new Scene(root);
//
//            Stage stage= (Stage) this.rootPane.getScene().getWindow();
//            stage.setScene(scene);
//
//            stage.setTitle("Main Menu");
//            stage.centerOnScreen();
//        } else if (userID.length()==0) {
//            notificationMessage.setText("Please enter the User Name");
//        } else if (password.length()==0) {
//            notificationMessage.setText("Please enter the Password");
//        } else {
//            setNotification();
//        }
//        txtUserId.setText("");
//        txtUserPassword.setText("");
    }


    @FXML
    void labelCreateAnAccount(MouseEvent event) throws IOException {
        Parent root= FXMLLoader.load(this.getClass().getResource("/view/createUser.fxml"));
        Scene scene=new Scene(root);

        Stage stage= (Stage) this.rootPane.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Create User Account");
        stage.centerOnScreen();
    }
    public  void setNotification(){
        notificationMessage.setText("Invalid Entry Please check the Credentials and Re-enter");
    }

}
