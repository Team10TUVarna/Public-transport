package bg.tu_varna.sit.oop_project_demo.business.services;

import bg.tu_varna.sit.oop_project_demo.data.entities.Request;
import bg.tu_varna.sit.oop_project_demo.data.entities.Ticket;
import bg.tu_varna.sit.oop_project_demo.data.repositories.RequestRepository;
import bg.tu_varna.sit.oop_project_demo.data.repositories.TicketRepository;
import bg.tu_varna.sit.oop_project_demo.presentation.models.TicketListViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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
                a.getCashierId(), a.getTripId());
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

    public ObservableList<TicketListViewModel> getAllTickets(){
        List<Ticket> all = repository.getAll();
        return FXCollections.observableList(
                all.stream().map(g -> new TicketListViewModel(
                        g.getSeatNumber(),
                        g.getCustomerName(),
                        g.getPurchaseDate(),
                        g.getCashierId(),
                        g.getTripId()
                )).collect(Collectors.toList())
        );
    }

    public Ticket getTicket(TicketListViewModel ticket){
        List<Ticket> all = repository.getAll();
        Ticket temp = new Ticket(ticket.getSeatNumber(), ticket.getCustomerName(), ticket.getPurchaseDate(), ticket.getCashierId(), ticket.getTripId());
        for (Ticket p:all)
        {
            if (p.equals(temp) && p.getCustomerName().equals("NOT SOLD"))
                return p;
        }
        log.info("No such ticket.");
        return null;
    }

    public boolean updateTicket(TicketListViewModel a, String s){
        Ticket ticket = getTicket(a);
        LocalDate date = LocalDate.now();
        ticket.setCustomerName(s);
        ticket.setCashierId(cashierService.getCashierByName(loggedCashierUsername));
        ticket.setPurchaseDate(date);
        try{
            repository.update(ticket);
            log.info("Ticket updated successfully!");
            return true;
        }catch(Exception e){
            e.printStackTrace();
            log.error("Error updating ticket!");
            return false;
        }
    }
}
