package org.alfer.iut.unice.cpoo.td3;

import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * Created by Alex on 16/10/2016.
 */
public class BusTest extends TestCase {

    Bus bus;
    String n;
    String b1;
    String m1;
    String m2;


    public void setUp() throws Exception {
        n = "Bus";
        b1 = "Boite A";
        m1 = "Un message";
        m2 = "Un autre message";

        bus = new Bus(n);
    }

    public void testGetBusName() throws Exception {
        bus = new Bus(n);
        Assert.assertEquals(n, bus.getBusName());
    }

    public void testBoxExist() throws Exception {
        bus = new Bus(n);
        bus.createBox(b1);
        Assert.assertEquals(true, bus.boxExist(b1));
        Assert.assertEquals(false, bus.boxExist("Boite B"));
    }

    public void testReadMessages() throws Exception {
        bus = new Bus(n);
        bus.createBox(b1);
        bus.sendMessage(b1, m1);
        bus.sendMessage(Bus.DEFAULTBOXNAME, m2);

        Assert.assertEquals(1, bus.readMessages(b1).size()); //Get messages in box b1...
        Assert.assertEquals(1, bus.readMessages(Bus.DEFAULTBOXNAME).size()); //Get messages in default box...
        Assert.assertEquals(2, bus.readMessages("").size()); //Get all messages...
    }

    public void testSendMessage() throws Exception {
        bus = new Bus(n);

        bus.createBox(b1);
        Assert.assertEquals(true, bus.sendMessage(b1, m1));
        Assert.assertEquals(true, bus.sendMessage(Bus.DEFAULTBOXNAME, m1));
        Assert.assertEquals(false, bus.sendMessage("Boite B", m1));
    }

    public void testCreateBox() throws Exception {
        bus = new Bus(n);

        Assert.assertEquals(true, bus.createBox(b1)); //Create box A...
        Assert.assertEquals(false, bus.createBox(b1)); //Create same box...
        Assert.assertEquals(false, bus.createBox(Bus.DEFAULTBOXNAME)); //Create default box...
    }

    public void testRemoveOldMessages() throws Exception {
        bus = new Bus(n);

        bus.sendMessage(Bus.DEFAULTBOXNAME, m1);
        bus.sendMessage(Bus.DEFAULTBOXNAME, m2);
        Assert.assertEquals(2, bus.readMessages(Bus.DEFAULTBOXNAME).size());
        Thread.sleep(3000);
        bus.removeOldMessages(2);
        Assert.assertEquals(0, bus.readMessages(Bus.DEFAULTBOXNAME).size());
    }

}