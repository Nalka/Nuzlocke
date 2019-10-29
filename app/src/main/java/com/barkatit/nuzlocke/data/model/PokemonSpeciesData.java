package com.barkatit.nuzlocke.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PokemonSpeciesData {

    private int id;

    private String name;

    @SerializedName("type")
    ArrayList<Type> types = new ArrayList<>();


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

    public ArrayList<Type> getTypes() {
        return types;
    }

    public void setTypes(ArrayList<Type> types) {
        this.types = types;
    }
}
