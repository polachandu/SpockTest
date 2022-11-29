package com.example.spocktest.model;

import java.awt.*;

public class Renderer {

    private Palatte palatte;

    public Renderer(Palatte palatte){
        this.palatte = palatte;
    }
    public void drawLine(){

    }

    public Color getForeGroundColor(){
        return palatte.getPrimaryColor();
    }
}
