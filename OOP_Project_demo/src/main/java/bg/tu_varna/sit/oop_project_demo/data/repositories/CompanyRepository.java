package bg.tu_varna.sit.oop_project_demo.data.repositories;

import bg.tu_varna.sit.oop_project_demo.data.access.Connection;
import bg.tu_varna.sit.oop_project_demo.data.entities.Company;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
public class CompanyRepository implements DAORepository<Company>{
    private static final Logger log = Logger.getLogger(CompanyRepository.class);

    public static CompanyRepository getInstance() {
        return CompanyRepository.CompanyRepositoryHolder.INSTANCE;
    }
    private static class CompanyRepositoryHolder {

        public static final CompanyRepository INSTANCE = new CompanyRepository();
    }

    @Override
    public void save(Company obj) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.save(obj);
            log.info("Company saved successfully");
        } catch (Exception ex) {
            log.error("Company save error. " + ex.getMessage());
        } finally {
            transaction.commit();
        }
    }

    @Override
    public void update(Company obj) {

    }

    @Override
    public void delete(Company obj) {

    }

    @Override
    public Optional<Company> getById(int id) {
        return Optional.empty();
    }

    @Override
    public List<Company> getAll() {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        List<Company> companies = new LinkedList<>();
        try {
            String jpql = "SELECT r FROM Company r";
            companies.addAll(session.createQuery(jpql, Company.class).getResultList());
            log.info("Got all companies");
        } catch (Exception ex){
            log.error("Get all companies error. " + ex);
        } finally {
            transaction.commit();
        }
        return companies;
    }
}
