package bg.tu_varna.sit.oop_project_demo.presentation.controllers;

import bg.tu_varna.sit.oop_project_demo.business.services.CompanyService;
import bg.tu_varna.sit.oop_project_demo.business.services.DistributorService;
import bg.tu_varna.sit.oop_project_demo.business.services.RequestService;
import bg.tu_varna.sit.oop_project_demo.business.services.TripService;
import bg.tu_varna.sit.oop_project_demo.presentation.models.RequestListViewModel;
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

import static bg.tu_varna.sit.oop_project_demo.common.Constants.User.loggedDistributorUsername;
import static bg.tu_varna.sit.oop_project_demo.common.Constants.User.trackUser;
import static bg.tu_varna.sit.oop_project_demo.common.Constants.View.*;
import static bg.tu_varna.sit.oop_project_demo.common.Constants.View.CASHIER_VIEW;

public class AddRequestController implements Initializable {
    RequestService service = RequestService.getInstance();

    TripService tripService = TripService.getInstance();
    DistributorService distributorService = DistributorService.getInstance();
    CompanyService companyService = CompanyService.getInstance();

    @FXML
    private Button addRequestButton;

    @FXML
    private TableColumn<TripListViewModel, String> arrivalLocationColumn;

    @FXML
    private Button backButton;

    @FXML
    private TableColumn<TripListViewModel, LocalDate> departureDateColumn;

    @FXML
    private TableColumn<TripListViewModel, String> departureLocationColumn;

    @FXML
    private Button logout;

    @FXML
    private TextField ticketsCount;

    @FXML
    private TableColumn<TripListViewModel, Integer> tripIdColumn;

    @FXML
    private TableView<TripListViewModel> tripTable;


    public void initTable(){
        TripService tripService = TripService.getInstance();
        ObservableList<TripListViewModel> tripListViewModel = tripService.getAllTrips();

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


        tripTable.setItems(tripListViewModel);

    }


    public void onAddRequestButtonClick(ActionEvent event) {
        TripListViewModel selectedItem = tripTable.getSelectionModel().getSelectedItem();
        //TripListViewModel selectedItem1 = tripTable.getSelectionModel().getSelectedItem();
        RequestListViewModel request = new RequestListViewModel(Integer.parseInt(ticketsCount.getText()), "pending", tripService.getTrip(selectedItem),
                distributorService.getDistributorByName(loggedDistributorUsername),
                tripService.getTrip(selectedItem).getCompanyId());
        int res=service.createRequest(request);
        if(res==0){
            Alert alert=new Alert(Alert.AlertType.CONFIRMATION,"Request added", ButtonType.OK);
            alert.show();

        }
        else{
            Alert alert=new Alert(Alert.AlertType.ERROR,"Couldn't add request!", ButtonType.CLOSE);
            alert.show();
        }

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

    public void onLogoutButtonClick()
    {
        trackUser = 0;
        loadNewPage(HELLO_VIEW);
    }

    public void loadNewPage(String path){
        try {
            Stage s = (Stage) addRequestButton.getScene().getWindow();
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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initTable();
    }
}
