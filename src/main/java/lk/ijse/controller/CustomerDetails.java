package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class CustomerDetails {
    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> colAddres;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colMobile;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colNic;

    @FXML
    private TextField custAdd;

    @FXML
    private TextField custId;

    @FXML
    private TextField custName;

    @FXML
    private TextField custNic;

    @FXML
    private ToggleGroup gender;

    @FXML
    private TextField mobile;

    @FXML
    private RadioButton remoFemale;

    @FXML
    private RadioButton remoMale;

    @FXML
    private TableView<?> tblCust;

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

    @FXML
    void txtClickOnAction(ActionEvent event) {

    }
}
