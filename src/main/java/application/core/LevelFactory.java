package application.core;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;

/**
 * Class for LevelFactory.
 * @author Thomas Oomens
 */
@SuppressWarnings({
        "checkstyle:visibilitymodifier"
})
public class LevelFactory {
    protected NodeList tLevels;
    private static LevelFactory tUniqueFactory;
    protected static int tScreenWidth;
    protected static int tScreenHeight;

    /**
     * Constructor for the LevelFactory.
     * @param width width of the game.
     * @param height height of the game.
     */
    private  LevelFactory(int width, int height) {
        try {
            tScreenWidth = width;
            tScreenHeight = height;
            File file = new File("src/main/java/application/levels.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = null;
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();

            //Start xml parsing
            tLevels = doc.getElementsByTagName("level");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static synchronized LevelFactory getFactory() {
        if (tUniqueFactory == null) {
            tUniqueFactory = new LevelFactory(tScreenWidth, tScreenHeight);
        }
        return tUniqueFactory;
    }


    /**
     * Build the parsed Level.
     * @param levelNumber the number belonging to the Level.
     * @return the built Level.
     */
    public Level buildLevel(int levelNumber) {
        Level level = new Level();

        Element levelXml = (Element) tLevels.item(levelNumber);

        ArrayList<Alien> aliens = loadAliens(levelXml);
        level.setBackground(levelXml.getElementsByTagName("background").item(0).getTextContent());
        level.addAliens(aliens);
        level.setStartPlayer();

        return level;
    }

    /**
     * Load all Aliens beloning to a Level.
     * @param level the read XMl for the Aliens in the Level.
     * @return an ArrayList of Aliens.
     */
    public ArrayList<Alien> loadAliens(Element level) {
        ArrayList<Alien> aliens = new ArrayList<Alien>();
        NodeList alienList = level.getElementsByTagName("alien");
        Alien alien = new Alien();
        for (int temp = 0; temp < alienList.getLength(); temp++) {
            Node node = alienList.item(temp);
            Element eElement = (Element) node;
            switch (eElement.getElementsByTagName("type").item(0).getTextContent()) {
                case "small":
                    alien = new SmallAlien();
                    break;
                case "mother":
                    alien = new MothershipAlien();
                    break;
                case "big":
                    alien = new BigAlien();
                    break;
                case "mini":
                    alien = new MiniAlien();
                    break;
                case "boss":
                    alien = new FinalBoss();
                    break;
                default: break;
            }
            alien.readXml(eElement);
            aliens.add(alien);
        }
        return aliens;
    }

    /**
     * Check whether a Level exists.
     * @param levelNumber the number belonging to the Level.
     * @return the boolean value.
     */
    public boolean levelExists(int levelNumber) {
        return (levelNumber < (tLevels.getLength()));
    }
}
