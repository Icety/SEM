/**
 * Created by Niek on 9/10/2015.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.Assert;
import org.junit.Test;

public class hashingMethodsTest {
    @Test
    public void testETHHashHelloWorld() {
        Assert.assertEquals(1,
                new hash1(DEFAULT_SIZE).hash("Hello World!"));
    }

    @Test
    public void testGNUCPPHashHelloWorld() {
        Assert.assertEquals(1,
                new GNUCPPHash(DEFAULT_SIZE).hash("Hello World!"));
    }

    @Test
    public void testGNUCC1HashHelloWorld() {
        Assert.assertEquals(1,
                new GNUCC1Hash(DEFAULT_SIZE).hash("Hello World!"));
    }

    @Test
    public void testHashCodeHashHelloWorld() {
        Assert.assertEquals(3,
                new HashCodeHash(DEFAULT_SIZE).hash("Hello World!"));
    }

    @Test
    public void testETHHashArthurDent2xSize() {
        Assert.assertEquals(4,
                new ETHHash(2 * DEFAULT_SIZE).hash("Arthur Dent"));
    }

    @Test
    public void testGNUCPPHashArthurDent2xSize() {
        Assert.assertEquals(4,
                new GNUCPPHash(2 * DEFAULT_SIZE).hash("Arthur Dent"));
    }

    @Test
    public void testGNUCC1HashArthurDent2xSize() {
        Assert.assertEquals(0,
                new GNUCC1Hash(2 * DEFAULT_SIZE).hash("Arthur Dent"));
    }

    @Test
    public void testHashCodeHashArthurDent2xSize() {
        Assert.assertEquals(3,
                new HashCodeHash(2 * DEFAULT_SIZE).hash("Arthur Dent"));
    }

    @Test
    public void testETHHashFortyTwo2xSize() {
        Assert.assertEquals(2,
                new ETHHash(2 * DEFAULT_SIZE).hash("FortyTwo"));
    }

    @Test
    public void testGNUCPPHashFortyTwo2xSize() {
        Assert.assertEquals(3,
                new GNUCPPHash(2 * DEFAULT_SIZE).hash("FortyTwo"));
    }

    @Test
    public void testGNUCC1HashFortyTwo2xSize() {
        Assert.assertEquals(6,
                new GNUCC1Hash(2 * DEFAULT_SIZE).hash("FortyTwo"));
    }

    @Test
    public void testHashCodeHashFortyTwo2xSize() {
        Assert.assertEquals(6,
                new HashCodeHash(2 * DEFAULT_SIZE).hash("FortyTwo"));
    }

    @Test
    public void testHashTablePut3Items() {
        Hashtable table = new ETHHash(DEFAULT_SIZE);
        table.put("Hello World!", 42);
        table.put("FortyTwo", 84);
        table.put("Arthur Dent", 123);

        Assert.assertTrue(table.containsKey("Hello World!"));
        Assert.assertTrue(table.containsKey("Arthur Dent"));
        Assert.assertTrue(table.containsKey("FortyTwo"));
        Assert.assertFalse(table.containsKey("Not In There!"));

        Assert.assertEquals(42, table.get("Hello World!"));
        Assert.assertEquals(84, table.get("FortyTwo"));
        Assert.assertEquals(123, table.get("Arthur Dent"));
        Assert.assertEquals(0, table.get("Not In There!"));

    }

    @Test
    public void testHashTableOverrideItem() {
        Hashtable table = new ETHHash(DEFAULT_SIZE);
        table.put("Hello World!", 42);

        Assert.assertTrue(table.containsKey("Hello World!"));
        Assert.assertFalse(table.containsKey("Not In There!"));

        Assert.assertEquals(42, table.get("Hello World!"));
        Assert.assertEquals(0, table.get("Not In There!"));

        table.put("Hello World!", 21);
        Assert.assertTrue(table.containsKey("Hello World!"));
        Assert.assertFalse(table.containsKey("Not In There!"));

        Assert.assertEquals(21, table.get("Hello World!"));
        Assert.assertEquals(0, table.get("Not In There!"));

    }
}
