package Pegas.DAO;

import java.util.List;
import java.util.Optional;

public interface DAO<T> {
    List<T> findAll();
    boolean delete(Long id);
    T save(T ticket);
    Optional<T> findById(Long id);
    boolean update(Long id, String name);
}
