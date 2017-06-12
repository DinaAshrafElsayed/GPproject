/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.iti.shareit.view.managedbeans;

import eg.iti.shareit.common.Exception.ServiceException;
import eg.iti.shareit.model.dto.BorrowStateDto;
import eg.iti.shareit.model.dto.NotificationDto;
import eg.iti.shareit.model.entity.BorrowStateEntity;
import eg.iti.shareit.service.ItemTrackingService;
import eg.iti.shareit.service.NotificationService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.CollectionDataModel;
import javax.faces.model.DataModel;

/**
 *
 * @author Adel Zaid
 */
@Named(value = "itemStatusBean")
@RequestScoped
public class ItemStatusBean {

    @EJB
    private ItemTrackingService itemTrackingService;
    private List<BorrowStateDto> borrowStateDtos;
    private DataModel<BorrowStateDto> borrowStateDataModel;
    int ItemNum;

    public ItemTrackingService getItemTrackingService() {
        return itemTrackingService;
    }

    public void setItemTrackingService(ItemTrackingService itemTrackingService) {
        this.itemTrackingService = itemTrackingService;
    }

    public List<BorrowStateDto> getBorrowStateDtos() {
        return borrowStateDtos;
    }

    public void setBorrowStateDtos(List<BorrowStateDto> borrowStateDtos) {
        this.borrowStateDtos = borrowStateDtos;
    }

    public DataModel<BorrowStateDto> getBorrowStateDataModel() {
        return borrowStateDataModel;
    }

    public void setBorrowStateDataModel(DataModel<BorrowStateDto> borrowStateDataModel) {
        this.borrowStateDataModel = borrowStateDataModel;
    }

    public int getItemNum() {
        return ItemNum;
    }

    public void setItemNum(int ItemNum) {
        this.ItemNum = ItemNum;
    }

    @PostConstruct
    public void init() {
        try {
            if (SessionUtil.getUser() != null) {
                borrowStateDtos = itemTrackingService.getBorrowStatus(SessionUtil.getUser());
                borrowStateDataModel = new CollectionDataModel<>(borrowStateDtos);
                ItemNum = borrowStateDtos.size();
            }
        } catch (ServiceException ex) {
            Logger.getLogger(ItemStatusBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setIsBack() {
        try {
            BorrowStateDto borrowStateDto = borrowStateDataModel.getRowData();
            itemTrackingService.updateBorrowStatus(borrowStateDto);
            init();
        } catch (ServiceException ex) {
            Logger.getLogger(ItemStatusBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public InputStream getImage(String filename) throws FileNotFoundException {
        return new FileInputStream(new File(filename));
    }
}
