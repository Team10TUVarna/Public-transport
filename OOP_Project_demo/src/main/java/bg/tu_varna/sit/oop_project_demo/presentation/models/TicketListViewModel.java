package bg.tu_varna.sit.oop_project_demo.presentation.models;

import bg.tu_varna.sit.oop_project_demo.data.entities.Cashier;
import bg.tu_varna.sit.oop_project_demo.data.entities.Trip;
import java.time.LocalDate;
import java.util.Objects;

public class TicketListViewModel {
    private int seatNumber;
    private String customerName;
    private LocalDate purchaseDate;
    private Cashier cashierId;
    private Trip tripId;

    public TicketListViewModel() {
    }

    public TicketListViewModel(int seatNumber, String customerName, LocalDate purchaseDate, Cashier cashierId, Trip tripId) {
        this.seatNumber = seatNumber;
        this.customerName = customerName;
        this.purchaseDate = purchaseDate;
        this.cashierId = cashierId;
        this.tripId = tripId;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Cashier getCashierId() {
        return cashierId;
    }

    public void setCashierId(Cashier cashierId) {
        this.cashierId = cashierId;
    }

    public Trip getTripId() {
        return tripId;
    }

    public void setTripId(Trip tripId) {
        this.tripId = tripId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketListViewModel that = (TicketListViewModel) o;
        return Objects.equals(customerName, that.customerName) && Objects.equals(purchaseDate, that.purchaseDate) && Objects.equals(cashierId, that.cashierId) && Objects.equals(tripId, that.tripId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerName, purchaseDate, cashierId, tripId);
    }

    @Override
    public String toString() {
        return String.format("%d | %s | %s | %s | %s", seatNumber, customerName, purchaseDate, cashierId, tripId);
        /*"TicketListViewModel{" +
                "seatNumber=" + seatNumber +
                ", customerName='" + customerName + '\'' +
                ", purchaseDate=" + purchaseDate +
                ", cashierId=" + cashierId +
                ", tripId=" + tripId +
                '}';*/
    }
}
