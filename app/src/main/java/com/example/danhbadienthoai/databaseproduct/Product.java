package com.example.danhbadienthoai.databaseproduct;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "product")
public class Product  implements Serializable {
    @PrimaryKey(autoGenerate = true)
    int id;
    String name;
    String url;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Product(int id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
    }

}
