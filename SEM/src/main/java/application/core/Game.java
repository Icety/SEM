package application.core;

import application.OldMain;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.newdawn.slick.SlickException;

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
    protected int tScreenWidth;
    protected int tScreenHeight;
    protected boolean tPaused;
    protected boolean tRightArrow;
    protected boolean tLeftArrow;
    protected boolean tSpace;
    protected ArrayList<Projectile> tProjectiles;


    public Game(int width, int height) {
        levelFactory = new LevelFactory();
        levelNumber = 0;
        tScreenWidth = width;
        tScreenHeight = height;
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
            OldMain.loadScene("won");
            return;
        }
        tPaused = false;
        OldMain.loadScene("level");
        OldMain.primaryStage.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
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
                    OldMain.game.getPlayer(). rightArrowPressed(tRightArrow);
                } else if (code == KeyCode.LEFT) {
                    tLeftArrow = true;
                    OldMain.game.getPlayer().leftArrowPressed(tLeftArrow);
                } else if (code == KeyCode.SPACE) {
                    OldMain.game.getPlayer().fireButtonPressed(true);
                }

            }
        });

        OldMain.primaryStage.getScene().setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                KeyCode code = event.getCode();
                if (code == KeyCode.RIGHT) {
                    tRightArrow = false;
                    OldMain.game.getPlayer().rightArrowPressed(tRightArrow);
                } else if (code == KeyCode.LEFT) {
                    tLeftArrow = false;
                    OldMain.game.getPlayer().leftArrowPressed(tLeftArrow);
                } else if (code == KeyCode.SPACE) {
                    OldMain.game.getPlayer().fireButtonPressed(false);
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

    public void update() throws SlickException {
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

    public int getHeight() {
        return tScreenHeight;
    }

    public int getWidth() {
        return tScreenWidth;
    }

//    public void removeProjectile(Projectile projectile) {
//        tProjectiles.remove(projectile);
//    }
}
