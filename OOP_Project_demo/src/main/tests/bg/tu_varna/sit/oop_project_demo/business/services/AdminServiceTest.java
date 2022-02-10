package bg.tu_varna.sit.oop_project_demo.business.services;
import bg.tu_varna.sit.oop_project_demo.data.entities.Admin;
import bg.tu_varna.sit.oop_project_demo.data.repositories.AdminRepository;
import bg.tu_varna.sit.oop_project_demo.presentation.models.AdminListViewModel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdminServiceTest {
    private AdminService adminService;
    private Admin admin;
    private AdminListViewModel adminListViewModel;

    @BeforeEach
    void setup(){
        adminService = AdminService.getInstance();
        admin=new Admin("firsttest", "12345");
        admin.setAdminId(1);
        adminListViewModel=new AdminListViewModel("firsttest","12345");
    }

    @Test
    void adminLogin() {
        assertTrue(adminService.adminLogin(adminListViewModel));
    }

    @Test
    void getAdminByName(){
        assertEquals(admin, adminService.getAdminByName(admin.getUsername()));
    }
}