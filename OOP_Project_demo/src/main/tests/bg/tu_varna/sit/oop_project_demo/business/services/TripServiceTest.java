package bg.tu_varna.sit.oop_project_demo.business.services;

import bg.tu_varna.sit.oop_project_demo.data.entities.*;
import bg.tu_varna.sit.oop_project_demo.data.repositories.TripRepository;
import bg.tu_varna.sit.oop_project_demo.presentation.models.TripListViewModel;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TripServiceTest {
    private TripService tripService;
    private Trip trip;
    private TripRepository tripRepository;
    private TripListViewModel tripListViewModel;
    private Company company;
    private TripType tripType;
    private TransportType transportType;
    private Location locationFrom;
    private Location locationTo;

    @BeforeEach
    void setUp() {
        tripService = TripService.getInstance();
        tripRepository = TripRepository.getInstance();
        company = new Company("TestCompany","test1", "12345", 70000.00);
        tripType = new TripType("Business trip");
        transportType = new TransportType("Bus");
        locationFrom = new Location("Varna");
        locationTo = new Location("Suvorovo");
        trip = new Trip(LocalDate.parse("2022-02-07"), LocalDate.parse("2022-02-07"), 30, tripType, transportType,
                company, locationFrom, locationTo, "15:00", "16:00");
        tripListViewModel = new TripListViewModel(LocalDate.parse("2022-02-07"), LocalDate.parse("2022-02-07"), 30, tripType, transportType,
                company, locationFrom, locationTo, "15:00", "16:00");
    }

    @Test
    void getAllTrips() {
        ObservableList<TripListViewModel> tripListViewModels = tripService.getAllTrips();
        assertEquals(tripListViewModels, tripService.getAllTrips());
    }

    @Test
    void createTrip() {
        assertEquals(0, tripService.createTrip(tripListViewModel));
    }

    @Test
    void checkIfTripExists() {
        assertTrue(tripService.checkIfTripExists(trip));
    }

    @Test
    void getTrip() {
        assertEquals(trip, tripService.getTrip(tripListViewModel));
    }
}