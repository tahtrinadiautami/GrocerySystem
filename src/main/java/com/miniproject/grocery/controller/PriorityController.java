package com.miniproject.grocery.controller;

import com.miniproject.grocery.model.GroceryItem;
import com.miniproject.grocery.model.Response;
import com.miniproject.grocery.services.PriorityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/priority")
public class PriorityController {

    @Autowired
    PriorityService priorityServices;
    Logger logger = LoggerFactory.getLogger(PriorityController.class);

    @GetMapping("/getShoppingList")
    public Response<List<GroceryItem>> getItemByStock(@RequestParam int budget) throws Exception {
        logger.info("Service getShoppingList : "+budget);
        return priorityServices.getShoppingList(budget);
    }
}
