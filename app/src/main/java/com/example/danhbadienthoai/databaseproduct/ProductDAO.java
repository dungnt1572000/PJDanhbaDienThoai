package com.example.danhbadienthoai.databaseproduct;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ProductDAO {
    @Insert
    void insertProduct(Product product);
    @Query("select * from product")
    List<Product> getListProduct();
    @Query("select * from product where name LIKE '%' || :productname || '%' ")
    List<Product> searchListString(String productname);
    @Query("select * from product where id = :proid")
    List<Product> searchList(int proid);
}
