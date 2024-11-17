package com.projects.cravecart.service;


import com.projects.cravecart.dto.MenuItemRequest;
import com.projects.cravecart.entity.MenuItem;
import com.projects.cravecart.dto.MenuItemResponse;
import com.projects.cravecart.repository.MenuRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    public MenuItemResponse addMenuItem(MenuItemRequest menuItemRequest){
        return transform(this.menuRepository.save(transform(menuItemRequest)));
    }

    public MenuItemResponse getMenuItem(ObjectId menuId){
        MenuItem persistedMenuItem = this.menuRepository.findById(menuId).orElse(null);
        if(persistedMenuItem != null)
            return transform(persistedMenuItem);
        else
            return null;
    }

    public List<MenuItemResponse> getAllMenuItems(){
        List<MenuItem> persistedMenuItems = this.menuRepository.findAll();
        List<MenuItemResponse> menuItemResponses = null;
        if(!persistedMenuItems.isEmpty()) {
            menuItemResponses = persistedMenuItems.stream().map(this::transform).collect(Collectors.toList());
            return menuItemResponses;
        }
        else
            return menuItemResponses;
    }

    public boolean deleteMenuItem(ObjectId menuId){
        Optional<MenuItem> persistedMenuItem = this.menuRepository.findById(menuId);
        if(persistedMenuItem.isPresent()){
            this.menuRepository.deleteById(menuId);
            return true;
        }
        return false;
    }

    public MenuItemResponse updateMenuItem(ObjectId menuId,MenuItemRequest menuItemRequest){
        MenuItem menuItemPersisted = this.menuRepository.findById(menuId).orElse(null);
        if(menuItemPersisted != null) {
            validateAndTransform(menuItemPersisted, transform(menuItemRequest));
            return transform(this.menuRepository.save(menuItemPersisted));
        }
        return null;
    }

    private void validateAndTransform(MenuItem menuItemOld,MenuItem menuItemNew){
        menuItemOld.setName(menuItemNew != null && !menuItemNew.getName().trim().isEmpty()?menuItemNew.getName():menuItemOld.getName());
        menuItemOld.setDescription(menuItemNew != null && !menuItemNew.getDescription().trim().isEmpty()?menuItemNew.getDescription():menuItemOld.getDescription());
        menuItemOld.setPrice(menuItemNew != null && menuItemNew.getPrice() != null ? menuItemNew.getPrice():menuItemOld.getPrice());
        menuItemOld.setVegetarian(menuItemNew != null && menuItemNew.getVegetarian() != null ? menuItemNew.getVegetarian():menuItemOld.getVegetarian());
    }

    private MenuItemResponse transform(MenuItem menuItem){
        MenuItemResponse menuItemResponse = new MenuItemResponse();
        menuItemResponse.setId(menuItem.getId().toString());
        menuItemResponse.setName(menuItem.getName());
        menuItemResponse.setDescription(menuItem.getDescription());
        menuItemResponse.setPrice(menuItem.getPrice());
        menuItemResponse.setVegetarian(menuItem.getVegetarian());
        return menuItemResponse;
    }

    private MenuItem transform(MenuItemRequest menuItemRequest){
        MenuItem menuItem = new MenuItem();
        menuItem.setName(menuItemRequest.getName());
        menuItem.setDescription(menuItemRequest.getDescription());
        menuItem.setPrice(menuItemRequest.getPrice());
        menuItem.setVegetarian(menuItemRequest.getVegetarian());
        return menuItem;
    }
}
