package application.controllers;

import application.Main;
import application.core.Alien;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

/**
 * Created by Thomas on 01-09-15.
 */
public class LevelController {

    ArrayList<Alien> tAliens = Main.game.getLevel().getAliens();;

    @FXML
    private void initialize() {
        for (Alien alien: tAliens) {
            //Todo: Draw alien at good location
        }

        //Todo: Draw player


        redrawAliens();
        redrawPlayer();
        redrawProjectiles();

//        Task task = new Task<Void>() {
//            @Override
//            public Void call() throws Exception {
//                int i = 0;
//                while (true) {
//                    final int finalI = i;
//                    Platform.runLater(new Runnable() {
//                        @Override
//                        public void run() {
//                        }
//                    });
//                    i++;
//                    Thread.sleep(1000);
//                }
//            }
//        };
//        Thread th = new Thread(task);
//        th.setDaemon(true);
//        th.start();
    }

    protected void redrawAliens() {

        Canvas canvas = new Canvas(512, 512);

        GraphicsContext gc = canvas.getGraphicsContext2D();

        final long startNanoTime = System.nanoTime();

        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                for (Alien alien: tAliens) {
                    alien.move();
                    gc.drawImage( alien.getImage(), alien.getX(), alien.getY() );
                }
            }
        }.start();
    }

    protected void redrawPlayer() {}

    protected void redrawProjectiles() {}



}
