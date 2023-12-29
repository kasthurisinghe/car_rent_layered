package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.bussines.BoType;
import lk.ijse.bussines.FactoryBo;
import lk.ijse.bussines.custom.CarBo;

import lk.ijse.dto.CarDto;
import lk.ijse.dto.tm.CarDtoTm;


import java.sql.SQLException;
import java.util.List;

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

    @FXML
    private TableColumn<?, ?> colBrand;

    @FXML
    private TableColumn<?, ?> colColour;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colModle;

    @FXML
    private TableColumn<?, ?> colReg;

    @FXML
    private TableColumn<?, ?> colRented;
    @FXML
    private TableColumn<?, ?> colType;

    @FXML
    private TableView<CarDtoTm> carTable;


    private CarBo carBoIpml= FactoryBo.getBo(BoType.CARBO);
    public void initialize() throws SQLException {
        ObservableList<String> listTypeVehi= FXCollections.observableArrayList("Sedan", "Hatchback", "Sport", "Convertible");

        typeComb.setItems(listTypeVehi);

//        load table data to UI
        setCellValueFactory();
        List<CarDtoTm> carDtoTm=loadAllCars();

        setTableData(carDtoTm);
    }

    private void setTableData(List<CarDtoTm> carDtoTm) {
        ObservableList <CarDtoTm>objects=FXCollections.observableArrayList();

        for (CarDtoTm carDtoTm1: carDtoTm){
            CarDtoTm carDtoTm2 = new CarDtoTm(
                    carDtoTm1.getRegNo(),
                    carDtoTm1.getId(),
                    carDtoTm1.getModle(),
                    carDtoTm1.getBrand(),
                    carDtoTm1.getColour(),
                    carDtoTm1.getType()
            );
            objects.add(carDtoTm2);
            carTable.setItems(objects);
        }
    }

    private List<CarDtoTm> loadAllCars() throws SQLException {
        return carBoIpml.getTableData();
    }

    private void setCellValueFactory() {
        colReg.setCellValueFactory(new PropertyValueFactory<>("regNo"));
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colModle.setCellValueFactory(new PropertyValueFactory<>("modle"));
        colBrand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        colColour.setCellValueFactory(new PropertyValueFactory<>("colour"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));

    }

    @FXML
    void bntDeleteClickOnAction(ActionEvent event) throws SQLException {
        notifyMsg.setText("");
        String id=VehiIdNo.getText();

        Boolean isDeleted=carBoIpml.deleteCar(id);
        if (isDeleted){
            new Alert(Alert.AlertType.CONFIRMATION,"Deletion is successfully").show();
            clearFields();
            initialize();
        }else {
            new Alert(Alert.AlertType.ERROR,"Error in deletion of car details").show();
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
                        initialize();
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
            initialize();
        }else {
            new Alert(Alert.AlertType.ERROR,"Error occurred in car detail update").show();
        }

    }

    @FXML
    void selectType(ActionEvent event) {

    }

}
