//package application;
//
///**
// * Created by Thomas on 08-09-15.
// */
///*
//* To change this template, choose Tools | Templates
//* and open the template in the editor.
//*/
//
//        import org.newdawn.slick.AppGameContainer;
//        import org.newdawn.slick.BasicGame;
//        import org.newdawn.slick.GameContainer;
//        import org.newdawn.slick.Graphics;
//        import org.newdawn.slick.SlickException;
//
///**
// * @author panos
// */
//public class NewMain extends BasicGame
//{
//    public NewMain()
//    {
//        super("Super Awesome Cool Fun Explosive Space Invaders");
//    }
//
//    public static void main(String[] arguments)
//    {
//        try
//        {
//            AppGameContainer app = new AppGameContainer(new NewMain());
//            app.setDisplayMode(1024, 1024, false);
//            app.start();
//        }
//        catch (SlickException e)
//        {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void init(GameContainer container) throws SlickException
//    {
//    }
//
//    @Override
//    public void update(GameContainer container, int delta) throws SlickException
//    {
//    }
//
//    public void render(GameContainer container, Graphics g) throws SlickException
//    {
//    }
//}
package application;

import application.controllers.Levels;
import application.controllers.Menu;
import application.core.Game;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Main extends StateBasedGame {

    // Game state identifiers
    public static final int MENU = 0;
    public static final int LEVELS = 1;
    public static final int PAUSE = 2;
    public static final int END = 3;

    // Application Properties
    public static final int WIDTH   = 1400;
    public static final int HEIGHT  = 1050;
    public static final int FPS     = 60;
    public static final double VERSION = 1.0;

    public static Game sGame;

    public Game tGame;

    // Class Constructor
    public Main(String appName) {
        super(appName);

        tGame = new Game(WIDTH, HEIGHT);
        sGame = tGame;
    }

    // Initialize your game states (calls init method of each gamestate, and set's the state ID)
    public void initStatesList(GameContainer gc) throws SlickException {
        // The first state added will be the one that is loaded first, when the application is launched
        this.addState(new Menu(MENU));
        this.addState(new Levels(LEVELS));
//        this.addState(new EndGame(END));
    }

    // OldMain Method
    public static void main(String[] args) {
        try {
            AppGameContainer app = new AppGameContainer(new Main("My Game v" + VERSION));
            app.setDisplayMode(WIDTH, HEIGHT, false);
            app.setTargetFrameRate(FPS);
            app.setShowFPS(true);
            app.start();
        } catch(SlickException e) {
            e.printStackTrace();
        }
    }

    public static void newGame() {
        sGame = new Game(WIDTH, HEIGHT);
        sGame.nextLevel();
    }
}