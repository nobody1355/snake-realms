package com.snakerealms.realms;

import com.snakerealms.entities.Snake;
import com.snakerealms.entities.Food;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class JungleRealmFactory implements RealmFactory {

    @Override
    public Snake createSnake() {
        return new Snake(Color.DARKGREEN, 5, 5);
    }

    @Override
    public Food createFood() {
        return new Food(Color.RED);
    }

    @Override
    public Pane createBackground() {
        Pane pane = new Pane();
        pane.setStyle("-fx-background-color: lightgreen;");
        return pane;
    }

    @Override
    public Color getBackgroundColor() {
        return Color.LIMEGREEN;
    }

}
