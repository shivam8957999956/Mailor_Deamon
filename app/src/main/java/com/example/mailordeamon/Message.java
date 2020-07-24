package com.example.mailordeamon;

public class Message {
    String text1;
    String text2;
    String text3;
    String image;

    public String getText3() {
        return text3;
    }

    public void setText3(String text3) {
        this.text3 = text3;
    }

    public String getText1() {
        return text1;
    }

    public void setText1(String text1) {
        this.text1 = text1;
    }

    public String getText2() {
        return text2;
    }

    public void setText2(String text2) {
        this.text2 = text2;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Message(String text1, String text2,String text3, String image) {
        this.text1 = text1;
        this.text2 = text2;
        this.text3 = text3;
        this.image = image;
    }

    public  Message(){

    }

}