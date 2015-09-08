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
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class NewMain extends StateBasedGame {

    // Game state identifiers
    public static final int SPLASHSCREEN = 0;
    public static final int MAINMENU     = 1;
    public static final int GAME         = 2;

    // Application Properties
    public static final int WIDTH   = 640;
    public static final int HEIGHT  = 480;
    public static final int FPS     = 60;
    public static final double VERSION = 1.0;

    // Class Constructor
    public NewMain(String appName) {
        super(appName);
    }

    // Initialize your game states (calls init method of each gamestate, and set's the state ID)
    public void initStatesList(GameContainer gc) throws SlickException {
        // The first state added will be the one that is loaded first, when the application is launched
        this.addState(new Menu(0));
        this.addState(new Levels(1));
//        this.addState(new EndGame());
    }

    // Main Method
    public static void main(String[] args) {
        try {
            AppGameContainer app = new AppGameContainer(new NewMain("My Game v" + VERSION));
            app.setDisplayMode(WIDTH, HEIGHT, false);
            app.setTargetFrameRate(FPS);
            app.setShowFPS(true);
            app.start();
        } catch(SlickException e) {
            e.printStackTrace();
        }
    }
}