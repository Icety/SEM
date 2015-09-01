package application.core;

import application.Main;

/**
 * Created by Thomas on 01-09-15.
 */
public class Game {
    protected int score;
    protected LevelFactory levelFactory;
    protected int levelNumber;
    protected Level level;

    public Game() {
        levelFactory = new LevelFactory();
        levelNumber = 0;
    }

    public void setScore(int value) {
        score += value;
    }

    protected void nextLevel() {
        levelNumber++;
        level = levelFactory.buildLevel(levelNumber);
        Main.loadScene("level");
    }

    public Level getLevel() {
        return level;
    }

    public void newGame() {
        levelNumber = 0;
        nextLevel();
    }



}
