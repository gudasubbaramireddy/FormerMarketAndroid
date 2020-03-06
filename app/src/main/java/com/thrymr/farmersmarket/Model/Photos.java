package com.thrymr.farmersmarket.Model;

public class Photos {
    private int formerImage;
    private String name;

    public Photos(int formerImage, String name) {
        this.formerImage = formerImage;
        this.name = name;
    }

    public int getFormerImage() {
        return formerImage;
    }

    public void setFormerImage(int formerImage) {
        this.formerImage = formerImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
