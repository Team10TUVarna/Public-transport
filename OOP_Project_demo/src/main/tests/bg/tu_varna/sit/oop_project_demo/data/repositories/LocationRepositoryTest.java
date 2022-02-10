package bg.tu_varna.sit.oop_project_demo.data.repositories;

import bg.tu_varna.sit.oop_project_demo.business.services.LocationService;
import bg.tu_varna.sit.oop_project_demo.data.entities.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LocationRepositoryTest {
    LocationRepository locationRepository;
    LocationService locationService;
    Location location;

    @BeforeEach
    void setUp() {
        locationRepository = LocationRepository.getInstance();
        locationService = LocationService.getInstance();
        location = new Location("Temporary location");
    }

    @Test
    void save() {
        List<Location> all=locationRepository.getAll();
        locationRepository.save(location);
        assertNotEquals(all,locationRepository.getAll());
    }

    @Test
    void update() {
        List<Location> all=locationRepository.getAll();
        Location a=locationRepository.getById(7);
        a.setLocationName("Still temporary");
        locationRepository.update(a);
        assertEquals(all,locationRepository.getAll());
    }

    @Test
    void delete() {
        List<Location> all=locationRepository.getAll();
        locationRepository.delete(location);
        assertEquals(all,locationRepository.getAll());
    }


    @Test
    void getAll() {
        List<Location> locations = locationRepository.getAll();
        List<Location> locations1 = locationRepository.getAll();
        assertEquals(locations, locations1);
    }
}