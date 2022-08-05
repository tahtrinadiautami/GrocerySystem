package com.miniproject.grocery.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "grocery_item", schema ="gs")
public class GroceryItem {
    @Id
    @Column(name = "item_id", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int item_id;

    @NotNull
    @Column(name = "item_name")
    private String item_name;

    @NotNull
    @Column(name = "item_type")
    private String item_type;

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
    @Column(name = "last_purchase")
    private String last_purchase;

    @NotNull
    @JsonFormat(pattern = "dd-MM-yyyy", timezone = "Asia/Jakarta")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @Column(name = "last_updated")
    private Date last_updated;

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getItem_type() {
        return item_type;
    }

    public void setItem_type(String item_type) {
        this.item_type = item_type;
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

    public String getLast_purchase() {
        return last_purchase;
    }

    public void setLast_purchase(String last_purchase) {
        this.last_purchase = last_purchase;
    }

    public Date getLast_updated() {
        return last_updated;
    }

    public void setLast_updated(Date last_updated) {
        this.last_updated = last_updated;
    }

    @Override
    public String toString() {
        return "GroceryItem{" +
                "item_id=" + item_id +
                ", item_name='" + item_name + '\'' +
                ", item_type='" + item_type + '\'' +
                ", price=" + price +
                ", store='" + store + '\'' +
                ", stock='" + stock + '\'' +
                ", last_purchase='" + last_purchase + '\'' +
                ", last_updated=" + last_updated +
                '}';
    }
}
