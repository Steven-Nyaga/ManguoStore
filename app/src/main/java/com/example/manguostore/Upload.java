package com.example.manguostore;

public class Upload {
    private String category;
    private String sub_category;
    private String fit;
    private String shoe_size;
    private String number;
    private String price;
    private String imageUri;

    public Upload(){

    }

    public Upload(String category, String sub_category, String fit, String shoe_size, String number, String price, String imageUri){

        if (fit.trim().equals("")) {
            fit = "N/A";
        }
        if (shoe_size.trim().equals("")) {
            shoe_size = "N/A";
        }

        this.category = category;
        this.sub_category = sub_category;
        this.fit =  fit;
        this.shoe_size = shoe_size;
        this.number = number;
        this.price = price;
        this.imageUri = imageUri;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSub_category() {
        return sub_category;
    }

    public void setSub_category(String sub_category) {
        this.sub_category = sub_category;
    }

    public String getFit() {
        return fit;
    }

    public void setFit(String fit) {
        this.fit = fit;
    }

    public String getShoe_size() {
        return shoe_size;
    }

    public void setShoe_size(String shoe_size) {
        this.shoe_size = shoe_size;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }
}

