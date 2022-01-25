package bg.tu_varna.sit.oop_project_demo.data.repositories;

import bg.tu_varna.sit.oop_project_demo.data.access.Connection;
import bg.tu_varna.sit.oop_project_demo.data.entities.Cashier;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class CashierRepository implements DAORepository<Cashier>{

    private static final Logger log = Logger.getLogger(CashierRepository.class);

    public static CashierRepository getInstance() {
        return CashierRepository.CashierRepositoryHolder.INSTANCE;
    }
    private static class CashierRepositoryHolder {

        public static final CashierRepository INSTANCE = new CashierRepository();
    }
    @Override
    public void save(Cashier obj) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.save(obj);
            log.info("Cashier saved successfully");
        } catch (Exception ex) {
            log.error("Cashier save error. " + ex.getMessage());
        } finally {
            transaction.commit();
        }
    }

    @Override
    public void update(Cashier obj) {

    }

    @Override
    public void delete(Cashier obj) {

    }

    @Override
    public Optional<Cashier> getById(int id) {
        return Optional.empty();
    }

    @Override
    public List<Cashier> getAll() {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        List<Cashier> cashiers = new LinkedList<>();
        try {
            String jpql = "SELECT r FROM Cashier r";
            cashiers.addAll(session.createQuery(jpql, Cashier.class).getResultList());
            log.info("Got all cashiers");
        } catch (Exception ex){
            log.error("Get all cashiers error. " + ex);
        } finally {
            transaction.commit();
        }
        return cashiers;
    }
}
