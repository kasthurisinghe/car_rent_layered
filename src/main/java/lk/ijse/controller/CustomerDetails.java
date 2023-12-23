package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import lk.ijse.bussines.BoType;
import lk.ijse.bussines.FactoryBo;
import lk.ijse.bussines.custom.CustomerBo;
import lk.ijse.dao.DaoFactory;
import lk.ijse.dao.DaoType;
import lk.ijse.dao.custom.CustomerDao;
import lk.ijse.dto.CustomerDto;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerDetails {
    public Button btnClear;
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

    CustomerBo customerBoImpl= FactoryBo.getBo(BoType.CUSTOMERBO);

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String cusId=custId.getText();

        try {
            boolean isDeleted=customerBoImpl.deleteCustomer(cusId);
            if (isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION,"Customer deleted successfully").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }

    }
    public static boolean isValidPhoneNumber(String phoneNumber) {
        // Sri Lankan phone number regular expression
        String regex = "^0\\d{9}$"; // Assuming the Sri Lankan phone number starts with 0 and is followed by 9 digits

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);

        return matcher.matches();
    }
    @FXML
    void btnSaveOnAction(ActionEvent event) {


                String cusId=custId.getText();
                String cusName=custName.getText();
                String cusAdd=custAdd.getText();
                String cusNic=custNic.getText();
                String phone=mobile.getText();

        if (!cusId.isEmpty() && !cusName.isEmpty() && !cusAdd.isEmpty() && !cusNic.isEmpty() && !phone.isEmpty()  ) {
            if (isValidPhoneNumber(phone)) {
                if (remoMale.isSelected() || remoFemale.isSelected()){
                    ToggleButton toggleButton= (ToggleButton) gender.getSelectedToggle();

                    String cusGender=toggleButton.getText();
                    CustomerDto customerDto=new CustomerDto(cusId,cusName,cusAdd,cusNic,phone,cusGender);

                    try {
                        Boolean isSaved=customerBoImpl.saveCustomer(customerDto);
                        if (isSaved){
                            new Alert(Alert.AlertType.CONFIRMATION,"Customer details are successfully saved").show();
                            clearFields();
                        }
                    } catch (Exception e) {
                        new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                    }
                }else {
                    new Alert(Alert.AlertType.WARNING, "please select the gender of the customer").show();
                }
            }else {
                new Alert(Alert.AlertType.WARNING,"Please insert a valid phone number").show();
            }
        }else {
            new Alert(Alert.AlertType.WARNING, "Please give all the required details here").show();
        }

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        ToggleButton toggleButton= (ToggleButton) gender.getSelectedToggle();
        if (toggleButton!=null) {
            CustomerDto customerDto=new CustomerDto(
                    custId.getText(),
                    custName.getText(),
                    custAdd.getText(),
                    custNic.getText(),
                    mobile.getText(),
                    toggleButton.getText());
            try {
                Boolean isSaved=customerBoImpl.updateCustomer(customerDto);
                if (isSaved){
                    new Alert(Alert.AlertType.CONFIRMATION,"Customer iupdated successfully").show();
                    clearFields();
                }
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }else {
            new Alert(Alert.AlertType.WARNING,"please select the gender of the customer").show();
        }
    }

    private void clearFields() {
        custId.setText("");
        custName.setText("");
        custNic.setText("");
        custAdd.setText("");
        mobile.setText("");
        remoFemale.setSelected(false);
        remoMale.setSelected(false);
    }

    @FXML
    void txtClickOnAction(ActionEvent event) throws SQLException {
        String cusId=custId.getText();
//        try {
            CustomerDto customerDto=customerBoImpl.findCustomer(cusId);
            custId.setText(customerDto.getCusId());
            custName.setText(customerDto.getCusName());
            custNic.setText(customerDto.getCusNic());
            custAdd.setText(customerDto.getCusAdd());
            mobile.setText(customerDto.getCusmobile());
            if (customerDto.getGender().equals("Male")){
                remoMale.setSelected(true);
            }
            else {
                remoFemale.setSelected(true);
            }
//        } catch (Exception e) {
//            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
//        }
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        clearFields();
    }
}
