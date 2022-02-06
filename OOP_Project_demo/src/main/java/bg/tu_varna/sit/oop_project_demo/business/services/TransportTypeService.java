package bg.tu_varna.sit.oop_project_demo.business.services;

import bg.tu_varna.sit.oop_project_demo.data.entities.Location;
import bg.tu_varna.sit.oop_project_demo.data.entities.TransportType;
import bg.tu_varna.sit.oop_project_demo.data.repositories.TransportTypeRepository;
import bg.tu_varna.sit.oop_project_demo.presentation.models.LocationListViewModel;
import bg.tu_varna.sit.oop_project_demo.presentation.models.TransportTypeListViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.stream.Collectors;

public class TransportTypeService {
    private static final Logger log=Logger.getLogger(TransportTypeService.class);
    private final TransportTypeRepository repository= TransportTypeRepository.getInstance();

    public static TransportTypeService getInstance() {
        return TransportTypeService.TransportTypeServiceHolder.INSTANCE;
    }

    private static class TransportTypeServiceHolder {
        public static final TransportTypeService INSTANCE = new TransportTypeService();
    }


    public void deleteTransportType(TransportTypeListViewModel transportType) {
        TransportType a=getTransportTypeByName(transportType.getTransportTypeName());
        try{
            repository.delete(a);
            log.info("Successfully deleted transport type "+a);
        }catch(Exception e){
            e.printStackTrace();
            log.error("Error deleting transport type"+a);
        }
    }


    public int createTransportType(TransportTypeListViewModel a){
        TransportType transportType=new TransportType(a.getTransportTypeName());
        if(checkIfTransportTypeExists(transportType)){
            log.info("Transport type "+a+" already exists!");
            return 0;
        }
        else {
            try{
                repository.save(transportType);
                log.info("Transport type "+transportType.getTransportTypeName()+" created!");
            }
            catch (Exception e) {
                e.printStackTrace();
                log.error("Create Transport type error!");
            }
            return 1;
        }
    }

    public boolean checkIfTransportTypeExists(TransportType a){
        List<TransportType> allTransportType=repository.getAll();
        for(TransportType transportType:allTransportType){
            if(transportType.equals(a))
            {
                log.info("Transport type: "+a.getTransportTypeName()+" already exists!");
                return true;
            }
        }
        return false;
    }

    public TransportType getTransportTypeByName(String name){
        List<TransportType> allTransportType=repository.getAll();
        for(TransportType a:allTransportType){
            if(a.getTransportTypeName().equals(name))
            {
                return a;
            }
        }
        log.error("Transport type not found!");
        return null;
    }

    public ObservableList<TransportTypeListViewModel> getAllTransportTypes() {
        List<TransportType> l = repository.getAll();

        return FXCollections.observableList(
                l.stream().map(w -> new TransportTypeListViewModel(
                        w.getTransportTypeName()
                )).collect(Collectors.toList()));
    }
}
