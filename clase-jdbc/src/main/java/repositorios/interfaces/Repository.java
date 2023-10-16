package repositorios.interfaces;

import java.util.List;

public interface Repository<T> {
    void add(T entidad);

    T update(T entidad);

    T delete(int codigo);

    T getById(int codigo);

    List<T> getAll();
}
