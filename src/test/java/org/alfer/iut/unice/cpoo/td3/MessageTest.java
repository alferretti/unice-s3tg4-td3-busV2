package org.alfer.iut.unice.cpoo.td3;

import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * Created by Alex on 16/10/2016.
 */
public class MessageTest extends TestCase {

    Message m;
    String c;

    public void setUp() throws Exception {
        super.setUp();
        c = "Un Message";
        m = new Message(c);
    }

    public void testGetContent() throws Exception {
        Assert.assertEquals(c, m.getContent());
    }

    public void testIsObsolete() throws Exception {
        Assert.assertEquals(false, m.isObsolete(2));
        Thread.sleep(3000); //Wait 3 seconds
        Assert.assertEquals(true, m.isObsolete(2));
    }

}