package bg.tu_varna.sit.oop_project_demo.presentation.controllers;

import bg.tu_varna.sit.oop_project_demo.business.services.NotificationService;
import bg.tu_varna.sit.oop_project_demo.presentation.models.HelloModel;
import bg.tu_varna.sit.oop_project_demo.presentation.models.NotificationListViewModel;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

public class HelloController implements EventHandler<MouseEvent> {

    private final NotificationService service = NotificationService.getInstance();

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
    }
}