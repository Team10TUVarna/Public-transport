package bg.tu_varna.sit.oop_project_demo.data.repositories;

import bg.tu_varna.sit.oop_project_demo.data.access.Connection;
import bg.tu_varna.sit.oop_project_demo.data.entities.TransportType;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class TransportTypeRepository implements DAORepository<TransportType>{
    private static final Logger log = Logger.getLogger(TransportTypeRepository.class);

    public static TransportTypeRepository getInstance() {
        return TransportTypeRepository.TransportTypeRepositoryHolder.INSTANCE;
    }
    private static class TransportTypeRepositoryHolder {

        public static final TransportTypeRepository INSTANCE = new TransportTypeRepository();
    }

    @Override
    public void save(TransportType obj) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.save(obj);
            log.info("TransportType saved successfully");
        } catch (Exception ex) {
            log.error("TransportType save error. " + ex.getMessage());
        } finally {
            transaction.commit();
        }
    }

    @Override
    public void update(TransportType obj) {

    }

    @Override
    public void delete(TransportType obj) {

    }

    @Override
    public Optional<TransportType> getById(int id) {
        return Optional.empty();
    }

    @Override
    public List<TransportType> getAll() {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        List<TransportType> transportTypes = new LinkedList<>();
        try {
            String jpql = "SELECT l FROM TransportType l";
            transportTypes.addAll(session.createQuery(jpql, TransportType.class).getResultList());
            log.info("Got all transportTypes");
        } catch (Exception ex){
            log.error("Get all transportTypes error. " + ex);
        } finally {
            transaction.commit();
        }
        return transportTypes;
    }
}
