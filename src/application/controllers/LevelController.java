package application.controllers;

import application.Main;
import application.core.Alien;
import application.core.Player;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Thomas on 01-09-15.
 */
public class LevelController {

    @FXML
    private Canvas gameCanvas;

    ArrayList<Alien> tAliens = Main.game.getLevel().getAliens();;

    @FXML
    private void initialize() {

        gameCanvas.setWidth(Main.getWidth());
        gameCanvas.setHeight(Main.getHeight());

        draw();
    }

    protected void draw() {

        GraphicsContext gc = gameCanvas.getGraphicsContext2D();

        new AnimationTimer()
        {
            long lastTime = System.nanoTime();
            public void handle(long currentNanoTime)
            {
                if (Main.game.isPaused()) {
                    return;
                }
                //Manage framerate
                double time = (System.nanoTime() - lastTime) / 1000000;
                lastTime = System.nanoTime();
                System.out.println(time);
                if (time < 16.66) {
                    try {
                        Thread.sleep((int) (16.66 - time));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //---------------

                //Clear screen
                gc.clearRect(0, 0, Main.getWidth(), Main.getHeight());

                //Draw all objects
                drawAliens(gc);
                drawPlayer(gc);
                drawProjectiles(gc);
            }
        }.start();
    }

    protected void drawAliens(GraphicsContext gc) {
        for (Alien alien: tAliens) {
            alien.move();
            gc.drawImage( alien.getImage(), alien.getX(), alien.getY() );
        }
    }

    protected void drawPlayer(GraphicsContext gc) {
        Player player = Main.game.getPlayer();
        gc.drawImage( player.getImage(), player.getX(), player.getY() );
    }

    protected void drawProjectiles(GraphicsContext gc) {
//        ArrayList<Projectile> projectiles = Main.game.getProjectiles();
//        for (Projectile projectile: projectiles) {
//            projectile.move();
//            gc.drawImage( projectile.getImage(), projectile.getX(), projectile.getY() );
//        }
    }



}
