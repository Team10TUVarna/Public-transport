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
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import static bg.tu_varna.sit.oop_project_demo.common.Constants.User.loggedDistributorUsername;
import static bg.tu_varna.sit.oop_project_demo.common.Constants.View.*;
import static bg.tu_varna.sit.oop_project_demo.common.Constants.User.trackUser;

public class DistributorLoginController {
    private final DistributorService service= DistributorService.getInstance();
    @FXML
    private Pane adminLogin;

    @FXML
    private Button backButton;

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    public void onCompanyLoginButtonClick(ActionEvent actionEvent) {
        DistributorListViewModel distributorToLogIn=new DistributorListViewModel(username.getText(),password.getText());
        if(service.distributorLogin(distributorToLogIn))
        {
            try {
                trackUser = 3;
                loggedDistributorUsername = distributorToLogIn.getUsername();
                Stage s = (Stage) loginButton.getScene().getWindow();
                s.close();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(DISTRIBUTOR_VIEW));
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
        else{
            Alert alert=new Alert(Alert.AlertType.ERROR,"No such user!", ButtonType.OK);
            alert.show();
            username.setText("");
            password.setText(null);
        }
    }

    public void goBack(ActionEvent actionEvent){
        loadNewPage(HELLO_VIEW);
    }

    public void loadNewPage(String path){
        try {
            Stage s = (Stage) loginButton.getScene().getWindow();
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
