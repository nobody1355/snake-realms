package com.snakerealms.entities;

public class IntangiblePower extends SnakePowerDecorator {
    public IntangiblePower(SnakePower decorated, long durationMillis) {
        super(decorated, durationMillis);
    }

    @Override
    public boolean isIntangible() {
        return true;
    }
}
