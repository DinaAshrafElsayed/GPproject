package eg.iti.shareit.model.dao;

import eg.iti.shareit.common.dao.GenericDao;
import eg.iti.shareit.common.entity.GenericEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import org.hibernate.annotations.Fetch;

/**
 * Created by Mohamed on 2015/07/04.
 */
public abstract class GenericDaoImpl<T extends GenericEntity> implements GenericDao<T> {

    private Class<T> type;

    @PersistenceContext(unitName = "shareitPersistenceUnit")
    protected EntityManager em;

    public GenericDaoImpl(Class<T> type) {
        this.type = type;
    }

    public T get(Object id) {
        if (id == null) {
            return null;
        } else {
            return em.find(type, id);
        }
    }

    public List<T> getAll() {
        return em.createQuery("From " + type.getSimpleName() + "").getResultList();

    }

    public void save(T object) {
        em.persist(object);
    }

    public void delete(T object) {
        if (!em.contains(object)) {
            object = em.merge(object);
        }
        em.remove(object);
    }

    public void update(T object) {
        em.merge(object);
    }

    public EntityManager getEntityManager() {
        return em;
    }

}
