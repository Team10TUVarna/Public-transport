package bg.tu_varna.sit.oop_project_demo.data.entities;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Table(name = "Location")
@Entity
public class Location {
    private static final long serialVersionUID=1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "locationId", nullable = false)
    private int locationId;

    @Column(name = "locationName", nullable = false)
    private String locationName;

    @OneToMany(mappedBy = "locationFrom")
    Set<Trip> tripSet1;

    @OneToMany(mappedBy = "locationTo")
    Set<Trip> tripSet2;

    @Override
    public String toString() {
        return "Location{" +
                "locationId=" + locationId +
                ", locationName='" + locationName + '\'' +
                ", tripSet1=" + tripSet1 +
                ", tripSet2=" + tripSet2 +
                '}';
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public Set<Trip> getTripSet1() {
        return tripSet1;
    }

    public void setTripSet1(Set<Trip> tripSet1) {
        this.tripSet1 = tripSet1;
    }

    public Set<Trip> getTripSet2() {
        return tripSet2;
    }

    public void setTripSet2(Set<Trip> tripSet2) {
        this.tripSet2 = tripSet2;
    }
}
