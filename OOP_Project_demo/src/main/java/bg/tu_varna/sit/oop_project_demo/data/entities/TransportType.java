package bg.tu_varna.sit.oop_project_demo.data.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Table(name = "TransportType")
@Entity
public class TransportType {
    private static final long serialVersionUID=1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transportTypeId", nullable = false)
    private int transportTypeId;

    @Column(name = "transportTypeName", nullable = false)
    private String transportTypeName;

    @Override
    public String toString() {
        return "TransportType{" +
                "transportTypeId=" + transportTypeId +
                ", transportTypeName='" + transportTypeName + '\'' +
                ", tripSet2=" + tripSet2 +
                '}';
    }

    public int getTransportTypeId() {
        return transportTypeId;
    }

    public void setTransportTypeId(int transportTypeId) {
        this.transportTypeId = transportTypeId;
    }

    public String getTransportTypeName() {
        return transportTypeName;
    }

    public void setTransportTypeName(String transportTypeName) {
        this.transportTypeName = transportTypeName;
    }

    public Set<Trip> getTripSet2() {
        return tripSet2;
    }

    public void setTripSet2(Set<Trip> tripSet2) {
        this.tripSet2 = tripSet2;
    }

    @OneToMany(mappedBy = "transportTypeId")
    Set<Trip> tripSet2;

}
