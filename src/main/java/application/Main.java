package application;

import application.controllers.Levels;
import application.controllers.Lost;
import application.controllers.Menu;
import application.controllers.Won;
import application.controllers.LevelBuilder;
import application.controllers.HighScoreBoard;
import application.controllers.StoryLine;
import application.controllers.HighScoreForm;
import application.core.Game;
import application.logger.Logger;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Main class for the game.
 */
@SuppressWarnings({
        "checkstyle:staticvariablename",
        "checkstyle:visibilitymodifier",
        "checkstyle:magicnumber",
        "checkstyle:methodlength"
})
public class Main extends StateBasedGame {

    // Game state identifiers
    public static final int MENU = 0;
    public static final int LEVELS = 1;
    public static final int WON = 2;
    public static final int LOST = 3;
    public static final int STORYLINE = 20;
    public static final int LEVELBUILDER = 4;
    public static final int ENTERNAME = 5;
    public static final int HIGHSCORE = 6;


    // Application Properties
    public static final int WIDTH   = 1400;
    public static final int HEIGHT  = 1080;
    public static final int FPS     = 60;
    public static final double VERSION = 1.0;

    public static Image BIG_ALIEN;
    public static Image SMALL_ALIEN;
    public static Image MINI_ALIEN;
    public static Image MOTHERSHIP_ALIEN;
    public static Image PLAYER;
    public static Image BOSS_PROJECTILE;
    public static Image BOSS_PROJECTILE_SPECIAL;
    public static Image PLAYER_PROJECTILE;
    public static Image SMALL_PROJECTILE;
    public static Image BOSS;
    public static Image BOSS_CHARGE;
    public static Image BOSS_BEAM_PROJECTILE;
    public static Image UPGRADE_0;
    public static Image UPGRADE_1;
    public static Image UPGRADE_2;
    public static Image UPGRADE_3;
    public static Image UPGRADED_PLAYER;
    public static Animation DAPHNALIEN;
    public static String imageRoot;
    public static String imageTheme;
    public static boolean sNewLevel;

    public static Image BARRIER_1;
    public static Image BARRIER_2;
    public static Image BARRIER_3;
    public static Image BARRIER_4;
    public static Image BARRIER_5;
    public static Image BARRIER_6;
    public static Image BARRIER_7;
    public static Image BARRIER_8;


    public static int DIFFICULTY = 1;

    protected Game tGame;
    protected Logger tLogger;
    public static Music BACKGROUNDMUSIC;
    public static Sound tPlayerDeathSound;
    public static Sound tInvaderKilledSound;
    public static Sound tMotherShipKilledSound;

    protected Image tBackground;
    protected boolean tTransition;
    protected int tStartTime = 80;

