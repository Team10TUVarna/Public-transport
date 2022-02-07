package bg.tu_varna.sit.oop_project_demo.presentation.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import static bg.tu_varna.sit.oop_project_demo.common.Constants.User.trackUser;
import static bg.tu_varna.sit.oop_project_demo.common.Constants.View.ADD_REQUEST;
import static bg.tu_varna.sit.oop_project_demo.common.Constants.View.HELLO_VIEW;

public class DistributorViewController {

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

    public void onLogoutButtonClick()
    {
        trackUser = 0;
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

}