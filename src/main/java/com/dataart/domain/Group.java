package com.dataart.domain;

public class Group {
    private long id;

    private String name;

    private int productCount;

    public Group() {
    }

    public Group(long id, String name, int productCount) {
        this.id = id;
        this.name = name;
        this.productCount = productCount;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getProductCount() {
        return productCount;
    }

    @Override
    public String toString() {
        return id + ": " + name + " (" + productCount + ")";
    }
}
