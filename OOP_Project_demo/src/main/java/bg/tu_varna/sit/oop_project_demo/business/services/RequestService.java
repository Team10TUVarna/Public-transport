package bg.tu_varna.sit.oop_project_demo.business.services;

import bg.tu_varna.sit.oop_project_demo.data.entities.Request;
import bg.tu_varna.sit.oop_project_demo.data.repositories.RequestRepository;
import bg.tu_varna.sit.oop_project_demo.presentation.models.RequestListViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import org.apache.log4j.Logger;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class RequestService {
    private static final Logger log=Logger.getLogger(RequestService.class);
    private final RequestRepository repository= RequestRepository.getInstance();

    public static RequestService getInstance() {
        return RequestService.RequestServiceHolder.INSTANCE;
    }



    private static class RequestServiceHolder {
        public static final RequestService INSTANCE = new RequestService();
    }

    private final TripService tripService = TripService.getInstance();
    private final DistributorService distributorService = DistributorService.getInstance();
    private final CompanyService companyService = CompanyService.getInstance();


    public int createRequest(RequestListViewModel a)
    {
        Request request = new Request(a.getTicketCount(), a.getStatus(), a.getTripId(),
                distributorService.getDistributorByName(a.getDistributorId().getUsername()), //tripService.getTrip()
                /*companyService.getCompanyByName(a.getCompanyId().getUsername())*/
        a.getCompanyId());
        try{
            repository.save(request);
            log.info("Request created!");
            return 0;
        }
        catch (Exception e) {
            e.printStackTrace();
            log.error("Create request error!");
        }
        return 1;
    }

    public Request getRequest(RequestListViewModel request)
    {
        List<Request> all = repository.getAll();

        Request temp = new Request(request.getTicketCount(), request.getStatus(), request.getTripId(),
                request.getDistributorId() /*distributorService.getDistributorByName(request.getDistributorId().getUsername())*/,
                request.getCompanyId()/*companyService.getCompanyByName(request.getCompanyId().getUsername())*/);
        for (Request p:all)
        {
            if (p.equals(temp))
                return p;
        }
        log.info("No such request.");
        return null;
    }

    public ObservableList<RequestListViewModel> getPendingRequests()
    {
        List<Request> all = repository.getAll();
        List<Request> res = new LinkedList<>();
        for (Request p:all)
        {
            if (p.getStatus().equals("pending")){
                res.add(p);
            }
        }
        return FXCollections.observableList(
                res.stream().map(g -> new RequestListViewModel(
                        g.getTicketCount(),
                        g.getStatus(),
                        g.getTripId(),
                        g.getDistributorId(),
                        g.getCompanyId()
                )).collect(Collectors.toList()));
    }
    public boolean updateRequestStatus(RequestListViewModel a, String s){
        Request request = getRequest(a);
        request.setStatus(s);
        try{
            repository.update(request);
            log.info("Request updated successfully!");
            return true;
        }catch(Exception e){
            e.printStackTrace();
            log.error("Error updating request!");
            return false;
        }
    }
}
