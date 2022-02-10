package bg.tu_varna.sit.oop_project_demo.business.services;

import bg.tu_varna.sit.oop_project_demo.data.entities.*;
import bg.tu_varna.sit.oop_project_demo.data.repositories.TicketRepository;
import bg.tu_varna.sit.oop_project_demo.presentation.models.TicketListViewModel;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TicketServiceTest {
    private TicketService ticketService;
    private Ticket ticket;
    private TicketRepository ticketRepository;
    private TicketListViewModel ticketListViewModel;
    private Trip trip;
    private Company company;
    private Cashier cashier;
    private TripType tripType;
    private TransportType transportType;
    private Location locationFrom;
    private Location locationTo;

    @BeforeEach
    void setUp() {
        ticketService = TicketService.getInstance();
        tripType = new TripType("Business trip");
        transportType = new TransportType("Bus");
        locationFrom = new Location("Varna");
        locationTo = new Location("Suvorovo");
        cashier = new Cashier("TestCashier", "test1", "12345", 2200.00);
        company = new Company("TestCompany","test1", "12345", 70000.00);
        trip = new Trip(LocalDate.parse("2022-02-07"), LocalDate.parse("2022-02-07"), 30, tripType, transportType,
                company, locationFrom, locationTo, "15:00", "16:00");
        ticket = new Ticket(1, "Test", LocalDate.parse("2022-02-09"), cashier, trip);
        ticketRepository = TicketRepository.getInstance();
        ticketListViewModel = new TicketListViewModel(29, "NOT SOLD", LocalDate.parse("2000-01-01"), cashier, trip);
    }

    @Test
    void createTicket() {
        assertEquals(0, ticketService.createTicket(ticketListViewModel));
    }

    @Test
    void getAllTickets() {
        ObservableList<TicketListViewModel> ticketListViewModels = ticketService.getAllTickets();
        assertEquals(ticketListViewModels, ticketService.getAllTickets());
    }

    @Test
    void getTicket() {
        Ticket a = ticketService.getTicket(ticketListViewModel);
        assertEquals(a, ticketService.getTicket(ticketListViewModel));
    }

    @Test
    void getAllSoldTickets() {
        ObservableList<TicketListViewModel> ticketListViewModels = ticketService.getAllSoldTickets();
        assertEquals(ticketListViewModels, ticketService.getAllSoldTickets());
    }

    @Test
    void updateTicket() {
        assertTrue(ticketService.updateTicket(ticketListViewModel, "Ivaylo"));
    }
}