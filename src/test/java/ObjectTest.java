import org.junit.Assert;
import org.junit.Test;

public class ObjectTest {
    @Test
    public void objects() {
        // Given
        LFUCache<String, Object> objectLFUCache = new LFUCache<>(5);
        Object expectedObject = new Object();
        objectLFUCache.insert("Key0", expectedObject);
        for (int i = 1; i < 5; i++) {
            objectLFUCache.insert("Key" + i, new Object());
            objectLFUCache.access("Key" + i);
            objectLFUCache.access("Key" + i);
        }

        //When
        Object actualObject = objectLFUCache.insert("Key5", new Object());

        //Then
        Assert.assertEquals(expectedObject, actualObject);
    }

    @Test
    public void objects2() {
        // Given
        LFUCache<String, Object> objectLFUCache = new LFUCache<>(5);
        Object expectedObject = new Object();
        objectLFUCache.insert("Key", expectedObject);

        //When
        Object actualObject = objectLFUCache.access("Key");

        //Then
        Assert.assertEquals(expectedObject, actualObject);
    }

}
