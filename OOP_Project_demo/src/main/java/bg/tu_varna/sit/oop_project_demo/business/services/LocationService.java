package bg.tu_varna.sit.oop_project_demo.business.services;

import bg.tu_varna.sit.oop_project_demo.data.entities.Location;
import bg.tu_varna.sit.oop_project_demo.data.repositories.LocationRepository;
import bg.tu_varna.sit.oop_project_demo.presentation.models.LocationListViewModel;
import org.apache.log4j.Logger;

import java.util.List;

public class LocationService {
    private static final Logger log=Logger.getLogger(LocationService.class);
    private final LocationRepository repository= LocationRepository.getInstance();

    public static LocationService getInstance() {
        return LocationService.LocationServiceHolder.INSTANCE;
    }

    private static class LocationServiceHolder {
        public static final LocationService INSTANCE = new LocationService();
    }


    public void deleteLocation(LocationListViewModel location) {
        Location a=getLocationByName(location.getLocationName());
        try{
            repository.delete(a);
            log.info("Successfully deleted location "+a);
        }catch(Exception e){
            e.printStackTrace();
            log.error("Error deleting location"+a);
        }
    }

    /*public boolean locationLogin(LocationListViewModel a)
    {
        if(getLocationByName(a.getLocationName())==null){
            log.error("Location login error!");
            return false;
        }
        log.info("Location login successful!");
        return true;
    }*/

    public int createLocation(LocationListViewModel a){
        Location location=new Location(a.getLocationName());
        if(checkIfLocationExists(location)){
            log.info("Location "+a+" already exists!");
            return 0;
        }
        else {
            try{
                repository.save(location);
                log.info("Location "+location.getLocationName()+" created!");
            }
            catch (Exception e) {
                e.printStackTrace();
                log.error("Create location error!");
            }
            return 1;
        }
    }

    public boolean checkIfLocationExists(Location a){
        List<Location> allLocation=repository.getAll();
        for(Location location:allLocation){
            if(location.equals(a))
            {
                log.info("Location: "+a.getLocationName()+" already exists!");
                return true;
            }
        }
        return false;
    }

    public Location getLocationByName(String name){
        List<Location> allLocation=repository.getAll();
        for(Location a:allLocation){
            if(a.getLocationName().equals(name))
            {
                return a;
            }
        }
        log.error("Location not found!");
        return null;
    }
}
