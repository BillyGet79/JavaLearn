package BitCompute;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * CodeTest
 *
 * @author 29096
 * @version 1.0
 * @date 2025/5/1
 * @description TODO
 */
public class CodeTest {
    private BitCompute bc;

    @Before
    public void setUp() throws Exception {
        bc = new BitCompute();
    }

    @Test
    public void testGetOddTimesNum1() {
        Assert.assertEquals(1, bc.getOddTimesNum1(new int[]{5, 5, 1, 1, 1, 3, 4, 7, 3, 4, 7}));
    }

    @Test
    public void testGetOddTimesNum2() {
        int[] test = new int[]{1, 1, 2, 2, 3, 3, 3, 5, 7, 7};
        int[] ans = bc.getOddTimesNum2(test);
        Assert.assertTrue((ans[0] == 3 || ans[0] == 5) && (ans[1] == 3 || ans[1] == 5) && ans[0] != ans[1]);
    }

    @Test
    public void testOnlyKTimes() {
        int[] test = new int[]{1, 1, 1, 2, 2, 2, 3, 3, 3, 5, 5, 7, 7, 7};
        Assert.assertEquals(5, bc.onlyKTimes(test, 2, 3));
    }

}
