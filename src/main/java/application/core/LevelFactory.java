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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Thomas on 01-09-15.
 */
public class LevelFactory {
    NodeList tLevels;
    protected int tScreenWidth;
    protected int tScreenHeight;

    public LevelFactory(int width, int height) {
        try {
            tScreenWidth = width;
            tScreenHeight = height;
            File file = new File("src/main/java/application/levels/levels.xml");
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
        NodeList alienList;
        Element levelXml = (Element) tLevels.item(levelNumber);
        String levelname = levelXml.getFirstChild().getNodeValue();
        try {
            File file = new File("src/main/java/application/levels/" + levelname +".xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = null;
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();

            //start xml parsing
            alienList = doc.getElementsByTagName("alien");

            ArrayList<Alien> aliens = loadAliens(alienList);
            level.setBackground(levelXml.getElementsByTagName("background").item(0).getTextContent());
            level.addAliens(aliens);
            level.setStartPlayer();
        } catch (Exception e) {

        }

        return level;
    }

    public ArrayList<Alien> loadAliens(NodeList alienList) {
        ArrayList<Alien> aliens = new ArrayList<Alien>();
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
