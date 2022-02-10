package bg.tu_varna.sit.oop_project_demo.presentation.controllers;

import bg.tu_varna.sit.oop_project_demo.business.services.*;
import bg.tu_varna.sit.oop_project_demo.data.entities.TripType;
import bg.tu_varna.sit.oop_project_demo.presentation.models.CompanyListViewModel;
import bg.tu_varna.sit.oop_project_demo.presentation.models.TripListViewModel;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import static bg.tu_varna.sit.oop_project_demo.common.Constants.User.trackUser;
import static bg.tu_varna.sit.oop_project_demo.common.Constants.View.*;

public class TripsReportController implements Initializable {

    CashierService cashierService = CashierService.getInstance();
    RequestService requestService = RequestService.getInstance();
    DistributorService distributorService = DistributorService.getInstance();
    CompanyService companyService = CompanyService.getInstance();
    TripService tripService = TripService.getInstance();
    TicketService ticketService = TicketService.getInstance();

    ObservableList<TripListViewModel> tripListViewModels = tripService.getAllTrips();

    @FXML
    private TableColumn<TripListViewModel, LocalDate> arrivalDateColumn;

    @FXML
    private TableColumn<TripListViewModel, String> arrivalLocationColumn;

    @FXML
    private Button backButton;

    @FXML
    private TableColumn<TripListViewModel, Integer> capacityColumn;

    @FXML
    private Button checkButton;

    @FXML
    private ComboBox<CompanyListViewModel> comboBox;

    @FXML
    private TableColumn<TripListViewModel, String> companyColumn;

    @FXML
    private DatePicker dateFrom;

    @FXML
    private DatePicker dateTo;

    @FXML
    private TableColumn<TripListViewModel, LocalDate> departureDateColumn;

    @FXML
    private TableColumn<TripListViewModel, String> departureLocationColumn;

    @FXML
    private Button logout;

    @FXML
    private TableColumn<TripListViewModel, String> transportTypeColumn;

    @FXML
    private TableView<TripListViewModel> tripTable;

    @FXML
    private TableColumn<TripListViewModel, String> tripTypeColumn;

    public void onGoBackButtonClick(ActionEvent event) {
        if (trackUser == 1)
            loadNewPage(ADMIN_VIEW);
        if (trackUser == 2)
            loadNewPage(COMPANY_VIEW);
        if (trackUser == 3)
            loadNewPage(DISTRIBUTOR_VIEW);
        if (trackUser == 4)
            loadNewPage(CASHIER_VIEW);
    }

    public void onLogoutButtonClick(ActionEvent event){
        trackUser = 0;
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
            stage.setWidth(615);
            stage.setHeight(440);
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void onCheckButtonClick(){
        ObservableList<TripListViewModel> tripListViewModels1 = FXCollections.observableArrayList();
        for(TripListViewModel a:tripListViewModels){
            if (a.getDeparture().isAfter(dateFrom.getValue()) && a.getArrival().isBefore(dateTo.getValue())
                    && a.getCompanyId().getUsername().equals(comboBox.getValue().getUsername())){
                tripListViewModels1.add(a);
            }
        }
        initTable(tripListViewModels1);
    }

    public void initTable(ObservableList<TripListViewModel> tripListViewModels){
        departureDateColumn.setCellValueFactory(new PropertyValueFactory<>("departure"));
        arrivalDateColumn.setCellValueFactory(new PropertyValueFactory<>("arrival"));
        capacityColumn.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        tripTypeColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TripListViewModel, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<TripListViewModel, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getTripTypeId().getTripTypeName());
            }
        });

        transportTypeColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TripListViewModel, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<TripListViewModel, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getTransportTypeId().getTransportTypeName());
            }
        });

        companyColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TripListViewModel, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<TripListViewModel, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getCompanyId().getCompanyName());
            }
        });

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

        tripTable.setItems(tripListViewModels);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<CompanyListViewModel> companyListViewModels = companyService.getAllCompany();
        comboBox.setItems(companyListViewModels);
        //ObservableList<TripListViewModel> tripListViewModels = tripService.getAllTrips();
        initTable(tripListViewModels);
    }
}
