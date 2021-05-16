package com.example.lendingapp.Model;

public class Model {

    private String mimageUrl;
    private String mtitle;
    private String mdescription;
    private String mprice;
    private String mlocation;

    public Model(){
        // empty constructor needed.
    }

    public Model(String imageUrl, String title, String description, String price, String location){

        if (title.trim().equals("")){
            title = "Enter title";
        }
        if (description.trim().equals("")){
            description = "Enter description";
        }
        if (price.trim().equals("")){
            price = "Enter Price";
        }
        if (location.trim().equals("")){
            location = "Enter Locatiion";
        }

        this.mimageUrl = imageUrl;
        this.mtitle = title;
        this.mdescription = description;
        this.mprice = price;
        this.mlocation = location;
    }

    public String getImageUrl() {
        return mimageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.mimageUrl = imageUrl;
    }

    public String getMtitle() {
        return mtitle;
    }

    public void settitle(String title) {
        this.mtitle = title;
    }

    public String getMdescription() {
        return mdescription;
    }

    public void setdescription(String description) {
        this.mdescription = description;
    }

    public String getMprice() {
        return mprice;
    }

    public void setprice(String price) {
        this.mprice = price;
    }

    public String getMlocation() {
        return mlocation;
    }

    public void setlocation(String location) {
        this.mlocation = location;
    }
}
