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
        GroceryItem groceryItem;
        try{
            result = itemRepository.getItemByStock("habis");
            result.stream()
                    .filter(p -> p.getItem_type().compareToIgnoreCase( "primer")==0)
                    .forEach((p) -> {
                        if(p.getPrice()<= budget[0]) {
                            System.out.println("price :"+p.getPrice());
                            priorities.add(p);
                            budget[0] -= p.getPrice();
                        }
                    });
            result.stream()
                    .filter(p -> p.getItem_type().compareToIgnoreCase( "sekunder")==0)
                    .forEach((p) -> {
                        if(p.getPrice()<= budget[0]) {
                            System.out.println("price :"+p.getPrice());
                            priorities.add(p);
                            budget[0] -= p.getPrice();
                        }
                    });
            result.stream()
                    .filter(p -> p.getItem_type().compareToIgnoreCase( "tersier")==0)
                    .forEach((p) -> {
                        if(p.getPrice()<= budget[0]) {
                            System.out.println("price :"+p.getPrice());
                            priorities.add(p);
                            budget[0] -= p.getPrice();
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
                response.setMessage("Tidak ada daftar belanja");
                response.setSuccess(true);
            }
            else{
                response.setData(result);
                response.setStatus("200");
                response.setMessage("Berikut daftar belanja : ");
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
