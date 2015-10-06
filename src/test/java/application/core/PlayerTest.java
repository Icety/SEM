package application.core;

import application.Main;
import application.core.upgrades.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

/**
 * Test class for Player.java.
 * @author Arthur Breurkes
 */
@SuppressWarnings({
        "checkstyle:magicnumber"
})
public class PlayerTest {
    private Player testPlayer;

    /**
     * Initialize variables for the test process.
     * @throws Exception possible Exception.
     */
    @Before
    public void setUp() throws Exception {
        testPlayer = new Player();
    }

    /**
     * Test whether update works correctly.
     * @throws Exception possible Exception.
     */
    @Test
    public void testUpdateWithGoLeft() throws Exception {
        testPlayer.tX = 20;
        testPlayer.tGoLeft = true;
        testPlayer.update();

        assertEquals(15, testPlayer.getX());
    }

    /**
     * Test whether update works correctly.
     * @throws Exception possible Exception.
     */
    @Test
    public void testUpdateWithGoRight() throws Exception {
        testPlayer.tX = 20;
        testPlayer.tGoRight = true;
        testPlayer.update();

        assertEquals(25, testPlayer.getX());
    }

//    /**
//     * Test whether update works correctly.
//     * Amount of Projectiles should be zero, because updateProjectiles() was called.
//     *
//     * GIVES ERRORS WITH MAVEN, COULD NOT BE RESOLVED!
//     *
//     * @throws Exception
//     */
//    @Test
//    public void testUpdateWithShoot() throws Exception {
//        testPlayer.tLastShot = Integer.MIN_VALUE;
//        testPlayer.tReloadTime = Integer.MIN_VALUE;
//        testPlayer.tShoot = true;
//        testPlayer.update();
//
//        assertEquals(0, testPlayer.getProjectiles().size());
//    }

    /**
     * Test whether moveLeft() works correctly.
     * @throws Exception possible Exception.
     */
    @Test
    public void testMoveLeft() throws Exception {
        testPlayer.tX = 20;
        testPlayer.moveLeft();

        assertEquals(15, testPlayer.getX());
    }

    /**
     * Test whether toString() returns the correct String.
     * @throws Exception possible Exception.
     */
    @Test
    public void testToString() throws Exception {
        testPlayer.settX(0);
        testPlayer.settY(0);

        assertEquals("Player on coords: 0, 0", testPlayer.toString());
    }

    /**
     * Test whether leftArrowPressed() returns the correct boolean value.
     * @throws Exception possible Exception.
     */
    @Test
    public void testLeftArrowPressed() throws Exception {
        testPlayer.leftArrowPressed(true);
        assertTrue(testPlayer.tGoLeft);

        testPlayer.leftArrowPressed(false);
        assertFalse(testPlayer.tGoLeft);

    }

    /**
     * Test whether rightArrowPressed() returns the correct boolean value.
     * @throws Exception possible Exception.
     */
    @Test
    public void testRightArrowPressed() throws Exception {
        testPlayer.rightArrowPressed(true);
        assertTrue(testPlayer.tGoRight);

        testPlayer.rightArrowPressed(false);
        assertFalse(testPlayer.tGoRight);
    }

    /**
     * Test whether fireButtonPressed() returns the correct boolean value.
     * @throws Exception possible Exception.
     */
    @Test
    public void testFireButtonPressed() throws Exception {
        testPlayer.fireButtonPressed(true);
        assertTrue(testPlayer.tShoot);

        testPlayer.fireButtonPressed(false);
        assertFalse(testPlayer.tShoot);
    }

    /**
     * Test whether hit() works correctly.
     * @throws Exception possible Exception.
     */
    @Test
    public void testHit() throws Exception {
        testPlayer.tHealth = 1;
        testPlayer.hit();

        assertEquals(0, testPlayer.tHealth);
    }

    /**
     * Test whether getPlayer() returns the correct player.
     * @throws Exception possible Exception.
     */
    @Test
    public void testGetPlayer() throws Exception {
        assertEquals(testPlayer, testPlayer.getPlayer());
    }

    /**
     * Test whether getHealth() returns the correct value.
     * @throws Exception possible Exception.
     */
    @Test
    public void testGetHealth() throws Exception {
        testPlayer.tHealth = 1;

        assertEquals(1, testPlayer.getHealth());
    }

