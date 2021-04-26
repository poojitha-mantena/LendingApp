package com.example.lendingapp.Model;

public class Model {

    private String imageUrl;
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

        this.imageUrl = imageUrl;
        this.mtitle = title;
        this.mdescription = description;
        this.mprice = price;
        this.mlocation = location;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getMtitle() {
        return mtitle;
    }

    public void setMtitle(String mtitle) {
        this.mtitle = mtitle;
    }

    public String getMdescription() {
        return mdescription;
    }

    public void setMdescription(String mdescription) {
        this.mdescription = mdescription;
    }

    public String getMprice() {
        return mprice;
    }

    public void setMprice(String mprice) {
        this.mprice = mprice;
    }

    public String getMlocation() {
        return mlocation;
    }

    public void setMlocation(String mlocation) {
        this.mlocation = mlocation;
    }
}
