package bg.tu_varna.sit.oop_project_demo.presentation.models;

import java.util.Objects;

public class CompanyListViewModel {
    private String username;
    private String password;

    private String companyName;
    private double honorarium;

    public CompanyListViewModel(String username, String password, String companyName, double honorarium) {
        this.username = username;
        this.password = password;
        this.companyName = companyName;
        this.honorarium = honorarium;
    }

    public CompanyListViewModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public CompanyListViewModel() {
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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public double getHonorarium() {
        return honorarium;
    }

    public void setHonorarium(double honorarium) {
        this.honorarium = honorarium;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompanyListViewModel that = (CompanyListViewModel) o;
        return Objects.equals(username, that.username) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }

    @Override
    public String toString() {
        return String.format("%s",username);
    }

}
