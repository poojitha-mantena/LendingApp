package com.example.lendingapp.Model;

public class NewModel {

    private String ximageUrl;
    private String xtitle;
    private String xdescription;
    private String xprice;
    private String xlocation;

    public NewModel(){
        // empty constructor needed.
    }

    public NewModel(String imageUrl, String title, String description, String price, String location){

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

        this.ximageUrl = imageUrl;
        this.xtitle = title;
        this.xdescription = description;
        this.xprice = price;
        this.xlocation = location;
    }

    public void setXimageUrl(String ximageUrl) {
        this.ximageUrl = ximageUrl;
    }

    public void setXtitle(String xtitle) {
        this.xtitle = xtitle;
    }

    public void setXdescription(String xdescription) {
        this.xdescription = xdescription;
    }

    public void setXprice(String xprice) {
        this.xprice = xprice;
    }

    public void setXlocation(String xlocation) {
        this.xlocation = xlocation;
    }

    public String getXimageUrl() {
        return ximageUrl;
    }

    public String getXtitle() {
        return xtitle;
    }

    public String getXdescription() {
        return xdescription;
    }

    public String getXprice() {
        return xprice;
    }

    public String getXlocation() {
        return xlocation;
    }
}
