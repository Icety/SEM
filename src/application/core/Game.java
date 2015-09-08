package application.core;

import application.Main;
import application.core.Player;

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
    protected Level tLevel;
    protected Player tPlayer;
    protected boolean tPaused;
    protected boolean tRightArrow;
    protected boolean tLeftArrow;
    protected boolean tSpace;
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

    public int getScore() { return score; }

    protected void nextLevel() {
        tLevel = levelFactory.buildLevel(levelNumber);
        if (tLevel == null) {
            tPaused = true;
            Main.loadScene("won");
            return;
        }
        tPaused = false;
        Main.loadScene("level");
        Main.primaryStage.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                KeyCode code = event.getCode();
                if (code == KeyCode.ESCAPE) {
                    // show menu
                } else if (code == KeyCode.P) {
                    if (!tPaused) {
                        tPaused = true;
                    } else {
                        tPaused = false;
                    }
                } else if (code == KeyCode.RIGHT) {
                    tRightArrow = true;
                    Main.game.getPlayer(). rightArrowPressed(tRightArrow);
                } else if (code == KeyCode.LEFT) {
                    tLeftArrow = true;
                    Main.game.getPlayer().leftArrowPressed(tLeftArrow);
                } else if (code == KeyCode.SPACE) {
                    Main.game.getPlayer().fireButtonPressed(true);
                }

            }
        });

        Main.primaryStage.getScene().setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                KeyCode code = event.getCode();
                if (code == KeyCode.RIGHT) {
                    tRightArrow = false;
                    Main.game.getPlayer().rightArrowPressed(tRightArrow);
                } else if (code == KeyCode.LEFT) {
                    tLeftArrow = false;
                    Main.game.getPlayer().leftArrowPressed(tLeftArrow);
                } else if (code == KeyCode.SPACE) {
                    Main.game.getPlayer().fireButtonPressed(false);
                }
            }
        });

        levelNumber++;
    }

    public Level getLevel() {
        return tLevel;
    }

    public void newGame() {
        levelNumber = 0;
        nextLevel();
    }

    public Player getPlayer() {
        return tPlayer;
    }

    public void update() {
        for (Alien alien: tLevel.getAliens()) {
            if (alien.isRemoved()) {
                tLevel.removeAlien(alien);
                return;
            }
            alien.move();
        }
        for (Projectile projectile: tProjectiles  ) {
            if (projectile.isRemoved()) {
                tProjectiles.remove(projectile);
                return;
            }
            projectile.move();
        }
        //If all aliens are dead
        if (tLevel.getAliens().size() == 0) {
            nextLevel();
        }
    }

    public boolean isPaused(){
        return tPaused;
    }

    public ArrayList<Projectile> getProjectiles() {
        return tProjectiles;
    }

    public void addProjectile(Projectile projectile) {
        tProjectiles.add(projectile);
    }

//    public void removeProjectile(Projectile projectile) {
//        tProjectiles.remove(projectile);
//    }
}
