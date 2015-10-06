package application.core;

import application.Main;
import application.core.upgrades.SpeedUpgrade;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test class for SpeedUpgrade.java.
 * @author Arthur Breurkes
 */
@SuppressWarnings({
        "checkstyle:magicnumber"
})
public class SpeedUpgradeTest {
    private SpeedUpgrade testUpgrade;

    /**
     * Test whether the constructor works with difficulty 1.
     * @throws Exception possible Exception.
     */
    @Test
    public void testConstructorWithDifficultyOne() throws Exception {
        Main.DIFFICULTY = 1;
        testUpgrade = new SpeedUpgrade(0, 0);

        assertEquals(2, testUpgrade.getSpeed());
    }

    /**
     * Test whether the constructor works with difficulty 2.
     * @throws Exception possible Exception.
     */
    @Test
    public void testConstructorWithDifficultyTwo() throws Exception {
        Main.DIFFICULTY = 2;
        testUpgrade = new SpeedUpgrade(0, 0);

        assertEquals(3, testUpgrade.getSpeed());
    }

    /**
     * Test whether the constructor works with difficulty 3.
     * @throws Exception possible Exception.
     */
    @Test
    public void testConstructorWithDifficultyThree() throws Exception {
        Main.DIFFICULTY = 3;
        testUpgrade = new SpeedUpgrade(0, 0);

        assertEquals(5, testUpgrade.getSpeed());
    }

    /**
     * Test whether getImage() returns the correct Image.
     * @throws Exception possible Exception.
     */
    @Test
    public void testGetImage() throws Exception {
        testUpgrade = new SpeedUpgrade(0, 0);

        assertEquals(Main.UPGRADE_0, testUpgrade.getImage());
    }
}
