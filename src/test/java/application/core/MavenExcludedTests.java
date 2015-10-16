//package application.core;
//
//import application.core.aliens.Alien;
//import application.core.aliens.SmallAlien;
//import application.core.projectiles.Projectile;
//import application.core.upgrades.*;
//import application.logger.Logger;
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.Mock;
//
//import java.util.ArrayList;
//
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertTrue;
//import static org.junit.Assert.assertEquals;
//import static org.mockito.Mockito.mock;
//
///**
// * Test class for tests that should be ignored by Maven.
// * @author Arthur Breurkes.
// */
//public class MavenExcludedTests {
//    private Game testGame;
//    private Game testMultiPlayerGame;
//    private Alien testAlien;
//    private Player testPlayer;
//    private Level testLevel;
//    private Projectile testProjectile;
//
//    @Mock
//    public final Player MockedPlayer = mock(Player.class);
//    @Mock
//    public final Logger mockedLogger = mock(Logger.class);
//
//    /**
//     * Initialize variables for the test process.
//     * @throws Exception possible Exception.
//     */
//    @Before
//    public void setUp() throws Exception {
//        testGame = new Game(10, 10, mockedLogger, 1);
//        testPlayer = new Player();
//        testLevel = new Level(1, testGame.getPlayerController());
//        testAlien = new Alien();
//        ArrayList<Alien> testAliens = new ArrayList<>();
//        testAliens.add(testAlien);
//        testGame = new Game(10, 10, mockedLogger, 1);
//        testMultiPlayerGame = new Game(10, 10, mockedLogger, 2);
//        testGame.tLogger = mockedLogger;
//        testMultiPlayerGame.tLogger = mockedLogger;
//        testLevel.tAliens = testAliens;
//        testGame.tLevel = testLevel;
//        testProjectile = new Projectile(10, 10);
//    }
//
//    /**
//     * Test whether update works correctly.
//     * Amount of Projectiles should be zero, because updateProjectiles() was called.
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
//
//    /**
//     * Test shoot() with an upgraded weapon 0.
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
//
//    /**
//     * Test shoot() with an upgraded weapon 1.
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
//
//    /**
//     * Test whether checkCollision() works correctly.
//     * Alien is hit.
//     * @throws Exception possible Exception.
//     */
//    @Test
//    public void testCheckCollisionBetweenAlienAndProjectile() throws Exception {
//    testGame.tWon = false;
//        ArrayList<Alien> smallAliens = new ArrayList<>();
//        Alien smallAlien = new SmallAlien();
//        smallAlien.tX = 10;
//        smallAlien.tY = 10;
//        smallAliens.add(smallAlien);
//        testLevel = new Level(1, testGame.getPlayerController());
//        testLevel.addAliens(smallAliens);
//        testGame.tLevel = testLevel;
//        ArrayList<Player> testPlayers = new ArrayList<>();
//        testPlayer.addProjectile(testProjectile);
//        testPlayers.add(testPlayer);
//        testGame.checkCollision();
//
//        assertFalse(testPlayer.getProjectiles().contains(testProjectile));
//    }
//
//    /**
//     * Test whether checkCollision() works correctly.
//     * Player is hit.
//     * Does not die.
//     * @throws Exception possible Exception.
//     */
//    @Test
//    public void testCheckCollisionBetweenPlayerAndProjectile() throws Exception {
//        testGame.tWon = false;
//        ArrayList<Alien> smallAliens = new ArrayList<>();
//        Alien smallAlien = new SmallAlien();
//        smallAlien.tX = 10;
//        smallAlien.tY = 10;
//        smallAliens.add(smallAlien);
//        testLevel = new Level();
//        testLevel.addAliens(smallAliens);
//        testGame.tLevel = testLevel;
//        testPlayer.tX = 10;
//        testPlayer.tY = 10;
//        testGame.tPlayer = testPlayer;
//        smallAlien.addProjectile(testProjectile);
//        testGame.checkCollision();
//
//        assertFalse(smallAlien.getProjectiles().contains(testProjectile));
//    }
//
//    /**
//     * Test whether checkCollision() works correctly.
//     * Player is hit.
//     * Dies.
//     * @throws Exception possible Exception.
//     */
//    @Test
//    public void testCheckCollisionKillPlayer() throws Exception {
//        ArrayList<Player> testPlayers = new ArrayList<>();
//        testGame.tWon = false;
//        ArrayList<Alien> smallAliens = new ArrayList<>();
//        Alien smallAlien = new SmallAlien();
//        smallAlien.tX = 10;
//        smallAlien.tY = 10;
//        smallAliens.add(smallAlien);
//        testLevel = new Level();
//        testLevel.addAliens(smallAliens);
//        testGame.tLevel = testLevel;
//        testPlayer.tX = 10;
//        testPlayer.tY = 10;
//        testPlayer.tHealth = 1;
//        testPlayer.addProjectile(testProjectile);
//        testPlayers.add(testPlayer);
//        smallAlien.addProjectile(testProjectile);
//        testGame.tPlayers = testPlayers;
//        testGame.checkCollision();
//
//        assertTrue(testGame.tLost);
//    }
//
//    /**
//     * Test whether the sounds work.
//     * @throws Exception possible Exception.
//     */
//    @Test
//    public void testSounds() throws Exception {
//        testPlayer.laserSound();
//        testGame.playerDeathSound();
//        testGame.invaderKilledSound();
//        testGame.motherShipKilled();
//    }
//}
