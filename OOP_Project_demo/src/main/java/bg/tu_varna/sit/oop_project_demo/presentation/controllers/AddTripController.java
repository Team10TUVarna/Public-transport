package bg.tu_varna.sit.oop_project_demo.presentation.controllers;

import bg.tu_varna.sit.oop_project_demo.presentation.models.LocationListViewModel;
import bg.tu_varna.sit.oop_project_demo.presentation.models.TransportTypeListViewModel;
import bg.tu_varna.sit.oop_project_demo.presentation.models.TripTypeListViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class AddTripController {

    @FXML
    private Button addTripButton;

    @FXML
    private DatePicker arrival;

    @FXML
    private Button backButton;

    @FXML
    private TextField capacity;

    @FXML
    private DatePicker departure;

    @FXML
    private ComboBox<LocationListViewModel> locationFrom;

    @FXML
    private ComboBox<LocationListViewModel> locationTo;

    @FXML
    private Button logout;

    @FXML
    private TextField timeOfDeparture;

    @FXML
    private ComboBox<TransportTypeListViewModel> transportType;

    @FXML
    private ComboBox<TripTypeListViewModel> tripType;

    @FXML
    void goBack(ActionEvent event) {

    }

}