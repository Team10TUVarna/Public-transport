package bg.tu_varna.sit.oop_project_demo.business.services;

import bg.tu_varna.sit.oop_project_demo.data.entities.Location;
import bg.tu_varna.sit.oop_project_demo.data.repositories.LocationRepository;
import bg.tu_varna.sit.oop_project_demo.presentation.models.LocationListViewModel;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocationServiceTest {
    private LocationService locationService;
    private Location location;
    private LocationRepository locationRepository;
    private LocationListViewModel locationListViewModel;

    @BeforeEach
    void setUp() {
        locationService = LocationService.getInstance();
        location = new Location("Varna");
        locationRepository = LocationRepository.getInstance();
        locationListViewModel = new LocationListViewModel("Berlin");
    }

    @Test
    void createLocation() {
        assertEquals(1, locationService.createLocation(locationListViewModel));
    }

    @Test
    void checkIfLocationExists() {
        assertTrue(locationService.checkIfLocationExists(location));
    }

    @Test
    void getLocationByName() {
        assertEquals(location, locationService.getLocationByName(location.getLocationName()));
    }

    @Test
    void getAllLocations() {
        ObservableList<LocationListViewModel> locationListViewModels = locationService.getAllLocations();
        assertEquals(locationListViewModels, locationService.getAllLocations());
    }
}