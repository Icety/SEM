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
import application.core.Player;
import application.logger.Logger;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Main class for the game.
 */
@SuppressWarnings({
        "checkstyle:staticvariablename",
        "checkstyle:visibilitymodifier",
        "checkstyle:magicnumber"
})
public class Main extends StateBasedGame {

    // Game state identifiers
    public static final int MENU = 0;
    public static final int LEVELS = 1;
    public static final int WON = 2;
    public static final int LOST = 3;
    public static final int STORYLINE = 7;
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
    public static Image BACHELLI_PROJECTILE;
    public static Image PLAYER_PROJECTILE;
    public static Image SMALL_PROJECTILE;
    public static Image BOSS_BACHELLI;
    public static Image BOSS_BACHELLI_CHARGE;
    public static Image UPGRADE_0;
    public static Image UPGRADE_1;
    public static Image UPGRADE_2;
    public static Image UPGRADE_3;
    public static Image UPGRADED_PLAYER;


    public static int DIFFICULTY = 1;

    protected Game tGame;
    protected Logger tLogger;
    public static Music tBackgroundmusic;
    protected Image tBackground;
    protected boolean tTransition;

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

        tGame = new Game(WIDTH, HEIGHT, tLogger);
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
        tBackgroundmusic = new Music("src/main/java/application/sound/normalmusic.wav");
        tBackgroundmusic.loop();

        String root = "src/main/java/application/images/";
        MINI_ALIEN = new Image(root + "miniAlien.png");
        SMALL_ALIEN = new Image(root + "smallAlien.png");
        BIG_ALIEN = new Image(root + "bigAlien.png");
        MOTHERSHIP_ALIEN = new Image(root + "mothership.png");
        PLAYER = new Image(root + "player.png");
        BOSS_PROJECTILE = new Image(root + "spaghettiheart.png");
        BACHELLI_PROJECTILE = new Image(root + "meatball.png");
        PLAYER_PROJECTILE = new Image(root + "smallbullet.png");
        SMALL_PROJECTILE = new Image(root + "smallbullet.png");
        BOSS_BACHELLI = new Image(root + "finalbossbachelli.png");
        BOSS_BACHELLI_CHARGE = new Image(root + "finalbossbachellicharge.png");
        UPGRADED_PLAYER = new Image(root + "player_upgraded.png");
        UPGRADE_0 = new Image(root + "upgrade_speed.png");
        UPGRADE_1 = new Image(root + "upgrade_weapon.png");
        UPGRADE_2 = new Image(root + "upgrade_health.png");
        UPGRADE_3 = new Image(root + "upgrade.png");
    }

    /**
     * Main method for running the game.
     * @param args default parameter.
     */
    public static void main(String[] args) {
        try {
            AppGameContainer app = new AppGameContainer(new Main("My Game v" + VERSION));
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
     */
    public void newGame() {
        tGame = new Game(WIDTH, HEIGHT, tLogger);
        tGame.nextLevel();
        Player p = tGame.getPlayer();
        p.setX(tGame.getWidth() / 2);
        p.setY(tGame.getHeight() - (p.getHeight() + 50));
    }

    /**
     * Get the current game.
     * @return the current game.
     */
    public Game getGame() {
        return tGame;
    }
}