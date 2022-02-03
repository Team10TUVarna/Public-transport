package bg.tu_varna.sit.oop_project_demo.presentation.models;

import java.util.Objects;

public class LocationListViewModel {
    private String locationName;

    public LocationListViewModel() {
    }

    public LocationListViewModel(String locationName) {
        this.locationName = locationName;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocationListViewModel that = (LocationListViewModel) o;
        return Objects.equals(locationName, that.locationName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(locationName);
    }

    @Override
    public String toString() {
        return String.format("%s",locationName);
    }
}
