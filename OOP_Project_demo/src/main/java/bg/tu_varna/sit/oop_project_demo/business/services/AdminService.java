package bg.tu_varna.sit.oop_project_demo.business.services;

import bg.tu_varna.sit.oop_project_demo.data.entities.Admin;

import java.util.List;

import bg.tu_varna.sit.oop_project_demo.data.repositories.AdminRepository;
import bg.tu_varna.sit.oop_project_demo.presentation.models.AdminListViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.log4j.Logger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AdminService {
    private static final Logger log=Logger.getLogger(AdminService.class);
    private final AdminRepository repository= AdminRepository.getInstance();

    public static AdminService getInstance() {
        return AdminServiceHolder.INSTANCE;
    }

    private static class AdminServiceHolder {
        public static final AdminService INSTANCE = new AdminService();
    }



    public boolean adminLogin(AdminListViewModel a)
    {
        if(getAdminByName(a.getUsername())==null){
            log.error("Admin login error!");
            return false;
        }
        log.info("Admin login successful!");
        return true;
    }


    public Admin getAdminByName(String name){
        List<Admin> allAdmins=repository.getAll();
        for(Admin a:allAdmins){
            if(a.getUsername().equals(name))
            {
                return a;
            }
        }
        log.error("Admin not found!");
        return null;
    }

}
