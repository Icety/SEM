package application.controllers;

import java.io.File;
import application.Main;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.w3c.dom.Element;
import java.util.ArrayList;
import org.w3c.dom.Document;
import application.core.Alien;
import application.core.BigAlien;
import application.core.MiniAlien;
import application.core.FinalBoss;
import application.core.SmallAlien;
import application.core.MothershipAlien;
import org.newdawn.slick.Font;
import org.newdawn.slick.Color;
import org.newdawn.slick.Input;
import org.newdawn.slick.Image;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 * Controller method for LevelBuilder.
 * @author Niek van der Laan.
 */
@SuppressWarnings({
        "checkstyle:visibilitymodifier",
        "checkstyle:magicnumber",
        "checkstyle:linelength",
        "checkstyle:methodlength"
})
public class LevelBuilder extends BasicGameState {

    protected Main tMain;
    protected int tId;
    protected Image tBackground;
    protected String tBackgroundString = "moving.jpg";
    protected Alien selected = new Alien();
    protected int menuHeight;
    protected Circle circle;
    protected int circlex;
    protected int circley;
    protected boolean circleDown, circleUp, circleLeft, circleRight, saveGame;
    protected TextField saveName;
    protected boolean focus = false;
    protected ArrayList<Alien> aliens = new ArrayList<Alien>();

    /**
     * Constructor method for the LevelBuilder.
     * @param id used by the controller.
     */
    public LevelBuilder(int id) {
        tId = id;
    }

    /**
     * Init method for the LevelBuilder.
     * @param container GameContainer used by the program.
     * @param game being played at the moment.
     * @throws SlickException
     */
    @Override
    public void init(GameContainer container, StateBasedGame game)
            throws SlickException {
        tMain = (Main) game;
        circlex = Main.WIDTH / 2;
        circley =  Main.HEIGHT / 3;
        circle = new Circle(circlex, circley, 70);
        menuHeight = 150;
        selected = null;
        tBackground = new Image("src/main/java/application/images/" + tBackgroundString);
        saveGame = false;
        Font font = container.getDefaultFont();
        saveName = new TextField(container,
                font,
                Main.WIDTH / 2 - Main.WIDTH / 16,
                Main.HEIGHT / 2, Main.WIDTH / 8,
                30);
        saveName.setAcceptingInput(false);
    }

    /**
     * Getter method for the ID of the controller.
     * @return ID of the controller.
     */
    public int getID() {
        return tId;
    }

