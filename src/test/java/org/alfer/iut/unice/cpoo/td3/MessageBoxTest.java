package org.alfer.iut.unice.cpoo.td3;

import junit.framework.Assert;
import junit.framework.TestCase;

import java.util.ArrayList;

/**
 * Created by Alex on 16/10/2016.
 */
public class MessageBoxTest extends TestCase {

    MessageBox m;
    String n;
    String m1;
    String m2;

    public void setUp() throws Exception {
        super.setUp();

        n = "Boite";
        m1 = "Un message banal";
        m2 = "Un autre message encore plus banal";

        m = new MessageBox(n);
    }

    public void testGetBoxName() throws Exception {
        Assert.assertEquals(n, m.getBoxName());
    }

    public void testAddMessage() throws Exception {
        m = new MessageBox(n);
        m.addMessage(m1);
        m.addMessage(m2);
    }

    public void testGetMessages() throws Exception {
        m = new MessageBox(n);
        m.addMessage(m1);
        m.addMessage(m2);

        ArrayList<String> busMessages = m.getMessages();
        Assert.assertEquals(2, busMessages.size());
    }

    public void testRemoveOldMessages() throws Exception {
        m = new MessageBox(n);
        m.addMessage(m1);
        m.addMessage(m2);

        Assert.assertEquals(2, m.getMessages().size());
        Thread.sleep(3000);
        m.removeOldMessages(2);
        Assert.assertEquals(0, m.getMessages().size());
    }

}