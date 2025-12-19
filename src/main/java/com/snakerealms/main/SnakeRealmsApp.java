package com.snakerealms.main;

import com.snakerealms.core.GameController;
import com.snakerealms.realms.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SnakeRealmsApp extends Application {

    private Stage mainStage;

    enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    //snake body parts
    class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    @Override
    public void start(Stage primaryStage) {
        this.mainStage = primaryStage;
        GameController.getInstance().loadHighScore();
        showMenu();
    }

    private void showMenu() {
        VBox menuLayout = new VBox(15);
        menuLayout.setStyle("-fx-background-color: grey; -fx-padding: 30;");
        menuLayout.setAlignment(javafx.geometry.Pos.TOP_CENTER);

        javafx.scene.control.Label highScoreLabel = new javafx.scene.control.Label(
                "High Score: " + GameController.getHighScore());
        highScoreLabel.setTextFill(javafx.scene.paint.Color.RED);
        highScoreLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        menuLayout.getChildren().add(highScoreLabel);

        Button jungleBtn = new Button("Jungle Realm");
        Button spaceBtn = new Button("Space Realm");
        Button oceanBtn = new Button("Ocean Realm");

        jungleBtn.setStyle(
            "-fx-background-color: #37bb42ff;" +      // green button
            "-fx-text-fill: white;" +               // text color
            "-fx-font-size: 18px;" +                // font size
            "-fx-font-weight: bold;" +              // bold text
            "-fx-background-radius: 10;"            // rounded corners);
        );

        spaceBtn.setStyle(
            "-fx-background-color: #121e57ff;" +      // dark blue button
            "-fx-text-fill: white;" +               // text color
            "-fx-font-size: 18px;" +                // font size
            "-fx-font-weight: bold;" +              // bold text
            "-fx-background-radius: 10;"            // rounded corners);
        );

        oceanBtn.setStyle(
            "-fx-background-color: #0b6be9ff;" +      // light blue button
            "-fx-text-fill: white;" +               // text color
            "-fx-font-size: 18px;" +                // font size
            "-fx-font-weight: bold;" +              // bold text
            "-fx-background-radius: 10;"            // rounded corners);
        );

        jungleBtn.setOnAction(e -> {
            RealmFactory factory = RealmFactoryProducer.getFactory("jungle");
            startGame(factory);
        });

        spaceBtn.setOnAction(e -> {
            RealmFactory factory = RealmFactoryProducer.getFactory("space");
            startGame(factory);
        });

        oceanBtn.setOnAction(e -> {
            RealmFactory factory = RealmFactoryProducer.getFactory("ocean");
            startGame(factory);
        });

        menuLayout.getChildren().addAll(jungleBtn, spaceBtn, oceanBtn);

        Scene menuScene = new Scene(menuLayout, 600, 400);
        mainStage.setTitle("Snake Realmssss");
        mainStage.setScene(menuScene);
        mainStage.setResizable(false);
        mainStage.show();
    }

    private void startGame(RealmFactory factory) {
        GameController controller = GameController.getInstance();
        controller.startGame(factory, mainStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
