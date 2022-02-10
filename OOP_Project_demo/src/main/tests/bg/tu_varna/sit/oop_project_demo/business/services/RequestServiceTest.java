package bg.tu_varna.sit.oop_project_demo.business.services;

import bg.tu_varna.sit.oop_project_demo.data.entities.*;
import bg.tu_varna.sit.oop_project_demo.data.repositories.RequestRepository;
import bg.tu_varna.sit.oop_project_demo.presentation.models.RequestListViewModel;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class RequestServiceTest {
    private RequestService requestService;
    private Request request;
    private RequestRepository requestRepository;
    private RequestListViewModel requestListViewModel;
    private Company company;
    private Trip trip;
    private Distributor distributor;
    private TripType tripType;
    private TransportType transportType;
    private Location locationFrom;
    private Location locationTo;

    @BeforeEach
    void setup(){
        requestService = RequestService.getInstance();
        requestRepository = RequestRepository.getInstance();
        company = new Company("TestCompany","test1", "12345", 70000.00);
        tripType = new TripType("Business trip");
        transportType = new TransportType("Bus");
        locationFrom = new Location("Varna");
        locationTo = new Location("Suvorovo");
        trip = new Trip(LocalDate.parse("2022-02-07"), LocalDate.parse("2022-02-07"), 30, tripType, transportType,
                company, locationFrom, locationTo, "15:00", "16:00");

        distributor = new Distributor("DistributorTest","test1", "12345", 20000.00);
        request = new Request(22, "APPROVED", trip, distributor, company);
        requestListViewModel = new RequestListViewModel(22, "APPROVED", trip, distributor, company);
    }

    @Test
    void createRequest() {
        assertEquals(1, requestService.createRequest(requestListViewModel));
    }

    @Test
    void getRequest() {
        assertEquals(request, requestService.getRequest(requestListViewModel));
        //assertNotEquals(request, requestService.getRequest(requestListViewModel));
    }

    @Test
    void getPendingRequests() {
        ObservableList<RequestListViewModel> requestListViewModels = requestService.getPendingRequests();
        assertEquals(requestListViewModels, requestService.getPendingRequests());
    }

    @Test
    void updateRequestStatus() {
        assertTrue(requestService.updateRequestStatus(requestListViewModel, "pending"));
    }
}