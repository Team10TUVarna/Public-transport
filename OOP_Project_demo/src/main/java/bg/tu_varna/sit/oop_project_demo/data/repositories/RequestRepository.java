package bg.tu_varna.sit.oop_project_demo.data.repositories;

import bg.tu_varna.sit.oop_project_demo.data.access.Connection;
import bg.tu_varna.sit.oop_project_demo.data.entities.Notification;
import bg.tu_varna.sit.oop_project_demo.data.entities.Request;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class RequestRepository implements DAORepository<Request>{
    private static final Logger log = Logger.getLogger(RequestRepository.class);

    public static RequestRepository getInstance() {
        return RequestRepository.RequestRepositoryHolder.INSTANCE;
    }
    private static class RequestRepositoryHolder {

        public static final RequestRepository INSTANCE = new RequestRepository();
    }

    @Override
    public void save(Request obj) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.save(obj);
            log.info("Request saved successfully");
        } catch (Exception ex) {
            log.error("Request save error. " + ex.getMessage());
        } finally {
            transaction.commit();
        }
    }

    @Override
    public void update(Request obj) {

    }

    @Override
    public void delete(Request obj) {

    }

    @Override
    public Request getById(int id) {
        Request obj = new Request();
        return obj;
    }

    @Override
    public List<Request> getAll() {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        List<Request> requests = new LinkedList<>();
        try {
            String jpql = "SELECT r FROM Request r";
            requests.addAll(session.createQuery(jpql, Request.class).getResultList());
            log.info("Got all requests");
        } catch (Exception ex){
            log.error("Get all requests error. " + ex);
        } finally {
            transaction.commit();
        }
        return requests;
    }
}
