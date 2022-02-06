package bg.tu_varna.sit.oop_project_demo.data.entities;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
import java.time.LocalDate;
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
    private LocalDate departure;

    @Column(name = "arrival", nullable = false)
    private LocalDate arrival;

    @Column(name = "capacity", nullable = false)
    private int capacity;

    @ManyToOne
    @JoinColumn(name = "tripTypeId", nullable = false)
    private TripType tripTypeId;

    @ManyToOne
    @JoinColumn(name = "transportTypeId", nullable = false)
    private TransportType transportTypeId;

    @ManyToOne
    @JoinColumn(name = "companyId", nullable = false)
    private Company companyId;

    @ManyToOne
    @JoinColumn(name = "locationFromId", nullable = false)
    private Location locationFrom;

    @ManyToOne
    @JoinColumn(name = "locationToId", nullable = false)
    private Location locationTo;

    @Column(name = "timeOfDeparture", nullable = false)
    private String timeOfDeparture;

    @Column(name = "timeOfArrival", nullable = false)
    private String timeOfArrival;

    @OneToMany(mappedBy = "tripId")
    Set<Request> requestSet1;

    @OneToMany(mappedBy = "tripId")
    Set<Ticket> ticketSet1;

    public int getTripId() {
        return tripId;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public TripType getTripTypeId() {
        return tripTypeId;
    }

    public void setTripTypeId(TripType tripTypeId) {
        this.tripTypeId = tripTypeId;
    }

    public TransportType getTransportTypeId() {
        return transportTypeId;
    }

    public void setTransportTypeId(TransportType transportTypeId) {
        this.transportTypeId = transportTypeId;
    }

    public Company getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Company companyId) {
        this.companyId = companyId;
    }

    public Location getLocationFrom() {
        return locationFrom;
    }

    public void setLocationFrom(Location locationFrom) {
        this.locationFrom = locationFrom;
    }

    public Location getLocationTo() {
        return locationTo;
    }

    public void setLocationTo(Location locationTo) {
        this.locationTo = locationTo;
    }

    public LocalDate getDeparture() {
        return departure;
    }

    public void setDeparture(LocalDate departure) {
        this.departure = departure;
    }

    public LocalDate getArrival() {
        return arrival;
    }

    public void setArrival(LocalDate arrival) {
        this.arrival = arrival;
    }

    public String getTimeOfDeparture() {
        return timeOfDeparture;
    }

    public void setTimeOfDeparture(String timeOfDeparture) {
        this.timeOfDeparture = timeOfDeparture;
    }

    public String getTimeOfArrival() {
        return timeOfArrival;
    }

    public void setTimeOfArrival(String timeOfArrival) {
        this.timeOfArrival = timeOfArrival;
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

    @Override
    public String toString() {
        return "Trip{" +
                "tripId=" + tripId +
                ", departure=" + departure +
                ", arrival=" + arrival +
                ", capacity=" + capacity +
                ", tripTypeId=" + tripTypeId +
                ", transportTypeId=" + transportTypeId +
                ", companyId=" + companyId +
                ", locationFrom=" + locationFrom +
                ", locationTo=" + locationTo +
                ", requestSet1=" + requestSet1 +
                ", ticketSet1=" + ticketSet1 +
                '}';
    }
}
