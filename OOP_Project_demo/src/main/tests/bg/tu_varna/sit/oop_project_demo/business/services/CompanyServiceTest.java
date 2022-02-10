package bg.tu_varna.sit.oop_project_demo.business.services;

import bg.tu_varna.sit.oop_project_demo.data.entities.Cashier;
import bg.tu_varna.sit.oop_project_demo.data.entities.Company;
import bg.tu_varna.sit.oop_project_demo.data.repositories.CashierRepository;
import bg.tu_varna.sit.oop_project_demo.data.repositories.CompanyRepository;
import bg.tu_varna.sit.oop_project_demo.presentation.models.CashierListViewModel;
import bg.tu_varna.sit.oop_project_demo.presentation.models.CompanyListViewModel;
import bg.tu_varna.sit.oop_project_demo.presentation.models.RequestListViewModel;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CompanyServiceTest {
    private CompanyService companyService;
    private Company company;
    private CompanyRepository companyRepository;
    private CompanyListViewModel companyListViewModel;

    @BeforeEach
    void setUp() {
        companyService = CompanyService.getInstance();
        company = new Company("TestCompany", "test1", "12345", 70000.00);
        companyRepository = CompanyRepository.getInstance();
        companyListViewModel = new CompanyListViewModel("test1", "12345", "TestCompany", 70000.00);
    }

    @Test
    void deleteCompany() {
        List<Company> companyList = companyRepository.getAll();
        companyService.deleteCompany(companyListViewModel);
        assertNotEquals(companyList, companyRepository.getAll());
    }

    @Test
    void companyLogin() {
        assertTrue(companyService.companyLogin(companyListViewModel));
    }

    @Test
    void createCompany() {
        assertEquals(true, companyService.companyLogin(companyListViewModel));
    }

    @Test
    void checkIfCompanyExists() {
        assertTrue(companyService.checkIfCompanyExists(company));
    }

    @Test
    void getCompanyByName() {
        assertEquals(company, companyService.getCompanyByName(company.getUsername()));
    }

    @Test
    void getMyPendingRequests() {
        ObservableList<RequestListViewModel> requestListViewModels = companyService.getMyPendingRequests();
        assertEquals(requestListViewModels, companyService.getMyPendingRequests());
    }

    @Test
    void getAllCompany() {
        ObservableList<CompanyListViewModel> companyListViewModels = companyService.getAllCompany();
        assertEquals(companyListViewModels, companyService.getAllCompany());
    }

    @Test
    void updateCompanyHonorarium() {
        assertTrue(companyService.updateCompanyHonorarium(companyListViewModel, 1));
    }
}