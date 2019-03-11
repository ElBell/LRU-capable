import org.junit.Assert;
import org.junit.Test;

public class LFUItemTest {
    @Test
    public void createLFUItem() {
        //Given
        FrequencyNode expectedNode = new FrequencyNode();
        Object expectedData = new Object();
        LFUItem lfuItem = new LFUItem(expectedData, expectedNode);

        //When
        FrequencyNode actualNode = lfuItem.getParent();
        Object actualData = lfuItem.getData();
        //Then
        Assert.assertEquals(expectedData, actualData);
        Assert.assertEquals(expectedNode, actualNode);
    }
}
