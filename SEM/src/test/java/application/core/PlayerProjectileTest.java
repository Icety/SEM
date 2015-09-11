package application.core;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test class for PlayerProjectile.java.
 *
 * @author Arthur Breurkes
 */
public class PlayerProjectileTest {

    /**
     * Test whether the constructor method works correctly.
     *
     * @throws Exception
     */
    @Test
    public void setUp() throws Exception {
        PlayerProjectile testProjectile = new PlayerProjectile(0, 0);

        assertNotNull(testProjectile);
    }
}