package bg.tu_varna.sit.oop_project_demo.presentation.controllers;

import bg.tu_varna.sit.oop_project_demo.business.services.CashierService;
import bg.tu_varna.sit.oop_project_demo.presentation.models.CashierListViewModel;
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

public class AddCashierController {
    private final CashierService service=CashierService.getInstance();
    @FXML
    private Button addCashierButton;

    @FXML
    private Button backButton;

    @FXML
    private TextField cashierName;

    @FXML
    private TextField honorarium;

    @FXML
    private Button logout;

    @FXML
    private TextField password;

    @FXML
    private TextField username;

    public void onAddButtonClick(ActionEvent actionEvent){
        CashierListViewModel cashier=new CashierListViewModel(username.getText(),password.getText(), cashierName.getText(), Double.parseDouble(honorarium.getText()));
        int res=service.createCashier(cashier);
        if(res==0){
            Alert alert=new Alert(Alert.AlertType.ERROR,"Cashier already exists!", ButtonType.CLOSE);
            alert.show();
        }
        else{
            Alert alert=new Alert(Alert.AlertType.CONFIRMATION,"Cashier added", ButtonType.OK);
            alert.show();
        }
    }

    public void goBack(){
        switch (trackUser){
            case 1: loadNewPage(ADMIN_VIEW);
            case 2: loadNewPage(COMPANY_VIEW);
            case 3: loadNewPage(DISTRIBUTOR_VIEW);
            case 4: loadNewPage(CASHIER_VIEW);
        }
    }

    public void loadNewPage(String path){
        try {
            Stage s = (Stage) backButton.getScene().getWindow();
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
