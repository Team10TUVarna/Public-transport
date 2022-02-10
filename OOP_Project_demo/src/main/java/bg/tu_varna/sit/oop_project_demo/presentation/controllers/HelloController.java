package bg.tu_varna.sit.oop_project_demo.presentation.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static bg.tu_varna.sit.oop_project_demo.common.Constants.View.*;

public class HelloController implements Initializable {

    //public Stage s;

    @FXML
    private Button adminButton;

    @FXML
    private Button cashierButton;

    @FXML
    private Button companyButton;

    @FXML
    private Button distributorButton;

    @FXML
    private ImageView iv1;

    @FXML
    private ImageView iv2;

    @FXML
    private ImageView iv3;

    @FXML
    private ImageView iv4;

    @FXML
    private ImageView iv5;

    @FXML
    private ImageView iv6;

    @FXML
    private Label label1;

    @FXML
    private Label label2;

    @FXML
    private Label label3;



    /*public HelloController(Stage s) {
        this.s = s;
    }*/

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void adminLogin(ActionEvent actionEvent) throws IOException {
/*
        //s.close();
        Stage s = (Stage) adminButton.getScene().getWindow();
        s.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(ADMIN_LOGIN));
        Stage stage = new Stage();
        fxmlLoader.setController(new AdminLoginController());
        Parent root1 = (Parent) fxmlLoader.load();
        stage.setScene(new Scene(root1));
        stage.setResizable(false);
        stage.setWidth(600);
        stage.setHeight(400);
        stage.show();*/
        try
        {
            Stage s = (Stage) adminButton.getScene().getWindow();
            s.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(ADMIN_LOGIN));
            Stage stage = new Stage();
            //fxmlLoader.setController(new AdminLoginController());
            Parent root1 = (Parent) fxmlLoader.load();
            stage.setScene(new Scene(root1));
            stage.setResizable(false);
            stage.setWidth(615);
            stage.setHeight(440);
            stage.show();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cashierLogin(ActionEvent actionEvent) throws IOException {
        try
        {
            Stage s = (Stage) cashierButton.getScene().getWindow();
            s.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(CASHIER_LOGIN));
            Stage stage = new Stage();
            //fxmlLoader.setController(new AdminLoginController());
            Parent root1 = (Parent) fxmlLoader.load();
            stage.setScene(new Scene(root1));
            stage.setResizable(false);
            stage.setWidth(615);
            stage.setHeight(440);
            stage.show();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void distributorLogin(ActionEvent actionEvent) throws IOException {
        try
        {
            Stage s = (Stage) distributorButton.getScene().getWindow();
            s.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(DISTRIBUTOR_LOGIN));
            Stage stage = new Stage();
            //fxmlLoader.setController(new AdminLoginController());
            Parent root1 = (Parent) fxmlLoader.load();
            stage.setScene(new Scene(root1));
            stage.setResizable(false);
            stage.setWidth(615);
            stage.setHeight(440);
            stage.show();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void companyLogin(ActionEvent actionEvent) throws IOException {
        try
        {
            Stage s = (Stage) companyButton.getScene().getWindow();
            s.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(COMPANY_LOGIN));
            Stage stage = new Stage();
            //fxmlLoader.setController(new AdminLoginController());
            Parent root1 = (Parent) fxmlLoader.load();
            stage.setScene(new Scene(root1));
            stage.setResizable(false);
            stage.setWidth(615);
            stage.setHeight(440);
            stage.show();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }




    /*private final NotificationService service = NotificationService.getInstance();
    @FXML
    private Label welcomeText;
    @FXML
    private Button helloButton;
    @FXML
    private ListView<NotificationListViewModel> listView;
    private final HelloModel model;
    public HelloController() {
        this.model = new HelloModel();
    }
    @FXML
    private void initialize (){
        helloButton.setOnMouseClicked(this::handle);
    }
    @Override
    public void handle(MouseEvent mouseEvent) {
        welcomeText.setText(model.getWelcomeMessage());
        ObservableList<NotificationListViewModel> notificationListViewModels = service.getAllNotification();
        listView.setItems(notificationListViewModels);
    }*/
}