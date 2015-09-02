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
    protected Level level;
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

    protected void nextLevel() {
        levelNumber++;
        level = levelFactory.buildLevel(levelNumber);
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
                    System.out.println("Right arrow pressed");
                    Main.game.getPlayer(). rightArrowPressed(tRightArrow);

                } else if (code == KeyCode.LEFT) {
                    tLeftArrow = true;
                    Main.game.getPlayer().leftArrowPressed(tLeftArrow);
                } else if (code == KeyCode.SPACE) {
                    tSpace = true;
                    Main.game.getPlayer().fireButtonPressed(tSpace);
                }

            }
        });

        Main.primaryStage.getScene().setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                KeyCode code = event.getCode();
                if(code == KeyCode.RIGHT) {
                    System.out.println("Right arrow released");
                    tRightArrow = false;
                    Main.game.getPlayer(). rightArrowPressed(tRightArrow);
                } else if (code == KeyCode.LEFT) {
                    tLeftArrow = false;
                    Main.game.getPlayer().leftArrowPressed(tLeftArrow);
                } else if (code == KeyCode.SPACE) {
                    tSpace = false;
                    Main.game.getPlayer().fireButtonPressed(tSpace);
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
