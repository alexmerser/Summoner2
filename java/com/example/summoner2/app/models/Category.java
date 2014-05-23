package com.example.summoner2.app.models;

/**
 * Created by alex on 14.05.14.
 */
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Category implements Serializable {

    private long id;
    private String name;
    private String descr;
    private List<League> itemList;

    public Category(long id, String name, String descr) {
        this.id = id;
        this.name = name;
        this.descr = descr;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public List<League> getItemList() {
        return itemList;
    }

    public void setItemList(List<League> itemList) {
        this.itemList = itemList;
    }



}
