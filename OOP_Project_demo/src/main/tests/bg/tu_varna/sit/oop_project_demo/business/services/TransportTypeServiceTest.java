package bg.tu_varna.sit.oop_project_demo.business.services;

import bg.tu_varna.sit.oop_project_demo.data.entities.TransportType;
import bg.tu_varna.sit.oop_project_demo.data.repositories.TransportTypeRepository;
import bg.tu_varna.sit.oop_project_demo.presentation.models.TransportTypeListViewModel;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransportTypeServiceTest {
    private TransportTypeService transportTypeService;
    private TransportType transportType;
    private TransportTypeRepository transportTypeRepository;
    private TransportTypeListViewModel transportTypeListViewModel;

    @BeforeEach
    void setUp() {
        transportTypeService = TransportTypeService.getInstance();
        transportType = new TransportType("Bus");
        transportTypeRepository = TransportTypeRepository.getInstance();
        transportTypeListViewModel = new TransportTypeListViewModel("Car");
    }

    @Test
    void createTransportType() {
        assertEquals(0, transportTypeService.createTransportType(transportTypeListViewModel));
    }

    @Test
    void checkIfTransportTypeExists() {
        assertTrue(transportTypeService.checkIfTransportTypeExists(transportType));
    }

    @Test
    void getTransportTypeByName() {
        assertEquals(transportType, transportTypeService.getTransportTypeByName(transportType.getTransportTypeName()));
    }

    @Test
    void getAllTransportTypes() {
        ObservableList<TransportTypeListViewModel> transportTypeListViewModels = transportTypeService.getAllTransportTypes();
        assertEquals(transportTypeListViewModels, transportTypeService.getAllTransportTypes());
    }
}