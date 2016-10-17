package org.alfer.iut.unice.cpoo.td3;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Represents a bus in which we can add boxes, and send messages to these boxes, for them to be read later.
 * @author Ferretti - Kacem
 */
public class Bus implements Serializable {

    public static final String DEFAULTBOXNAME = "Default";

    private String _busName;
    private ArrayList<MessageBox> _boxList;

    /**
     * Bus Constructor
     * @param busName the bus' name
     */
    public Bus(String busName){
        _busName = busName;
        _boxList = new ArrayList<MessageBox>();
        _boxList.add(new MessageBox(DEFAULTBOXNAME));
    }


    /**
     * Gets the bus name
     * @return the bus' name
     */
    public String getBusName(){
        return _busName;
    }


    /**
     * Check if a box exist
     * @param boxName the box that needs to be checked
     * @return true is box exists, false if not
     */
    public boolean boxExist(String boxName){
        for(MessageBox m : _boxList){
            if(m.getBoxName().equals(boxName)) return true;
        }
        return false;
    }


    /**
     * Read messages from a box (or all)
     * @param boxName the box to read from ("" for every boxes)
     * @return list of messages in the box (or all if boxName="")
     */
    public ArrayList<String> readMessages(String boxName){
        ArrayList<String> list = new ArrayList<String>();
        if(boxName.equals("")){
            for (MessageBox m : _boxList) {
                list.addAll(m.getMessages());
            }
        }else{
            for (MessageBox m : _boxList) {
                if(m.getBoxName().equals(boxName)){
                    list.addAll(m.getMessages());
                    break;
                }
            }
        }

        if(list.size() > 0 ){
            return list;
        }
        else{
            return null;
        }
    }


    /**
     * Send a message to a box
     * @param boxName the box name to send a message
     * @param content the content of the message
     * @return true if message was sent, false if not
     */
    public boolean sendMessage(String boxName, String content){
        if(boxName.equals("*")){
            for (MessageBox m : _boxList) {
                m.addMessage(content);
            }
        }else{
            for (MessageBox m : _boxList) {
                if(m.getBoxName().equals(boxName)){
                    m.addMessage(content);
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Create a box in the bus
     * @param boxName the name of the box to create
     * @return true if box was created, false if not
     */
    public boolean createBox(String boxName){
        if(boxExist(boxName)) return false;

        _boxList.add(new MessageBox(boxName));
        return true;
    }


    /**
     * Remove messages that are considered obsolete
     * @param seconds the time (in seconds) that to pass for a message to be considered obsolete
     * @return amount of messages deleted
     */
    public int removeOldMessages(int seconds){
        int removed = 0;
        for(MessageBox b: _boxList){
            removed += b.removeOldMessages(seconds);
        }

        return removed;
    }


    /**
     * Cleans a box of the current bus
     * @param boxName name of the box to clean
     * @return true if box was cleaned, false if not
     */
    public boolean cleanBox(String boxName){
        for (MessageBox b: _boxList) {
            if(b.getBoxName().equals(boxName)){
                b.clean();
                return true;
            }
        }
        return false;
    }
}
