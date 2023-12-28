package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.List;

public class CarDetails {
    @FXML
    private TextField VehiIdNo;

    @FXML
    private Button checkVehiButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Label notifyMsg;

    @FXML
    private TextField regNo;

    @FXML
    private Button registerButton;

    @FXML
    private TextField txtBrand;

    @FXML
    private TextField txtColour;

    @FXML
    private TextField txtModle;

    @FXML
    private ComboBox typeComb;

    @FXML
    private Button updateButton;
    public void initialize(){
        ObservableList<String> listTypeVehi= FXCollections.observableArrayList("Sedan", "Hatchback", "Sport", "Convertible");

        typeComb.setItems(listTypeVehi);

    }

    @FXML
    void bntDeleteClickOnAction(ActionEvent event) {

    }

    @FXML
    void btnDetailsCliskOnAction(ActionEvent event) {

    }

    @FXML
    void btnRegsterClickOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateClickOnAction(ActionEvent event) {

    }

    @FXML
    void selectType(ActionEvent event) {

    }

}
