package com.miniproject.grocery.services;


import com.miniproject.grocery.model.GroceryItem;
import com.miniproject.grocery.model.Response;
import com.miniproject.grocery.repository.ItemRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


@Service
public class GroceryServices {


    @Autowired
    ItemRepository itemRepository;


    public Response<String> addItem(String input) throws Exception {
        Response response = new Response();
        try {
            JSONObject jsonObject = new JSONObject(input);
            String item_name = jsonObject.optString("item_name");
            String item_type = jsonObject.optString("item_type");
            int price = jsonObject.optInt("price");
            String store = jsonObject.optString("store");
            String stock = jsonObject.optString("stock");
            String last_purchase = jsonObject.optString("last_purchase");
//            Date last_purchase = new SimpleDateFormat("dd-MM-yyyy").parse(last_purchase_str);

            //Validate item name
            List<GroceryItem> item_nameValidationResult = itemRepository.getItemByName(item_name);
            if (item_nameValidationResult.size() > 0) {
                response.setStatus("0");
                response.setMessage("Nama item sudah digunakan / item sudah terdaftar");
                response.setSuccess(false);
                return response;
            }
            String[] type = {"primer", "sekunder", "tersier"};
            List typeList = new ArrayList(Arrays.asList(type));
            if (!typeList.contains(item_type.toLowerCase())) {
                response.setStatus("0");
                response.setMessage("Silakan isi tipe item dengan : primer / sekunder / tersier");
                response.setSuccess(false);
                return response;
            }
            String[] stock_ar = {"ada", "habis"};
            List stockList = new ArrayList(Arrays.asList(stock_ar));
            if (!stockList.contains(stock.toLowerCase())) {
                response.setStatus("0");
                response.setMessage("Silakan isi stok dengan : ada / habis");
                response.setSuccess(false);
                return response;
            }
            itemRepository.addItem(item_name, item_type.toLowerCase(), price, store, stock, last_purchase);
            response.setStatus("200");
            response.setMessage("Item berhasil ditambahkan");
            response.setSuccess(true);
        } catch (Exception e) {
            response.setStatus("0");
            response.setMessage(e.getMessage());
            response.setSuccess(false);
        }
        return response;
    }

    public Response<List<GroceryItem>> getAllItem() throws Exception {
        Response response = new Response();
        List<GroceryItem> result = new ArrayList<>();
        try {
            result = itemRepository.findAll();
            response.setData(result);
            response.setStatus("200");
            response.setMessage("Berikut daftar Item :");
            response.setSuccess(true);
        } catch (Exception e) {
            response.setStatus("0");
            response.setMessage(e.getMessage());
            response.setSuccess(false);
        }
        return response;
    }

    public Response<GroceryItem> updateItem(String input) throws Exception {
        Response response = new Response();


        try {
            JSONObject jsonObject = new JSONObject(input);
            int id = jsonObject.getInt("item_id");
            String item_name = jsonObject.optString("item_name");
            String item_type = jsonObject.optString("item_type");
            int price = jsonObject.optInt("price");
            String store = jsonObject.optString("store");
            String stock = jsonObject.optString("stock");
            String last_purchase = jsonObject.optString("last_purchase");
//            Date last_purchase = new SimpleDateFormat("dd/MM/yyyy").parse(last_purchase_str);

            GroceryItem item = itemRepository.getItemById(id);
            if (item_name.isEmpty()) {
                item_name = item.getItem_name();
            } else {
                if (item.getItem_name().compareToIgnoreCase(item_name) != 0) {
                    //Validate item name
                    List<GroceryItem> item_nameValidationResult = itemRepository.getItemByName(item_name);
                    if (item_nameValidationResult.size() > 0) {
                        response.setStatus("0");
                        response.setMessage("Nama item sudah digunakan / item sudah terdaftar");
                        response.setSuccess(false);
                    }

                }
            }
            if (item_type.isEmpty()) {
                item_type = item.getItem_type();
            } else {
                if (item.getItem_type().compareToIgnoreCase(item_type) != 0) {
                    String[] type = {"primer", "sekunder", "tersier"};
                    List typeList = new ArrayList(Arrays.asList(type));
                    if (!typeList.contains(item_type.toLowerCase())) {
                        response.setStatus("0");
                        response.setMessage("Silakan isi tipe item dengan : primer / sekunder / tersier");
                        response.setSuccess(false);
                        return response;
                    }
                }
            }
            if (price == 0) {
                price = item.getPrice();
            }
            if (store.isEmpty()) {
                store = item.getStore();
            }
            if (stock.isEmpty()) {
                stock = item.getStock();
            } else {
                if (item.getStock().compareToIgnoreCase(stock) != 0) {
                    String[] stock_ar = {"ada", "habis"};
                    List stockList = new ArrayList(Arrays.asList(stock_ar));
                    if (!stockList.contains(stock.toLowerCase())) {
                        response.setStatus("0");
                        response.setMessage("Silakan isi stok dengan : ada / habis");
                        response.setSuccess(false);
                        return response;
                    }
                }
            }

            if (last_purchase.isEmpty()) {
                last_purchase = item.getLast_purchase();
            }


            itemRepository.updateItem(item_name, item_type, price, store, stock, last_purchase,id);
            GroceryItem result = itemRepository.getItemById(id);
            response.setData(result);
            response.setStatus("200");
            response.setMessage("Item berhasil diperbarui");
            response.setSuccess(true);

        } catch (Exception e) {
            response.setStatus("0");
            response.setMessage(e.getMessage());
            response.setSuccess(false);
        }
        return response;
    }

    public Response<GroceryItem> deleteItem(int id) {
        Response response = new Response();

        try {
            ;
            itemRepository.deleteItem(id);
            response.setStatus("200");
            response.setMessage("Item berhasil dihapus");
            response.setSuccess(true);
        } catch (Exception e) {
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
            response.setMessage("Berikut daftar Item :");
            response.setSuccess(true);
        } catch (Exception e) {
            response.setStatus("0");
            response.setMessage(e.getMessage());
            response.setSuccess(false);
        }
        return response;
    }


}
