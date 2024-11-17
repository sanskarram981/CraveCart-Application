package com.projects.cravecart.controller;


import com.projects.cravecart.dto.MenuItemRequest;
import com.projects.cravecart.entity.MenuItem;
import com.projects.cravecart.dto.MenuItemResponse;
import com.projects.cravecart.service.MenuService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @PostMapping
    public ResponseEntity<MenuItemResponse> addMenuItem(@RequestBody MenuItemRequest menuItemRequest){
          return new ResponseEntity<>(this.menuService.addMenuItem(menuItemRequest), HttpStatus.CREATED);
    }

    @GetMapping("/{menuId}")
    public ResponseEntity<MenuItemResponse> getMenuItem(@PathVariable ObjectId menuId){
        MenuItemResponse menuItemResponse = this.menuService.getMenuItem(menuId);
        if(menuItemResponse != null)
            return new ResponseEntity<>(menuItemResponse,HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<MenuItemResponse>> getAllMenuItems(){
        List<MenuItemResponse> menuItemResponses = this.menuService.getAllMenuItems();
        if(menuItemResponses != null)
            return new ResponseEntity<>(menuItemResponses,HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{menuId}")
    public ResponseEntity<Void> deleteMenuItem(@PathVariable ObjectId menuId) {
        boolean isDeleted = this.menuService.deleteMenuItem(menuId);
        if(isDeleted)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{menuId}")
    public ResponseEntity<MenuItemResponse> updateMenuItem(@PathVariable ObjectId menuId, @RequestBody MenuItemRequest menuItemRequest){
        MenuItemResponse menuItemResponse = this.menuService.updateMenuItem(menuId,menuItemRequest);
        if(menuItemResponse != null)
            return new ResponseEntity<>(menuItemResponse,HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
