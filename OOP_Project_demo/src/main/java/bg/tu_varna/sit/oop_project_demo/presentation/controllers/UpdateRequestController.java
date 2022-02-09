package bg.tu_varna.sit.oop_project_demo.presentation.controllers;

import bg.tu_varna.sit.oop_project_demo.business.services.*;
import bg.tu_varna.sit.oop_project_demo.data.entities.Request;
import bg.tu_varna.sit.oop_project_demo.presentation.models.RequestListViewModel;
import bg.tu_varna.sit.oop_project_demo.presentation.models.TicketListViewModel;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import static bg.tu_varna.sit.oop_project_demo.common.Constants.User.loggedCashierUsername;
import static bg.tu_varna.sit.oop_project_demo.common.Constants.User.loggedCompanyUsername;

public class UpdateRequestController implements Initializable {

    RequestService requestService = RequestService.getInstance();
    DistributorService distributorService = DistributorService.getInstance();
    CompanyService companyService = CompanyService.getInstance();
    TripService tripService = TripService.getInstance();
    TicketService ticketService = TicketService.getInstance();
    CashierService cashierService = CashierService.getInstance();

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


    public void goBack(ActionEvent event) {

    }

    public void onApproveButtonClick(){
        LocalDate date = LocalDate.of(2000, 1, 1);
        RequestListViewModel selectedItem = requestTable.getSelectionModel().getSelectedItem();
        int ticketCnt = selectedItem.getTicketCount();
        int cpcty = selectedItem.getTripId().getCapacity();
        int maxSeatNum = 0;
        ObservableList<TicketListViewModel> allTickets = ticketService.getAllTickets();
        for (TicketListViewModel a : allTickets){
            if (a.getTripId().equals(selectedItem.getTripId()) && a.getSeatNumber() > maxSeatNum){
                maxSeatNum = a.getSeatNumber();
            }
        }
        if (cpcty > ticketCnt + maxSeatNum){
            if(requestService.updateRequestStatus(selectedItem, "APPROVED")){
                Alert alert=new Alert(Alert.AlertType.CONFIRMATION,"Successfully approved status!",ButtonType.OK);
                alert.show();
            }
            else{
                Alert alert=new Alert(Alert.AlertType.ERROR,"Error when approving status!",ButtonType.OK);
                alert.show();
            }
            do{

                TicketListViewModel ticket = new TicketListViewModel(++maxSeatNum, "NOT SOLD", date,
                        cashierService.getCashierByName("test1"), selectedItem.getTripId());
                int res = ticketService.createTicket(ticket);
                if (res == 0){
                    Alert alert=new Alert(Alert.AlertType.CONFIRMATION,"Ticket added", ButtonType.OK);
                    alert.show();
                }
                else{
                    Alert alert=new Alert(Alert.AlertType.ERROR,"Couldn't add ticket!", ButtonType.CLOSE);
                    alert.show();
                    break;
                }
                --ticketCnt;
            }while (ticketCnt != 0);
        }
        else{
            Alert alert=new Alert(Alert.AlertType.ERROR,"Not enough space!", ButtonType.CLOSE);
            alert.show();
        }
        initTable();
    }

    public void onRejectButtonClick(){
        RequestListViewModel selectedItem = requestTable.getSelectionModel().getSelectedItem();
        /*Alert alert=new Alert(Alert.AlertType.CONFIRMATION,"Request rejected.", ButtonType.CLOSE);
        alert.show();*/
        if(requestService.updateRequestStatus(selectedItem, "REJECTED")){
            Alert alert1=new Alert(Alert.AlertType.CONFIRMATION,"Successfully approved status!",ButtonType.OK);
            alert1.show();
        }
        else{
            Alert alert1=new Alert(Alert.AlertType.ERROR,"Error when approving status!",ButtonType.OK);
            alert1.show();
        }
        initTable();
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