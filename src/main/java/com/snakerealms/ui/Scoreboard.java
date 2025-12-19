package com.snakerealms.ui;

import com.snakerealms.core.GameObserver;
import javafx.scene.text.Text;

public class Scoreboard extends Text implements GameObserver {

    public Scoreboard() {
        setText("Score: 0");
        setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-fill: white;");
        setX(10);
        setY(25);
    }

    @Override
    public void onScoreChanged(int newScore) {
        setText("Score: " + newScore);
    }
}
