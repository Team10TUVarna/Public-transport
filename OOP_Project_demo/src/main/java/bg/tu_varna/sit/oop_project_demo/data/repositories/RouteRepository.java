package bg.tu_varna.sit.oop_project_demo.data.repositories;

import bg.tu_varna.sit.oop_project_demo.data.access.Connection;
import bg.tu_varna.sit.oop_project_demo.data.entities.Request;
import bg.tu_varna.sit.oop_project_demo.data.entities.Route;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class RouteRepository implements DAORepository<Route>{
    private static final Logger log = Logger.getLogger(RouteRepository.class);

    public static RouteRepository getInstance() {
        return RouteRepository.RouteRepositoryHolder.INSTANCE;
    }
    private static class RouteRepositoryHolder {

        public static final RouteRepository INSTANCE = new RouteRepository();
    }

    @Override
    public void save(Route obj) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.save(obj);
            log.info("Route saved successfully");
        } catch (Exception ex) {
            log.error("Route save error. " + ex.getMessage());
        } finally {
            transaction.commit();
        }
    }

    @Override
    public void update(Route obj) {

    }

    @Override
    public void delete(Route obj) {

    }

    @Override
    public Route getById(int id) {
        Route obj = new Route();
        return obj;
    }

    @Override
    public List<Route> getAll() {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        List<Route> routes = new LinkedList<>();
        try {
            String jpql = "SELECT r FROM Route r";
            routes.addAll(session.createQuery(jpql, Route.class).getResultList());
            log.info("Got all routes");
        } catch (Exception ex){
            log.error("Get all routes error. " + ex);
        } finally {
            transaction.commit();
        }
        return routes;
    }
}
