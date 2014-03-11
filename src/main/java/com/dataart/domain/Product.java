package com.dataart.domain;

public class Product {
    private long id;
    private String name;
    private long groupId;
    private String price;

    public Product(long id, String name, long groupId, String price) {
        this.id = id;
        this.name = name;
        this.groupId = groupId;
        this.price = price;
    }

    public Product() {

    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getGroupId() {
        return groupId;
    }

    public String getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", groupId=" + groupId +
                ", price='" + price + '\'' +
                '}';
    }
}
