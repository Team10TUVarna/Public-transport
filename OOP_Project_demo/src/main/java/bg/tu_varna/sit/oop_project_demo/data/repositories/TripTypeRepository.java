package bg.tu_varna.sit.oop_project_demo.data.repositories;

import bg.tu_varna.sit.oop_project_demo.data.access.Connection;
import bg.tu_varna.sit.oop_project_demo.data.entities.TripType;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class TripTypeRepository implements DAORepository<TripType>{
    private static final Logger log = Logger.getLogger(TripTypeRepository.class);

    public static TripTypeRepository getInstance() {
        return TripTypeRepository.TripTypeRepositoryHolder.INSTANCE;
    }
    private static class TripTypeRepositoryHolder {

        public static final TripTypeRepository INSTANCE = new TripTypeRepository();
    }

    @Override
    public void save(TripType obj) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.save(obj);
            log.info("TripType saved successfully");
        } catch (Exception ex) {
            log.error("TripType save error. " + ex.getMessage());
        } finally {
            transaction.commit();
        }
    }

    @Override
    public void update(TripType obj) {

    }

    @Override
    public void delete(TripType obj) {

    }

    @Override
    public Optional<TripType> getById(int id) {
        return Optional.empty();
    }

    @Override
    public List<TripType> getAll() {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        List<TripType> tripTypes = new LinkedList<>();
        try {
            String jpql = "SELECT l FROM TripType l";
            tripTypes.addAll(session.createQuery(jpql, TripType.class).getResultList());
            log.info("Got all tripTypes");
        } catch (Exception ex){
            log.error("Get all tripTypes error. " + ex);
        } finally {
            transaction.commit();
        }
        return tripTypes;
    }
}
