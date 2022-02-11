package bg.tu_varna.sit.oop_project_demo.business.services;

import bg.tu_varna.sit.oop_project_demo.data.entities.Cashier;
import bg.tu_varna.sit.oop_project_demo.data.entities.Company;
import bg.tu_varna.sit.oop_project_demo.data.entities.Request;
import bg.tu_varna.sit.oop_project_demo.data.repositories.CompanyRepository;
import bg.tu_varna.sit.oop_project_demo.presentation.models.CashierListViewModel;
import bg.tu_varna.sit.oop_project_demo.presentation.models.CompanyListViewModel;
import bg.tu_varna.sit.oop_project_demo.presentation.models.RequestListViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.log4j.Logger;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static bg.tu_varna.sit.oop_project_demo.common.Constants.User.loggedCompanyUsername;

public class CompanyService {
    private static final Logger log=Logger.getLogger(CompanyService.class);
    private final CompanyRepository repository= CompanyRepository.getInstance();

    public static CompanyService getInstance() {
        return CompanyService.CompanyServiceHolder.INSTANCE;
    }

    private static class CompanyServiceHolder {
        public static final CompanyService INSTANCE = new CompanyService();
    }


    public void deleteCompany(CompanyListViewModel company) {
        Company a=getCompanyByName(company.getUsername());
        try{
            repository.delete(a);
            log.info("Successfully deleted company "+a);
        }catch(Exception e){
            e.printStackTrace();
            log.error("Error deleting company"+a);
        }
    }

    public boolean companyLogin(CompanyListViewModel a)
    {
        if(getCompanyByName(a.getUsername())==null){
            log.error("Company login error!");
            return false;
        }
        log.info("Company login successful!");
        return true;
    }

    public int createCompany(CompanyListViewModel a){
        Company company=new Company(a.getCompanyName(), a.getUsername(),a.getPassword(),a.getHonorarium());
        if(checkIfCompanyExists(company)){
            log.info("Company "+a+" already exists!");
            return 0;
        }
        else {
            try{
                repository.save(company);
                log.info("Company "+company.getUsername()+" created!");
            }
            catch (Exception e) {
                e.printStackTrace();
                log.error("Create company error!");
            }
            return 1;
        }
    }

    public boolean checkIfCompanyExists(Company a){
        List<Company> allCompany=repository.getAll();
        for(Company company:allCompany){
            if(company.equals(a))
            {
                log.info("Company: "+a.getUsername()+" already exists!");
                return true;
            }
        }
        return false;
    }

    public Company getCompanyByName(String name){
        List<Company> allCompany=repository.getAll();
        for(Company a:allCompany){
            if(a.getUsername().equals(name))
            {
                return a;
            }
        }
        log.error("Company not found!");
        return null;
    }

    public ObservableList<RequestListViewModel> getMyPendingRequests()
    {
        RequestService requestService = RequestService.getInstance();
        List<RequestListViewModel> allRequests = requestService.getPendingRequests();
        List<RequestListViewModel> myRequests = new LinkedList<>();
        for(RequestListViewModel p:allRequests)
        {
            if(p.getCompanyId().getUsername().equals(loggedCompanyUsername))
            {
                myRequests.add(p);
            }
        }
        return FXCollections.observableList(
                myRequests.stream().map(g -> new RequestListViewModel(
                        g.getTicketCount(),
                        g.getStatus(),
                        g.getTripId(),
                        g.getDistributorId(),
                        g.getCompanyId()
                )).collect(Collectors.toList()));
    }

    public ObservableList<CompanyListViewModel> getAllCompany(){
        List<Company> all = repository.getAll();
        return FXCollections.observableList(
                all.stream().map(w -> new CompanyListViewModel(
                        w.getUsername(),
                        w.getPassword(),
                        w.getCompanyName(),
                        w.getHonorarium()
                )).collect(Collectors.toList()));
    }

    public boolean updateCompanyHonorarium(CompanyListViewModel a, double d){
        Company company = getCompanyByName(a.getUsername());
        company.setHonorarium(d);
        try{
            repository.update(company);
            log.info("Company updated successfully!");
            return true;
        }catch(Exception e){
            e.printStackTrace();
            log.error("Error updating company!");
            return false;
        }
    }
    /*public ObservableList<CompanyListViewModel> getAllNamesCompany(){
        List<Company> all = repository.getAll();
        return FXCollections.observableList(
                all.stream().map(w -> new CompanyListViewModel(
                        w.getCompanyName()
                )).collect(Collectors.toList()));
    }*/
}
