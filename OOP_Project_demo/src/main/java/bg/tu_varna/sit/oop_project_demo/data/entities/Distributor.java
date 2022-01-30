package bg.tu_varna.sit.oop_project_demo.data.entities;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Set;

@Table(name = "Distributor")
@Entity
public class Distributor {
    private static final long serialVersionUID=1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "distributorId", nullable = false)
    private int distributorId;

    @Column(name = "distributorName", nullable = false)
    private String distributorName;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "honorarium", nullable = false)
    private double honorarium;

    @OneToMany(mappedBy = "distributorId")
    Set<Request> requestSet2;

    public Distributor() {
    }

    public Distributor(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Distributor{" +
                "distributorId=" + distributorId +
                ", distributorName='" + distributorName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", honorarium=" + honorarium +
                ", requestSet2=" + requestSet2 +
                '}';
    }

    public int getDistributorId() {
        return distributorId;
    }

    public void setDistributorId(int distributorId) {
        this.distributorId = distributorId;
    }

    public String getDistributorName() {
        return distributorName;
    }

    public void setDistributorName(String distributorName) {
        this.distributorName = distributorName;
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

    public double getHonorarium() {
        return honorarium;
    }

    public void setHonorarium(double honorarium) {
        this.honorarium = honorarium;
    }

    public Set<Request> getRequestSet2() {
        return requestSet2;
    }

    public void setRequestSet2(Set<Request> requestSet2) {
        this.requestSet2 = requestSet2;
    }
}
