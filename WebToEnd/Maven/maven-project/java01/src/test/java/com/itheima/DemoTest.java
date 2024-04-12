package com.itheima;

import org.junit.Assert;
import org.junit.Test;

public class DemoTest {
    @Test
    public void testSay() {
        Demo demo = new Demo();
        String ret = demo.say("itheima");
        Assert.assertEquals("hello itheima", ret);
    }
}
