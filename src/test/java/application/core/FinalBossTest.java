package application.core;

import application.Main;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * Test class for FinalBoss.java.
 * @author Arthur Breurkes
 */
@SuppressWarnings({
        "checkstyle:magicnumber"
})
public class FinalBossTest {
    private FinalBoss testBoss;

    /**
     * Initialize variables for tests.
     */
    @Before
    public void setUp() {
        testBoss = new FinalBoss();
        testBoss.tCanShoot = true;
    }

    /**
     * Test whether addShootChance() adds a correct shoot chance.
     * @throws Exception possible Exception.
     */
    @Test
    public void testAddShootChance() throws Exception {
        testBoss.addShootChance();

        assertTrue(testBoss.tShootChance >= 0);
    }

    /**
     * Test whether shoot() works correctly.
     * @throws Exception possible Exception.
     */
    @Test
    public void testShoot() throws Exception {
        testBoss.setCanShoot(true);
        for (int i = 0; i < 10000; i++) {
            testBoss.shoot();
        }
        assertTrue(testBoss.tProjectiles.size() > 0);
    }

    /**
     * Test whether endOfScreen() works correctly.
     * @throws Exception possible Exception.
     */
    @Test
    public void testEndOfScreen() throws Exception {
        testBoss.tX = 10;
        assertTrue(testBoss.endOfScreen());

        testBoss.tX = 0;
        assertFalse(testBoss.endOfScreen());
    }

    /**
     * Test whether getImage() returns the correct image.
     * @throws Exception possible Exception.
     */
    @Test
    public void testGetImageNormal() throws Exception {
        assertEquals(Main.BOSS_BACHELLI, testBoss.getImage());
    }

    /**
     * Test whether getImage() returns the correct image.
     * @throws Exception possible Exception.
     */
    @Test
    public void testGetImageCharge() throws Exception {
        testBoss.tSecondShot = 151;

        assertEquals(Main.BOSS_BACHELLI_CHARGE, testBoss.getImage());
        assertEquals(250, testBoss.tHeight);
    }

    /**
     * Test whether toString() returns the correct string.
     * @throws Exception possible Exception.
     */
    @Test
    public void testToString() throws Exception {
        testBoss.tX = 1;
        testBoss.tY = 1;

        assertEquals("FinalBoss on coords: 1, 1", testBoss.toString());
    }
}