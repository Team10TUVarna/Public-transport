package bg.tu_varna.sit.oop_project_demo.presentation.controllers;

import bg.tu_varna.sit.oop_project_demo.business.services.CashierService;
import bg.tu_varna.sit.oop_project_demo.business.services.DistributorService;
import bg.tu_varna.sit.oop_project_demo.presentation.models.CashierListViewModel;
import bg.tu_varna.sit.oop_project_demo.presentation.models.DistributorListViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import static bg.tu_varna.sit.oop_project_demo.common.Constants.User.trackUser;
import static bg.tu_varna.sit.oop_project_demo.common.Constants.View.*;
import static bg.tu_varna.sit.oop_project_demo.common.Constants.View.CASHIER_VIEW;

public class AddDistributorController {
    private final DistributorService service=DistributorService.getInstance();

    @FXML
    private Button addDistributorButton;

    @FXML
    private Button backButton;

    @FXML
    private TextField distributorName;

    @FXML
    private TextField honorarium;

    @FXML
    private Button logout;

    @FXML
    private TextField password;

    @FXML
    private TextField username;

    public void onAddDistributorButtonClick(ActionEvent actionEvent)
    {
        DistributorListViewModel distributor=new DistributorListViewModel(username.getText(),password.getText(), distributorName.getText(), Double.parseDouble(honorarium.getText()));
        int res=service.createDistributor(distributor);
        if(res==0){
            Alert alert=new Alert(Alert.AlertType.ERROR,"Distributor already exists!", ButtonType.CLOSE);
            alert.show();
        }
        else{
            Alert alert=new Alert(Alert.AlertType.CONFIRMATION,"Distributor added", ButtonType.OK);
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


    public void onLogoutButtonClick(ActionEvent event) {
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
            stage.setWidth(615);
            stage.setHeight(440);
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
