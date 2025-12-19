package com.snakerealms.realms;

import com.snakerealms.entities.Snake;
import com.snakerealms.entities.Food;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class SpaceRealmFactory implements RealmFactory {

    @Override
    public Snake createSnake() {
        return new Snake(Color.CYAN, 5, 5);
    }

    @Override
    public Food createFood() {
        return new Food(Color.ORANGE);
    }

    @Override
    public Pane createBackground() {
        Pane pane = new Pane();
        pane.setStyle("-fx-background-color: black;");
        return pane;
    }

    @Override
    public Color getBackgroundColor() {
        return Color.MIDNIGHTBLUE;
    }
}
