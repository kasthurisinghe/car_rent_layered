package lk.ijse.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class MainMenu {
    @FXML
    private TableColumn<?, ?> CustId;

    @FXML
    private TableColumn<?, ?> VehiId;

    @FXML
    private Button bookingBtn1;

    @FXML
    private Button cutomerBtn;

    @FXML
    private Button exitBtn;

    @FXML
    private Button rentalBtn;

    @FXML
    private AnchorPane rootNode1;

    @FXML
    private Button vehicleBtn;
    public void btnExitClickOnAction(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void btnClickOnAllRental(ActionEvent actionEvent) {
    }

    public void btnClickOnCreateCustomerOnAction(ActionEvent actionEvent) throws IOException {
        Parent root= FXMLLoader.load(this.getClass().getResource("/view/customerDetails.fxml"));


// Clear and set the existing AnchorPane content
        this.rootNode1.getChildren().clear();
        this.rootNode1.getChildren().add(root);
//
    }

    public void btnVehicleRegistryClickOnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/carDetails.fxml"));

        this.rootNode1.getChildren().clear();
        this.rootNode1.getChildren().add(root);
    }

    public void btnBookingClickOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(this.getClass().getResource("/view/bookingDetails.fxml"));
        rootNode1.getChildren().clear();
        rootNode1.getChildren().add(parent);
    }
}
