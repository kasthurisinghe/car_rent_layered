package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import lk.ijse.bussines.BoType;
import lk.ijse.bussines.FactoryBo;
import lk.ijse.bussines.custom.BookingBo;
import lk.ijse.dto.BookingDto;

import java.sql.SQLException;
import java.time.LocalDate;


public class BookingDetails {
    @FXML
    private Button BtnDelete;

    @FXML
    private Button BtnRent;

    @FXML
    private Button BtnUpdate;

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
    private TextField TxtCustomerId;
    @FXML
    private DatePicker endDate;
    @FXML
    private Label msgLab;
    @FXML
    private DatePicker startDate;

    BookingBo bookingBoImpl=FactoryBo.getBo(BoType.BOOKINGBO);
    @FXML
    void btnDeleteClickOnAction(ActionEvent event) {

    }

    @FXML
    void btnRentClickOnAction(ActionEvent event) throws SQLException {

//        try {
            String bookingId=txtBookingId.getText();
            String carId=txtCarId.getText();
            Integer rate= Integer.valueOf(txtRate.getText());
            String custId=TxtCustomerId.getText();
            LocalDate endDat=endDate.getValue();
            LocalDate startDat=startDate.getValue();

            BookingDto bookingDto=new BookingDto(bookingId,carId,rate,custId,endDat,startDat);
            boolean isSaved= bookingBoImpl.saveBooking(bookingDto);

            if (isSaved){
                new Alert(Alert.AlertType.INFORMATION, "Booking details saved successfully").show();
            }
//        } catch (NumberFormatException e) {
//            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
//        } catch (SQLException e) {
//            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
//        }

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
