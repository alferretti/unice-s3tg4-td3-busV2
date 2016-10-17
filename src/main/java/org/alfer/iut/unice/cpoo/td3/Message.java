package org.alfer.iut.unice.cpoo.td3;

import java.io.Serializable;
import java.util.Date;

/**
 * Represents a message at the date it was created.
 * Created by Alex on 16/10/2016.
 */
public class Message implements Serializable {

    private String _content;
    private long _emissionTime;

    public Message(String content){
        _content = content;
        _emissionTime = new Date().getTime();
    }

    public String getContent(){ return _content; }

    public boolean isObsolete(int seconds){
        long actualTime = new Date().getTime();
        long gap = actualTime - _emissionTime;

        return (gap >= seconds);
    }

}
