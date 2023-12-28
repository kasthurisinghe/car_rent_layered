package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import lk.ijse.bussines.BoType;
import lk.ijse.bussines.FactoryBo;
import lk.ijse.bussines.custom.CarBo;

import lk.ijse.dto.CarDto;


import java.sql.SQLException;

public class CarDetails {
    @FXML
    private TextField VehiIdNo;

    @FXML
    private Label notifyMsg;

    @FXML
    private TextField regNo;

    @FXML
    private TextField txtBrand;

    @FXML
    private TextField txtColour;

    @FXML
    private TextField txtModle;

    @FXML
    private ComboBox typeComb;

    private CarBo carBoIpml= FactoryBo.getBo(BoType.CARBO);
    public void initialize(){
        ObservableList<String> listTypeVehi= FXCollections.observableArrayList("Sedan", "Hatchback", "Sport", "Convertible");

        typeComb.setItems(listTypeVehi);

    }

    @FXML
    void bntDeleteClickOnAction(ActionEvent event) throws SQLException {
        notifyMsg.setText("");
        String id=VehiIdNo.getText();

        Boolean isDeleted=carBoIpml.deleteCar(id);
        if (isDeleted){
            new Alert(Alert.AlertType.CONFIRMATION,"Deletion is successfull").show();
            clearFields();
        }else {
            new Alert(Alert.AlertType.ERROR,"Error in deletion of car detalis").show();
        }

    }

    @FXML
    void btnDetailsCliskOnAction(ActionEvent event) throws SQLException {
        notifyMsg.setText("");
        String id=VehiIdNo.getText();

        CarDto carDto=carBoIpml.chekcCar(id);

        try {
            if (carDto!=null) {
                VehiIdNo.setText(carDto.getId());
                regNo.setText(carDto.getRegNo());
                txtModle.setText(carDto.getModle());
                txtBrand.setText(carDto.getBrand());
                txtColour.setText(carDto.getColour());
                VehiIdNo.setText(carDto.getId());
                typeComb.setValue(carDto.getType());
            }else {
                notifyMsg.setText("The ID that you have entered is not valid");
                clearFields();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void clearFields() {
        VehiIdNo.setText("");
        regNo.setText("");
        txtModle.setText("");
        txtBrand.setText("");
        txtColour.setText("");
        VehiIdNo.setText("");
        typeComb.setValue(null);
    }


    @FXML
    void btnRegsterClickOnAction(ActionEvent event) throws SQLException {
        notifyMsg.setText("");
        try {
            String id=VehiIdNo.getText();
            String reg=regNo.getText();
            String modle=txtModle.getText();
            String colour=txtColour.getText();
            String brand= txtBrand.getText();
            String type= (String) typeComb.getValue();

            if (!id.isEmpty() && !reg.isEmpty() && !modle.isEmpty() && !colour.isEmpty()&& !brand.isEmpty() && !type.isEmpty()) {
                if (carBoIpml.chekcCar(id)==null) {
                    CarDto carDto=new CarDto(reg,id,modle,brand,colour,type);

                    Boolean isSaved=carBoIpml.saveCar(carDto);
                    if (isSaved){
                        new Alert(Alert.AlertType.CONFIRMATION,"Car details are saved successfully").show();
                        clearFields();
                    }
                    else{
                        new Alert(Alert.AlertType.ERROR, "Recheck the details that you have entered").show();
                    }
                }else {
                    notifyMsg.setText("The car ID number that you have entered is already exist, please use a new car ID number ");
                }
            }
            else {
                notifyMsg.setText("Please give all the details regarding to the car details");
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    void btnUpdateClickOnAction(ActionEvent event) throws SQLException {
        notifyMsg.setText("");

        String id=VehiIdNo.getText();
        String reg=regNo.getText();
        String modle=txtModle.getText();
        String colour=txtColour.getText();
        String brand= txtBrand.getText();
        String type= (String) typeComb.getValue();

        CarDto carDto=new CarDto(reg,id,modle,brand,colour,type);
        Boolean isUpdated=carBoIpml.updateCar(carDto);
        if (isUpdated){
            new Alert(Alert.AlertType.CONFIRMATION,"Car details successfully updated").show();
            clearFields();
        }else {
            new Alert(Alert.AlertType.ERROR,"Error occurred in car detail update").show();
        }

    }

    @FXML
    void selectType(ActionEvent event) {

    }

}
