package bg.tu_varna.sit.oop_project_demo.data.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Table(name = "Route")
@Entity
public class Route {
    private static final long serialVersionUID=1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "routeId", nullable = false)
    private int routeId;

    @ManyToOne
    @JoinColumn(name = "locationFrom", nullable = false)
    private Location locationFrom;

    @ManyToOne
    @JoinColumn(name = "locationTo", nullable = false)
    private Location locationTo;

    @Override
    public String toString() {
        return "Route{" +
                "routeId=" + routeId +
                ", locationFrom=" + locationFrom +
                ", locationTo=" + locationTo +
                ", tripSet3=" + tripSet3 +
                '}';
    }

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
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

    public Set<Trip> getTripSet3() {
        return tripSet3;
    }

    public void setTripSet3(Set<Trip> tripSet3) {
        this.tripSet3 = tripSet3;
    }

    @OneToMany(mappedBy = "routeId")
    Set<Trip> tripSet3;
}
