package bg.tu_varna.sit.oop_project_demo.presentation.controllers;

import bg.tu_varna.sit.oop_project_demo.business.services.CompanyService;
import bg.tu_varna.sit.oop_project_demo.business.services.DistributorService;
import bg.tu_varna.sit.oop_project_demo.business.services.RequestService;
import bg.tu_varna.sit.oop_project_demo.business.services.TripService;
import bg.tu_varna.sit.oop_project_demo.data.entities.Request;
import bg.tu_varna.sit.oop_project_demo.presentation.models.RequestListViewModel;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class UpdateRequestController implements Initializable {

    RequestService requestService = RequestService.getInstance();
    DistributorService distributorService = DistributorService.getInstance();
    CompanyService companyService = CompanyService.getInstance();
    TripService tripService = TripService.getInstance();

    @FXML
    private Button approveButton;

    @FXML
    private TableColumn<RequestListViewModel, String> arrivalLocationColumn;

    @FXML
    private Button backButton;

    @FXML
    private Button deleteRequestButton;

    @FXML
    private TableColumn<RequestListViewModel, LocalDate> departureDateColumn;

    @FXML
    private TableColumn<RequestListViewModel, String> departureLocationColumn;

    @FXML
    private TableColumn<RequestListViewModel, String> distributorColumn;

    @FXML
    private Button logout;

    @FXML
    private Button rejectButton;

    @FXML
    private TableColumn<Request, Integer> ticketCount;

    @FXML
    private TableView<RequestListViewModel> requestTable;

    @FXML
    void goBack(ActionEvent event) {

    }


    public void initTable(){
        ObservableList<RequestListViewModel> requestListViewModels = companyService.getMyPendingRequests();
        departureLocationColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<RequestListViewModel, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<RequestListViewModel, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getTripId().getLocationFrom().getLocationName());
            }
        });

        arrivalLocationColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<RequestListViewModel, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<RequestListViewModel, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getTripId().getLocationTo().getLocationName());
            }
        });
        departureDateColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<RequestListViewModel, LocalDate>, ObservableValue<LocalDate>>() {
            @Override
            public ObservableValue<LocalDate> call(TableColumn.CellDataFeatures<RequestListViewModel, LocalDate> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getTripId().getDeparture());
            }
        });
        distributorColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<RequestListViewModel, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<RequestListViewModel, String> p) {
                return new ReadOnlyObjectWrapper<>(p.getValue().getDistributorId().getDistributorName());
            }
        });
        ticketCount.setCellValueFactory(new PropertyValueFactory<>("ticketCount"));
        requestTable.setItems(requestListViewModels);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initTable();
    }
}