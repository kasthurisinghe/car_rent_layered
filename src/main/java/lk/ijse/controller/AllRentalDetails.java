package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.bussines.BoType;
import lk.ijse.bussines.FactoryBo;
import lk.ijse.bussines.custom.AllOverDueBo;
import lk.ijse.bussines.custom.AllRental;
import lk.ijse.dto.tm.AllOverDueDtoTm;
import lk.ijse.dto.tm.AllRentalsDtoTm;
import lk.ijse.dto.tm.BookingDtoTm;

import java.sql.SQLException;
import java.util.List;

public class AllRentalDetails {
    @FXML
    private TableColumn<?, ?> colAmount;
    @FXML
    private TableColumn<?, ?> colCustId;
    @FXML
    private TableColumn<?, ?> colEnd;
    @FXML
    private TableColumn<?, ?> colRate;
    @FXML
    private TableColumn<?, ?> colReturned;
    @FXML
    private TableColumn<?, ?> colStart;
    @FXML
    private TableColumn<?, ?> colVehiId;


    @FXML
    private TableColumn<?, ?> overAmount;

    @FXML
    private TableColumn<?, ?> overCust;

    @FXML
    private TableColumn<?, ?> overDue;

    @FXML
    private TableColumn<?, ?> overRate;
    @FXML
    private TableColumn<?, ?> overVehicle;
    @FXML
    private TableView<AllRentalsDtoTm> tbl;
    @FXML
    private TableView<AllOverDueDtoTm> overTbl;
    @FXML
    private Label msg;

    @FXML
    private DatePicker txtToday;

    AllRental allRentalImpl= FactoryBo.getBo(BoType.ALLRENTALS);
    AllOverDueBo allOverDueBoImpl=FactoryBo.getBo(BoType.ALLOVERDUE);
    public void initialize() throws SQLException {
        setCellValueAllRentalsFactory();
        List<AllRentalsDtoTm> allRentalsDtoTms=loadAllRentals();

        setTableDataOfAllRentals(allRentalsDtoTms);
    }

    private List<AllRentalsDtoTm> loadAllRentals() throws SQLException {
        return allRentalImpl.loadAllrentalDetails();
    }

    private void setTableDataOfAllRentals(List<AllRentalsDtoTm> allRentalsDtoTms) {
        ObservableList<AllRentalsDtoTm>objects= FXCollections.observableArrayList();

        try {
            for (AllRentalsDtoTm allRentalsDtoTm: allRentalsDtoTms){
                AllRentalsDtoTm allRentalsDtoTm1=new AllRentalsDtoTm(
                        allRentalsDtoTm.getCustId(),
                        allRentalsDtoTm.getVehiId(),
                        allRentalsDtoTm.getStartDate(),
                        allRentalsDtoTm.getEndDate(),
                        allRentalsDtoTm.getRate(),
                        allRentalsDtoTm.getAmount(),
                        allRentalsDtoTm.getReturned()
                );
                objects.add(allRentalsDtoTm1);
                tbl.setItems(objects);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void setCellValueAllRentalsFactory() {
        colCustId.setCellValueFactory(new PropertyValueFactory<>("custId"));
        colVehiId.setCellValueFactory(new PropertyValueFactory<>("vehiId"));
        colStart.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        colEnd.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        colRate.setCellValueFactory(new PropertyValueFactory<>("rate"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colReturned.setCellValueFactory(new PropertyValueFactory<>("returned"));
    }

    @FXML
    void txtClickOnDate(ActionEvent event) throws SQLException {
        msg.setText("");

        setCellValueAllOverDueFactory();
        List<AllOverDueDtoTm> allOverDueDtoTms=loadAllOverDues();

        setTableDataOfAllOverdue(allOverDueDtoTms);
    }

    private void setTableDataOfAllOverdue(List<AllOverDueDtoTm> allOverDueDtoTms) {
        ObservableList<AllOverDueDtoTm> objectsOverDue=FXCollections.observableArrayList();

        for (AllOverDueDtoTm allOverDueDtoTm: allOverDueDtoTms){
            AllOverDueDtoTm allOverDueDtoTm1=new AllOverDueDtoTm(
                    allOverDueDtoTm.getCustId(),
                    allOverDueDtoTm.getVehicleId(),
                    allOverDueDtoTm.getDueDate(),
                    allOverDueDtoTm.getRate(),
                    allOverDueDtoTm.getTotalCharge()
            );
            objectsOverDue.add(allOverDueDtoTm1);
            overTbl.setItems(objectsOverDue);
        }
    }

    private List<AllOverDueDtoTm> loadAllOverDues() throws SQLException {
        return allOverDueBoImpl.getAllOverDueTable(txtToday.getValue());
    }

    private void setCellValueAllOverDueFactory() {
        overCust.setCellValueFactory(new PropertyValueFactory<>("custId"));
        overVehicle.setCellValueFactory(new PropertyValueFactory<>("vehicleId"));
        overDue.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        overRate.setCellValueFactory(new PropertyValueFactory<>("rate"));
        overAmount.setCellValueFactory(new PropertyValueFactory<>("totalCharge"));
    }
}
