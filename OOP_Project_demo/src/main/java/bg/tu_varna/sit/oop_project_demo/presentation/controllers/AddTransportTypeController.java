package bg.tu_varna.sit.oop_project_demo.presentation.controllers;

import bg.tu_varna.sit.oop_project_demo.business.services.TransportTypeService;
import bg.tu_varna.sit.oop_project_demo.presentation.models.TransportTypeListViewModel;
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

public class AddTransportTypeController {
    private final TransportTypeService service= TransportTypeService.getInstance();
    @FXML
    private Button addTransportTypeButton;

    @FXML
    private Button backButton;

    @FXML
    private Button logout;

    @FXML
    private TextField transportType;



    public void onAddTransportTypeButtonClick(ActionEvent event) {
        TransportTypeListViewModel transportTypeListViewModel=new TransportTypeListViewModel(transportType.getText());
        int res=service.createTransportType(transportTypeListViewModel);
        if(res==0){
            Alert alert=new Alert(Alert.AlertType.ERROR,"Transport type already exists!", ButtonType.CLOSE);
            alert.show();
        }
        else{
            Alert alert=new Alert(Alert.AlertType.CONFIRMATION,"Transport type added", ButtonType.OK);
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
            Stage s = (Stage) addTransportTypeButton.getScene().getWindow();
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
