/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.iti.shareit.service;

import eg.iti.shareit.common.Exception.DatabaseException;
import eg.iti.shareit.common.Exception.DatabaseRollbackException;
import eg.iti.shareit.common.Exception.ServiceException;
import eg.iti.shareit.model.dao.ActivityDao;
import eg.iti.shareit.model.dao.ActivityDaoBTM;
import eg.iti.shareit.model.dto.ActivityDto;
import eg.iti.shareit.model.dto.NotificationDto;
import eg.iti.shareit.model.entity.ActivityEntity;
import eg.iti.shareit.model.util.MappingUtil;
import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.management.Notification;

/**
 *
 * @author Adel Zaid
 */
@Stateless
public class ActivityService {

    private static final Logger logger = Logger.getLogger(ActivityService.class.getName());

    @EJB
    private ActivityDao activityDao;
    @EJB
    private ActivityDaoBTM activityDaoBTM;
    @EJB(beanName = "MappingUtil")
    private MappingUtil mappingUtil;

    public List<ActivityDto> getAllActivities() throws ServiceException {
        List<ActivityEntity> allActivities = activityDao.getAll();
        return mappingUtil.< ActivityEntity, ActivityDto>getDtoList(allActivities, ActivityDto.class);
    }

    public ActivityDto getActivity(int id) throws ServiceException {
        try {
            ActivityEntity get = activityDao.get(new BigDecimal(id));
            ActivityDto ActivityDto = mappingUtil.getDto(get, ActivityDto.class);
            return ActivityDto;

        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);

            throw new ServiceException(e.getMessage());
        }
    }

    public void deleteActivity(int id) throws ServiceException {
        activityDao.delete(new BigDecimal(id));
    }

    public void acceptRequest(ActivityDto activityDto) throws ServiceException {
        try {
            activityDaoBTM.acceptRequest(activityDto);

        } catch (DatabaseException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            throw new ServiceException(ex.getMessage());
        }
    }

    public NotificationDto getNotification(int id) throws ServiceException {
        try {
            NotificationDto notificationDto = activityDaoBTM.getNotification(id);
            return notificationDto;
        } catch (DatabaseException ex) {

            logger.log(Level.SEVERE, ex.getMessage(), ex);
            throw new ServiceException(ex.getMessage());
        }
    }
}
