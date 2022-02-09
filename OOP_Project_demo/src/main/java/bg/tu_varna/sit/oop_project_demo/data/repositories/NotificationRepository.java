package bg.tu_varna.sit.oop_project_demo.data.repositories;

import bg.tu_varna.sit.oop_project_demo.data.access.Connection;
import bg.tu_varna.sit.oop_project_demo.data.entities.Admin;
import bg.tu_varna.sit.oop_project_demo.data.entities.Location;
import bg.tu_varna.sit.oop_project_demo.data.entities.Notification;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class NotificationRepository implements DAORepository<Notification>{
    private static final Logger log = Logger.getLogger(NotificationRepository.class);

    public static NotificationRepository getInstance() {
        return NotificationRepository.NotificationRepositoryHolder.INSTANCE;
    }
    private static class NotificationRepositoryHolder {

        public static final NotificationRepository INSTANCE = new NotificationRepository();
    }

    @Override
    public void save(Notification obj) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.save(obj);
            log.info("Notification saved successfully");
        } catch (Exception ex) {
            log.error("Notification save error. " + ex.getMessage());
        } finally {
            transaction.commit();
        }
    }

    @Override
    public void update(Notification obj) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.update(obj);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            transaction.commit();
            session.close();
        }
    }

    @Override
    public void delete(Notification obj) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.delete(obj);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            transaction.commit();
            session.close();
        }
    }

    @Override
    public Notification getById(int id) {
        Notification obj = new Notification();
        return obj;
    }

    @Override
    public List<Notification> getAll() {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        List<Notification> notifications = new LinkedList<>();
        try {
            String jpql = "SELECT r FROM Notification r";
            notifications.addAll(session.createQuery(jpql, Notification.class).getResultList());
            log.info("Got all notifications");
        } catch (Exception ex){
            log.error("Get all notifications error. " + ex);
        } finally {
            transaction.commit();
        }
        return notifications;
    }
}
