package com.dataart.domain;

import java.io.Serializable;

public class Product implements Serializable {
    private long id;
    private String name;
    private long groupId;
    private long price;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getGroupId() {
        return groupId;
    }

    public long getPrice() {
        return price;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public void setPrice(long price) {
        this.price = price;
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
