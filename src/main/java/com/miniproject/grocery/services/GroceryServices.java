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
public class GroceryServices {


    @Autowired
    ItemRepository itemRepository;


    public Response<String> addItem(String input) throws Exception {
        Response response = new Response();
        try{
            JSONObject jsonObject = new JSONObject(input);
            String item_name = jsonObject.optString("item_name");
            int price = jsonObject.optInt("price");
            String store = jsonObject.optString("store");
            String stock = jsonObject.optString("stock");
            String last_purchase =  jsonObject.optString("last_purchase");
//            Date last_purchase = new SimpleDateFormat("dd-MM-yyyy").parse(last_purchase_str);

            //Validate item name
            List<GroceryItem> item_nameValidationResult = itemRepository.getItemByName(item_name);
            if(item_nameValidationResult.size() > 0){
                response.setStatus("0");
                response.setMessage("Item name is already exist / used");
                response.setSuccess(false);
                return response;
            }
           itemRepository.addItem(item_name, price, store, stock, last_purchase);
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
            int id = jsonObject.optInt("item_id");
            String item_name = jsonObject.optString("item_name");
            int price = jsonObject.optInt("price");
            String store = jsonObject.optString("store");
            String stock = jsonObject.optString("stock");
            String last_purchase =  jsonObject.optString("last_purchase");
//            Date last_purchase = new SimpleDateFormat("dd/MM/yyyy").parse(last_purchase_str);

            GroceryItem item = itemRepository.getItemById(id);
            if(item.getItem_name() != item_name){
                //Validate item name
                List<GroceryItem> item_nameValidationResult = itemRepository.getItemByName(item_name);
                if(item_nameValidationResult.size() > 0){
                    response.setStatus("0");
                    response.setMessage("Item name is already exist / used");
                    response.setSuccess(false);
                }
            }
            else{
                if(item_name.isEmpty()) {
                    item_name = item.getItem_name();
                }
                else if (price==0){
                    price=item.getPrice();
                }
                else if(store.isEmpty()) {
                    store = item.getStore();
                }
                else if(stock.isEmpty()) {
                    stock = item.getStock();
                } else if (last_purchase.isEmpty()) {
                    last_purchase = item.getLast_purchase();
                }
                itemRepository.updateItem(item_name, price, store, stock, last_purchase);
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

    public Response<GroceryItem> deleteItem(int id){
        Response response = new Response();

        try {;
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

    public Response<List<GroceryItem>> getItemByStock(String stock) {
        Response response = new Response();
        List<GroceryItem> result = new ArrayList<>();

        try {
            result = itemRepository.getItemByStock(stock);
            response.setData(result);
            response.setStatus("200");
            response.setMessage("Get item success");
            response.setSuccess(true);
        } catch (Exception e) {
            response.setStatus("0");
            response.setMessage(e.getMessage());
            response.setSuccess(false);
        }
        return response;
    }


}
