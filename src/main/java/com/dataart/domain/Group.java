package com.dataart.domain;

import java.io.Serializable;

public class Group implements Serializable {
    private long id;

    private String name;

    private int productCount;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getProductCount() {
        return productCount;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProductCount(int productCount) {
        this.productCount = productCount;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", productCount=" + productCount +
                '}';
    }
}
