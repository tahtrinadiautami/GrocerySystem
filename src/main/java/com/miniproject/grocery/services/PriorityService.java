package com.miniproject.grocery.services;

import com.miniproject.grocery.model.GroceryItem;
import com.miniproject.grocery.model.Response;
import com.miniproject.grocery.repository.ItemRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PriorityService {
    @Autowired
    ItemRepository itemRepository;

    public List<GroceryItem> priority(int b) throws Exception {
        List<GroceryItem> result = new ArrayList<>();
        final int[] budget = {b};
        List<GroceryItem> priorities = new ArrayList();
        try{
            result = itemRepository.getItemByStock("Habis");
            result.stream().filter(p -> p.getItem_type() == "primer")
                    .map(GroceryItem::getItem_id)
                    .forEach(id -> {
                        GroceryItem item = itemRepository.getItemById(id);
                        if(item.getPrice() < budget[0]) {
                            priorities.add(item);
                            budget[0] -= item.getPrice();
                        }
                    });
            result.stream().filter(p -> p.getItem_type() == "sekunder")
                    .map(GroceryItem::getItem_id)
                    .forEach(id -> {
                        GroceryItem item = itemRepository.getItemById(id);
                        if(item.getPrice() < budget[0]) {
                            priorities.add(item);
                            budget[0] -= item.getPrice();
                        }
                    });
            result.stream().filter(p -> p.getItem_type() == "tersier")
                    .map(GroceryItem::getItem_id)
                    .forEach(id -> {
                        GroceryItem item = itemRepository.getItemById(id);
                        if(item.getPrice() < budget[0]) {
                            priorities.add(item);
                            budget[0] -= item.getPrice();
                        }
                    });
            result = priorities;

        }
        catch (Exception e){
           e.getMessage();
        }
        return result;
    }

    public Response<List<GroceryItem>> getShoppingList(int budget) throws Exception{
        Response response = new Response();
        List<GroceryItem> result = new ArrayList<>();

        try{
            result = priority(budget);
            if(result.isEmpty()){
                response.setStatus("200");
                response.setMessage("No shopping list yet");
                response.setSuccess(true);
            }
            else{
                response.setData(result);
                response.setStatus("200");
                response.setMessage("You have the shopping list");
                response.setSuccess(true);
            }
        }
        catch (Exception e){
            response.setStatus("0");
            response.setMessage(e.getMessage());
            response.setSuccess(false);
        }
        return response;
    }
}
