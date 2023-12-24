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
        try {
            String bId=txtBookingId.getText();

            Boolean isDeleted=bookingBoImpl.deleteBooking(bId);
            if (isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION, "successfully deleted").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    @FXML
    void btnRentClickOnAction(ActionEvent event) throws SQLException {
        Boolean isReturned=false;
        String bookingId=txtBookingId.getText();
        String carId=txtCarId.getText();

        String custId=TxtCustomerId.getText();
        LocalDate endDat=endDate.getValue();
        LocalDate startDat=startDate.getValue();



            if (!bookingId.equals("") && !carId.equals("") &&  !custId.equals("") && !txtRate.getText().equals("") ) {
                try {
                    Integer rate= Integer.valueOf(txtRate.getText());
                    BookingDto bookingDto=new BookingDto(bookingId,carId,rate,custId,startDat,endDat,isReturned);
                    boolean isSaved= bookingBoImpl.saveBooking(bookingDto);

                    if (isSaved){
                        new Alert(Alert.AlertType.INFORMATION, "Booking details saved successfully").show();
                        clearFields();
                    }
                } catch (NumberFormatException e) {
                    new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
                } catch (SQLException e) {
                    new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
                }
            }
            else {
                new Alert(Alert.AlertType.WARNING, "Please fill all the fields here").show();
            }
    }

    private void clearFields() {
        txtCarId.setText("");
        txtBookingId.setText("");
        TxtCustomerId.setText("");
        txtRate.setText("");
        startDate.setValue(null);
        endDate.setValue(null);
        msgLab.setText("");
    }

    @FXML
    void btnUpdateClickOnAction(ActionEvent event) throws SQLException {
        boolean isReturned=false;
        try {
            String bId=txtBookingId.getText();
            String carId=txtCarId.getText();
            Integer rate= Integer.valueOf(txtRate.getText());
            String custId=TxtCustomerId.getText();
            LocalDate startDa=startDate.getValue();
            LocalDate endDa=endDate.getValue();
            BookingDto bookingDto=new BookingDto(bId,carId,rate,custId,startDa,endDa,isReturned);
            Boolean isUpdated=bookingBoImpl.updateBooking(bookingDto);
            if (isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION,"Booking updated successfully").show();
                clearFields();
            }
        } catch (NumberFormatException | SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    @FXML
    void txtClickOnAction(ActionEvent event) throws SQLException {


    }

    @FXML
    void txtIdClickOnAction(ActionEvent event) throws SQLException {
        String bookingId=txtBookingId.getText();
        try {
            BookingDto bookingDto=bookingBoImpl.searchBooking(bookingId);

            txtCarId.setText(bookingDto.getCarId());
            txtRate.setText(String.valueOf(bookingDto.getRate()));
            TxtCustomerId.setText(bookingDto.getCustId());
            startDate.setValue(bookingDto.getStartDat());
            endDate.setValue(bookingDto.getEndDat());
            txtBookingId.setText(bookingDto.getBookingId());
            if (bookingDto.getIsReturned()) {
                msgLab.setText("vehicle is not returned yet");
            }else {
                msgLab.setText("vehicle is returned");
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }
}
