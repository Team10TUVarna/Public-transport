package bg.tu_varna.sit.oop_project_demo.business.services;

import bg.tu_varna.sit.oop_project_demo.data.entities.Distributor;
import bg.tu_varna.sit.oop_project_demo.data.repositories.DistributorRepository;
import bg.tu_varna.sit.oop_project_demo.presentation.models.DistributorListViewModel;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DistributorServiceTest {
    private DistributorService distributorService;
    private Distributor distributor;
    private DistributorRepository distributorRepository;
    private DistributorListViewModel distributorListViewModel;

    @BeforeEach
    void setUp() {
        distributorService = DistributorService.getInstance();
        distributor = new Distributor("DistributorTest", "test1", "12345", 20000.00);
        distributorRepository = DistributorRepository.getInstance();
        distributorListViewModel = new DistributorListViewModel("test11", "12345", "DistributorTest1", 20000.00);
    }

    @Test
    void deleteDistributor() {
        List<Distributor> distributors = distributorRepository.getAll();
        distributorService.deleteDistributor(distributorListViewModel);
        assertNotEquals(distributors, distributorRepository.getAll());
    }

    @Test
    void distributorLogin() {
        assertTrue(distributorService.distributorLogin(distributorListViewModel));
    }

    @Test
    void createDistributor() {
        assertEquals(0, distributorService.createDistributor(distributorListViewModel));
    }

    @Test
    void checkIfDistributorExists() {
        assertTrue(distributorService.checkIfDistributorExists(distributor));
    }

    @Test
    void getDistributorByName() {
        assertEquals(distributor, distributorService.getDistributorByName(distributor.getUsername()));
    }

    @Test
    void getAllDistributor() {
        ObservableList<DistributorListViewModel> distributorListViewModels = distributorService.getAllDistributor();
        assertEquals(distributorListViewModels, distributorService.getAllDistributor());
    }

    @Test
    void updateDistributorHonorarium() {
        assertTrue(distributorService.updateDistributorHonorarium(distributorListViewModel, 200));
    }
}