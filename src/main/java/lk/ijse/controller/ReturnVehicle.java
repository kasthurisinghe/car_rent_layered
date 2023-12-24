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
        String booId=txtBookingId.getText();
        Boolean isReturne=true;
        try {
            Boolean isReturned=returnBoImpl.acceptReturn(booId,isReturne);
            if (isReturned){
                new Alert(Alert.AlertType.CONFIRMATION,"Vehicle return Accepted").show();
                clearFields();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    private void clearFields() {
        txtBookingId.setText("");
        OverDue.setText("");
        dueDate.setValue(null);
        custId.setText("");
        custName.setText("");
        panelty.setText("");
        total.setText("");
        vehicleRegNo.setText("");
    }


    public void btnCheckClickOnAction(ActionEvent actionEvent) throws SQLException {
        String bookId=txtBookingId.getText();
        LocalDate duDate=dueDate.getValue();

//        try {
        if (bookId!="" && duDate!=null) {
            ReturnDto returnDto= returnBoImpl.findRental(bookId,duDate);

            if (returnDto!=null) {
                txtBookingId.setText(returnDto.getBookingId());
                custId.setText(returnDto.getCustId());
                OverDue.setText(returnDto.getIsOverDue());
                custName.setText(returnDto.getCustName());
                vehicleRegNo.setText(returnDto.getVehiRegNo());
                panelty.setText(String.valueOf(returnDto.getPanelty()));
                total.setText(String.valueOf(returnDto.getTotal()));
            }else {
                msgTxr.setText("The vehicle of the booking '"+bookId+"' has been returned ");
            }

//        } catch (Exception e) {
//            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
//        }
        }
        else {
            msgTxr.setText("Give the booking Id and today date");
        }
    }
}
