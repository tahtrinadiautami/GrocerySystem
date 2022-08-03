package com.miniproject.grocery.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "groceryItem", schema ="gs")
public class GroceryItem {
    @Id
    @Column(name = "itemId", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int itemId;

    @NotNull
    @Column(name = "itemName")
    private String itemName;

    @NotNull
    @Column(name = "price")
    private int price;

    @NotNull
    @Column(name = "store")
    private String store;

    @NotNull
    @Column(name = "stock")
    private String stock;

    @NotNull
    @JsonFormat(pattern = "dd-MM-yyyy", timezone = "Asia/Jakarta")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @Column(name = "lastPurchase")
    private Date lastPurchase;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public Date getLastPurchase() {
        return lastPurchase;
    }

    public void setLastPurchase(Date lastPurchase) {
        this.lastPurchase = lastPurchase;
    }

    @Override
    public String toString() {
        return "GroceryItem{" +
                "itemId=" + itemId +
                ", itemName='" + itemName + '\'' +
                ", price=" + price +
                ", store='" + store + '\'' +
                ", stock='" + stock + '\'' +
                ", lastPurchase=" + lastPurchase +
                '}';
    }
}
