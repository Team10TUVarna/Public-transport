package bg.tu_varna.sit.oop_project_demo.data.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Table(name = "TripType")
@Entity
public class TripType {
    private static final long serialVersionUID=1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tripTypeId", nullable = false)
    private int tripTypeId;

    @Column(name = "tripTypeName", nullable = false)
    private String tripTypeName;

    public TripType(String tripTypeName) {
        this.tripTypeName = tripTypeName;
    }

    public TripType() {
    }

    @Override
    public String toString() {
        return "TripType{" +
                "tripTypeId=" + tripTypeId +
                ", tripTypeName='" + tripTypeName + '\'' +
                ", tripSet1=" + tripSet1 +
                '}';
    }

    public int getTripTypeId() {
        return tripTypeId;
    }

    public void setTripTypeId(int tripTypeId) {
        this.tripTypeId = tripTypeId;
    }

    public String getTripTypeName() {
        return tripTypeName;
    }

    public void setTripTypeName(String tripTypeName) {
        this.tripTypeName = tripTypeName;
    }

    public Set<Trip> getTripSet1() {
        return tripSet1;
    }

    public void setTripSet1(Set<Trip> tripSet1) {
        this.tripSet1 = tripSet1;
    }

    @OneToMany(mappedBy = "tripTypeId")
    Set<Trip> tripSet1;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TripType tripType = (TripType) o;
        return Objects.equals(tripTypeName, tripType.tripTypeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tripTypeName);
    }
}
