package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.bussines.BoType;
import lk.ijse.bussines.FactoryBo;
import lk.ijse.bussines.custom.CustomerBo;
import lk.ijse.dto.CustomerDto;
import lk.ijse.dto.tm.CustomerDtoTm;
import lk.ijse.dto.tm.CustomerRentalDtoTm;

import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerDetails {
    public TableColumn colReturned;
    public DatePicker todayDate;
    @FXML
    private TableColumn<?, ?> colAddres;
    @FXML
    private TableColumn<?, ?> colBoookingId;
    @FXML
    private TableColumn<?, ?> colEnd;
    @FXML
    private TableColumn<?, ?> colId;
    @FXML
    private TableColumn<?, ?> colMobile;
    @FXML
    private TableColumn<?, ?> coGender;
    @FXML
    private TableColumn<?, ?> colName;
    @FXML
    private TableColumn<?, ?> colNic;
    @FXML
    private TableColumn<?, ?> colOverDue;
    @FXML
    private TableColumn<?, ?> colRate;
    @FXML
    private TableColumn<?, ?> colStart;
    @FXML
    private TableColumn<?, ?> colTotal;
    @FXML
    private TableView<CustomerDtoTm> tblCust;
    @FXML
    private TableView<CustomerRentalDtoTm> tblRentals;


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
    private Label msgLabel;

    @FXML
    private RadioButton remoFemale;

    @FXML
    private RadioButton remoMale;



    CustomerBo customerBoImpl= FactoryBo.getBo(BoType.CUSTOMERBO);

    public void initialize() throws SQLException {
//        first table(customer details)
        setCellValueFactoryCustomer();
        List<CustomerDtoTm>customerDtoTms =loadAllCustomers();
        setCustomerTableData(customerDtoTms);
    }
    public void dateClickOnAction(ActionEvent actionEvent) throws SQLException {
        //        second table(rental details)
        setCellValueFactoryRental();
        List<CustomerRentalDtoTm>customerRentalDtoTms =loadAllCustomerRentals();
        if (customerRentalDtoTms!=null) {
            setCustomerRetalTable(customerRentalDtoTms);
        }else {
            new Alert(Alert.AlertType.INFORMATION,"The customer "+custId.getText()+" is not registered in bookings").show();
        }
    }

    private void setCustomerRetalTable(List<CustomerRentalDtoTm> customerRentalDtoTms) {
        ObservableList<CustomerRentalDtoTm>RetntalObjects= FXCollections.observableArrayList();

        for(CustomerRentalDtoTm customerRentalDtoTm:customerRentalDtoTms){
            CustomerRentalDtoTm customerRentalDtoTm1 = new CustomerRentalDtoTm(
                    customerRentalDtoTm.getBookingId(),
                    customerRentalDtoTm.getStartDate(),
                    customerRentalDtoTm.getEndDate(),
                    customerRentalDtoTm.getRate(),
                    customerRentalDtoTm.getOverDue(),
                    customerRentalDtoTm.getTotal(),
                    customerRentalDtoTm.getReturned()
            );
            RetntalObjects.add(customerRentalDtoTm1);
            tblRentals.setItems(RetntalObjects);
        }
    }

    private List<CustomerRentalDtoTm> loadAllCustomerRentals() throws SQLException {

        return customerBoImpl.loadAllCustomerRentals(todayDate.getValue(),custId.getText());

    }

    private void setCustomerTableData(List<CustomerDtoTm> customerDtoTms) {
        ObservableList<CustomerDtoTm>objects= FXCollections.observableArrayList();

        for(CustomerDtoTm customerDtoTm:customerDtoTms){
            CustomerDtoTm customerDtoTm1 = new CustomerDtoTm(
                    customerDtoTm.getCustId(),
                    customerDtoTm.getCustName(),
                    customerDtoTm.getCustAddress(),
                    customerDtoTm.getCustNic(),
                    customerDtoTm.getCustMobile(),
                    customerDtoTm.getCustGender()
            );
            objects.add(customerDtoTm1);
            tblCust.setItems(objects);
        }
    }

    private List<CustomerDtoTm> loadAllCustomers() throws SQLException {
        return customerBoImpl.loadTableData();
    }

    private void setCellValueFactoryRental() {
        colBoookingId.setCellValueFactory(new PropertyValueFactory<>("bookingId"));
        colStart.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        colEnd.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        colRate.setCellValueFactory(new PropertyValueFactory<>("rate"));
        colOverDue.setCellValueFactory(new PropertyValueFactory<>("overDue"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colReturned.setCellValueFactory(new PropertyValueFactory<>("returned"));
    }

    private void setCellValueFactoryCustomer() {
        colId.setCellValueFactory(new PropertyValueFactory<>("custId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("custName"));
        colAddres.setCellValueFactory(new PropertyValueFactory<>("custAddress"));
        colNic.setCellValueFactory(new PropertyValueFactory<>("custNic"));
        colMobile.setCellValueFactory(new PropertyValueFactory<>("custMobile"));
        coGender.setCellValueFactory(new PropertyValueFactory<>("custGender"));
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String cusId=custId.getText();

        if (cusId!="") {
            try {
                boolean isDeleted=customerBoImpl.deleteCustomer(cusId);
                if (isDeleted){
                    new Alert(Alert.AlertType.CONFIRMATION,"Customer deleted successfully").show();
                    clearFields();
                }
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            }
        }else {
            new Alert(Alert.AlertType.ERROR,"please enter a valid customer ID here").show();
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
                    new Alert(Alert.AlertType.CONFIRMATION,"Customer is updated successfully").show();
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
        if (todayDate.getValue()==null){
            msgLabel.setText("Please select today date to check the rentals of the customer.");
        }

        String cusId=custId.getText();
        try {
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
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,"Customer not found").show();
        }
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        clearFields();
    }


}
