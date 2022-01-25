package bg.tu_varna.sit.oop_project_demo.business.services;

import bg.tu_varna.sit.oop_project_demo.data.entities.Notification;
import bg.tu_varna.sit.oop_project_demo.data.repositories.NotificationRepository;
import bg.tu_varna.sit.oop_project_demo.presentation.models.NotificationListViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.stream.Collectors;

public class NotificationService {
    private final NotificationRepository repository = NotificationRepository.getInstance();

    public static NotificationService getInstance(){
        return NotificationServiceHolder.INSTANCE;
    }

    private static class NotificationServiceHolder {
        public static final NotificationService INSTANCE = new NotificationService();
    }

    public ObservableList<NotificationListViewModel> getAllNotification(){
        List<Notification> notifications = repository.getAll();


        return FXCollections.observableList(
                notifications.stream().map(t -> new NotificationListViewModel(
                    t.getMessage()
                )).collect(Collectors.toList())
        );
    }
}
