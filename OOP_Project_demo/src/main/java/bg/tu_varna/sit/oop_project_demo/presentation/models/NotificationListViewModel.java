package bg.tu_varna.sit.oop_project_demo.presentation.models;

public class NotificationListViewModel {
    private String message;

    public NotificationListViewModel(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return String.format("%s", message);
    }
}
