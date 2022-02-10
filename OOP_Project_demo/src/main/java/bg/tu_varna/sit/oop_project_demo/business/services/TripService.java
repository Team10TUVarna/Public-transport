package bg.tu_varna.sit.oop_project_demo.business.services;

import bg.tu_varna.sit.oop_project_demo.data.entities.Trip;
import bg.tu_varna.sit.oop_project_demo.data.entities.TripType;
import bg.tu_varna.sit.oop_project_demo.data.repositories.*;
import bg.tu_varna.sit.oop_project_demo.presentation.models.TripListViewModel;
import bg.tu_varna.sit.oop_project_demo.presentation.models.TripTypeListViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.stream.Collectors;

public class TripService {
    private static final Logger log=Logger.getLogger(TripService.class);
    private final TripRepository repository= TripRepository.getInstance();

    public static TripService getInstance() {
        return TripService.TripServiceHolder.INSTANCE;
    }

    private static class TripServiceHolder {
        public static final TripService INSTANCE = new TripService();
    }

    private final TripTypeRepository tripTypeRepository= TripTypeRepository.getInstance();
    private final TransportTypeRepository transportTypeRepository= TransportTypeRepository.getInstance();
    private final LocationRepository locationRepository= LocationRepository.getInstance();
    private final CompanyRepository companyRepository= CompanyRepository.getInstance();

    private final TripTypeService tripTypeService = TripTypeService.getInstance();
    private final TransportTypeService transportTypeService = TransportTypeService.getInstance();
    private final LocationService locationService = LocationService.getInstance();
    private final CompanyService companyService = CompanyService.getInstance();


    /*public void deleteTrip(TripListViewModel trip) {
        //Trip a=getTrip(trip.get());
        try{
            repository.delete(a);
            log.info("Successfully deleted trip type "+a);
        }catch(Exception e){
            e.printStackTrace();
            log.error("Error deleting trip type"+a);
        }
    }


    public int createTripType(TripTypeListViewModel a){
        TripType location=new TripType(a.getTripTypeName());
        if(checkIfTripTypeExists(location)){
            log.info("TripType "+a+" already exists!");
            return 0;
        }
        else {
            try{
                repository.save(location);
                log.info("TripType "+location.getTripTypeName()+" created!");
            }
            catch (Exception e) {
                e.printStackTrace();
                log.error("Create trip type error!");
            }
            return 1;
        }
    }

    public boolean checkIfTripTypeExists(TripType a){
        List<TripType> allTripType=repository.getAll();
        for(TripType tripType:allTripType){
            if(tripType.equals(a))
            {
                log.info("TripType: "+a.getTripTypeName()+" already exists!");
                return true;
            }
        }
        return false;
    }



    public ObservableList<TripTypeListViewModel> getAllTripTypes() {
        List<TripType> l = repository.getAll();

        return FXCollections.observableList(
                l.stream().map(w -> new TripTypeListViewModel(
                        w.getTripTypeName()
                )).collect(Collectors.toList()));
    }*/

    public ObservableList<TripListViewModel> getAllTrips() {
        List<Trip> l = repository.getAll();

        return FXCollections.observableList(
                l.stream().map(w -> new TripListViewModel(
                        w.getDeparture(),
                        w.getArrival(),
                        w.getCapacity(),
                        w.getTripTypeId(),
                        w.getTransportTypeId(),
                        w.getCompanyId(),
                        w.getLocationFrom(),
                        w.getLocationTo(),
                        w.getTimeOfDeparture(),
                        w.getTimeOfArrival()
                )).collect(Collectors.toList()));
    }


    public int createTrip(TripListViewModel a){
        TripService tripService=TripService.getInstance();
        Trip trip=new Trip(a.getDeparture(), a.getArrival(), a.getCapacity(), tripTypeService.getTripTypeByName(a.getTripTypeId().getTripTypeName()),
                transportTypeService.getTransportTypeByName(a.getTransportTypeId().getTransportTypeName()),
                companyService.getCompanyByName(a.getCompanyId().getUsername()),locationService.getLocationByName(a.getLocationFrom().getLocationName()),
                locationService.getLocationByName(a.getLocationTo().getLocationName()), a.getTimeOfDeparture(), a.getTimeOfArrival());
        if(checkIfTripExists(trip)){
            log.info("Trip already exists!");
            return 0;
        }
        else {
            try{
                repository.save(trip);
                log.info("Trip created!");
            }
            catch (Exception e) {
                e.printStackTrace();
                log.error("Create trip error!");
            }
            return 1;
        }
    }

    /*public boolean deleteTrip(TripListViewModel trip) {
        Trip a=getTrip(trip);
        if(a==null){
            return false;
        }
        try{
            repository.delete(a);
            log.info("Successfully deleted trip "+a.getTripId());
            return true;
        }catch(Exception e){
            e.printStackTrace();
            log.error("Error deleting trip "+a.getTripId());
            return false;
        }
    }*/

    public boolean checkIfTripExists(Trip a){
        List<Trip> allTrip=repository.getAll();
        for(Trip trip:allTrip){
            if(trip.equals(a))
            {
                log.info("TripType already exists!");
                return true;
            }
        }
        return false;
    }

    public Trip getTrip(TripListViewModel trip){
        //TripService tripService=TripService.getInstance();
        List<Trip> all=repository.getAll();
        Trip temp=new Trip(trip.getDeparture(), trip.getArrival(), trip.getCapacity(), tripTypeService.getTripTypeByName(trip.getTripTypeId().getTripTypeName()),
                transportTypeService.getTransportTypeByName(trip.getTransportTypeId().getTransportTypeName()),
                companyService.getCompanyByName(trip.getCompanyId().getUsername()),locationService.getLocationByName(trip.getLocationFrom().getLocationName()),
                locationService.getLocationByName(trip.getLocationTo().getLocationName()), trip.getTimeOfDeparture(), trip.getTimeOfArrival());
        for(Trip p:all){
            if(p.equals(temp)){
                return p;
            }
        }
        log.info("No such trip");
        return null;
    }
}
