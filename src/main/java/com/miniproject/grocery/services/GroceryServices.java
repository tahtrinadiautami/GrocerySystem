package com.miniproject.grocery.services;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.miniproject.grocery.model.GroceryItem;
import com.miniproject.grocery.model.Response;
import com.miniproject.grocery.repository.ItemRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class GroceryServices {


    @Autowired
    ItemRepository itemRepository;


    public Response<String> addItem(String input) throws Exception {
        Response response = new Response();
        try{
            JSONObject jsonObject = new JSONObject(input);
            String itemName = jsonObject.optString("itemName");
            int price = jsonObject.optInt("price");
            String store = jsonObject.optString("store");
            String stock = jsonObject.optString("stock");


            //Validate item name
            List<GroceryItem> itemNameValidationResult = itemRepository.getItemByName(itemName);
            if(itemNameValidationResult.size() > 0){
                response.setStatus("0");
                response.setMessage("Item name is already exist / used");
                response.setSuccess(false);
                return response;
            }
            itemRepository.addItem(itemName, price, store, stock);
            response.setStatus("200");
            response.setMessage("Item successfully added");
            response.setSuccess(true);
        }
        catch (Exception e){
            response.setStatus("0");
            response.setMessage(e.getMessage());
            response.setSuccess(false);
        }
        return response;
    }

    public Response<List<GroceryItem>> getAllItem() throws Exception{
        Response response = new Response();
        List<GroceryItem> result = new ArrayList<>();
        try{
            result = itemRepository.findAll();
            response.setData(result);
            response.setStatus("200");
            response.setMessage("Get All Item success");
            response.setSuccess(true);
        }
        catch(Exception e){
            response.setStatus("0");
            response.setMessage(e.getMessage());
            response.setSuccess(false);
        }
        return response;
    }

    public Response<GroceryItem> updateItem(String input) throws Exception{
        Response response = new Response();

        try{
            JSONObject jsonObject = new JSONObject();
            int id = jsonObject.optInt("itemId");
            String itemName = jsonObject.optString("itemName");
            int price = jsonObject.optInt("price");
            String store = jsonObject.optString("store");
            String stock = jsonObject.optString("stock");

            GroceryItem item = itemRepository.getItemById(id);
            if(item.getItemName() != itemName){
                //Validate item name
                List<GroceryItem> itemNameValidationResult = itemRepository.getItemByName(itemName);
                if(itemNameValidationResult.size() > 0){
                    response.setStatus("0");
                    response.setMessage("Item name is already exist / used");
                    response.setSuccess(false);
                }
            }
            else{
                if(itemName.isEmpty()) {
                    itemName = item.getItemName();
                }
                else if (price==0){
                    price=item.getPrice();
                }
                else if(store.isEmpty()) {
                    store = item.getStore();
                }
                else if(stock.isEmpty()) {
                    stock = item.getStock();
                }
                itemRepository.updateItem(itemName, price, store, stock);
                GroceryItem result = itemRepository.getItemById(id);
                response.setData(result);
                response.setStatus("200");
                response.setMessage("Item successfully updated");
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

    public Response<GroceryItem> deleteItem(String input){
        Response response = new Response();

        try {
            JSONObject jsonObject = new JSONObject(input);
            int id = jsonObject.optInt("itemId");
            itemRepository.deleteItem(id);
            response.setStatus("200");
            response.setMessage("Item successfully updated");
            response.setSuccess(true);
        }
        catch (Exception e){
            response.setStatus("0");
            response.setMessage(e.getMessage());
            response.setSuccess(false);
        }
        return response;
    }


}
