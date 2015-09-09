package application.core;

import application.Main;
import org.newdawn.slick.Color;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

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
    protected boolean tWon = false;
    protected boolean tLost = false;
    protected boolean mothershipAlive;
    protected boolean bossStage;
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

    public void nextLevel() {
        tLevel = levelFactory.buildLevel(levelNumber);
        levelNumber++;
    }

    public boolean hasNextLevel() {
        return levelFactory.levelExists(levelNumber);
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
        //needs right time implemented
        if(tPlayer.getHealth() <= 0) {
            tLost = true;
        }
        tPlayer.update();

        for (Alien alien: tLevel.getAliens()) {
            if (alien instanceof FinalBoss) {
                bossStage = true;
            }

            if (alien.isRemoved()) {
                tLevel.removeAlien(alien);

                //Check if mothership is alive
                if (alien instanceof MothershipAlien) {
                    mothershipAlive = false;
                }
                return;
            }
            alien.move();
            alien.addShootChance();

            //Switch direction when the borders are reached
            if (!(alien instanceof MothershipAlien)) {
                if (alien.endOfScreen()) {
                    System.out.println("SWITCH");
                    for (Alien alien2: tLevel.getAliens()) {
                        System.out.println("SWITCH");
                        if (!(alien2 instanceof MothershipAlien)) {
                            alien2.switchDirection();
                            System.out.println("SWITCH");
                        }
                    }
                }
            } else {
                alien.switchDirection();
            }
        }
        for (Projectile projectile: tProjectiles  ) {
            if (projectile.isRemoved()) {
                tProjectiles.remove(projectile);
                return;
            }
            projectile.move();
        }

        if (bossStage) {
            if (tLevel.getAliens().size() == 0) {
                if (hasNextLevel()) {
                    nextLevel();
                }
                else {
                    System.out.println("NOPE");
                    tWon = true;
                    bossStage = false;
                }
            }
        }
        //If all aliens are dead
         else if (tLevel.getAliens().size() == 0 || (tLevel.getAliens().size() == 1 && (!mothershipAlive))) {
            if (hasNextLevel()) {
                nextLevel();
            }
            else {
                tWon = true;
            }
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

    public boolean hasWon() {
        return tWon;
    }

    public boolean hasLost() {
        return tLost;
    }

//    public void removeProjectile(Projectile projectile) {
//        tProjectiles.remove(projectile);
//    }
}
