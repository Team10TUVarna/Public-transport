package bg.tu_varna.sit.oop_project_demo.business.services;
import bg.tu_varna.sit.oop_project_demo.data.entities.TripType;
import bg.tu_varna.sit.oop_project_demo.data.repositories.TripTypeRepository;
import bg.tu_varna.sit.oop_project_demo.presentation.models.TripTypeListViewModel;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TripTypeServiceTest {
    private TripTypeService tripTypeService;
    private TripType tripType;
    private TripTypeRepository tripTypeRepository;
    private TripTypeListViewModel tripTypeListViewModel;

    @BeforeEach
    void setUp() {
        tripTypeService = TripTypeService.getInstance();
        tripType = new TripType("Business trip");
        tripTypeRepository = TripTypeRepository.getInstance();
        tripTypeListViewModel = new TripTypeListViewModel("Business trip");
    }

    @Test
    void createTripType() {
        assertEquals(0, tripTypeService.createTripType(tripTypeListViewModel));
    }

    @Test
    void checkIfTripTypeExists() {
        assertTrue(tripTypeService.checkIfTripTypeExists(tripType));
    }

    @Test
    void getTripTypeByName() {
        assertEquals(tripType, tripTypeService.getTripTypeByName(tripType.getTripTypeName()));
    }

    @Test
    void getAllTripTypes() {
        ObservableList<TripTypeListViewModel> tripTypeListViewModels = tripTypeService.getAllTripTypes();
        assertEquals(tripTypeListViewModels, tripTypeService.getAllTripTypes());
    }
}