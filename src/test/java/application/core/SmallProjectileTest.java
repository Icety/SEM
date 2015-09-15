package application.core;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test class for SmallProjectile.java.
 *
 * @author Arthur Breurkes
 */
public class SmallProjectileTest {

    @Test
    public void testSmallProjectile() throws Exception {
        SmallProjectile testProjectile = new SmallProjectile(0, 0);

        assertNotNull(testProjectile);
    }
}