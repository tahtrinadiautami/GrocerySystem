package com.miniproject.grocery.controller;

import com.miniproject.grocery.model.GroceryItem;
import com.miniproject.grocery.model.Response;
import com.miniproject.grocery.repository.ItemRepository;
import com.miniproject.grocery.services.GroceryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/items")
public class GroceryController {

    @Autowired
    GroceryServices groceryServices;
    Logger logger = LoggerFactory.getLogger(GroceryController.class);

    @PostMapping("/addItem")
    public Response<String> addItem(@RequestBody String input) throws Exception, ParseException{
        logger.info("Service addItem : "+input);
        return groceryServices.addItem(input);
    }

    @PostMapping("/updateItem")
    public Response<GroceryItem> updateItem(@RequestBody String input) throws Exception, ParseException{
        logger.info("Service updateItem : "+input);
        return groceryServices.updateItem(input);
    }

    @PostMapping("/deleteItem")
    public Response<GroceryItem> deleteItem(@RequestParam int id) throws Exception {
        logger.info("Service deleteItem : "+id);
        return groceryServices.deleteItem(id);
    }

    @GetMapping("/getAllItem")
    public Response<List<GroceryItem>> getAllItem() throws Exception {
        logger.info("Service getAllItem ");
        return groceryServices.getAllItem();
    }

    @GetMapping("/getItemByStock")
    public Response<List<GroceryItem>> getItemByStock(@RequestParam String stock) throws Exception {
        logger.info("Service getItemByStock : "+stock);
        return groceryServices.getItemByStock(stock);
    }
}
