package bg.tu_varna.sit.oop_project_demo.data.repositories;

import bg.tu_varna.sit.oop_project_demo.data.access.Connection;
import bg.tu_varna.sit.oop_project_demo.data.entities.Distributor;
import bg.tu_varna.sit.oop_project_demo.data.entities.Location;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class LocationRepository implements DAORepository<Location> {
    private static final Logger log = Logger.getLogger(LocationRepository.class);

    public static LocationRepository getInstance() {
        return LocationRepository.LocationRepositoryHolder.INSTANCE;
    }
    private static class LocationRepositoryHolder {

        public static final LocationRepository INSTANCE = new LocationRepository();
    }

    @Override
    public void save(Location obj) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.save(obj);
            log.info("Location saved successfully");
        } catch (Exception ex) {
            log.error("Location save error. " + ex.getMessage());
        } finally {
            transaction.commit();
        }
    }

    @Override
    public void update(Location obj) {

    }

    @Override
    public void delete(Location obj) {

    }

    @Override
    public Location getById(int id) {
        Location obj = new Location();
        return obj;
    }

    @Override
    public List<Location> getAll() {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        List<Location> locations = new LinkedList<>();
        try {
            String jpql = "SELECT l FROM Location l";
            locations.addAll(session.createQuery(jpql, Location.class).getResultList());
            log.info("Got all locations");
        } catch (Exception ex){
            log.error("Get all locations error. " + ex);
        } finally {
            transaction.commit();
        }
        return locations;
    }


}
