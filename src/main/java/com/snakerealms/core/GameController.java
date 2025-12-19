package com.snakerealms.core;

import com.snakerealms.entities.*;
import com.snakerealms.realms.RealmFactory;


import java.util.ArrayList;
import java.util.List;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class GameController implements GameSubject {

    private List<GameObserver> observers = new ArrayList<>();

    private Snake snake;
    private Food food;
    private Direction direction;
    private Point newHead;
    private Point foodPos;
    private int tileSize;
    private final int WIDTH = 30;
    private final int HEIGHT = 20;
    private GameRenderer renderer;
    private boolean isPaused = false;
    private static final String HIGH_SCORE_FILE = "highscore.txt";
    private static int highScore = 0;
    private int score = 0;

    private SnakePower snakePower = new BaseSnakePower();
    private String activePowerText = "";
    private long powerDuration = 10_000; // 10 seconds
    private int foodEaten = 0;

    private static GameController instance;

    @Override
    public void addObserver(GameObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(GameObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyScoreChanged(int newScore) {
        for (GameObserver observer : observers) {
            observer.onScoreChanged(newScore);
        }
    }

    public void loadHighScore() {
        try (java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader(HIGH_SCORE_FILE))) {
            highScore = Integer.parseInt(reader.readLine());
        } catch (Exception e) {
            highScore = 0;
        }
    }

    private void saveHighScore() {
        try (java.io.BufferedWriter writer = new java.io.BufferedWriter(new java.io.FileWriter(HIGH_SCORE_FILE))) {
            writer.write(String.valueOf(highScore));
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    public void increaseScore(int points) {
        score += points;
        if (score > highScore) {
            highScore = score;
            saveHighScore();
        }
        notifyScoreChanged(score);
    }

    public static int getHighScore() {
        return highScore;
    }

    public int getScore() {
        return score;
    }

    public void resetScore() {
        score = 0;
        foodEaten = 0;
        snakePower = new BaseSnakePower();
        activePowerText = "";
    }

    private GameController() {
    }

    public static GameController getInstance() {
        if (instance == null) {
            instance = new GameController();
        }
        return instance;
    }

    class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    public void startGame(RealmFactory factory, Stage stage) {

        loadHighScore();
        resetScore();

        javafx.scene.layout.Pane root = new javafx.scene.layout.Pane();

        javafx.scene.canvas.Canvas canvas = new javafx.scene.canvas.Canvas(600, 400);
        root.getChildren().add(canvas);

        javafx.scene.control.Button restartBtn = new javafx.scene.control.Button("Restart");
        javafx.scene.control.Button menuBtn = new javafx.scene.control.Button("Main Menu");

        restartBtn.setLayoutX(255);
        restartBtn.setLayoutY(175);
        menuBtn.setLayoutX(235);
        menuBtn.setLayoutY(220);

        restartBtn.setStyle(
                "-fx-background-color: #ff4d4d;" + // red button
                        "-fx-text-fill: white;" + // text color
                        "-fx-font-size: 18px;" + // font size
                        "-fx-font-weight: bold;" + // bold text
                        "-fx-background-radius: 10;" // rounded corners
        );

        menuBtn.setStyle(
                "-fx-background-color: #4d79ff;" + // blue button
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 18px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 12;");

        restartBtn.setVisible(false);
        menuBtn.setVisible(false);

        restartBtn.setOnAction(e -> startGame(factory, stage));
        menuBtn.setOnAction(e -> new com.snakerealms.main.SnakeRealmsApp().start(stage));

        root.getChildren().addAll(restartBtn, menuBtn);

        javafx.scene.canvas.GraphicsContext gc = canvas.getGraphicsContext2D();

        renderer = new GameRenderer();

        com.snakerealms.ui.Scoreboard scoreboard = new com.snakerealms.ui.Scoreboard();
        addObserver(scoreboard);
        root.getChildren().add(scoreboard);

        snake = factory.createSnake();
        food = factory.createFood();
        foodPos = new Point(10, 10);
        direction = Direction.RIGHT;

        Scene gameScene = new Scene(root, 600, 400);
        gameScene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case UP:
                    if (direction != Direction.DOWN)
                        direction = Direction.UP;
                    break;
                case DOWN:
                    if (direction != Direction.UP)
                        direction = Direction.DOWN;
                    break;
                case LEFT:
                    if (direction != Direction.RIGHT)
                        direction = Direction.LEFT;
                    break;
                case RIGHT:
                    if (direction != Direction.LEFT)
                        direction = Direction.RIGHT;
                    break;
                case SPACE:
                    isPaused = !isPaused;
                    break;
            }
        });

        stage.setScene(gameScene);
        stage.setTitle("Snake Realmssss");
        stage.setResizable(false);
        stage.show();

        new AnimationTimer() {
            long lastUpdate = 0;

            @Override
            public void handle(long now) {

                if (isPaused)
                    return;

                long speedDelay = (long) (200_000_000 / snakePower.getSpeedMultiplier());
                if (now - lastUpdate >= speedDelay) {
                    lastUpdate = now;

                    Snake.Segment headSeg = snake.getHead();
                    int headX = headSeg.getX();
                    int headY = headSeg.getY();

                    switch (direction) {
                        case UP:
                            newHead = new Point(headX, headY - 1);
                            break;
                        case DOWN:
                            newHead = new Point(headX, headY + 1);
                            break;
                        case LEFT:
                            newHead = new Point(headX - 1, headY);
                            break;
                        case RIGHT:
                            newHead = new Point(headX + 1, headY);
                            break;
                    }

                    // eat food
                    if (newHead.x == foodPos.x && newHead.y == foodPos.y) {
                        snake.grow(newHead.x, newHead.y);
                        foodEaten++;

                        if (foodEaten % 15 == 0) {
                            snakePower = new IntangiblePower(new BaseSnakePower(), powerDuration);
                            activePowerText = "INTANGIBLE!";
                        } else if (foodEaten % 10 == 0) {
                            snakePower = new SpeedBoostPower(new BaseSnakePower(), powerDuration);
                            activePowerText = "SPEED BOOST!";
                        } else if (foodEaten % 5 == 0) {
                            snakePower = new ScoreMultiplyPower(new BaseSnakePower(), powerDuration);
                            activePowerText = "SCORE MULTIPLIER!";
                        }

                        increaseScore(5 * snakePower.getScoreMultiplier());

                        foodPos = new Point((int) (Math.random() * WIDTH), (int) (Math.random() * HEIGHT));

                    } else {
                        int nextX = newHead.x;
                        int nextY = newHead.y;

                        if (snakePower.isIntangible()) {
                            nextX = (nextX + WIDTH) % WIDTH;
                            nextY = (nextY + HEIGHT) % HEIGHT;
                        } else if (nextX < 0 || nextY < 0 || nextX >= WIDTH || nextY >= HEIGHT) {
                            stop();
                            gc.setFill(Color.RED);
                            gc.setFont(Font.font("Arial", FontWeight.BOLD, 25));
                            gc.fillText("GAME OVER", canvas.getWidth() / 2 - 80, canvas.getHeight() / 2.5);
                            restartBtn.setVisible(true);
                            menuBtn.setVisible(true);
                            return;
                        }

                        // self collision
                        if (!snakePower.isIntangible() && snake.isCollision(nextX, nextY)) {
                            stop();
                            gc.setFill(Color.RED);
                            gc.setFont(Font.font("Arial", FontWeight.BOLD, 25));
                            gc.fillText("GAME OVER", canvas.getWidth() / 2 - 80, canvas.getHeight() / 2.5);
                            restartBtn.setVisible(true);
                            menuBtn.setVisible(true);
                            return;
                        }

                        snake.move(nextX, nextY);
                    }

                    // expire power-ups
                    if (snakePower instanceof SnakePowerDecorator && ((SnakePowerDecorator) snakePower).isExpired()) {
                        snakePower = new BaseSnakePower();
                        activePowerText = "";
                    }

                    tileSize = (int) Math.min(canvas.getWidth() / WIDTH, canvas.getHeight() / HEIGHT);

                    gc.setFill(Color.BLACK);
                    gc.setFont(new javafx.scene.text.Font(20));
                    gc.fillText("Score: " + score, 10, 25);

                    if (!activePowerText.isEmpty()) {
                        gc.setFill(Color.BLUE);
                        gc.fillText(activePowerText, 400, 25);
                    }

                    renderer.render(gc, snake, food, factory.getBackgroundColor(), foodPos.x, foodPos.y, tileSize,
                            activePowerText);
                }
            }
        }.start();
    }
}
