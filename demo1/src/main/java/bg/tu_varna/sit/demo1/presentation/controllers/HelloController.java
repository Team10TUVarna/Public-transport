package bg.tu_varna.sit.demo1.presentation.controllers;

import bg.tu_varna.sit.demo1.presentation.models.HelloModel;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class HelloController implements EventHandler<MouseEvent>
{
    @FXML
    private Label welcomeText;

    @FXML
    private Button helloButton;

    private final HelloModel model;

    public HelloController(HelloModel model) {
        this.model = new HelloModel();
    }

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    private void initialize()
    {
        helloButton.setOnMouseClicked(this::handle);
    }
    @Override
    public void handle(MouseEvent mouseEvent) {
        welcomeText.setText(model.getWelcomeMessage());
    }
}