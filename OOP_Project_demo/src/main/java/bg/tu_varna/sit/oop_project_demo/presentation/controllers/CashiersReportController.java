package bg.tu_varna.sit.oop_project_demo.presentation.controllers;

import bg.tu_varna.sit.oop_project_demo.business.services.CashierService;
import bg.tu_varna.sit.oop_project_demo.presentation.models.CashierListViewModel;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

import static bg.tu_varna.sit.oop_project_demo.common.Constants.User.*;
import static bg.tu_varna.sit.oop_project_demo.common.Constants.View.*;

public class CashiersReportController implements Initializable {
    CashierService cashierService = CashierService.getInstance();

    @FXML
    private Button backButton;

    @FXML
    private TableColumn<CashierListViewModel, Double> honorariumColumn;

    @FXML
    private Button logout;

    @FXML
    private TableColumn<CashierListViewModel, String> nameColumn;

    @FXML
    private TableColumn<CashierListViewModel, String> usernameColumn;

    @FXML
    private TableView<CashierListViewModel> cashierTable;

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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initTable();
    }

    public void initTable(){
        cashierTable.getItems().clear();
        ObservableList<CashierListViewModel> cashierListViewModels = cashierService.getAllCashier();
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("cashierName"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        honorariumColumn.setCellValueFactory(new PropertyValueFactory<>("honorarium"));
        cashierTable.setItems(cashierListViewModels);
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
