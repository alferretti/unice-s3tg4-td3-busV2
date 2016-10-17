package org.alfer.iut.unice.cpoo.td3;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Alex on 17/10/2016.
 */
public class AutoDeleteMessageBox extends MessageBox {

    public AutoDeleteMessageBox(String boxName){
        super(boxName);
    }

    @Override
    public ArrayList<String> getMessages() {
        ArrayList<String> list = new ArrayList<String>();
        Iterator<Message> it = _messageList.iterator();
        while(it.hasNext()){
            Message next = it.next();
            it.remove();
            list.add(next.getContent());
        }

        return list;
    }
}
