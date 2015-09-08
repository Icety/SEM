package application.controllers;

import application.OldMain;
import application.core.Alien;
import application.core.Player;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import application.core.Projectile;
import javafx.scene.paint.Color;

/**
 * Created by Thomas on 01-09-15.
 */
public class LevelController {

    @FXML
    private Canvas gameCanvas;

    ArrayList<Alien> tAliens = OldMain.game.getLevel().getAliens();

    @FXML
    private void initialize() {


        gameCanvas.setWidth(OldMain.getWidth());
        gameCanvas.setHeight(OldMain.getHeight());
        draw();
    }

    protected void draw() {

        GraphicsContext gc = gameCanvas.getGraphicsContext2D();

        new AnimationTimer()
        {
            long lastTime = System.nanoTime();
            public void handle(long currentNanoTime)
            {
                if (OldMain.game.isPaused()) {
                    return;
                }
                //Manage framerate
                double time = (currentNanoTime - lastTime) / 1000000;
                lastTime = currentNanoTime;
                System.out.println(time);
                if (time < 16.66 && 1==2) {
                    try {
                        Thread.sleep((int) (16.66 - time));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                int fps = (int) (1000 / time);
                //---------------

                //Clear screen
                gc.clearRect(0, 0, OldMain.getWidth(), OldMain.getHeight());

                OldMain.game.update();
                //Draw all objects
                drawProjectiles(gc);
                drawAliens(gc);
                drawPlayer(gc);
                gc.setFill(Color.RED);

                gc.fillText(("SCORE: " + Integer.toString(OldMain.game.getScore())), OldMain.getWidth() - 140, 50);
                gc.fillText(("FPS: " + Integer.toString(fps)), OldMain.getWidth()-140, 70);
            }
        }.start();
    }

    protected void drawAliens(GraphicsContext gc) {
        for (Alien alien: tAliens) {
            gc.drawImage( alien.getImage(), alien.getX(), alien.getY() );
        }
    }

    protected void drawPlayer(GraphicsContext gc) {
        Player player = OldMain.game.getPlayer();
        gc.drawImage( player.getImage(), player.getX(), player.getY() );
    }

    protected void drawProjectiles(GraphicsContext gc) {
        ArrayList<Projectile> projectiles = OldMain.game.getProjectiles();
        for (Projectile projectile: projectiles) {
            gc.drawImage( projectile.getImage(), projectile.getX(), projectile.getY() );
        }
    }



}
