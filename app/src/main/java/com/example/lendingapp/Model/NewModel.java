package com.example.lendingapp.Model;

public class NewModel {

    private String image;
    private String title;
    private String description;
    private String price;
    private String location;

    public NewModel(){
        // empty constructor needed.
    }

    public NewModel(String image, String title, String description, String price, String location){

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

        this.image = image;
        this.title = title;
        this.description = description;
        this.price = price;
        this.location = location;
    }

    public void setimage(String image) {
        this.image = image;
    }

    public void settitle(String title) {
        this.title = title;
    }

    public void setdescription(String description) {
        this.description = description;
    }

    public void setprice(String price) {
        this.price = price;
    }

    public void setlocation(String location) {
        this.location = location;
    }

    public String getimage() {
        return image;
    }

    public String gettitle() {
        return title;
    }

    public String getdescription() {
        return description;
    }

    public String getprice() {
        return price;
    }

    public String getlocation() {
        return location;
    }
}
