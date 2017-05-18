package eg.iti.shareit.model.dao;


import eg.iti.shareit.common.dao.GenericDao;
import eg.iti.shareit.common.entity.GenericEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Mohamed on 2015/07/04.
 */
public abstract class GenericDaoImpl<T extends GenericEntity> implements GenericDao<T> {

    private Class<T> type;
    protected EntityManager entityManager;

    @PersistenceContext(unitName = "shareitPersistenceUnit")
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void setType(Class<T> type) {
        this.type = type;
    }

    public T get(Integer id) {
        if (id == null) {
            return null;
        } else {
            return entityManager.find(type, id);
        }
    }

    public List<T> getAll() {
        return entityManager.createQuery("Select entity From " + type.getName() + " entity").getResultList();
    }

    public void save(T object) {
        entityManager.persist(object);
    }

    public void delete(T object) {
        if (!entityManager.contains(object)) {
            object = entityManager.merge(object);
        }
        entityManager.remove(object);
    }

    public void merge(T object) {
        entityManager.merge(object);
    }


}
