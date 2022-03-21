package com.example.fortnox.entities;

import java.util.Objects;

public class Box {

    private Integer id;
    private String name;
    private Double weight;
    private String color;
    private String country;

    public Box() {
    }

    public Box(String name, Double weight, String color, String country) {
        this.name = name;
        this.weight = weight;
        this.color = color;
        this.country = country;
    }

    public Box(Integer id, String name, Double weight, String color, String country) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.color = color;
        this.country = country;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Box)) return false;
        Box box = (Box) o;
        return getId().equals(box.getId())
                && getName().equals(box.getName())
                && getWeight().equals(box.getWeight())
                && getColor().equals(box.getColor())
                && getCountry().equals(box.getCountry());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getWeight(), getColor(), getCountry());
    }

    @Override
    public String toString() {
        return "Box{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", color='" + color + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
