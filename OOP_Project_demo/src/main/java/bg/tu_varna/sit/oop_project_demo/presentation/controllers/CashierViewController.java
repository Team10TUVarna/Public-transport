package bg.tu_varna.sit.oop_project_demo.presentation.controllers;

import bg.tu_varna.sit.oop_project_demo.business.services.CashierService;
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

import static bg.tu_varna.sit.oop_project_demo.common.Constants.User.loggedCashierUsername;
import static bg.tu_varna.sit.oop_project_demo.common.Constants.User.trackUser;
import static bg.tu_varna.sit.oop_project_demo.common.Constants.View.*;

public class CashierViewController implements Initializable {

    CashierService cashierService = CashierService.getInstance();

    @FXML
    private Label cashierName;

    @FXML
    private Button logout;

    @FXML
    private Button sellTicketButton;




    public void onTripsReportButtonClick(){
        loadNewPage(TRIPS_REPORT);
    }

    public void onSellTicketButtonClick(){
        loadNewPage(SELL_TICKET);
    }

    public void onLogoutButtonClick(ActionEvent event){
        trackUser = 0;
        loggedCashierUsername = "";
        loadNewPage(HELLO_VIEW);
    }

    public void loadNewPage(String path){
        try {
            Stage s = (Stage) sellTicketButton.getScene().getWindow();
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
        cashierName.setText("Hello, " + cashierService.getCashierByName(loggedCashierUsername).getCashierName()
                + "      Honorarium: " + String.valueOf(cashierService.getCashierByName(loggedCashierUsername).getHonorarium()));
    }
}
