package com.snakerealms.entities;

public abstract class SnakePowerDecorator implements SnakePower {
    protected SnakePower decoratedSnake;
    protected long startTime;
    protected long duration;

    public SnakePowerDecorator(SnakePower decoratedSnake, long duration) {
        this.decoratedSnake = decoratedSnake;
        this.duration = duration;
        this.startTime = System.currentTimeMillis();
    }

    public boolean isExpired() {
        return System.currentTimeMillis() - startTime > duration;
    }

    @Override
    public int getScoreMultiplier() {
        return decoratedSnake.getScoreMultiplier();
    }

    @Override
    public double getSpeedMultiplier() {
        return decoratedSnake.getSpeedMultiplier();
    }

    @Override
    public boolean isIntangible() {
        return decoratedSnake.isIntangible();
    }
}
