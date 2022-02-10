package bg.tu_varna.sit.oop_project_demo.presentation.controllers;

import bg.tu_varna.sit.oop_project_demo.business.services.*;
import bg.tu_varna.sit.oop_project_demo.data.entities.Ticket;
import bg.tu_varna.sit.oop_project_demo.presentation.models.TicketListViewModel;
import bg.tu_varna.sit.oop_project_demo.presentation.models.TripListViewModel;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class TicketReportController implements Initializable {
    CashierService cashierService = CashierService.getInstance();
    RequestService requestService = RequestService.getInstance();
    DistributorService distributorService = DistributorService.getInstance();
    CompanyService companyService = CompanyService.getInstance();
    TripService tripService = TripService.getInstance();
    TicketService ticketService = TicketService.getInstance();



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

    }


    public void onGoBackButtonClick(ActionEvent event) {

    }


    public void onLogoutButtonClick(ActionEvent event) {

    }

    ObservableList<TicketListViewModel> ticketListViewModels = ticketService.getAllSoldTickets();
    public void initTable(){

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
        ticketTable.setItems(ticketListViewModels);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initTable();
    }
}
