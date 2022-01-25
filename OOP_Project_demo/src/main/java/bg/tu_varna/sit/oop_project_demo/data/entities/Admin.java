package bg.tu_varna.sit.oop_project_demo.data.entities;

import javax.persistence.*;

import java.io.Serializable;

@Table(name = "Admin")
@Entity
public class Admin implements Serializable{
    private static final long serialVersionUID=1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "adminId", nullable = false)
    private int adminId;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Override
    public String toString() {
        return "Admin{" +
                "adminId=" + adminId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
