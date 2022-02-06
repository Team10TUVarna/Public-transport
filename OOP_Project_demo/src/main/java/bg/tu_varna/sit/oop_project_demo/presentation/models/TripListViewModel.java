package bg.tu_varna.sit.oop_project_demo.presentation.models;

import bg.tu_varna.sit.oop_project_demo.data.entities.Company;
import bg.tu_varna.sit.oop_project_demo.data.entities.Location;
import bg.tu_varna.sit.oop_project_demo.data.entities.TransportType;
import bg.tu_varna.sit.oop_project_demo.data.entities.TripType;
import java.time.LocalDate;
import java.util.Objects;

public class TripListViewModel {
    private LocalDate departure;

    private LocalDate arrival;

    private int capacity;

    private TripType tripTypeId;

    private TransportType transportTypeId;

    private Company companyId;

    private Location locationFrom;

    private Location locationTo;

    private String timeOfDeparture;

    private String timeOfArrival;

    public TripListViewModel() {
    }

    public TripListViewModel(LocalDate departure, LocalDate arrival, int capacity, TripType tripTypeId, TransportType transportTypeId, Company companyId,
                             Location locationFrom, Location locationTo, String timeOfDeparture, String timeOfArrival) {
        this.departure = departure;
        this.arrival = arrival;
        this.capacity = capacity;
        this.tripTypeId = tripTypeId;
        this.transportTypeId = transportTypeId;
        this.companyId = companyId;
        this.locationFrom = locationFrom;
        this.locationTo = locationTo;
        this.timeOfDeparture = timeOfDeparture;
        this.timeOfArrival = timeOfArrival;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TripListViewModel that = (TripListViewModel) o;
        return capacity == that.capacity && Objects.equals(departure, that.departure) && Objects.equals(arrival, that.arrival) && Objects.equals(tripTypeId, that.tripTypeId)
                && Objects.equals(transportTypeId, that.transportTypeId) && Objects.equals(companyId, that.companyId) && Objects.equals(locationFrom, that.locationFrom)
                && Objects.equals(locationTo, that.locationTo) && Objects.equals(timeOfDeparture, that.timeOfDeparture) && Objects.equals(timeOfArrival, that.timeOfArrival);
    }

    @Override
    public int hashCode() {
        return Objects.hash(departure, arrival, capacity, tripTypeId, transportTypeId, companyId, locationFrom, locationTo, timeOfDeparture, timeOfArrival);
    }

    @Override
    public String toString() {
        return String.format("%s | %s | %d | %s | %s | %s | %s | %s | %s | %s ",departure, arrival, capacity, tripTypeId.getTripTypeName(), transportTypeId.getTransportTypeName(),
                companyId.getCompanyName(), locationFrom.getLocationName(), locationTo.getLocationName(), timeOfDeparture, timeOfArrival);
                /*"TripListViewModel{" +
                "departure=" + departure +
                ", arrival=" + arrival +
                ", capacity=" + capacity +
                ", tripTypeId=" + tripTypeId +
                ", transportTypeId=" + transportTypeId +
                ", companyId=" + companyId +
                ", locationFrom=" + locationFrom +
                ", locationTo=" + locationTo +
                ", timeOfDeparture='" + timeOfDeparture + '\'' +
                ", timeOfArrival='" + timeOfArrival + '\'' +
                '}';*/
    }
}
