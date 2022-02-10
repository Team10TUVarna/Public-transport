package bg.tu_varna.sit.oop_project_demo.presentation.controllers;

import bg.tu_varna.sit.oop_project_demo.business.services.DistributorService;
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

public class DistributorViewController implements Initializable {

    DistributorService distributorService = DistributorService.getInstance();

    @FXML
    private Button addCashierButton;

    @FXML
    private Button addTicketRequestButton;

    @FXML
    private Button checkCashiersButton;

    @FXML
    private Label distributorName;

    @FXML
    private Button logout;

    public void onAddRequestButtonClick(){
        loadNewPage(ADD_REQUEST);
    }

    public void onAddCashierButtonClick()
    {
        loadNewPage(ADD_CASHIER);
    }

    public void onLogoutButtonClick()
    {
        trackUser = 0;
        loggedDistributorUsername = "";
        loadNewPage(HELLO_VIEW);
    }

    public void loadNewPage(String path){
        try {
            Stage s = (Stage) addCashierButton.getScene().getWindow();
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
        distributorName.setText("Hello, " + distributorService.getDistributorByName(loggedDistributorUsername).getDistributorName()
                + "      Honorarium: " + String.valueOf(distributorService.getDistributorByName(loggedDistributorUsername).getHonorarium()));
    }
}