package com.example.chang_won_choi_fetch;

/***
 * A Class for list items retrieved from the API.
 ***/
public class Item {
    private int id;
    private int listId;
    private String name;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getListId() {
        return listId;
    }
    public void setListId(int listId) {
        this.listId = listId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
