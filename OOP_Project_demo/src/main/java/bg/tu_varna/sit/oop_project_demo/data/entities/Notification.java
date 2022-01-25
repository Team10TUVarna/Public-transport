package bg.tu_varna.sit.oop_project_demo.data.entities;

import javax.persistence.*;

import java.io.Serializable;

@Table(name = "Notification")
@Entity
public class Notification implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notificationId", nullable = false)
    private int notificationId;

    @Column(name = "message", nullable = false)
    private String message;

    @Override
    public String toString() {
        return "Notification{" +
                "notificationId=" + notificationId +
                ", message='" + message + '\'' +
                '}';
    }

    public int getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
