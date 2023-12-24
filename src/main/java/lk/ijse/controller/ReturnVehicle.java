package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import lk.ijse.bussines.BoType;
import lk.ijse.bussines.FactoryBo;
import lk.ijse.bussines.custom.ReturnBo;
import lk.ijse.bussines.custom.impl.ReturnBoImpl;
import lk.ijse.dto.CreateUserdto;
import lk.ijse.dto.ReturnDto;

import java.sql.SQLException;
import java.time.LocalDate;

public class ReturnVehicle {
    public DatePicker dueDate;
    public TextField txtBookingId;
    @FXML
    private Label OverDue;

    @FXML
    private Button btnAccept;

    @FXML
    private Label custId;

    @FXML
    private Label custName;

    @FXML
    private Label msgTxr;

    @FXML
    private Label panelty;

    @FXML
    private Label total;

    @FXML
    private Label vehicleRegNo;

    ReturnBo returnBoImpl= FactoryBo.getBo(BoType.RETURNBO);
    public void btnAcceptOnAction(ActionEvent actionEvent) {
        String cusId= custId.getText();


    }



    public void btnCheckClickOnAction(ActionEvent actionEvent) {
        String bookId=txtBookingId.getText();
        LocalDate duDate=dueDate.getValue();

        try {
        ReturnDto returnDto= returnBoImpl.findRental(bookId,duDate);

        txtBookingId.setText(returnDto.getBookingId());
        custId.setText(returnDto.getCustId());
        OverDue.setText(returnDto.getIsOverDue());
        custName.setText(returnDto.getCustName());
        vehicleRegNo.setText(returnDto.getVehiRegNo());
        panelty.setText(String.valueOf(returnDto.getPanelty()));
        total.setText(String.valueOf(returnDto.getTotal()));

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }
}
