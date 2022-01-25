package bg.tu_varna.sit.oop_project_demo.data.repositories;

import bg.tu_varna.sit.oop_project_demo.data.access.Connection;
import bg.tu_varna.sit.oop_project_demo.data.entities.Admin;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class AdminRepository implements DAORepository<Admin>{

    private static final Logger log = Logger.getLogger(AdminRepository.class);

    public static AdminRepository getInstance() {
        return AdminRepository.AdminRepositoryHolder.INSTANCE;
    }
    private static class AdminRepositoryHolder {

        public static final AdminRepository INSTANCE = new AdminRepository();
    }

    @Override
    public void save(Admin obj) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.save(obj);
            log.info("Admin saved successfully");
        } catch (Exception ex) {
            log.error("Admin save error. " + ex.getMessage());
        } finally {
            transaction.commit();
        }
    }

    @Override
    public void update(Admin obj) {

    }

    @Override
    public void delete(Admin obj) {

    }

    @Override
    public Optional<Admin> getById(int id) {
        return Optional.empty();
    }

    @Override
    public List<Admin> getAll() {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        List<Admin> admins = new LinkedList<>();
        try {
            String jpql = "SELECT r FROM Admin r";
            admins.addAll(session.createQuery(jpql, Admin.class).getResultList());
            log.info("Got all admins");
        } catch (Exception ex){
            log.error("Get all admins error. " + ex);
        } finally {
            transaction.commit();
        }
        return admins;
    }
}
