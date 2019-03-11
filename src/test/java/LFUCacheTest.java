
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;


public class LFUCacheTest {
    private LFUCache<Integer, String> testCache;
    @Before
    public void setUp() {
        testCache = new LFUCache<>(10);
    }

    @Test
    public void access() {
        // Given
        testCache.insert(1, "Testing1");
        String expected = "Testing1";

        //When
        String actual = testCache.access(1);

        //Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void access2() {
        // Given
        testCache.insert(1, "Testing1");
        testCache.insert(2, "Testing2");
        String expected = "Testing1";

        //When
        String actual = testCache.access(1);

        //Then
        Assert.assertEquals(expected, actual);
    }

    @Test(expected = NoSuchElementException.class)
    public void accessNoKey() {
        // Given
        testCache.insert(1, "Testing1");
        testCache.insert(2, "Testing2");

        //When
        testCache.access(0);

        //Then
        //Exception is thrown
    }

    @Test(expected = IllegalArgumentException.class)
    public void insertError() {
        // Given
        testCache.insert(1, "Testing1");

        //When
        testCache.insert(1, "Testing2");

        //Then
        //Exception is thrown
    }

    @Test
    public void insertLessThanCapacity() {
        // Given
        for (int i = 1; i < 10; i++) {
            testCache.insert(i, "Testing" + i);
        }

        //When
        String actual = testCache.insert(10, "Testing10");

        //Then
        Assert.assertNull(actual);
    }

    @Test
    public void insertMoreThanCapacity() {
        // Given
        for (int i = 1; i < 11; i++) {
            testCache.insert(i, "Testing" + i);
        }
        String expected = "Testing1";

        //When
        String actual = testCache.insert(11, "Testing11");

        //Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void insertMoreThanCapacityWithAccess() {
        // Given
        for (int i = 1; i < 10; i++) {
            testCache.insert(i, "Testing" + i);
            testCache.access(i);
        }
        testCache.insert(0, "Testing");
        String expected = "Testing";

        //When
        String actual = testCache.insert(11, "Testing11");

        //Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void insertMoreThanCapacityWithAccess2() {
        // Given
        testCache.insert(0, "Testing");
        for (int i = 1; i < 10; i++) {
            testCache.insert(i, "Testing" + i);
            testCache.access(i);
            testCache.access(i);
        }
        testCache.access(0);
        String expected = "Testing";

        //When
        String actual = testCache.insert(11, "Testing11");

        //Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void insertMoreThanCapacityWithAccess3() {
        // Given
        testCache.insert(0, "Testing");
        testCache.access(0);
        for (int i = 1; i < 20; i++) {
            testCache.insert(i, "Testing" + i);
            testCache.access(i);
            testCache.access(i);
        }

        String expected = "Testing10";

        //When
        String actual = testCache.insert(20, "Testing20");

        //Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getLFUItem() {
        //Given
        testCache.insert(1, "Testing1");
        testCache.insert(2, "Testing2");
        testCache.access(1);
        testCache.access(1);
        testCache.access(1);
        String expected = "Testing2";

        //When
        String actual = testCache.getLFUItem();

        //Then
        Assert.assertEquals(expected, actual);

    }

    @Test(expected = NoSuchElementException.class)
    public void getLFUItemNoSet() {
        //Given

        //When
        testCache.getLFUItem();

        //Then
        //Exception is thrown

    }

}