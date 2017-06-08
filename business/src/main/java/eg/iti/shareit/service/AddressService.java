/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.iti.shareit.service;

import eg.iti.shareit.common.Exception.DatabaseRollbackException;
import eg.iti.shareit.common.Exception.ServiceException;
import eg.iti.shareit.model.dao.AddressDao;
import eg.iti.shareit.model.dto.AddressDto;
import eg.iti.shareit.model.entity.AddressEntity;
import eg.iti.shareit.model.util.MappingUtil;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Stateless;

/**
 *
 * @author Dina Ashraf
 */
@Singleton
public class AddressService {

    @EJB(beanName = "AddressDaoImpl")
    private AddressDao addressDao;
    @EJB(beanName = "MappingUtil")
    private MappingUtil mappingUtil;

    private static final Logger logger = Logger.getLogger(AddressService.class.getName());

    public List<AddressDto> getAllAddresses() throws ServiceException {
        try {
            List<AddressEntity> addresses = addressDao.getAll();
            return mappingUtil.getDtoList(addresses, AddressDto.class);
        } catch (DatabaseRollbackException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            throw new ServiceException(ex.getMessage());
        }
    }
}
