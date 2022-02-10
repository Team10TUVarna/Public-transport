package bg.tu_varna.sit.oop_project_demo.presentation.controllers;

import bg.tu_varna.sit.oop_project_demo.business.services.*;
import bg.tu_varna.sit.oop_project_demo.presentation.models.CashierListViewModel;
import bg.tu_varna.sit.oop_project_demo.presentation.models.DistributorListViewModel;
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

import java.net.URL;
import java.util.ResourceBundle;

import static bg.tu_varna.sit.oop_project_demo.common.Constants.User.*;
import static bg.tu_varna.sit.oop_project_demo.common.Constants.View.*;

public class UpdateDeleteDistributorController implements Initializable {

    CashierService cashierService = CashierService.getInstance();
    RequestService requestService = RequestService.getInstance();
    DistributorService distributorService = DistributorService.getInstance();
    CompanyService companyService = CompanyService.getInstance();
    TripService tripService = TripService.getInstance();
    TicketService ticketService = TicketService.getInstance();

    @FXML
    private Button backButton;

    @FXML
    private TableView<DistributorListViewModel> distributorTable;

    @FXML
    private Button deleteDistributorButton;

    @FXML
    private TextField honorarium;

    @FXML
    private TableColumn<DistributorListViewModel, Double> honorariumColumn;

    @FXML
    private Button logout;

    @FXML
    private TableColumn<DistributorListViewModel, String> nameColumn;

    @FXML
    private Button updateDistributorButton;

    @FXML
    private TableColumn<DistributorListViewModel, String> usernameColumn;


    public void onDeleteDistributorButtonClick(ActionEvent event) {
        DistributorListViewModel selectedItem = distributorTable.getSelectionModel().getSelectedItem();
        distributorService.deleteDistributor(selectedItem);
        initTable();
        Alert alert1=new Alert(Alert.AlertType.CONFIRMATION,"Successfully deleted distributor!",ButtonType.OK);
        alert1.show();
    }

    public void onUpdateDistributorButtonClick(ActionEvent event) {
        DistributorListViewModel selectedItem = distributorTable.getSelectionModel().getSelectedItem();
        if (distributorService.updateDistributorHonorarium(selectedItem, Double.parseDouble(honorarium.getText()))){
            Alert alert1=new Alert(Alert.AlertType.CONFIRMATION,"Successfully updated distributor!",ButtonType.OK);
            alert1.show();
            initTable();
        }else{
            Alert alert1=new Alert(Alert.AlertType.ERROR,"Error when updating distributor!",ButtonType.OK);
            alert1.show();
        }
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
        loggedCashierUsername = "";
        loggedAdminUsername = "";
        loggedCompanyUsername = "";
        loggedDistributorUsername = "";
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

    public void initTable(){
        distributorTable.getItems().clear();
        ObservableList<DistributorListViewModel> distributorListViewModels = distributorService.getAllDistributor();
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("distributorName"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        honorariumColumn.setCellValueFactory(new PropertyValueFactory<>("honorarium"));
        distributorTable.setItems(distributorListViewModels);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initTable();
    }
}