    /**
     * Render method for the LevelBuilder.
     * @param container GameContainer being used by the program.
     * @param game being played at the moment.
     * @param g Graphics used by the program.
     * @throws SlickException
     */
    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g)
            throws SlickException {
        //Draw the background
        tBackground.draw(0, 0, container.getWidth(), container.getHeight());

        //Draw all text
        g.setColor(Color.white);
        g.drawString("Space:   Add selected alien", Main.WIDTH / 8, menuHeight / 6);
        g.drawString("R:       Delete alien in circle", Main.WIDTH / 8, 2 * menuHeight / 6);
        g.drawString("Arrows:  Move circle", Main.WIDTH / 8, 3 * menuHeight / 6);
        g.drawString("Escape:  Save current level", Main.WIDTH / 8, 4 * menuHeight / 6);
        g.drawString("Tab:     Leave without saving", Main.WIDTH / 8, 5 * menuHeight / 6);
        if (saveGame) {
            g.drawString("PLEASE ENTER A NAME FOR THE LEVEL FILE", Main.WIDTH / 2 - Main.WIDTH / 16, Main.HEIGHT / 2 - 50);
            saveName.render(container, g);
            saveName.setFocus(focus);
        }
        renderDrawAliens(g);
        //Draw the circle
        g.setColor(Color.red);
        g.draw(circle);
        //Display the Alien we have currently selected
        if (selected != null) {
            selected.getImage().draw(Main.WIDTH / 2, menuHeight / 3, menuHeight / 2, menuHeight / 2);
        }
        //Draw all aliens
        for (Alien alien : aliens) {
            alien.getImage().draw(alien.getX(), alien.getY(), alien.getWidth(), alien.getHeight());
        }
    }

    /**
     * Render method to draw Aliens in the LevelBuilder.
     * @param g required graphics for placing Aliens.
     */
    public void renderDrawAliens(Graphics g) {
        g.drawString("Selected Alien: ", Main.WIDTH / 3, menuHeight / 2);
        g.drawString("1: MiniAlien       ", Main.WIDTH - Main.WIDTH / 4, menuHeight / 6);
        g.drawString("2: SmallAlien      ", Main.WIDTH - Main.WIDTH / 4, 2 * menuHeight / 6);
        g.drawString("3: BigAlien      ", Main.WIDTH - Main.WIDTH / 4, 3 * menuHeight / 6);
        g.drawString("4: MothershipAlien      ", Main.WIDTH - Main.WIDTH / 4, 4 * menuHeight / 6);
        g.drawString("5: FinalBoss", Main.WIDTH - Main.WIDTH / 4, 5 * menuHeight / 6);
    }

    /**
     * Update method for LevelBuilder.
     * @param container GameContainer used by the program.
     * @param game being played at the moment.
     * @param delta an integer value.
     * @throws SlickException
     */
    @Override
    public void update(GameContainer container, StateBasedGame game, int delta)
            throws SlickException {
        //Move circle up
        if (circleUp && circle.getMinY() > menuHeight) {
            circley -= 4;
        }
        //Move circle down
        if (circleDown && circle.getMaxY() < Main.HEIGHT) {
            circley += 4;
        }
        //Move circle right
        if (circleRight && circle.getMaxX() < Main.WIDTH) {
            circlex += 4;
        }
        //Move circle left
        if (circleLeft && circle.getMinX() > 0) {
            circlex -= 4;
        }
        //Update circle coordinates
        circle.setCenterX(circlex);
        circle.setCenterY(circley);
    }

    /**
     * Method which checks whether a key is released.
     * @param key integer value for a specific key.
     * @param c character value of the key.
     */
    public void keyReleased(int key, char c) {
        switch (key) {
            //Update selected alien
            case Input.KEY_1:
                selected = new MiniAlien();
                break;
            case Input.KEY_2:
                selected = new SmallAlien();
                break;
            case Input.KEY_3:
                selected = new BigAlien();
                break;
            case Input.KEY_4:
                selected = new MothershipAlien();
                break;
            case Input.KEY_5:
                selected = new FinalBoss();
                break;
            //If we stop pressing an arrow, we make the circle stop moving aswell
            case Input.KEY_DOWN:
                    circleDown = false;
                break;
            case Input.KEY_UP:
                    circleUp = false;
                break;
            case Input.KEY_LEFT:
                    circleLeft = false;
                break;
            case Input.KEY_RIGHT:
                    circleRight = false;
                break;
            //Place the selected alien in the middle of our circle
            case Input.KEY_SPACE:
                if (selected != null) {
                    Alien newAlien = makeAlien();
                    placeAlien(newAlien, circlex - newAlien.getWidth() / 2, circley - newAlien.getHeight() / 2);
                }
                break;
            //Remove the alien inside our circle
            case Input.KEY_R:
                Alien toRemove = checkCollision();
                if (checkCollision() != null) {
                    aliens.remove(toRemove);
                }
                break;
            case Input.KEY_ESCAPE:
                saveGame = !saveGame;
                saveName.setAcceptingInput(!saveName.isAcceptingInput());
                focus = !focus;
                break;
            case Input.KEY_ENTER:
                if (saveGame) {
                    toXML(saveName.getText());
                    saveGame = !saveGame;
                }
                tMain.enterState(0, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
                break;
            case Input.KEY_TAB:
                tMain.enterState(0, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
            default:
                break;
        }
    }

    /**
     * Method which checks whether a key is pressed.
     * @param key integer value of the key.
     * @param c character value of the key.
     */
    public void keyPressed(int key, char c) {
        switch (key) {
            case Input.KEY_DOWN:
                if (circle.getY() < Main.HEIGHT) {
                    circleDown = true;
                    break;
                }
            case Input.KEY_UP:
                if (circle.getY() > 0) {
                    circleUp = true;
                    break;
                }
            case Input.KEY_LEFT:
                if (circle.getX() > 0) {
                    circleLeft = true;
                    break;
                }
            case Input.KEY_RIGHT:
                if (circle.getX() < Main.WIDTH) {
                    circleRight = true;
                    break;
                }
            default: break;
        }
    }

    /**
     * Checks whether circle is above an alien in the game.
     * @return the alien which is under our circle, null if none.
     */
    public Alien checkCollision() {
        for (Alien a: aliens) {
            if (a.getBoundingBox().intersects(circle)) {
                return a;
            }
        }
        System.out.println("collision is null");
        return null;
    }

    /**
     * Places an alien according to the parameters.
     * @param alien determines which type of alien is to be placed.
     * @param x determines the x coordinate.
     * @param y determines the y coordinate.
     */
    public void placeAlien(Alien alien, int x, int y) {
        if (alien != null) {
            alien.settX(x);
            alien.settY(y);
            aliens.add(alien);
        }
    }

    /**
     * Makes a new alien which is the same type as our selected alien (Alien selected).
     * @return the new made alien.
     */
    public Alien makeAlien() {
        Alien alien;

        if (selected instanceof MiniAlien) {
            alien = new MiniAlien();
            return alien;
        } else if (selected instanceof SmallAlien) {
            alien = new SmallAlien();
            return alien;
        } else if (selected instanceof BigAlien) {
            alien = new BigAlien();
            return alien;
        } else if (selected instanceof FinalBoss) {
            alien = new FinalBoss();
            return alien;
        } else if (selected instanceof MothershipAlien) {
            alien = new MothershipAlien();
            return alien;
        } else {
            return null;
        }
    }

    /**
     * Write XML corresponding to the created Level.
     * @param docname a String value to represent the Level.
     */
    public void toXML(String docname) {
        try {

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root element
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("level");
            doc.appendChild(rootElement);

            // background element
            Element background = doc.createElement("background");
            rootElement.appendChild(background);

            // aliens elements
            Element aliens = doc.createElement("aliens");
            rootElement.appendChild(aliens);

            // add every alien
            for (Alien a : this.aliens) {
                Element alien = doc.createElement("alien");
                Element x = doc.createElement("x");
                Element y = doc.createElement("y");
                x.appendChild(doc.createTextNode(Integer.toString(a.getX())));
                y.appendChild(doc.createTextNode(Integer.toString(a.getY())));
                Element type = doc.createElement("type");
                type.appendChild(doc.createTextNode(classToXML(a)));
                alien.appendChild(x);
                alien.appendChild(y);
                alien.appendChild(type);
                aliens.appendChild(alien);
            }

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("src/main/java/application/" +  docname + ".xml"));

            transformer.transform(source, result);

            System.out.println("File saved!");

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }

    /**
     * Parse the type of Alien into XML.
     * @param selected the selected Alien.
     * @return the String name value for the Alien.
     */
    private String classToXML(Alien selected) {
        if (selected instanceof MiniAlien) {
            return "mini";
        } else if (selected instanceof SmallAlien) {
            return "small";
        } else if (selected instanceof BigAlien) {
            return "big";
        } else if (selected instanceof FinalBoss) {
            return "boss";
        } else if (selected instanceof MothershipAlien) {
            return "mother";
        } else {
            return null;
        }
    }
}
