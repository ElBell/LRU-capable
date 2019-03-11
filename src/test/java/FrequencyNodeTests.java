import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FrequencyNodeTests {
    private LFUCache<Integer, String> testCache;
    @Before
    public void setUp() {
        testCache = new LFUCache<>(10);
    }

    @Test(expected = NullPointerException.class)
    public void freqSizeNothing() {
        //Given

        //When
        int size = testCache.size(testCache.getHead());
        //Then
    }

    @Test
    public void freqSize2() {
        //Given
        testCache.insert(1, "Testing1");
        testCache.insert(2, "Testing2");
        int expected = 2;

        //When
        int actual = testCache.size(testCache.getFirstNode());
        //Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void freqSizedOut() {
        //Given
        testCache.insert(1, "Testing1");
        testCache.insert(2, "Testing2");
        testCache.access(1);
        int expected = 1;

        //When
        int actual = testCache.size(testCache.getFirstNode());
        //Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void allItemsSizedOutNodeDeleted() {
        //Given
        testCache.insert(1, "Testing1");
        testCache.insert(2, "Testing2");
        Object firstNode = testCache.getFirstNode();
        testCache.access(1);
        testCache.access(2);
        Object secondNode = testCache.getFirstNode();

        //When
        Assert.assertNotEquals(firstNode, secondNode);
        //Then


    }
}
