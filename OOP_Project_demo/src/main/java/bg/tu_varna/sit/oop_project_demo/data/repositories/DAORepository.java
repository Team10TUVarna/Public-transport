package bg.tu_varna.sit.oop_project_demo.data.repositories;

import java.util.List;
import java.util.Optional;

public interface DAORepository<T> {
    void save(T obj);
    void update(T obj);
    void delete(T obj);
    Optional<T> getById(int id);
    List<T> getAll();
}
