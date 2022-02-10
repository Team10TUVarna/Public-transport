package bg.tu_varna.sit.oop_project_demo.data.repositories;

import bg.tu_varna.sit.oop_project_demo.data.access.Connection;
import bg.tu_varna.sit.oop_project_demo.data.entities.Company;
import bg.tu_varna.sit.oop_project_demo.data.entities.Distributor;
import bg.tu_varna.sit.oop_project_demo.data.entities.Location;
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
    public void delete(Distributor obj) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            //session.delete(obj);
            session.createQuery("Delete from Distributor where distributorId = " + obj.getDistributorId()).executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            transaction.commit();
            session.close();
        }
    }

    @Override
    public Distributor getById(int id) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        Distributor distributor = new Distributor();
        try {
            String jpql = "SELECT a FROM Distributor a WHERE distributorId =" + String.valueOf(id);
            distributor=session.createQuery(jpql, Distributor.class).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            transaction.commit();
            session.close();
        }
        return distributor;
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
