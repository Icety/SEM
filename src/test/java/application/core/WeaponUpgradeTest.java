package application.core;

import application.Main;
import application.core.upgrades.WeaponUpgrade;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Test class for HealthUpgrade.java.
 * @author Arthur Breurkes
 */
@SuppressWarnings({
        "checkstyle:magicnumber"
})
public class WeaponUpgradeTest {
    private WeaponUpgrade testUpgrade;

    /**
     * Test whether the constructor works with difficulty 1.
     * @throws Exception possible Exception.
     */
    @Test
    public void testConstructorWithDifficultyOne() throws Exception {
        Main.DIFFICULTY = 1;

        testUpgrade = new WeaponUpgrade(0, 0);

        assertEquals(2, testUpgrade.tSpeed);
    }

    /**
     * Test whether the constructor works with difficulty 2.
     * @throws Exception possible Exception.
     */
    @Test
    public void testConstructorWithDifficultyTwo() throws Exception {
        Main.DIFFICULTY = 2;

        testUpgrade = new WeaponUpgrade(0, 0);

        assertEquals(2, testUpgrade.tSpeed);
    }

    /**
     * Test whether the constructor works with difficulty 3.
     * @throws Exception possible Exception.
     */
    @Test
    public void testConstructorWithDifficultyThree() throws Exception {
        Main.DIFFICULTY = 3;

        testUpgrade = new WeaponUpgrade(0, 0);

        assertEquals(4, testUpgrade.tSpeed);
    }

    /**
     * Test whether getImage() returns the correct Image.
     * @throws Exception possible Exception.
     */
    @Test
    public void testGetImage() throws Exception {
        testUpgrade = new WeaponUpgrade(0, 0);

        assertNull(testUpgrade.getImage());
    }
}
