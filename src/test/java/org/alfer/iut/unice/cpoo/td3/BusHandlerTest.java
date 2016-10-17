package org.alfer.iut.unice.cpoo.td3;

import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * Created by Alex on 16/10/2016.
 */
public class BusHandlerTest extends TestCase {
    BusHandler bh;

    public void setUp() throws Exception {
        super.setUp();
        bh = new BusHandler();
    }

    public void testCreateBus() throws Exception {
        bh = new BusHandler();
        Assert.assertEquals(true, bh.createBus("Un Bus"));
        //On tente de recreer le même bus...
        Assert.assertEquals(false, bh.createBus("Un Bus"));
    }

    public void testCreateMessageBox() throws Exception {
        bh = new BusHandler();
        bh.createBus("Un Bus");
        Assert.assertEquals(true, bh.createMessageBox("Un Bus", "Une Boite"));
        //On tente de recreer la même boite...
        Assert.assertEquals(false, bh.createMessageBox("Un Bus", "Une Boite"));
    }

    public void testSendMessage() throws Exception {
        bh = new BusHandler();
        bh.createBus("Un Bus");
        bh.createMessageBox("Un Bus", "Une Boite");
        Assert.assertEquals(true, bh.sendMessage("Un Bus", "Une Boite", "Le message dans la boite"));
        Assert.assertEquals(true, bh.sendMessage("Un Bus", "Le message dans le default"));
        Assert.assertEquals(false, bh.sendMessage("Un Bus Inexistant", "Le Message dans le default"));
    }

    public void testReadMessages() throws Exception {
        bh = new BusHandler();
        bh.createBus("Un Bus");
        bh.createMessageBox("Un Bus", "Une Boite");
        bh.sendMessage("Un Bus", "Une Boite", "Le message dans la boite");
        Assert.assertEquals("Le message dans la boite", bh.readMessages("Un Bus").get(0));
        Assert.assertEquals("Le message dans la boite", bh.readMessages("Un Bus", "Une Boite").get(0));
    }

    public void testBusExist() throws Exception {
        bh = new BusHandler();
        bh.createBus("Un Bus");
        Assert.assertEquals(true, bh.busExist("Un Bus"));
        Assert.assertEquals(false, bh.busExist("Un Bus Inexistant"));
    }

    public void testListBus() throws Exception {
        bh = new BusHandler();
        bh.createBus("Un Bus");
        bh.createBus("Un autre bus");

        Assert.assertEquals(2, bh.listBus().size());
        Assert.assertEquals("Un Bus", bh.listBus().get(0));
        Assert.assertEquals("Un autre bus", bh.listBus().get(1));
    }
}