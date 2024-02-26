package com.assignment.pokemongame.model;

import com.google.gson.annotations.SerializedName;

public class Pokemon {
    int id;
    String name;
    int height;
    @SerializedName(value = "weight")
    int dinoWeight;

    String url;

    Species species;

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

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getDinoWeight() {
        return dinoWeight;
    }

    public void setDinoWeight(int weight) {
        this.dinoWeight = weight;
    }

    public Species getSpecies() {
        return species;
    }

    public void setSpecies(Species species) {
        this.species = species;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
