package bg.tu_varna.sit.oop_project_demo.presentation.controllers;

import bg.tu_varna.sit.oop_project_demo.business.services.*;
import bg.tu_varna.sit.oop_project_demo.data.entities.Location;
import bg.tu_varna.sit.oop_project_demo.presentation.models.RequestListViewModel;
import bg.tu_varna.sit.oop_project_demo.presentation.models.TicketListViewModel;
import bg.tu_varna.sit.oop_project_demo.presentation.models.TripListViewModel;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import static bg.tu_varna.sit.oop_project_demo.common.Constants.User.loggedCashierUsername;
import static bg.tu_varna.sit.oop_project_demo.common.Constants.User.trackUser;
import static bg.tu_varna.sit.oop_project_demo.common.Constants.View.CASHIER_VIEW;
import static bg.tu_varna.sit.oop_project_demo.common.Constants.View.HELLO_VIEW;

public class BuyTicketController implements Initializable {

    RequestService requestService = RequestService.getInstance();
    DistributorService distributorService = DistributorService.getInstance();
    CompanyService companyService = CompanyService.getInstance();
    TripService tripService = TripService.getInstance();
    TicketService ticketService = TicketService.getInstance();
    CashierService cashierService = CashierService.getInstance();

    @FXML
    private TableColumn<TripListViewModel, LocalDate> arrivalDateColumn;

    @FXML
    private TableColumn<TripListViewModel, String> arrivalHourColumn;

    @FXML
    private TableColumn<TripListViewModel, String> arrivalLocationColumn;

    @FXML
    private Button backButton;

    @FXML
    private Button buyTicketButton;

    @FXML
    private TextField customerName;

    @FXML
    private TableColumn<TripListViewModel, LocalDate> departureDateColumn;

    @FXML
    private TableColumn<TripListViewModel, String> departureHourColumn;

    @FXML
    private TableColumn<TripListViewModel, String> departureLocationColumn;

    @FXML
    private Button logout;

    @FXML
    private TableView<TripListViewModel> tableView;

    @FXML
    private TableColumn<TripListViewModel, String> transportTypeColumn;

    @FXML
    private TableColumn<TripListViewModel, String> tripTypeColumn;


    public void onBuyTicketButtonClick() {
        TripListViewModel selectedItem = tableView.getSelectionModel().getSelectedItem();
        ObservableList<TicketListViewModel> allTickets = ticketService.getAllTickets();
        for (TicketListViewModel a : allTickets){
            if(a.getTripId().equals(tripService.getTrip(selectedItem))){
                if(ticketService.updateTicket(a, customerName.getText())){
                    Alert alert=new Alert(Alert.AlertType.CONFIRMATION,"Successfully bought ticket!",ButtonType.OK);
                    alert.show();
                }
                else{
                    Alert alert=new Alert(Alert.AlertType.ERROR,"Error when buying ticket!",ButtonType.OK);
                    alert.show();
                }
                break;
            }
        }
    }



    public void onGoBackButtonClick() {
        loadNewPage(CASHIER_VIEW);
    }

    public void tableInit(){
        ObservableList<TripListViewModel> tripListViewModels = tripService.getAllTrips();
        departureLocationColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TripListViewModel, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<TripListViewModel, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getLocationFrom().getLocationName());
            }
        });
        arrivalLocationColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TripListViewModel, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<TripListViewModel, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getLocationTo().getLocationName());
            }
        });
        departureDateColumn.setCellValueFactory(new PropertyValueFactory<>("departure"));
        arrivalDateColumn.setCellValueFactory(new PropertyValueFactory<>("arrival"));
        departureHourColumn.setCellValueFactory(new PropertyValueFactory<>("timeOfDeparture"));
        arrivalHourColumn.setCellValueFactory(new PropertyValueFactory<>("timeOfArrival"));
        transportTypeColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TripListViewModel, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<TripListViewModel, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getTransportTypeId().getTransportTypeName());
            }
        });
        tripTypeColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TripListViewModel, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<TripListViewModel, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getTripTypeId().getTripTypeName());
            }
        });
        tableView.setItems(tripListViewModels);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableInit();
    }

    public void onLogoutButtonClick(ActionEvent event){
        trackUser = 0;
        loggedCashierUsername = "";
        loadNewPage(HELLO_VIEW);
    }

    public void loadNewPage(String path){
        try {
            Stage s = (Stage) backButton.getScene().getWindow();
            s.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(path));
            Stage stage = new Stage();
            Parent root1 = (Parent) fxmlLoader.load();
            stage.setScene(new Scene(root1));
            stage.setResizable(false);
            stage.setWidth(630);
            stage.setHeight(440);
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
