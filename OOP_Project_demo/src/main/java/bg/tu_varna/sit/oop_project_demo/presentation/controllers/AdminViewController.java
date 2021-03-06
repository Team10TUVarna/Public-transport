package bg.tu_varna.sit.oop_project_demo.presentation.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

import static bg.tu_varna.sit.oop_project_demo.common.Constants.User.loggedAdminUsername;
import static bg.tu_varna.sit.oop_project_demo.common.Constants.User.trackUser;
import static bg.tu_varna.sit.oop_project_demo.common.Constants.View.*;

public class AdminViewController implements Initializable {
    public AdminViewController() {
    }

    @FXML
    private Button clientsOperationsButton;

    @FXML
    private Button addLocationButton;

    @FXML
    private Label adminName;

    @FXML
    private Button logout;

    public void onClientsOperationsButtonClick(ActionEvent event) {
        try {
            trackUser = 1;
            Stage s = (Stage) clientsOperationsButton.getScene().getWindow();
            s.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(ADD_CLIENTS));
            Stage stage = new Stage();
            //fxmlLoader.setController(new AdminViewController());
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
    public void onAddLocationButtonClick(){
        loadNewPage(ADD_LOCATION);
    }
    public void onAddTripTypeButtonClick(){
        loadNewPage(ADD_TRIPTYPE);
    }
    public void onTripsReportButtonClick(){
        loadNewPage(TRIPS_REPORT);
    }
    public void onAddTripButtonClick(){
        loadNewPage(ADD_TRIP);
    }
    public void onAddTransportTypeButtonClick(){
        loadNewPage(ADD_TRANSPORTTYPE);
    }
    public void onSoldTicketsReportButtonClick(){
        loadNewPage(TICKETS_REPORT);
    }
    public void onCashiersReportButtonClick(){
        loadNewPage(CASHIERS_REPORT);
    }
    public void onDistributorsReportButtonClick(){
        loadNewPage(DISTRIBUTORS_REPORT);
    }


    public void onLogoutButtonClick(ActionEvent event){
        trackUser = 0;
        loadNewPage(HELLO_VIEW);
    }

    public void loadNewPage(String path){
        try {
            Stage s = (Stage) clientsOperationsButton.getScene().getWindow();
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
        adminName.setText("Hello, " + loggedAdminUsername);
    }
}
