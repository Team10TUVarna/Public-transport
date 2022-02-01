package bg.tu_varna.sit.oop_project_demo.data.entities;
import javax.persistence.*;

import java.io.Serializable;
import java.util.Set;

@Table(name = "Company")
@Entity
public class Company {
    private static final long serialVersionUID=1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "companyId", nullable = false)
    private int companyId;

    @Column(name = "companyName", nullable = false)
    private String companyName;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "honorarium", nullable = false)
    private double honorarium;

    @OneToMany(mappedBy = "companyId")
    Set<Request> requestSet3;

    @OneToMany(mappedBy = "companyId")
    Set<Trip> tripSet3;

    public Company() {
    }

    public Company(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Company{" +
                "companyId=" + companyId +
                ", companyName='" + companyName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", honorarium=" + honorarium +
                ", requestSet3=" + requestSet3 +
                '}';
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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

    public Set<Request> getRequestSet3() {
        return requestSet3;
    }

    public void setRequestSet3(Set<Request> requestSet3) {
        this.requestSet3 = requestSet3;
    }
}
