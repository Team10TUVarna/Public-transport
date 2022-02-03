package bg.tu_varna.sit.oop_project_demo.presentation.models;

import java.util.Objects;

public class TripTypeListViewModel {
    String tripTypeName;

    public TripTypeListViewModel(String tripTypeName) {
        this.tripTypeName = tripTypeName;
    }

    public TripTypeListViewModel() {

    }

    public String getTripTypeName() {
        return tripTypeName;
    }

    public void setTripTypeName(String tripTypeName) {
        this.tripTypeName = tripTypeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TripTypeListViewModel that = (TripTypeListViewModel) o;
        return Objects.equals(tripTypeName, that.tripTypeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tripTypeName);
    }

    @Override
    public String toString() {
        return String.format("%s",tripTypeName);
    }
}
