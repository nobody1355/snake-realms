package com.snakerealms.entities;

public class SpeedBoostPower extends SnakePowerDecorator {
    public SpeedBoostPower(SnakePower decorated, long durationMillis) {
        super(decorated, durationMillis);
    }

    @Override
    public double getSpeedMultiplier() {
        return 2;
    }
}
