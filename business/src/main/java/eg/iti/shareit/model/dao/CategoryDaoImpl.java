/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.iti.shareit.model.dao;

import eg.iti.shareit.common.Exception.DatabaseRollbackException;
import eg.iti.shareit.model.entity.CategoryEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

/**
 *
 * @author Yousef
 */
@Stateless(mappedName = "CategoryDaoImpl")
public class CategoryDaoImpl extends GenericDaoImpl<CategoryEntity> implements CategoryDao {

    public CategoryDaoImpl() {
        super(CategoryEntity.class);
    }

    @Override
    public CategoryEntity getCategoryByName(String name) throws DatabaseRollbackException {
        System.out.println("++++++++ in get cat by name");
        Query query = getEntityManager().createQuery("Select c From CategoryEntity c where c.name = :name");
        query.setParameter("name", name);

        try {
            List<CategoryEntity> categoryList = query.getResultList();
            System.out.println("-------------------- in Cat Dao" + categoryList.size());
            if (categoryList != null && categoryList.size() == 1) {
                return categoryList.get(0);
            } else {
                throw new DatabaseRollbackException("category  Not Found");
            }
        } catch (Exception ex) {
            throw new DatabaseRollbackException(ex.getMessage());
        }
    }

    @Override
    public List<CategoryEntity> getAllCategories() throws DatabaseRollbackException {
        Query query = getEntityManager().createQuery("Select c From CategoryEntity c ", CategoryEntity.class);

        try {
            List<CategoryEntity> categoryList = query.getResultList();
            if (categoryList != null) {
                return categoryList;
            }else {
                throw new DatabaseRollbackException(" No Categories Found");
            }
        } 
        catch (PersistenceException ex) {
            throw new DatabaseRollbackException(ex.getMessage());
        }
    }

    //get all categories for navigaton bar
//    @Override
//    public List<CategoryEntity> AllCategoriesName() throws DatabaseRollbackException {
//        Query query = getEntityManager().createQuery("select c.name from CategoryEntity c");
//        List<CategoryEntity> categoryNames;
//        try {
//            categoryNames = query.getResultList();
//            if (categoryNames != null) {
//                for (int i = 0; i < categoryNames.size(); i++) {
//                   categoryNames.add(categoryNames.get(i));
//                }
//            } else {
//                throw new DatabaseRollbackException("No categories are found");
//            }
//        } catch (DatabaseRollbackException e) {
//            throw new DatabaseRollbackException(e.getMessage());
//        }
//        return categoryNames;
//    }
    
}
