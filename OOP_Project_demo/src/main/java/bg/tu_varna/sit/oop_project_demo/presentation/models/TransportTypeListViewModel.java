package bg.tu_varna.sit.oop_project_demo.presentation.models;

import java.util.Objects;

public class TransportTypeListViewModel {
    String transportTypeName;

    public TransportTypeListViewModel() {
    }

    public TransportTypeListViewModel(String transportTypeName) {
        this.transportTypeName = transportTypeName;
    }

    public String getTransportTypeName() {
        return transportTypeName;
    }

    public void setTransportTypeName(String transportTypeName) {
        this.transportTypeName = transportTypeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransportTypeListViewModel that = (TransportTypeListViewModel) o;
        return Objects.equals(transportTypeName, that.transportTypeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transportTypeName);
    }

    @Override
    public String toString() {
        return String.format("%s",transportTypeName);
    }
}
