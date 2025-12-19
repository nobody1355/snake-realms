package com.snakerealms.core;

public interface GameSubject {
    void addObserver(GameObserver observer);
    void removeObserver(GameObserver observer);
    void notifyScoreChanged(int newScore);
}
