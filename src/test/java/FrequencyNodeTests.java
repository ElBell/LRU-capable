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
        FrequencyNode frequencyNode = testCache.getHead();
        frequencyNode.size();
        //Then
    }

    @Test
    public void freqSize2() {
        //Given
        testCache.insert(1, "Testing1");
        testCache.insert(2, "Testing2");
        int expected = 2;

        //When
        FrequencyNode frequencyNode = testCache.getHead().getNext();
        int actual = frequencyNode.size();
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
        FrequencyNode frequencyNode = testCache.getHead().getNext();
        int actual = frequencyNode.size();
        //Then
        Assert.assertEquals(expected, actual);
    }

    @Test(expected = NullPointerException.class)
    public void allItemsSizedOutNodeDeleted() {
        //Given
        testCache.insert(1, "Testing1");
        testCache.insert(2, "Testing2");
        testCache.access(1);
        testCache.access(2);

        //When
        FrequencyNode frequencyNode = testCache.getHead();
        frequencyNode.size();
        //Then
    }
}
