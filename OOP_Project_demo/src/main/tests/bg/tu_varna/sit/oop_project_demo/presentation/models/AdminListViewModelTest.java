package bg.tu_varna.sit.oop_project_demo.presentation.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdminListViewModelTest {
    AdminListViewModel adminListViewModel;
    AdminListViewModel adminListViewModel1;

    @BeforeEach
    void setUp() {
        adminListViewModel = new AdminListViewModel();
        adminListViewModel1 = new AdminListViewModel();
        adminListViewModel1.setPassword("1");
        adminListViewModel1.setUsername("1");
        adminListViewModel.setPassword("1");
        adminListViewModel.setUsername("1");
    }

    @Test
    void equals(){
        assertTrue(adminListViewModel.equals(adminListViewModel1));
    }

    @Test
    void getUsername() {
        assertEquals("1",adminListViewModel1.getUsername());
    }

    @Test
    void getPassword() {
        assertEquals("1", adminListViewModel1.getPassword());
    }
}