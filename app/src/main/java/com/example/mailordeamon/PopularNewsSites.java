package com.example.mailordeamon;

public class PopularNewsSites {
    String site;
    String name;
    String image;

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public PopularNewsSites(String site, String name, String image) {
        this.site = site;
        this.name = name;
        this.image = image;
    }

    public PopularNewsSites(){



    }
}
