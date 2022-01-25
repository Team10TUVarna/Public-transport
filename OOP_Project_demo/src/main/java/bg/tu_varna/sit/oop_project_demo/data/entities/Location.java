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
    Set<Route> routeSet1;

    @OneToMany(mappedBy = "locationTo")
    Set<Route> routeSet2;

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

    public Set<Route> getRouteSet1() {
        return routeSet1;
    }

    public void setRouteSet1(Set<Route> routeSet1) {
        this.routeSet1 = routeSet1;
    }

    public Set<Route> getRouteSet2() {
        return routeSet2;
    }

    public void setRouteSet2(Set<Route> routeSet2) {
        this.routeSet2 = routeSet2;
    }

    @Override
    public String toString() {
        return "Location{" +
                "locationId=" + locationId +
                ", locationName='" + locationName + '\'' +
                ", routeSet1=" + routeSet1 +
                ", routeSet2=" + routeSet2 +
                '}';
    }
}
