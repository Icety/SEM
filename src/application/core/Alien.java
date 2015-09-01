package application.core;

import javafx.scene.image.Image;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * Created by Thomas on 01-09-15.
 */
public class Alien {
    protected int tX;
    protected int tY;
    protected Image tImage;

    public void readXml(Node node) {
        Element eElement = (Element) node;

        tX = Integer.parseInt(eElement.getElementsByTagName("x").item(0).getTextContent());
        tY = Integer.parseInt(eElement.getElementsByTagName("y").item(0).getTextContent());
    }

    public int getX() {
        return tX;
    }

    public int getY() {
        return tY;
    }

    public Image getImage() {
        return tImage;
    }

    public void move() {

    }


}
