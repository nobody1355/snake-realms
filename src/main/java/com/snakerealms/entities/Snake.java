package com.snakerealms.entities;

import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.List;

public class Snake {
    //inner class for snake body segment
    public static class Segment {
        private int gridX, gridY;

        public Segment(int gridX, int gridY) {
            this.gridX = gridX;
            this.gridY = gridY;
        }

        public int getX() {
            return gridX;
        }

        public int getY() {
            return gridY;
        }

        public void setPosition(int x, int y) {
            this.gridX = x;
            this.gridY = y;
        }
    }

    protected List<Segment> body;
    protected Color color;

    public Snake(Color color, int startX, int startY) {
        this.color = color;
        body = new ArrayList<>();
        body.add(new Segment(startX, startY));
    }

    protected Snake(Color color, List<Segment> sharedBody) {
        this.color = color;
        this.body = sharedBody;
    }

    public List<Segment> getBodySegments() {
        return body;
    }

    public Color getColor() {
        return color;
    }

    public Segment getHead() {
        return body.get(0);
    }

    //check segment collision with grid position
    public boolean isCollision(int x, int y) {
        return body.stream().anyMatch(seg -> seg.getX() == x && seg.getY() == y);
    }

    //add a new head at (x,y) and remove tail
    public void move(int x, int y) {
        body.add(0, new Segment(x, y));
        body.remove(body.size() - 1);
    }

    //add a new head at (x,y); NOT remove tail
    public void grow(int x, int y) {
        body.add(0, new Segment(x, y));
    }

    public double getSpeedMultiplier() {
        return 1.0;
    }

    public boolean isIntangible() {
        return false;
    }
}
