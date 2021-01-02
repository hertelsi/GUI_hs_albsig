package com.example.einkaufsliste.models;

import java.io.Serializable;

public class Shop  implements Serializable {
    private static final long serialVersionUID = 1L;
    long id;
    String location;
    String name;

    public Shop(long id, String location, String name) {
        this.id = id;
        this.location = location;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
