package bg.tu_varna.sit.oop_project_demo.presentation.controllers;

import bg.tu_varna.sit.oop_project_demo.business.services.AdminService;
import bg.tu_varna.sit.oop_project_demo.presentation.models.AdminListViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import static bg.tu_varna.sit.oop_project_demo.common.Constants.User.*;
import static bg.tu_varna.sit.oop_project_demo.common.Constants.View.ADMIN_VIEW;
import static bg.tu_varna.sit.oop_project_demo.common.Constants.View.HELLO_VIEW;

public class AdminLoginController {
    private final AdminService service=AdminService.getInstance();
    @FXML
    private Pane adminLogin;

    @FXML
    private Button loginButton;

    @FXML
    private Button backButton;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    public AdminLoginController() {
    }

    public AdminLoginController(Stage stage) {
    }

    public void onAdminLoginButtonClick(ActionEvent actionEvent) {
        AdminListViewModel adminToLogIn=new AdminListViewModel(username.getText(),password.getText());
        if(service.adminLogin(adminToLogIn))
        {
            try {
                trackUser = 1;
                loggedAdminUsername = adminToLogIn.getUsername();
                loggedDistributorUsername = "ADMIN";
                loggedCashierUsername = "ADMIN";
                loggedCompanyUsername = "ADMIN";
                Stage s = (Stage) loginButton.getScene().getWindow();
                s.close();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(ADMIN_VIEW));
                Stage stage = new Stage();
                //fxmlLoader.setController(new AdminViewController());
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
            /*DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add("Alerts.css");
            dialogPane.getStyleClass().add("Alert");
            alert.show();*/
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
