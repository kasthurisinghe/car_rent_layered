package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.bussines.BoType;
import lk.ijse.bussines.FactoryBo;
import lk.ijse.bussines.custom.CreateUserBo;
import lk.ijse.bussines.custom.impl.CreateUserBoImpl;
import lk.ijse.dto.CreateUserdto;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class CreateUser {


    @FXML
    private Button backBtn;

    @FXML
    private Button createUserBtn;

    @FXML
    private Button deleteUserbtn;

    @FXML
    private Label msg;
    @FXML
    private AnchorPane rootNode2;


    @FXML
    private RadioButton female;
    @FXML
    private RadioButton male;
    @FXML
    private TextField UserId;
    @FXML
    private TextField address;
    @FXML
    private PasswordField password;
    @FXML
    private TextField phoneNumber;
    @FXML
    private PasswordField rePassword;
    @FXML
    private TextField userName;
    @FXML
    private ToggleGroup gender;
    @FXML
    String genderOfUser;
    CreateUserBo createUserBoimpl=FactoryBo.getBo(BoType.ADMINUSER);
    public void getGender(ActionEvent actionEvent) {

        if(female.isSelected()){
            genderOfUser= female.getText();
        }else {
            genderOfUser=male.getText();
        }
    }

    @FXML
    void btnBackClickonAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/userLogin.fxml"));
        Scene scene= new Scene(root);

        Stage stage= (Stage) this.rootNode2.getScene().getWindow();
        stage.setScene(scene);

        stage.setTitle("Main Menu");
    }


    @FXML
    void btnCreateUserClockOnAction(ActionEvent event) throws SQLException, NoSuchAlgorithmException {

         String id =UserId.getText();
         String addr= address.getText();
         String pass=password.getText();
        String md5Pass=doHashing(pass);
         String mobile= phoneNumber.getText();
         String rePass= rePassword.getText();
        String name= userName.getText();
        String regex = "^[\\+\\d]{9,13}$"; // Basic 9-13 digit pattern
        Pattern pattern = Pattern.compile(regex);

        boolean isValid = pattern.matcher(mobile).matches();

        if (!id.equals(null) && !name.equals(null) && !addr.equals(null)  && !mobile.equals(null) && !pass.equals(null) && !rePass.equals(null) && !genderOfUser.equals(null)){
            if (isValid){
                if(pass.equals(rePass)){
                    CreateUserdto createUserDto=new CreateUserdto(id,name,addr,mobile,md5Pass,genderOfUser);

                    boolean isSaved= createUserBoimpl.saveAdminUser(createUserDto) ;
                    if (isSaved){
                        new Alert(Alert.AlertType.CONFIRMATION, "the adminuser Successfully saved").show();
                        clearFields();
                    }
                }else {
                    new Alert(Alert.AlertType.ERROR, "Password is in not matching").show();
                }
            }else {
                new Alert(Alert.AlertType.ERROR, "Mobile Phone number is not valid").show();
            }
        }else {
            new Alert(Alert.AlertType.ERROR, "Please fill all the fiels").show();
        }

    }

    private String doHashing(String pass) throws NoSuchAlgorithmException {
        String hashValue;

//            Initialize the MessageDigest object for MD5 hashing.
        MessageDigest md = MessageDigest.getInstance("MD5");

//            Input the data you want to hash into a byte array.
        String originalString = pass;
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

    private void clearFields() {
          female.setSelected(false);
          male.setSelected(false);
          UserId.setText("");
          address.setText("");
          password.setText("");
          phoneNumber.setText("");
          rePassword.setText("");
          userName.setText("");
    }


    @FXML
    void btnDeleteClickOnAction(ActionEvent event) throws SQLException {
        try {
            String userId=UserId.getText();
            boolean isDeleted= createUserBoimpl.deleteUser(userId);
            if (isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION,"Admin user deleted !").show();
                clearFields();
            }
            else {
                new Alert(Alert.AlertType.INFORMATION, "No user found").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    @FXML
    void txtClickOnId(ActionEvent event) {
        try {
            String id=UserId.getText();

            CreateUserdto createUserdto=createUserBoimpl.findUser(id);
            UserId.setText(createUserdto.getId());
            userName.setText(createUserdto.getName());
            address.setText(createUserdto.getAddr());
            phoneNumber.setText(createUserdto.getMobile());
            if (createUserdto.getGender().equals("Female")){
                female.setSelected(true);
            }else {
                male.setSelected(true);
            }
            msg.setText("Passwords cannot be exposed here.");
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }


}
