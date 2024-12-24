package com.ozaslan.e_commerce_backend.model;


import java.util.Objects;

public class Size {

    private String name;
    private int quantity;


    public Size(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public Size() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Size size = (Size) o;
        return quantity == size.quantity && Objects.equals(name, size.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, quantity);
    }

    @Override
    public String toString() {
        return "Size{" +
                "name='" + name + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
