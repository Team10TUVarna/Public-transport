package bg.tu_varna.sit.oop_project_demo.presentation.controllers;

import bg.tu_varna.sit.oop_project_demo.business.services.CompanyService;
import bg.tu_varna.sit.oop_project_demo.business.services.DistributorService;
import bg.tu_varna.sit.oop_project_demo.presentation.models.CompanyListViewModel;
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

public class AddCompanyController {
    private final CompanyService service=CompanyService.getInstance();
    @FXML
    private Button addCompanyButton;

    @FXML
    private Button backButton;

    @FXML
    private TextField companyName;

    @FXML
    private TextField honorarium;

    @FXML
    private Button logout;

    @FXML
    private TextField password;

    @FXML
    private TextField username;



    public void onAddCompanyButtonClick(ActionEvent event) {
        CompanyListViewModel company=new CompanyListViewModel(username.getText(),password.getText(), companyName.getText(), Double.parseDouble(honorarium.getText()));
        int res=service.createCompany(company);
        if(res==0){
            /*try {
                s.close();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(CREATE_OPERATOR));
                Stage stage = new Stage();
                //fxmlLoader.setController(new CreateOperatorController(stage));
                Parent root1 = (Parent) fxmlLoader.load();
                stage.setScene(new Scene(root1));
                stage.setResizable(false);
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }*/
            Alert alert=new Alert(Alert.AlertType.ERROR,"Company already exists!", ButtonType.CLOSE);
            /*DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add("Alerts.css");
            dialogPane.getStyleClass().add("Alert");*/
            alert.show();
        }
        else{
            /*switch (userTracking) {
                case 1 -> loadNewPage(ADMIN_VIEW);
                case 2 -> loadNewPage(OPERATOR_VIEW);
                case 3 -> loadNewPage(WAREHOUSEHOST_VIEW);
            }*/
            Alert alert=new Alert(Alert.AlertType.CONFIRMATION,"Company added", ButtonType.OK);
            /*DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add("Alerts.css");
            dialogPane.getStyleClass().add("Alert");*/
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
