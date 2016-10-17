package org.alfer.iut.unice.cpoo.td3;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Model class, used to store buses, and interact with them, from an exterior viewpoint.
 * @author Ferretti - Kacem
 */
public class BusHandler implements Serializable {
    private ArrayList<Bus> _busList;

    /**
     * Bus Handler constructor
     */
    public BusHandler(){
        _busList = new ArrayList<Bus>();
    }


    /**
     * Creates a new bus. If a bus exists with the same name, it won't do nothing.
     * @param busName the name of the bus to create
     * @return true if bus was create, false if not
     */
    public boolean createBus(String busName){
        if(busExist(busName)){
            return false;
        }
        _busList.add(new Bus(busName));
        return true;
    }

    /**
     * Creates a new message box in a bus, if the bus doesn't exist, it won't do nothing.
     * @param busName the name of the bus to add the box to
     * @param boxName the name of the box to create
     * @return true if box was created, false if not.
     */
    public boolean createMessageBox(String busName, String boxName){
        for(Bus b : _busList){
            if(b.getBusName().equals(busName)){
                return b.createBox(boxName);
            }
        }
        return false;
    }


    /**
     * Send a message to a specific box, in a specific bus
     * @param busName the name of the bus
     * @param boxName the name of the box
     * @param content the content of the message
     * @return true if message was sent, false if not
     */
    public boolean sendMessage(String busName, String boxName, String content){
        for(Bus b : _busList){
            if(b.getBusName().equals(busName)){
                return b.sendMessage(boxName, content);
            }
        }
        return false;
    }

    /**
     * Send a message to the default box, in a specific bus
     * @param busName the name of the bus
     * @param content the content of the message
     * @return true if message was sent, false if not
     */
    public boolean sendMessage(String busName, String content){
        for(Bus b : _busList){
            if(b.getBusName().equals(busName)){
                return b.sendMessage(Bus.DEFAULTBOXNAME, content);
            }
        }
        return false;
    }

    /**
     * Read messages from a box in a bus.
     * @param busName the name of the bus
     * @param boxName the name of the box
     * @return list of messages, or null if bus or box doesn't exist, or if no messages were found
     */
    public ArrayList<String> readMessages(String busName, String boxName){
        for(Bus b : _busList){
            if(b.getBusName().equals(busName)){
                return b.readMessages(boxName);
            }
        }

        return null;
    }

    /**
     * Read messages from every boxes in a bus
     * @param busName the name of the bus
     * @return list of messages, or null if bus doesn't exist, or if no messages were found
     */
    public ArrayList<String> readMessages(String busName){
        return readMessages(busName, "");
    }

    public boolean busExist(String busName){
        for(Bus b : _busList){
            if(b.getBusName().equals(busName)){
                return true;
            }
        }
        return false;
    }

    /**
     * @return list of names of each bus
     */
    public ArrayList<String> listBus(){
        ArrayList<String> list = new ArrayList<String>();
        for(Bus b : _busList){
            list.add(b.getBusName());
        }

        return list;
    }

    /**
     * Remove messages that are considered obsolete
     * @param seconds the time (in seconds) that to pass for a message to be considered obsolete
     * @return amount of messages deleted
     */
    public int removeOldMessages(int seconds){
        int removed = 0;
        for(Bus b : _busList){
            removed += b.removeOldMessages(seconds);
        }
        return removed;
    }

    /**
     * Cleans a box of a specified bus
     * @param busName name of the bus to find the box
     * @param boxName name of the box to clean
     * @return true if box was cleaned, false if not
     */
    public boolean cleanBus(String busName, String boxName){
        for(Bus b : _busList){
            if(b.getBusName().equals(busName)){
                return b.cleanBox(boxName);
            }
        }

        return false;
    }

}
