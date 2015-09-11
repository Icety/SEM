package application.core;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

//ToDo: Write testShoot() and testEndOfScreen() (Dependency unreachable)
/**
 * Test class for FinalBoss.java.
 *
 * @author Arthur Breurkes
 */
public class FinalBossTest {
    private FinalBoss testBoss;

    /**
     * Initialize variables for tests.
     */
    @Before
    public void setUp() {
        testBoss = new FinalBoss();
    }

    /**
     * Test whether addShootChance() adds a correct shoot chance.
     *
     * @throws Exception
     */
    @Test
    public void testAddShootChance() throws Exception {
        int oldChance = testBoss.tShootChance;
        testBoss.addShootChance();

        assertTrue(testBoss.tShootChance >= oldChance);
    }

//    @Test
//    public void testShoot() throws Exception {
//
//    }
//
//    @Test
//    public void testEndOfScreen() throws Exception {
//
//    }
}