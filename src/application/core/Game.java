package application.core;

import application.Main;


import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;

/**
 * Created by Thomas on 01-09-15.
 */
public class Game {
    protected int score;
    protected LevelFactory levelFactory;
    protected int levelNumber;
    protected Level level;
    protected Player tPlayer;
    protected boolean tPaused;
    protected ArrayList<Projectile> tProjectiles;


    public Game() {
        levelFactory = new LevelFactory();
        levelNumber = 0;
        tPlayer = new Player();
        tProjectiles = new ArrayList<Projectile>();
        tPaused = false;
    }

    public void setScore(int value) {
        score += value;
    }

    protected void nextLevel() {
        levelNumber++;
        level = levelFactory.buildLevel(levelNumber);
        Main.loadScene("level");
        Main.primaryStage.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                KeyCode code = event.getCode();
                System.out.println(code);
                System.out.println(tPaused);
                if (code == KeyCode.ESCAPE) {
                    // show menu
                } else if (code == KeyCode.P){
                   if ( !tPaused ) {
                       tPaused = true;
                   } else {
                       tPaused = false;
                   }
                }
            }
        });
    }

    public Level getLevel() {
        return level;
    }

    public void newGame() {
        levelNumber = 0;
        nextLevel();
    }

    public Player getPlayer() {
        return tPlayer;
    }

    public void update() {
        for (Alien alien: getLevel().getAliens()) {
            alien.move();
        }
        ArrayList<Projectile> projectiles = Main.game.getProjectiles();
        for (Projectile projectile: projectiles) {
            projectile.move();
        }
    }

    public boolean isPaused(){
        return tPaused;
    }

    public ArrayList<Projectile> getProjectiles() {
        return tProjectiles;
    }


}
