package bg.tu_varna.sit.oop_project_demo.data.entities;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Table(name = "Request")
@Entity
public class Request {
    private static final long serialVersionUID=1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "requestId", nullable = false)
    private int requestId;

    @Column(name = "ticketCount", nullable = false)
    private int ticketCount;

    @Column(name = "status", nullable = false)
    private String status;

    @ManyToOne
    @JoinColumn(name = "tripId", nullable = false)
    private Location tripId;

    @ManyToOne
    @JoinColumn(name = "distributorId", nullable = false)
    private Location distributorId;

    @ManyToOne
    @JoinColumn(name = "companyId", nullable = false)
    private Location companyId;

    @Override
    public String toString() {
        return "Request{" +
                "requestId=" + requestId +
                ", ticketCount=" + ticketCount +
                ", status='" + status + '\'' +
                ", tripId=" + tripId +
                ", distributorId=" + distributorId +
                ", companyId=" + companyId +
                '}';
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public int getTicketCount() {
        return ticketCount;
    }

    public void setTicketCount(int ticketCount) {
        this.ticketCount = ticketCount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Location getTripId() {
        return tripId;
    }

    public void setTripId(Location tripId) {
        this.tripId = tripId;
    }

    public Location getDistributorId() {
        return distributorId;
    }

    public void setDistributorId(Location distributorId) {
        this.distributorId = distributorId;
    }

    public Location getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Location companyId) {
        this.companyId = companyId;
    }
}
