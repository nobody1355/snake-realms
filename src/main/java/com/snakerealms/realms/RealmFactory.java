package com.snakerealms.realms;

import com.snakerealms.entities.Food;
import com.snakerealms.entities.Snake;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public interface RealmFactory {
    Snake createSnake();
    Food createFood();
    Pane createBackground();
    Color getBackgroundColor();
}
