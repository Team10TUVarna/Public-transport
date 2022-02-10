package bg.tu_varna.sit.oop_project_demo.presentation.controllers;

import bg.tu_varna.sit.oop_project_demo.business.services.*;
import bg.tu_varna.sit.oop_project_demo.presentation.models.CashierListViewModel;
import bg.tu_varna.sit.oop_project_demo.presentation.models.CompanyListViewModel;
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

public class UpdateDeleteCompanyController implements Initializable {

    CashierService cashierService = CashierService.getInstance();
    RequestService requestService = RequestService.getInstance();
    DistributorService distributorService = DistributorService.getInstance();
    CompanyService companyService = CompanyService.getInstance();
    TripService tripService = TripService.getInstance();
    TicketService ticketService = TicketService.getInstance();

    @FXML
    private Button backButton;

    @FXML
    private TableView<CompanyListViewModel> companyTable;

    @FXML
    private Button deleteCompanyButton;

    @FXML
    private TextField honorarium;

    @FXML
    private TableColumn<CompanyListViewModel, Double> honorariumColumn;

    @FXML
    private Button logout;

    @FXML
    private TableColumn<CompanyListViewModel, String> nameColumn;

    @FXML
    private Button updateCompanyButton;

    @FXML
    private TableColumn<CompanyListViewModel, String> usernameColumn;

    public void onUpdateCompanyButtonClick(){
        CompanyListViewModel selectedItem = companyTable.getSelectionModel().getSelectedItem();
        if (companyService.updateCompanyHonorarium(selectedItem, Double.parseDouble(honorarium.getText()))){
            Alert alert1=new Alert(Alert.AlertType.CONFIRMATION,"Successfully updated company!",ButtonType.OK);
            alert1.show();
            initTable();
        }else{
            Alert alert1=new Alert(Alert.AlertType.ERROR,"Error when updating company!",ButtonType.OK);
            alert1.show();
        }
    }

    public void onDeleteCompanyButtonClick(){
        CompanyListViewModel selectedItem = companyTable.getSelectionModel().getSelectedItem();
        companyService.deleteCompany(selectedItem);
        initTable();
        Alert alert1=new Alert(Alert.AlertType.CONFIRMATION,"Successfully deleted company!",ButtonType.OK);
        alert1.show();
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



    public void initTable(){
        companyTable.getItems().clear();
        ObservableList<CompanyListViewModel> companyListViewModels = companyService.getAllCompany();
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("companyName"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        honorariumColumn.setCellValueFactory(new PropertyValueFactory<>("honorarium"));
        companyTable.setItems(companyListViewModels);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initTable();
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

}
