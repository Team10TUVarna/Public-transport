package bg.tu_varna.sit.oop_project_demo.data.entities;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Table(name = "Trip")
@Entity
public class Trip {
    private static final long serialVersionUID=1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tripId", nullable = false)
    private int tripId;

    @Column(name = "departure", nullable = false)
    private LocalDateTime departure;

    @Column(name = "arrival", nullable = false)
    private LocalDateTime arrival;

    @Column(name = "capacity", nullable = false)
    private int capacity;

    @ManyToOne
    @JoinColumn(name = "tripTypeId", nullable = false)
    private Location tripTypeId;

    @ManyToOne
    @JoinColumn(name = "routeId", nullable = false)
    private Location routeId;

    @ManyToOne
    @JoinColumn(name = "transportTypeId", nullable = false)
    private Location transportTypeId;

    @OneToMany(mappedBy = "tripId")
    Set<Request> requestSet1;

    @Override
    public String toString() {
        return "Trip{" +
                "tripId=" + tripId +
                ", departure=" + departure +
                ", arrival=" + arrival +
                ", capacity=" + capacity +
                ", tripTypeId=" + tripTypeId +
                ", routeId=" + routeId +
                ", transportTypeId=" + transportTypeId +
                ", requestSet1=" + requestSet1 +
                ", ticketSet1=" + ticketSet1 +
                '}';
    }

    public int getTripId() {
        return tripId;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    public LocalDateTime getDeparture() {
        return departure;
    }

    public void setDeparture(LocalDateTime departure) {
        this.departure = departure;
    }

    public LocalDateTime getArrival() {
        return arrival;
    }

    public void setArrival(LocalDateTime arrival) {
        this.arrival = arrival;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Location getTripTypeId() {
        return tripTypeId;
    }

    public void setTripTypeId(Location tripTypeId) {
        this.tripTypeId = tripTypeId;
    }

    public Location getRouteId() {
        return routeId;
    }

    public void setRouteId(Location routeId) {
        this.routeId = routeId;
    }

    public Location getTransportTypeId() {
        return transportTypeId;
    }

    public void setTransportTypeId(Location transportTypeId) {
        this.transportTypeId = transportTypeId;
    }

    public Set<Request> getRequestSet1() {
        return requestSet1;
    }

    public void setRequestSet1(Set<Request> requestSet1) {
        this.requestSet1 = requestSet1;
    }

    public Set<Ticket> getTicketSet1() {
        return ticketSet1;
    }

    public void setTicketSet1(Set<Ticket> ticketSet1) {
        this.ticketSet1 = ticketSet1;
    }

    @OneToMany(mappedBy = "tripId")
    Set<Ticket> ticketSet1;
}
