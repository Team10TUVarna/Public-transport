package bg.tu_varna.sit.oop_project_demo.data.entities;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
import java.time.LocalDate;

@Table(name = "Ticket")
@Entity
public class Ticket {
    private static final long serialVersionUID=1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticketId", nullable = false)
    private int ticketId;

    @Column(name = "seatNumber", nullable = false)
    private int seatNumber;

    @Column(name = "customerName", nullable = false)
    private String customerName;

    @Column(name = "purchaseDate", nullable = false)
    private LocalDate purchaseDate;

    @ManyToOne
    @JoinColumn(name = "cashierId", nullable = false)
    private Location cashierId;

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId=" + ticketId +
                ", seatNumber=" + seatNumber +
                ", customerName='" + customerName + '\'' +
                ", purchaseDate=" + purchaseDate +
                ", cashierId=" + cashierId +
                ", tripId=" + tripId +
                '}';
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
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

    public Location getCashierId() {
        return cashierId;
    }

    public void setCashierId(Location cashierId) {
        this.cashierId = cashierId;
    }

    public Location getTripId() {
        return tripId;
    }

    public void setTripId(Location tripId) {
        this.tripId = tripId;
    }

    @ManyToOne
    @JoinColumn(name = "tripId", nullable = false)
    private Location tripId;



}
