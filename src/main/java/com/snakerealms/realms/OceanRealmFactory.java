package com.snakerealms.realms;

import com.snakerealms.entities.Snake;
import com.snakerealms.entities.Food;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class OceanRealmFactory implements RealmFactory {

    @Override
    public Snake createSnake() {
        return new Snake(Color.ORANGERED, 5, 5);
    }

    @Override
    public Food createFood() {
        return new Food(Color.CORAL);
    }

    @Override
    public Pane createBackground() {
        Pane pane = new Pane();
        pane.setStyle("-fx-background-color: blue;");
        return pane;
    }

    @Override
    public Color getBackgroundColor() {
        return Color.DODGERBLUE;
    }
}
