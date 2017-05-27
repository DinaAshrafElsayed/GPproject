package eg.iti.shareit.common.dao;

import eg.iti.shareit.common.entity.GenericEntity;

import java.util.List;

public interface GenericDao<T extends GenericEntity> {

    T get(Object id);

    List<T> getAll();

    void save(T object);

    void update(T object);

    void delete(T object);
}
