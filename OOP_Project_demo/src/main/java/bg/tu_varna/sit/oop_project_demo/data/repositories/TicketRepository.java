package bg.tu_varna.sit.oop_project_demo.data.repositories;

import bg.tu_varna.sit.oop_project_demo.data.access.Connection;
import bg.tu_varna.sit.oop_project_demo.data.entities.Route;
import bg.tu_varna.sit.oop_project_demo.data.entities.Ticket;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class TicketRepository implements DAORepository<Ticket>{
    private static final Logger log = Logger.getLogger(TicketRepository.class);

    public static TicketRepository getInstance() {
        return TicketRepository.TicketRepositoryHolder.INSTANCE;
    }
    private static class TicketRepositoryHolder {

        public static final TicketRepository INSTANCE = new TicketRepository();
    }

    @Override
    public void save(Ticket obj) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.save(obj);
            log.info("Ticket saved successfully");
        } catch (Exception ex) {
            log.error("Ticket save error. " + ex.getMessage());
        } finally {
            transaction.commit();
        }
    }

    @Override
    public void update(Ticket obj) {

    }

    @Override
    public void delete(Ticket obj) {

    }

    @Override
    public Ticket getById(int id) {
        Ticket obj = new Ticket();
        return obj;
    }

    @Override
    public List<Ticket> getAll() {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        List<Ticket> tickets = new LinkedList<>();
        try {
            String jpql = "SELECT r FROM Ticket r";
            tickets.addAll(session.createQuery(jpql, Ticket.class).getResultList());
            log.info("Got all tickets");
        } catch (Exception ex){
            log.error("Get all tickets error. " + ex);
        } finally {
            transaction.commit();
        }
        return tickets;    }
}
