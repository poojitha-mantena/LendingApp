package com.example.lendingapp;

public class ModelBooks {

    String Book_name,book_price,book_location,book_description;

    ModelBooks(){

    }

    public ModelBooks(String book_name, String book_price, String book_location, String book_description) {
        Book_name = book_name;
        this.book_price = book_price;
        this.book_location = book_location;
        this.book_description = book_description;
    }

    public String getBook_name() {
        return Book_name;
    }

    public void setBook_name(String book_name) {
        Book_name = book_name;
    }

    public String getBook_price() {
        return book_price;
    }

    public void setBook_price(String book_price) {
        this.book_price = book_price;
    }

    public String getBook_location() {
        return book_location;
    }

    public void setBook_location(String book_location) {
        this.book_location = book_location;
    }

    public String getBook_description() {
        return book_description;
    }

    public void setBook_description(String book_description) {
        this.book_description = book_description;
    }
}
