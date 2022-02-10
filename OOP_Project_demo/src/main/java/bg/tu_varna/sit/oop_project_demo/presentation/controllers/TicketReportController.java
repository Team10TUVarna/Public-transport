package bg.tu_varna.sit.oop_project_demo.presentation.controllers;

import bg.tu_varna.sit.oop_project_demo.business.services.*;
import bg.tu_varna.sit.oop_project_demo.data.entities.Ticket;
import bg.tu_varna.sit.oop_project_demo.presentation.models.TicketListViewModel;
import bg.tu_varna.sit.oop_project_demo.presentation.models.TripListViewModel;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

public class TicketReportController implements Initializable {
    CashierService cashierService = CashierService.getInstance();
    RequestService requestService = RequestService.getInstance();
    DistributorService distributorService = DistributorService.getInstance();
    CompanyService companyService = CompanyService.getInstance();
    TripService tripService = TripService.getInstance();
    TicketService ticketService = TicketService.getInstance();

    ObservableList<TicketListViewModel> ticketListViewModels = ticketService.getAllSoldTickets();

    @FXML
    private TableColumn<TicketListViewModel, String> arrivalLocationColumn;

    @FXML
    private Button backButton;

    @FXML
    private TableColumn<TicketListViewModel, String> cashierNameColumn;

    @FXML
    private Button checkButton;

    @FXML
    private TableColumn<TicketListViewModel, String> customerNameColumn;

    @FXML
    private DatePicker dateFrom;

    @FXML
    private DatePicker dateTo;

    @FXML
    private TableColumn<TicketListViewModel, LocalDate> departureDateColumn;

    @FXML
    private TableColumn<TicketListViewModel, String> departureHourColumn;

    @FXML
    private TableColumn<TicketListViewModel, String> departureLocationColumn;

    @FXML
    private Button logout;

    @FXML
    private TableColumn<TicketListViewModel, LocalDate> purchaseDateColumn;

    @FXML
    private TableColumn<TicketListViewModel, Integer> seatNumberColumn;

    @FXML
    private TableView<TicketListViewModel> ticketTable;


    public void onCheckButtonClick(ActionEvent event) {
        ObservableList<TicketListViewModel> ticketListViewModels1 = FXCollections.observableArrayList();
        for(TicketListViewModel a:ticketListViewModels){
            if (a.getPurchaseDate().isAfter(dateFrom.getValue()) && a.getPurchaseDate().isBefore(dateTo.getValue())){
                ticketListViewModels1.add(a);
            }
        }
        initTable(ticketListViewModels1);
    }


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


    public void initTable(ObservableList<TicketListViewModel> a){
        seatNumberColumn.setCellValueFactory(new PropertyValueFactory<>("seatNumber"));
        customerNameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        purchaseDateColumn.setCellValueFactory(new PropertyValueFactory<>("purchaseDate"));
        cashierNameColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TicketListViewModel, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<TicketListViewModel, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getCashierId().getCashierName());
            }
        });
        departureLocationColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TicketListViewModel, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<TicketListViewModel, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getTripId().getLocationFrom().getLocationName());
            }
        });
        arrivalLocationColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TicketListViewModel, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<TicketListViewModel, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getTripId().getLocationTo().getLocationName());
            }
        });
        departureDateColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TicketListViewModel, LocalDate>, ObservableValue<LocalDate>>() {
            @Override
            public ObservableValue<LocalDate> call(TableColumn.CellDataFeatures<TicketListViewModel, LocalDate> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getTripId().getDeparture());
            }
        });
        departureHourColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TicketListViewModel, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<TicketListViewModel, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getTripId().getTimeOfDeparture());
            }
        });
        ticketTable.setItems(a);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initTable(ticketListViewModels);
    }
}
