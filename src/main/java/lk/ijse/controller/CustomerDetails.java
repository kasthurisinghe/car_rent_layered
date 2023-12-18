package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class CustomerDetails {
    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colCustomerId;

    @FXML
    private TableColumn<?, ?> colCustomername;

    @FXML
    private TableColumn<?, ?> colMobile;

    @FXML
    private TableColumn<?, ?> colNic;

    @FXML
    private TableColumn<?, ?> colRented;

    @FXML
    private Button  createCustomerBtn;

    @FXML
    private Button deleteCustomerbtn;

    @FXML
    private Button findCustomerbtn;

    @FXML
    private Label notifyMessage;

    @FXML
    private TableView<?> tblCustomer;

    @FXML
    private TextField txtCstId;

    @FXML
    private TextField txtCustomerAddress;

    @FXML
    private TextField txtCustomerMobile;

    @FXML
    private TextField txtCustomerName;

    @FXML
    private TextField txtCustomerNic;

    @FXML
    private Button updateCustomerbtn;

    @FXML
    void btnCreateCustomerClickOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteClickOnAction(ActionEvent event) {

    }

    @FXML
    void btnFindCustomerClickOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateClickOnAction(ActionEvent event) {

    }
}
