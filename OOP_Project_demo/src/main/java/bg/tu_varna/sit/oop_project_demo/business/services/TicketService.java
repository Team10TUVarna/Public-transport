package bg.tu_varna.sit.oop_project_demo.business.services;

import bg.tu_varna.sit.oop_project_demo.data.entities.Ticket;
import bg.tu_varna.sit.oop_project_demo.data.repositories.RequestRepository;
import bg.tu_varna.sit.oop_project_demo.data.repositories.TicketRepository;
import bg.tu_varna.sit.oop_project_demo.presentation.models.TicketListViewModel;
import org.apache.log4j.Logger;

import static bg.tu_varna.sit.oop_project_demo.common.Constants.User.loggedCashierUsername;

public class TicketService {
    private static final Logger log=Logger.getLogger(TicketService.class);
    private final TicketRepository repository= TicketRepository.getInstance();

    public static TicketService getInstance() {
        return TicketService.TicketServiceHolder.INSTANCE;
    }

    TicketService ticketService = TicketService.getInstance();
    CashierService cashierService = CashierService.getInstance();
    TripService tripService = TripService.getInstance();



    private static class TicketServiceHolder {
        public static final TicketService INSTANCE = new TicketService();
    }

    public int createTicket(TicketListViewModel a){
        Ticket ticket = new Ticket(a.getSeatNumber(), a.getCustomerName(), a.getPurchaseDate(),
                cashierService.getCashierByName(loggedCashierUsername), a.getTripId());
        try{
            repository.save(ticket);
            log.info("Ticket created!");
            return 0;
        }
        catch (Exception e) {
            e.printStackTrace();
            log.error("Create ticket error!");
        }
        return 1;

    }
}
