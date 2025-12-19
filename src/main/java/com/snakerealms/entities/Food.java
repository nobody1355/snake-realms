package com.snakerealms.entities;

import javafx.scene.paint.Color;

public class Food {
    private int x, y;
    private Color color;

    public Food(Color color) {
        this.color = color;
        //default food coordinates
        this.x = 10;
        this.y = 10;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Color getColor() {
        return color;
    }
}
