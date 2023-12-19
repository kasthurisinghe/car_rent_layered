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
import lk.ijse.bussines.BoType;
import lk.ijse.bussines.FactoryBo;
import lk.ijse.bussines.custom.CreateUserBo;
import lk.ijse.bussines.custom.UserLoginBo;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

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

    UserLoginBo loginBoImpl=FactoryBo.getBo(BoType.LOGINBO);

    @FXML
    void btnLoginOnAction(ActionEvent event) throws IOException, NoSuchAlgorithmException, SQLException {
        notificationMessage.setText("");
        String userID = txtUserId.getText();
        String password=txtUserPassword.getText();
        String md5pass=doHashing(password);
        String mdPass=doHashing(password);

        String pass=loginBoImpl.findAdmin(userID);

        if (md5pass.equals(pass)){
            Parent root= FXMLLoader.load(this.getClass().getResource("/view/mainMenu.fxml"));

            Scene scene=new Scene(root);

            Stage stage= (Stage) this.rootPane.getScene().getWindow();
            stage.setScene(scene);

            stage.setTitle("Main Menu");
            stage.centerOnScreen();
        } else if (userID.length()==0) {
            notificationMessage.setText("Please enter the User Name");
        } else if (password.length()==0) {
            notificationMessage.setText("Please enter the Password");
        } else {
            setNotification();
        }
        txtUserId.setText("");
        txtUserPassword.setText("");
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
    private static String doHashing(String password) throws NoSuchAlgorithmException {
        String hashValue;

//            Initialize the MessageDigest object for MD5 hashing.
        MessageDigest md = MessageDigest.getInstance("MD5");

//            Input the data you want to hash into a byte array.
        String originalString = password;
        byte[] bytesOfMessage = originalString.getBytes();

//            Use the digest method to perform the hashing.
        byte[] digest = md.digest(bytesOfMessage);

//            Finally, convert the byte array to a hexadecimal String.
        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            sb.append(String.format("%02x", b));
        }
        hashValue = sb.toString();

        return hashValue;
    }

}
