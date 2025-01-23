package model;

import javafx.animation.AnimationTimer;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Bloons extends Application {

        public static final int TARGET_FPS = 60;

        private static final double INTERVAL = 1_000_000_000.0 / TARGET_FPS; // interval in nanoseconds

        private static GameManager gm;

        Pane root;
        Scene scene;
        Timeline timeline;

        public static void main(String[] args) {
                gm = new GameManager();
                launch(args);
        }

        @Override
        public void start(Stage primaryStage) {

                root = new Pane();

                scene = new Scene(root);

                scene.getStylesheets().add("/sprites/layout/StyledButton.css");
                System.out.println(scene.getStylesheets());

                Canvas canvas = new Canvas(GameManager.resX, GameManager.resY);
                root.getChildren().add(canvas);

                root.getChildren().add(gm.getRoundButton());

                primaryStage.setResizable(false);

                primaryStage.setScene(scene);
                primaryStage.setTitle("BloonsTDClone");
                primaryStage.show();

                canvas.addEventHandler(MouseEvent.MOUSE_MOVED, e -> {

                        gm.handleMouseMovement((int) e.getX(), (int) e.getY());
                });

                canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {

                        gm.handleMouseClick((int) e.getX(), (int) e.getY());
                });

                GraphicsContext gc = canvas.getGraphicsContext2D();

                AnimationTimer gameLoop = new AnimationTimer() {
                        private long lastUpdate = 0;
                        private long accumulatedTime = 0;

                        @Override
                        public void handle(long now) {
                                if (lastUpdate == 0) {
                                        lastUpdate = now;
                                        return;
                                }

                                long deltaTime = now - lastUpdate;
                                lastUpdate = now;
                                accumulatedTime += deltaTime;

                                if (!GameManager.fastFowarded) {
                                        while (accumulatedTime >= (INTERVAL)) {
                                                render(gc);
                                                update();

                                                accumulatedTime -= INTERVAL;
                                        }
                                } else {
                                        while (accumulatedTime >= (INTERVAL) / 2) {
                                                render(gc);
                                                update();

                                                accumulatedTime -= INTERVAL / 2;
                                        }
                                }

                        }
                };

                gameLoop.start();
        }

        int i = 0;

        private void update() {

                gm.update();

        }

        private void render(GraphicsContext gc) {
                gm.draw(gc);
        }
}
