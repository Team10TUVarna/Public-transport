package bg.tu_varna.sit.oop_project_demo.business.services;

import bg.tu_varna.sit.oop_project_demo.data.entities.TripType;
import bg.tu_varna.sit.oop_project_demo.data.repositories.TripTypeRepository;
import bg.tu_varna.sit.oop_project_demo.presentation.models.TripTypeListViewModel;
import org.apache.log4j.Logger;

import java.util.List;

public class TripTypeService {
    private static final Logger log=Logger.getLogger(TripTypeService.class);
    private final TripTypeRepository repository= TripTypeRepository.getInstance();

    public static TripTypeService getInstance() {
        return TripTypeService.TripTypeServiceHolder.INSTANCE;
    }

    private static class TripTypeServiceHolder {
        public static final TripTypeService INSTANCE = new TripTypeService();
    }


    public void deleteTripType(TripTypeListViewModel location) {
        TripType a=getTripTypeByName(location.getTripTypeName());
        try{
            repository.delete(a);
            log.info("Successfully deleted trip type "+a);
        }catch(Exception e){
            e.printStackTrace();
            log.error("Error deleting trip type"+a);
        }
    }

    /*public boolean tripTypeLogin(TripTypeListViewModel a)
    {
        if(getLocationByName(a.getTripTypeName())==null){
            log.error("Location login error!");
            return false;
        }
        log.info("Location login successful!");
        return true;
    }*/

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

    public TripType getTripTypeByName(String name){
        List<TripType> allTripType=repository.getAll();
        for(TripType a:allTripType){
            if(a.getTripTypeName().equals(name))
            {
                return a;
            }
        }
        log.error("TripType not found!");
        return null;
    }
}
