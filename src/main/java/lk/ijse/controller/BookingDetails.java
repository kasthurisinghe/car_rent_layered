package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;


public class BookingDetails {
    @FXML
    private Button BtnDelete;

    @FXML
    private Button BtnRent;

    @FXML
    private Button BtnUpdate;

    @FXML
    private TextField TxtCustomerId;

    @FXML
    private DatePicker endDate;

    @FXML
    private Label msgLab;

    @FXML
    private DatePicker startDate;

    @FXML
    private TableView<?> table;

    @FXML
    private TableColumn<?, ?> tblBookingId;

    @FXML
    private TableColumn<?, ?> tblCarId;

    @FXML
    private TableColumn<?, ?> tblCustomerId;

    @FXML
    private TableColumn<?, ?> tblDate;

    @FXML
    private TableColumn<?, ?> tblRate;

    @FXML
    private TableColumn<?, ?> tblTotalPrice;

    @FXML
    private TextField txtBookingId;

    @FXML
    private TextField txtCarId;

    @FXML
    private TextField txtRate;

    @FXML
    void btnDeleteClickOnAction(ActionEvent event) {

    }

    @FXML
    void btnRentClickOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateClickOnAction(ActionEvent event) {

    }

    @FXML
    void txtClickOnAction(ActionEvent event) {

    }

    @FXML
    void txtIdClickOnAction(ActionEvent event) {

    }
}
