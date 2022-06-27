package model;

import java.util.HashMap;

public class Restaurant extends Location {

    private String name;


    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }

    public Restaurant(String name, double latitude, double longitude  )
    {
        super(latitude, longitude, name);
        this.name = name;
    }
}
