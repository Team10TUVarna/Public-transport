package bg.tu_varna.sit.oop_project_demo.business.services;

import bg.tu_varna.sit.oop_project_demo.data.entities.Cashier;
import bg.tu_varna.sit.oop_project_demo.data.repositories.CashierRepository;
import bg.tu_varna.sit.oop_project_demo.presentation.models.CashierListViewModel;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CashierServiceTest {
    private CashierService cashierService;
    private Cashier cashier;
    private CashierRepository cashierRepository;
    private CashierListViewModel cashierListViewModel;
    private CashierListViewModel cashierListViewModel1;

    @BeforeEach
    void setUp() {
        cashierService = CashierService.getInstance();
        cashier = new Cashier("TestCashier", "test1", "12345", 2200.00);
        cashier.setCashierId(1);
        cashierRepository = CashierRepository.getInstance();
        cashierListViewModel = new CashierListViewModel("test1", "12345", "TestCashier", 2200.00);
        cashierListViewModel1 = new CashierListViewModel("test2", "test", "test", 1);

    }

    @Test
    void deleteCashier() {
        List<Cashier> cashiers=cashierRepository.getAll();
        cashierService.deleteCashier(cashierListViewModel);
        assertNotEquals(cashiers,cashierRepository.getAll());
    }

    @Test
    void cashierLogin() {
        assertTrue(cashierService.cashierLogin(cashierListViewModel));
    }

    @Test
    void createCashier() {
        assertEquals(0, cashierService.createCashier(cashierListViewModel1));
    }

    @Test
    void checkIfCashierExists() {
        assertTrue(cashierService.checkIfCashierExists(cashier));
    }

    @Test
    void getCashierByName() {
        assertEquals(cashier, cashierService.getCashierByName(cashier.getUsername()));
    }

    @Test
    void getAllCashier() {
        ObservableList<CashierListViewModel> cashierListViewModels = cashierService.getAllCashier();
        assertEquals(cashierListViewModels, cashierService.getAllCashier());
    }

    @Test
    void updateCashierHonorarium() {
        assertTrue(cashierService.updateCashierHonorarium(cashierListViewModel, 22));
    }
}