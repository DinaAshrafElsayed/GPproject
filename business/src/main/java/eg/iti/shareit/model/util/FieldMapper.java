package eg.iti.shareit.model.util;

import org.dozer.CustomFieldMapper;
import org.dozer.classmap.ClassMap;
import org.dozer.fieldmap.FieldMap;
import org.hibernate.collection.internal.PersistentSet;


/**
 * Created by Mohamed_2 on 11/20/2015.
 */
public class FieldMapper implements CustomFieldMapper {


    @Override
    public boolean mapField(Object source, Object destination, Object sourceFieldValue, ClassMap classMap, FieldMap fieldMapping) {

        // Check if field is a Hibernate PersistentSet
        if (!(sourceFieldValue instanceof PersistentSet)) {
            // Allow dozer to map as normal
            return false;
        }

        // Check if field is already initialized
        if (((PersistentSet) sourceFieldValue).wasInitialized()) {
            // Allow dozer to map as normal
            return false;
        }

        // Set destination to null, and tell dozer that the field is mapped
        destination = null;
        return true;
    }
}