    /**
     * Constructor for Main.
     * @param appName name of the application.
     */
    public Main(String appName) {
        super(appName);

        //Start the logger
        tLogger = new Logger();
        tLogger.startLogging();

        //Make sure that the logger is stopped when the game is exited.
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                tLogger.stopLogging();
            }
        });
    }

    /**
     * Initial variable setter.
     * @param gc the required GameContainer.
     * @throws SlickException possible Exception.
     */
    public void initStatesList(GameContainer gc) throws SlickException {
        this.addState(new Menu(MENU));
        this.addState(new Levels(LEVELS));
        this.addState(new Won(WON));
        this.addState(new Lost(LOST));
        this.addState(new LevelBuilder(LEVELBUILDER));
        this.addState(new HighScoreBoard(HIGHSCORE));
        this.addState(new StoryLine(STORYLINE));
        this.addState(new HighScoreForm(ENTERNAME));

        String soundRoot = "sound/";
        tPlayerDeathSound = new Sound(soundRoot + "explosion.wav");
        tInvaderKilledSound = new Sound(soundRoot + "invaderkilled.wav");
        tMotherShipKilledSound = new Sound(soundRoot + "mothership.wav");
        BACKGROUNDMUSIC = new Music(soundRoot + "normal.wav");
        BACKGROUNDMUSIC.loop();

        imageRoot = "images/";
        imageTheme = "classic";
        setAlienImages(imageTheme);

        String imageRoot = "images/";
        MINI_ALIEN = new Image(imageRoot + "classic/miniAlien.png");
        SMALL_ALIEN = new Image(imageRoot + "classic/smallAlien.png");
        BIG_ALIEN = new Image(imageRoot + "classic/bigAlien.png");
        MOTHERSHIP_ALIEN = new Image(imageRoot + "classic/mothership.png");
        PLAYER = new Image(imageRoot + "player.png");
        BOSS_PROJECTILE = new Image(imageRoot + "classic/bossProjectile.png");
        PLAYER_PROJECTILE = new Image(imageRoot + "smallbullet.png");
        SMALL_PROJECTILE = new Image(imageRoot + "smallbullet.png");
        UPGRADED_PLAYER = new Image(imageRoot + "player_upgraded.png");

        BARRIER_1 = new Image(imageRoot + "barrier/barier_1.png");
        BARRIER_2 = new Image(imageRoot + "barrier/barier_2.png");
        BARRIER_3 = new Image(imageRoot + "barrier/barier_3.png");
        BARRIER_4 = new Image(imageRoot + "barrier/barier_4.png");
        BARRIER_5 = new Image(imageRoot + "barrier/barier_5.png");
        BARRIER_6 = new Image(imageRoot + "barrier/barier_6.png");
        BARRIER_7 = new Image(imageRoot + "barrier/barier_7.png");
        BARRIER_8 = new Image(imageRoot + "barrier/barier_8.png");

        UPGRADE_0 = new Image(imageRoot + "upgrades/upgrade_speed.png");
        UPGRADE_1 = new Image(imageRoot + "upgrades/upgrade_weapon.png");
        UPGRADE_2 = new Image(imageRoot + "upgrades/upgrade_health.png");
        UPGRADE_3 = new Image(imageRoot + "upgrades/upgrade.png");


        SpriteSheet spriteSheet = new SpriteSheet(imageRoot + "daphne/bossAnimation.png", 320, 358);
        DAPHNALIEN = new Animation(spriteSheet, 30);
    }

    /**
     * Main method for running the game.
     * @param args default parameter.
     */
    public static void main(String[] args) {
        try {
            AppGameContainer app = new AppGameContainer(new Main("Space Invaders" + VERSION));
            app.setDisplayMode(WIDTH, HEIGHT, false);
            app.setTargetFrameRate(FPS);
            app.setShowFPS(true);
            app.isFullscreen();
            app.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    /**
     * Let Main start a new game.
     * @param numPlayers the amount of playing players.
     */
    public void newGame(int numPlayers) {
        tGame = new Game(WIDTH, HEIGHT, tLogger, numPlayers);
        tGame.nextLevel();
    }

    /**
     * Get the current game.
     * @return the current game.
     */
    public Game getGame() {
        return tGame;
    }

    /**
     * Set the game.
     * @param game the game.
     */
    public void setGame(Game game) {
        tGame = game;
    }

    /**
     * Setter method for the image files of the Aliens.
     * @param theme the theme of the current level.
     * @throws SlickException possible Exception.
     */
    public void setAlienImages(String theme) throws SlickException {
        MINI_ALIEN = new Image(imageRoot + theme + "/miniAlien.png");
        SMALL_ALIEN = new Image(imageRoot + theme + "/smallAlien.png");
        BIG_ALIEN = new Image(imageRoot + theme + "/bigAlien.png");
        MOTHERSHIP_ALIEN = new Image(imageRoot + theme + "/mothership.png");
        BOSS = new Image(imageRoot + theme + "/boss.png");
        BOSS_CHARGE = new Image(imageRoot + theme + "/bossCharge.png");
        BOSS_PROJECTILE_SPECIAL = new Image(imageRoot + theme + "/bossWeaponSpecial.png");
        BOSS_PROJECTILE = new Image(imageRoot + theme + "/bossProjectile.png");
        BOSS_BEAM_PROJECTILE = new Image(imageRoot + theme + "/bossBeamProjectile.png");
        PLAYER = new Image(imageRoot + "player.png");
        PLAYER_PROJECTILE = new Image(imageRoot + "smallbullet.png");
        SMALL_PROJECTILE = new Image(imageRoot + "smallbullet.png");
        UPGRADED_PLAYER = new Image(imageRoot + "player_upgraded.png");
    }

    /**
     * Method to change difficulty of the game.
     */
    public void changeDifficulty() {
        DIFFICULTY++;
        if (DIFFICULTY > 3) {
            DIFFICULTY = 1;
        }
    }
}