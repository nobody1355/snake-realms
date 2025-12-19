package com.snakerealms.entities;

public class BaseSnakePower implements SnakePower {

    @Override
    public int getScoreMultiplier() {
        return 1;
    }

    @Override
    public double getSpeedMultiplier() {
        return 1.0;
    }

    @Override
    public boolean isIntangible() {
        return false;
    }
}
