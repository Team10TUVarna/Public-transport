package bg.tu_varna.sit.oop_project_demo.presentation.controllers;

import bg.tu_varna.sit.oop_project_demo.business.services.CompanyService;
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

import static bg.tu_varna.sit.oop_project_demo.common.Constants.User.*;
import static bg.tu_varna.sit.oop_project_demo.common.Constants.User.loggedCashierUsername;
import static bg.tu_varna.sit.oop_project_demo.common.Constants.View.*;

public class CompanyViewController implements Initializable {

    CompanyService companyService = CompanyService.getInstance();

    @FXML
    private Button addTripButton;

    @FXML
    private Label companyName;

    @FXML
    private Button logout;

    @FXML
    private Button r;

    @FXML
    private Button requestButton;

    public void onAddLocationButtonClick(){
        loadNewPage(ADD_LOCATION);
    }
    public void onAddTripTypeButtonClick(){
        loadNewPage(ADD_TRIPTYPE);
    }
    public void onAddTripButtonClick() {
        loadNewPage(ADD_TRIP);
    }
    public void onRequestButtonClick(){
        loadNewPage(ALL_REQUESTS);
    }
    public void onTripsReportButtonClick(){
        loadNewPage(TRIPS_REPORT);
    }
    public void onSoldTicketsReportButtonClick(){
        loadNewPage(TICKETS_REPORT);
    }


    public void onLogoutButtonClick(ActionEvent event) {
        trackUser = 0;
        loggedCompanyUsername = "";
        loadNewPage(HELLO_VIEW);
    }

    public void loadNewPage(String path){
        try {
            Stage s = (Stage) logout.getScene().getWindow();
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
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        companyName.setText("Hello, " + companyService.getCompanyByName(loggedCompanyUsername).getCompanyName()
                + "      Honorarium: " + String.valueOf(companyService.getCompanyByName(loggedCompanyUsername).getHonorarium()));
    }

}