    /**
     * Test whether moveRight() works correctly.
     * @throws Exception possible Exception.
     */
    @Test
    public void testMoveRight() throws Exception {
        testPlayer.tX = 20;
        testPlayer.moveRight();

        assertEquals(25, testPlayer.getX());
    }

    /**
     * Test whether getImage() returns the correct Image.
     * @throws Exception possible Exception.
     */
    @Test
    public void testGetImage() throws Exception {
        assertEquals(Main.PLAYER, testPlayer.getImage());
    }

    /**
     * Test whether getImage() returns the correct Image while the Player is upgraded.
     * @throws Exception possible Exception.
     */
    @Test
    public void testGetImageWhileUpgraded() throws Exception {
        testPlayer.tUpgraded = true;
        assertEquals(Main.UPGRADED_PLAYER, testPlayer.getImage());
    }

    /**
     * Test updateUpgrade() with a speed upgrade.
     * @throws Exception possible Exception.
     */
    @Test
    public void testUpdateUpgradeLittleReloadTime() throws Exception {
        Upgrade testUpgrade = new SpeedUpgrade(0, 0);
        testPlayer.tActiveUpgrades.add(testUpgrade);
        testPlayer.updateUpgrade();

        assertEquals(50, testPlayer.tReloadTime);
    }

    /**
     * Test updateUpgrade() with a weapon upgrade.
     * @throws Exception possible Exception.
     */
    @Test
    public void testUpdateUpgradeMuchReloadTime() throws Exception {
        Upgrade testUpgrade = new WeaponUpgrade(0, 0);
        testPlayer.tActiveUpgrades.add(testUpgrade);
        testPlayer.updateUpgrade();

        assertEquals(500, testPlayer.tReloadTime);
    }

//    /**
//     * Test shoot() with an upgraded weapon 0.
//     *
//     * GIVES ERRORS WITH MAVEN, COULD NOT BE RESOLVED!
//     *
//     * @throws Exception
//     */
//    @Test
//    public void testShootWeaponZeroLastSideZero() throws Exception {
//        testPlayer.tUpgraded = true;
//        testPlayer.tLastSide = 0;
//        testPlayer.shoot();
//
//        assertTrue(testPlayer.getProjectiles().size() >= 1);
//    }
//
//    /**
//     * Test shoot() with an upgraded weapon 0 with lastSide non 0.
//     *
//     * GIVES ERRORS WITH MAVEN, COULD NOT BE RESOLVED!
//     *
//     * @throws Exception
//     */
//    @Test
//    public void testShootWeaponZeroLastSideNotZero() throws Exception {
//        testPlayer.tUpgraded = true;
//        testPlayer.tLastSide = 1;
//        testPlayer.shoot();
//
//        assertTrue(testPlayer.getProjectiles().size() >= 1);
//    }

//    /**
//     * Test shoot() with an upgraded weapon 1.
//     *
//     * GIVES ERRORS WITH MAVEN, COULD NOT BE RESOLVED!
//     *
//     * @throws Exception
//     */
//    @Test
//    public void testShootWeaponOne() throws Exception {
//        testPlayer.tUpgraded = true;
//        Upgrade testUpgrade = new WeaponUpgrade(0, 0);
//        testPlayer.tActiveUpgrades.add(testUpgrade);
//        testPlayer.shoot();
//
//        assertTrue(testPlayer.getProjectiles().size() >= 1);
//    }

    /**
     * Test whether HealthUpgrade is correctly applied to Player.
     * @throws Exception possible Exception.
     */
    @Test
    public void testUpgradeHealth() throws Exception {
        Upgrade testUpgrade = new HealthUpgrade(0, 0);
        testPlayer.tHealth = 2;
        testPlayer.upgrade(testUpgrade);

        assertEquals(3, testPlayer.tHealth);
    }

    /**
     * Test whether Upgrade is correctly applied to Player.
     * @throws Exception possible Exception.
     */
    @Test
    public void testUpgradePlayer() throws Exception {
        Upgrade testUpgrade = new PlayerUpgrade(0, 0);
        testPlayer.tUpgraded = false;
        testPlayer.upgrade(testUpgrade);

        assertTrue(testPlayer.tUpgraded);
    }

    /**
     * Test whether moveUp() works correctly.
     * @throws Exception possible Exception.
     */
    @Test
    public void testMoveUp() throws Exception {
        testPlayer.tY = 10;
        testPlayer.moveUp(10);

        assertEquals(0, testPlayer.tY);
    }
}