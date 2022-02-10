package bg.tu_varna.sit.oop_project_demo.presentation.controllers;

import bg.tu_varna.sit.oop_project_demo.business.services.CashierService;
import bg.tu_varna.sit.oop_project_demo.presentation.models.CashierListViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import static bg.tu_varna.sit.oop_project_demo.common.Constants.User.loggedCashierUsername;
import static bg.tu_varna.sit.oop_project_demo.common.Constants.User.trackUser;
import static bg.tu_varna.sit.oop_project_demo.common.Constants.View.*;

public class CashierLoginController {
    private final CashierService service=CashierService.getInstance();
    @FXML
    private Button backButton;

    @FXML
    private Pane cashierLogin;

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;
    public void onCashierLoginButtonClick(ActionEvent actionEvent) {
        CashierListViewModel cashierToLogIn=new CashierListViewModel(username.getText(),password.getText());
        if(service.cashierLogin(cashierToLogIn))
        {
            try {
                trackUser = 4;
                loggedCashierUsername = cashierToLogIn.getUsername();
                Stage s = (Stage) loginButton.getScene().getWindow();
                s.close();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(CASHIER_VIEW));
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
