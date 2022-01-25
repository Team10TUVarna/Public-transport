package bg.tu_varna.sit.oop_project_demo.data.repositories;

import bg.tu_varna.sit.oop_project_demo.data.access.Connection;
import bg.tu_varna.sit.oop_project_demo.data.entities.Distributor;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class DistributorRepository implements DAORepository<Distributor>{
    private static final Logger log = Logger.getLogger(DistributorRepository.class);

    public static DistributorRepository getInstance() {
        return DistributorRepository.DistributorRepositoryHolder.INSTANCE;
    }
    private static class DistributorRepositoryHolder {

        public static final DistributorRepository INSTANCE = new DistributorRepository();
    }

    @Override
    public void save(Distributor obj) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.save(obj);
            log.info("Distributor saved successfully");
        } catch (Exception ex) {
            log.error("Distributor save error. " + ex.getMessage());
        } finally {
            transaction.commit();
        }
    }

    @Override
    public void update(Distributor obj) {

    }

    @Override
    public void delete(Distributor obj) {

    }

    @Override
    public Optional<Distributor> getById(int id) {
        return Optional.empty();
    }

    @Override
    public List<Distributor> getAll() {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        List<Distributor> distributors = new LinkedList<>();
        try {
            String jpql = "SELECT r FROM Distributor r";
            distributors.addAll(session.createQuery(jpql, Distributor.class).getResultList());
            log.info("Got all distributors");
        } catch (Exception ex){
            log.error("Get all distributors error. " + ex);
        } finally {
            transaction.commit();
        }
        return distributors;    }
}
