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
 * Created by Thomas on 01-09-15.
 */
public class LevelFactory {

    public Level buildLevel(int levelNumber) {
        Level level = new Level();

        ArrayList<Alien> aliens = loadAliens(levelNumber);
        if (aliens == null) {
            return null;
        }
        level.addAliens(aliens);
        level.setStartPlayer();

        return level;
    }

    public ArrayList<Alien> loadAliens(int levelNumber) {
        ArrayList<Alien> aliens = new ArrayList<Alien>();
        try {
            File file = new File("src\\main\\java\\application\\levels.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);

            doc.getDocumentElement().normalize();

            //Start xml parsing
            NodeList levels = doc.getElementsByTagName("level");

            if (levels.getLength() <= levelNumber) {
                return null;
            }

            Element level = (Element) levels.item(levelNumber);

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
                }
                alien.readXml(eElement);
                aliens.add(alien);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return aliens;
    }
}
