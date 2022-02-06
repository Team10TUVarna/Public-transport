package bg.tu_varna.sit.oop_project_demo.presentation.controllers;

import bg.tu_varna.sit.oop_project_demo.business.services.CompanyService;
import bg.tu_varna.sit.oop_project_demo.presentation.models.CompanyListViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import static bg.tu_varna.sit.oop_project_demo.common.Constants.User.loggedCompanyUsername;
import static bg.tu_varna.sit.oop_project_demo.common.Constants.User.trackUser;
import static bg.tu_varna.sit.oop_project_demo.common.Constants.View.*;

public class CompanyLoginController {
    private final CompanyService service=CompanyService.getInstance();

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
        CompanyListViewModel companyToLogIn=new CompanyListViewModel(username.getText(),password.getText());
        if(service.companyLogin(companyToLogIn))
        {
            try {
                trackUser = 2;
                loggedCompanyUsername = companyToLogIn.getUsername();
                System.out.println("Logged company username: " + loggedCompanyUsername);
                Stage s = (Stage) loginButton.getScene().getWindow();
                s.close();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(COMPANY_VIEW));
                Stage stage = new Stage();
                Parent root1 = (Parent) fxmlLoader.load();
                stage.setScene(new Scene(root1));
                stage.setResizable(false);
                stage.setWidth(600);
                stage.setHeight(400);
                stage.show();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        else{
            Alert alert=new Alert(Alert.AlertType.INFORMATION,"No such user!", ButtonType.OK);
            alert.show();
            username.setText("");
            password.setText("");
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
