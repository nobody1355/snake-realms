package com.snakerealms.core;

import com.snakerealms.entities.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class GameRenderer {

    /*
    gc = GraphicsContext
    snake = current snake
    food = current food
    backgroundColor = realm background
    foodX = food grid X
    foodY = food grid Y
    tileSize = tile size
    activePowerText = optional power-up text
     */
    
    public void render(GraphicsContext gc, Snake snake, Food food, Color backgroundColor,
                       int foodX, int foodY, int tileSize, String activePowerText) {
        double offset = 1;

        //clear background
        gc.setFill(backgroundColor);
        gc.fillRect(0, 0, 30 * tileSize, 20 * tileSize);

        //draw food
        gc.setFill(food.getColor());
        gc.fillOval(foodX * tileSize + offset, foodY * tileSize + offset,
                tileSize - 2 * offset, tileSize - 2 * offset);

        //draw snake
        gc.setFill(snake.getColor());
        for (Snake.Segment seg : snake.getBodySegments()) {
            int x = seg.getX();
            int y = seg.getY();
            gc.fillRect(x * tileSize + offset, y * tileSize + offset,
                    tileSize - 2 * offset, tileSize - 2 * offset);
        }

        //draw active power text, if any
        if (activePowerText != null && !activePowerText.isEmpty()) {
            gc.setFill(Color.YELLOW);
            gc.setFont(new Font(20));
            gc.fillText(activePowerText, 10, 50);
        }
    }
}
