package bg.tu_varna.sit.oop_project_demo.data.repositories;

import bg.tu_varna.sit.oop_project_demo.data.access.Connection;
import bg.tu_varna.sit.oop_project_demo.data.entities.Admin;
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
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.merge(obj);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            transaction.commit();
            session.close();
        }
    }

    @Override
    public void delete(Cashier obj) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            //session.delete(obj);
            session.createQuery("Delete from Cashier where cashierId = " + obj.getCashierId()).executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            transaction.commit();
            session.close();
        }
    }

    @Override
    public Cashier getById(int id) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        Cashier cashier = new Cashier();
        try {
            String jpql = "SELECT a FROM Cashier a WHERE cashierId =" + String.valueOf(id);
            cashier=session.createQuery(jpql, Cashier.class).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            transaction.commit();
            //session.close();
        }
        return cashier;
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
            //session.close();
        }
        return cashiers;
    }
}
