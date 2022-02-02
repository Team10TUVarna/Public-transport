package bg.tu_varna.sit.oop_project_demo.presentation.models;

import java.util.Objects;

public class DistributorListViewModel {
    private String username;
    private String password;
    private String distributorName;
    private double honorarium;

    public DistributorListViewModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public DistributorListViewModel() { }

    public DistributorListViewModel(String username, String password, String distributorName, double honorarium) {
        this.username = username;
        this.password = password;
        this.distributorName = distributorName;
        this.honorarium = honorarium;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DistributorListViewModel that = (DistributorListViewModel) o;
        return Objects.equals(username, that.username) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
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

    public String getDistributorName() {
        return distributorName;
    }

    public void setDistributorName(String distributorName) {
        this.distributorName = distributorName;
    }

    public double getHonorarium() {
        return honorarium;
    }

    public void setHonorarium(double honorarium) {
        this.honorarium = honorarium;
    }

    @Override
    public String toString() {
        return String.format("%s",username);
    }
}
