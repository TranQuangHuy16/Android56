package com.example.android56_day6.models;

public class Product {
    private String id;
    private String productName;
    private String productThump;
    private float productPrice;
    private int productQuantity;

    public Product(String id, String productName, String productThump, float productPrice, int productQuantity) {
        this.id = id;
        this.productName = productName;
        this.productThump = productThump;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
    }

    public Product() {
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductThump() {
        return productThump;
    }

    public void setProductThump(String productThump) {
        this.productThump = productThump;
    }

    public float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(float productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", productName='" + productName + '\'' +
                ", productThump='" + productThump + '\'' +
                ", productPrice=" + productPrice +
                ", productQuantity=" + productQuantity +
                '}';
    }
}
