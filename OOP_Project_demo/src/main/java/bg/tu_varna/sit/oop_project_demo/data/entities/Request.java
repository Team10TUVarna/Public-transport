package bg.tu_varna.sit.oop_project_demo.data.entities;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Table(name = "Request")
@Entity
public class Request {
    private static final long serialVersionUID = 1L;
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
    private Trip tripId;

    @ManyToOne
    @JoinColumn(name = "distributorId", nullable = false)
    private Distributor distributorId;

    @ManyToOne
    @JoinColumn(name = "companyId", nullable = false)
    private Company companyId;

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

    public Trip getTripId() {
        return tripId;
    }

    public void setTripId(Trip tripId) {
        this.tripId = tripId;
    }

    public Distributor getDistributorId() {
        return distributorId;
    }

    public void setDistributorId(Distributor distributorId) {
        this.distributorId = distributorId;
    }

    public Company getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Company companyId) {
        this.companyId = companyId;
    }

    public Request() {
    }

    public Request(int ticketCount, String status, Trip tripId, Distributor distributorId, Company companyId) {
        this.ticketCount = ticketCount;
        this.status = status;
        this.tripId = tripId;
        this.distributorId = distributorId;
        this.companyId = companyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return ticketCount == request.ticketCount && Objects.equals(status, request.status) && Objects.equals(tripId, request.tripId) && Objects.equals(distributorId, request.distributorId) && Objects.equals(companyId, request.companyId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticketCount, status, tripId, distributorId, companyId);
    }
}


