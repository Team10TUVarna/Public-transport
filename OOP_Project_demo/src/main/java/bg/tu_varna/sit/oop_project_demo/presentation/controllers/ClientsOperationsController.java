package bg.tu_varna.sit.oop_project_demo.presentation.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import static bg.tu_varna.sit.oop_project_demo.common.Constants.View.ADD_CASHIER;
import static bg.tu_varna.sit.oop_project_demo.common.Constants.View.ADD_DISTRIBUTOR;

public class ClientsOperationsController {
    @FXML
    private Button addCashierButton;

    @FXML
    private Button addCompanyButton;

    @FXML
    private Button addDistributorButton;

    public void onAddDistributorButtonClick()
    {
        loadNewPage(ADD_DISTRIBUTOR);
    }

    public void onAddCashierButtonClick(){
        loadNewPage(ADD_CASHIER);
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
