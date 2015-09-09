package application.core;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Thomas on 01-09-15.
 */
public class LevelFactory {
    NodeList tLevels;

    public LevelFactory() {
        try {
            File file = new File("src\\main\\java\\application\\levels.xml");
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

    public Level buildLevel(int levelNumber) {
        Level level = new Level();

        Element levelXml = (Element) tLevels.item(levelNumber);

        ArrayList<Alien> aliens = loadAliens(levelXml);
        level.setBackground(levelXml.getElementsByTagName("background").item(0).getTextContent());
        level.addAliens(aliens);
        level.setStartPlayer();

        return level;
    }

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
            }
            alien.readXml(eElement);
            aliens.add(alien);
        }
        return aliens;
    }

    public boolean levelExists(int levelNumber) {
        return (levelNumber < (tLevels.getLength()));
    }
}
