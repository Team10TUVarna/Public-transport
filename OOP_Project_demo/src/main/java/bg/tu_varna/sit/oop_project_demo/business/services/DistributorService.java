package bg.tu_varna.sit.oop_project_demo.business.services;

import bg.tu_varna.sit.oop_project_demo.data.entities.Company;
import bg.tu_varna.sit.oop_project_demo.data.entities.Distributor;
import bg.tu_varna.sit.oop_project_demo.data.repositories.CompanyRepository;
import bg.tu_varna.sit.oop_project_demo.data.repositories.DistributorRepository;
import bg.tu_varna.sit.oop_project_demo.presentation.models.CompanyListViewModel;
import bg.tu_varna.sit.oop_project_demo.presentation.models.DistributorListViewModel;
import org.apache.log4j.Logger;

import java.util.List;

public class DistributorService {
    private static final Logger log=Logger.getLogger(DistributorService.class);
    private final DistributorRepository repository= DistributorRepository.getInstance();

    public static DistributorService getInstance() {
        return DistributorService.DistributorServiceHolder.INSTANCE;
    }

    private static class DistributorServiceHolder {
        public static final DistributorService INSTANCE = new DistributorService();
    }


    public void deleteDistributor(DistributorListViewModel distributor) {
        Distributor a=getDistributorByName(distributor.getUsername());
        try{
            repository.delete(a);
            log.info("Successfully deleted distributor "+a);
        }catch(Exception e){
            e.printStackTrace();
            log.error("Error deleting distributor"+a);
        }
    }

    public boolean distributorLogin(DistributorListViewModel a)
    {
        if(getDistributorByName(a.getUsername())==null){
            log.error("Distributor login error!");
            return false;
        }
        log.info("Distributor login successful!");
        return true;
    }

    public int createDistributor(DistributorListViewModel a){
        Distributor distributor=new Distributor(a.getDistributorName(), a.getUsername(),a.getPassword(),a.getHonorarium());
        if(checkIfDistributorExists(distributor)){
            log.info("Distributor "+a+" already exists!");
            return 0;
        }
        else {
            try{
                repository.save(distributor);
                log.info("Distributor "+distributor.getUsername()+" created!");
            }
            catch (Exception e) {
                e.printStackTrace();
                log.error("Create distributor error!");
            }
            return 1;
        }
    }

    public boolean checkIfDistributorExists(Distributor a){
        List<Distributor> allDistributor=repository.getAll();
        for(Distributor distributor:allDistributor){
            if(distributor.equals(a))
            {
                log.info("Distributor: "+a.getUsername()+" already exists!");
                return true;
            }
        }
        return false;
    }

    public Distributor getDistributorByName(String name){
        List<Distributor> allDistributor=repository.getAll();
        for(Distributor a:allDistributor){
            if(a.getUsername().equals(name))
            {
                return a;
            }
        }
        log.error("Distributor not found!");
        return null;
    }
}
