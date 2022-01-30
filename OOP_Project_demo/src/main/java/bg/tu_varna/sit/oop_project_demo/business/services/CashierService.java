package bg.tu_varna.sit.oop_project_demo.business.services;

import bg.tu_varna.sit.oop_project_demo.data.entities.Admin;

import java.util.List;

import bg.tu_varna.sit.oop_project_demo.data.entities.Cashier;
import bg.tu_varna.sit.oop_project_demo.data.repositories.CashierRepository;
import bg.tu_varna.sit.oop_project_demo.presentation.models.CashierListViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.log4j.Logger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
public class CashierService {
    private static final Logger log=Logger.getLogger(CashierService.class);
    private final CashierRepository repository= CashierRepository.getInstance();

    public static CashierService getInstance() {
        return CashierService.CashierServiceHolder.INSTANCE;
    }

    private static class CashierServiceHolder {
        public static final CashierService INSTANCE = new CashierService();
    }


    public void deleteCashier(CashierListViewModel cashier) {
        Cashier a=getCashierByName(cashier.getUsername());
        try{
            repository.delete(a);
            log.info("Successfully deleted cashier "+a);
        }catch(Exception e){
            e.printStackTrace();
            log.error("Error deleting cashier"+a);
        }
    }

    public boolean cashierLogin(CashierListViewModel a)
    {
        if(getCashierByName(a.getUsername())==null){
            log.error("Cashier login error!");
            return false;
        }
        log.info("Cashier login successful!");
        return true;
    }

    public int createCashier(CashierListViewModel a){
        Cashier cashier=new Cashier(a.getUsername(),a.getPassword());
        if(checkIfCashierExists(cashier)){
            log.info("Cashier "+a+" already exists!");
            return 0;
        }
        else {
            try{
                repository.save(cashier);
                log.info("Cashier "+cashier.getUsername()+" created!");
            }
            catch (Exception e) {
                e.printStackTrace();
                log.error("Create cashier error!");
            }
            return 1;
        }
    }

    public boolean checkIfCashierExists(Cashier a){
        List<Cashier> allCashier=repository.getAll();
        for(Cashier cashier:allCashier){
            if(cashier.equals(a))
            {
                log.info("Cashier: "+a.getUsername()+" already exists!");
                return true;
            }
        }
        return false;
    }

    public Cashier getCashierByName(String name){
        List<Cashier> allCashier=repository.getAll();
        for(Cashier a:allCashier){
            if(a.getUsername().equals(name))
            {
                return a;
            }
        }
        log.error("Cashier not found!");
        return null;
    }
}
