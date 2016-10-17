package org.alfer.iut.unice.cpoo.td3;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Represents a message box in which we can store messages for them to be read later.
 * @author Ferretti - Kacem
 */
public class MessageBox implements Serializable {

    private String _boxName;
    protected ArrayList<Message> _messageList;


    /**
     * Message Box constructor
     * @param boxName the name of the box
     */
    public MessageBox(String boxName){
        _boxName = boxName;
        _messageList = new ArrayList<Message>();
    }

    /**
     * Gets the box's name
     * @return the box's name
     */
    public String getBoxName(){
        return _boxName;
    }

    /**
     * Store a new message in the box
     * @param content the content of the message
     */
    public void addMessage(String content){
        _messageList.add(new Message(content));
    }


    /**
     * Gets every messages in the form of strings
     * @return a list of messages
     */
    public ArrayList<String> getMessages(){
        ArrayList<String> list = new ArrayList<String>();
        for(Message m : _messageList){
            list.add(m.getContent());
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
        Iterator<Message> it = _messageList.iterator();
        while(it.hasNext()){
            Message m = it.next();
            if(m.isObsolete(seconds)){
                it.remove();
                removed++;
            }
        }

        return removed;
    }


    /**
     * Cleans the box, removing every messages inside.
     */
    public void clean(){
        _messageList.clear();
        _messageList = new ArrayList<Message>();
    }
}
