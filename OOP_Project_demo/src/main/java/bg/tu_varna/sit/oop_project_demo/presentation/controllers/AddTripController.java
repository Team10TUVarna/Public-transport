package bg.tu_varna.sit.oop_project_demo.presentation.controllers;

import bg.tu_varna.sit.oop_project_demo.business.services.*;
import bg.tu_varna.sit.oop_project_demo.data.entities.Company;
import bg.tu_varna.sit.oop_project_demo.presentation.models.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

import static bg.tu_varna.sit.oop_project_demo.common.Constants.User.*;
import static bg.tu_varna.sit.oop_project_demo.common.Constants.View.*;
import static bg.tu_varna.sit.oop_project_demo.common.Constants.View.CASHIER_VIEW;

public class AddTripController implements Initializable {

    private final LocationService locationService=LocationService.getInstance();
    private final TransportTypeService transportTypeService=TransportTypeService.getInstance();
    private final TripTypeService tripTypeService=TripTypeService.getInstance();
    private final TripService tripService=TripService.getInstance();
    private final CompanyService companyService=CompanyService.getInstance();

    //Company company = new Company(1, "TestCompany", "test1", "12345", 70000.00);

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
    //private ComboBox<String> locationFrom;
    private ComboBox<LocationListViewModel> locationFrom;

    @FXML
    //private ComboBox<String> locationTo;
    private ComboBox<LocationListViewModel> locationTo;

    @FXML
    private Button logout;

    @FXML
    private TextField timeOfArrival;

    @FXML
    private TextField timeOfDeparture;

    @FXML
    //private ComboBox<String> transportType;
    private ComboBox<TransportTypeListViewModel> transportType;

    @FXML
    //private ComboBox<String> tripType;
    private ComboBox<TripTypeListViewModel> tripType;

    public void onAddTripButtonClick(){
        /*TripListViewModel tripListViewModel=new TripListViewModel(departure.getValue(), arrival.getValue(), Integer.parseInt(capacity.getText()),
                tripTypeService.getTripTypeByName(tripType.getValue()), transportTypeService.getTransportTypeByName(transportType.getValue()),
                1, locationService.getLocationByName(locationFrom.getValue()), locationService.getLocationByName(locationTo.getValue()), timeOfDeparture.getText(),
                timeOfArrival.getText());*/
        TripListViewModel tripListViewModel = new TripListViewModel(departure.getValue(), arrival.getValue(), Integer.parseInt(capacity.getText()),
                tripTypeService.getTripTypeByName(tripType.getValue().getTripTypeName()),transportTypeService.getTransportTypeByName(transportType.getValue().getTransportTypeName()),
                companyService.getCompanyByName(loggedCompanyUsername), locationService.getLocationByName(locationFrom.getValue().getLocationName()),
                locationService.getLocationByName(locationTo.getValue().getLocationName()),
                timeOfDeparture.getText(), timeOfArrival.getText());
        int res=tripService.createTrip(tripListViewModel);
        if(res==0){
            Alert alert=new Alert(Alert.AlertType.ERROR,"Trip already exists!", ButtonType.CLOSE);
            alert.show();
        }
        else{
            Alert alert=new Alert(Alert.AlertType.CONFIRMATION,"Trip added", ButtonType.OK);
            alert.show();
        }
    }

    public void onLogoutButtonClick()
    {
        trackUser = 0;
        loadNewPage(HELLO_VIEW);
    }

    public void onGoBackButtonClick(){
        if (trackUser == 1)
            loadNewPage(ADMIN_VIEW);
        if (trackUser == 2)
            loadNewPage(COMPANY_VIEW);
        if (trackUser == 3)
            loadNewPage(DISTRIBUTOR_VIEW);
        if (trackUser == 4)
            loadNewPage(CASHIER_VIEW);
    }

    public void fillComboBoxes(){
        ObservableList<LocationListViewModel> locFrom = locationService.getAllLocations();
        ObservableList<LocationListViewModel> locTo = locationService.getAllLocations();
        ObservableList<TransportTypeListViewModel> transType = transportTypeService.getAllTransportTypes();
        ObservableList<TripTypeListViewModel> trpType = tripTypeService.getAllTripTypes();
        /*ObservableList<String> locFrom = locationService.getAllLocations();
        ObservableList<String> locTo = locationService.getAllLocations();
        ObservableList<String> transType = transportTypeService.getAllTransportTypes();
        ObservableList<String> trpType = tripTypeService.getAllTripTypes();*/

        locationFrom.setItems(locFrom);
        locationTo.setItems(locTo);
        transportType.setItems(transType);
        tripType.setItems(trpType);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fillComboBoxes();
    }

    public void loadNewPage(String path){
        try {
            Stage s = (Stage) addTripButton.getScene().getWindow();
            s.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(path));
            Stage stage = new Stage();
            //fxmlLoader.setController(new AdminViewController(stage));
            Parent root1 = (Parent) fxmlLoader.load();
            stage.setScene(new Scene(root1));
            stage.setResizable(false);
            stage.setWidth(615);
            stage.setHeight(440);
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}