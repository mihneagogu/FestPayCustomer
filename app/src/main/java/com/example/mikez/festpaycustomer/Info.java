package com.example.mikez.festpaycustomer;

/**
 * Created by mikez on 7/18/2017.
 */

public class Info {

    private int Id;
    private String text;

    public Info(int Id, String text){

        this.Id = Id;
        this.text = text;

    }

    public int getItemId(){
        return Id;
    }
    public String getItemText(){
        return text;
    }
}
