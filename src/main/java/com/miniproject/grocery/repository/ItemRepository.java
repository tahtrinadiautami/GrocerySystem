package com.miniproject.grocery.repository;

import com.miniproject.grocery.model.GroceryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Repository
@Transactional
public interface ItemRepository extends JpaRepository<GroceryItem, Integer> {

    @Modifying
    @Query(value = "INSERT INTO gs.grocery_item(item_name,price,store,stock,last_purchase,last_updated) " +
            "VALUES(:item_name,:price,:store,:stock,CAST(:last_purchase AS timestamp),current_timestamp)", nativeQuery = true)
    void addItem(@Param("item_name") String item_name,
                 @Param("price") int price,
                 @Param("store") String store,
                 @Param("stock") String stock,
                 @Param("last_purchase") String last_purchase);

    @Modifying
    @Query(value = "UPDATE gs.grocery_item SET item_name=:item_name, price=:price, store=:store, stock=:stock, last_purchase=CAST(:last_purchase AS timestamp), last_updated=current_timestamp WHERE item_id =:item_id", nativeQuery = true)
    void updateItem(@Param("item_name") String item_name,
                    @Param("price") int price,
                    @Param("store") String store,
                    @Param("stock") String stock,
                    @Param("last_purchase") String last_purchase);

    @Modifying
    @Query(value = "DELETE FROM gs.grocery_item WHERE item_id=:item_id", nativeQuery = true)
    void deleteItem(@Param("item_id") int item_id);

    @Query(value = "SELECT * FROM gs.grocery_item WHERE item_name =:item_name", nativeQuery = true)
    List<GroceryItem> getItemByName(@Param("item_name") String item_name);

    @Query(value = "SELECT * FROM gs.grocery_item WHERE item_id =:item_id", nativeQuery = true)
    GroceryItem getItemById(@Param("item_id") int item_id);

    @Query(value = "SELECT * FROM gs.grocery_item WHERE store =:store", nativeQuery = true)
    List<GroceryItem> getItemByStore(@Param("store") String store);

    @Query(value = "SELECT * FROM gs.grocery_item WHERE stock =:stock", nativeQuery = true)
    List<GroceryItem> getItemByStock(@Param("stock") String stock);

    @Query(value = "SELECT * FROM gs.grocery_item WHERE last_purchase =:last_purchase", nativeQuery = true)
    List<GroceryItem> getItemByLastPurchase(@Param("last_purchase") Date last_purchase);

    @Query(value = "SELECT * FROM gs.grocery_item WHERE " +
            "lower(item_name) like lower(:item_name) AND " +
            "price like :price AND " +
            "store like :store AND " +
            "stock like :stock AND " +
            "CAST(last_purchase AS VARCHAR) like :last_purchase", nativeQuery = true)
    List<GroceryItem> getAllItem(@Param("item_name") String item_name,
                                  @Param("price") int price,
                                  @Param("store") String store,
                                  @Param("stock") String stock,
                                  @Param("last_purchase") String last_purchase);
}
