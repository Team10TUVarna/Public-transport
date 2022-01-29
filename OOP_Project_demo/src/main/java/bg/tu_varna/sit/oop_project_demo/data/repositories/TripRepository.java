package bg.tu_varna.sit.oop_project_demo.data.repositories;

import bg.tu_varna.sit.oop_project_demo.data.access.Connection;
import bg.tu_varna.sit.oop_project_demo.data.entities.TransportType;
import bg.tu_varna.sit.oop_project_demo.data.entities.Trip;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class TripRepository implements DAORepository<Trip>{
    private static final Logger log = Logger.getLogger(TripRepository.class);

    public static TripRepository getInstance() {
        return TripRepository.TripRepositoryHolder.INSTANCE;
    }
    private static class TripRepositoryHolder {

        public static final TripRepository INSTANCE = new TripRepository();
    }

    @Override
    public void save(Trip obj) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.save(obj);
            log.info("Trip saved successfully");
        } catch (Exception ex) {
            log.error("Trip save error. " + ex.getMessage());
        } finally {
            transaction.commit();
        }
    }

    @Override
    public void update(Trip obj) {

    }

    @Override
    public void delete(Trip obj) {

    }

    @Override
    public Trip getById(int id) {
        Trip obj = new Trip();
        return obj;
    }

    @Override
    public List<Trip> getAll() {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        List<Trip> trips = new LinkedList<>();
        try {
            String jpql = "SELECT l FROM Trip l";
            trips.addAll(session.createQuery(jpql, Trip.class).getResultList());
            log.info("Got all trips");
        } catch (Exception ex){
            log.error("Get all trips error. " + ex);
        } finally {
            transaction.commit();
        }
        return trips;
    }
}
