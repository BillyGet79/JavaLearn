package BinarySearch;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

/**
 * CodeTest
 *
 * @author 29096
 * @version 1.0
 * @date 2025/4/18
 * @description TODO
 */
public class CodeTest {

    private BinarySearch bs;

    @Before
    public void setUp() {
        bs = new BinarySearch();
    }

    @Test
    public void testExist() {
        Assert.assertTrue(bs.exist(new int[]{1, 3, 5, 7, 9}, 5));
    }

    @Test
    public void testNearestRightIndex() {
        Assert.assertEquals(2, bs.nearestRightIndex(new int[]{1, 3, 5, 7, 9}, 5));
    }

    @Test
    public void testNearestLeftIndex() {
        Assert.assertEquals(2, bs.nearestLeftIndex(new int[]{1, 3, 5, 7, 9}, 5));
    }

    @Test
    public void testGetLessIndex() {
        Assert.assertEquals(1, bs.getLessIndex(new int[]{3, 1, 5, 7, 9}));
    }

}
