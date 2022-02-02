package bg.tu_varna.sit.oop_project_demo.presentation.models;

import java.util.Objects;

public class CashierListViewModel {
    private String username;
    private String password;

    private String cashierName;
    private double honorarium;

    public CashierListViewModel(String username, String password, String cashierName, double honorarium) {
        this.username = username;
        this.password = password;
        this.cashierName = cashierName;
        this.honorarium = honorarium;
    }

    public CashierListViewModel() {

    }

    public CashierListViewModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CashierListViewModel that = (CashierListViewModel) o;
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

    public String getCashierName() {
        return cashierName;
    }

    public void setCashierName(String cashierName) {
        this.cashierName = cashierName;
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
