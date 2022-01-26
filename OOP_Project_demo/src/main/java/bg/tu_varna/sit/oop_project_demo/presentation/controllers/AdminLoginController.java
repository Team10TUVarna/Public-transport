package bg.tu_varna.sit.oop_project_demo.presentation.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AdminLoginController {
    //private final AdminService service=AdminService.getInstance();
    public Stage s;
    @FXML
    public TextField admin_name;
    @FXML
    public PasswordField admin_pass;
    @FXML
    public Button login;

    public AdminLoginController(Stage stage){
        s=stage;
    }
}
