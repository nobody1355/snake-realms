package com.snakerealms.entities;

public class ScoreMultiplyPower extends SnakePowerDecorator {
    public ScoreMultiplyPower(SnakePower decorated, long durationMillis) {
        super(decorated, durationMillis);
    }

    @Override
    public int getScoreMultiplier() {
        return 2;
    }
}
