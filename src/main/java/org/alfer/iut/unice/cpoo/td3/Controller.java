package org.alfer.iut.unice.cpoo.td3;

import java.util.ArrayList;

/**
 * Used by the user to interact with the bus handler, via the UI.
 * @author Ferretti - Kacem
 */
public class Controller {

    public static final String SAVEFILE = "bus_states.c3po";

    private UI _ui;
    private BusHandler _handler;
    private boolean _isRunning;

    /**
     * Controller constructor
     */
    public Controller(){
        _ui = new UI();
        _handler = new BusHandler();
    }

    /**
     * Starts the controller loop.
     */
    public void start(){
        _isRunning = true;

        Object loaded = Memory.read(SAVEFILE);
        if(loaded != null && loaded instanceof BusHandler){
            _handler = (BusHandler)loaded;
        }

        while(_isRunning){
            String s = _ui.readInput();

            Character c = null;
            if(s.length() > 0){
                c = s.charAt(0);

                switch (c){
                    case 'c':
                        createBus();
                        break;
                    case 'b':
                        createMessageBox();
                        break;
                    case 's':
                        sendMessage();
                        break;
                    case 'r':
                        readMessages();
                        break;
                    case 'e':
                        busExist();
                        break;
                    case 'l':
                        listBus();
                        break;
                    case 'd':
                        cleanBus();
                        break;
                    case 'o':
                        removeOldMessages();
                        break;
                    case 'q':
                        _isRunning = false; //Exit loop...
                        break;
                    default:
                        _ui.printLine("What? \'" + c + "\' is not a known command...");
                        break;
                }
            }else{
                _ui.printLine("Don't be silly, enter something...");
            }
        }

        Memory.save(_handler, SAVEFILE);
    }

    /**
     * Creates a bus.
     */
    private void createBus(){
        _ui.printLine(">START bus_factory.exe");
        String busName = _ui.getBusName();
        boolean success = (busName.length() > 0)?_handler.createBus(busName):false;
        if(success){
            _ui.printLine("INFO :\tbus creation success !");
            _ui.printLine("bus_factory : process finished with exit code : 0");
        }else{
            _ui.printLine("ERROR :\tbus creation failed !");
            _ui.printLine("bus_factory : process finished with exit code : 1");
        }
    }

    /**
     * Creates a message box.
     */
    private void createMessageBox(){
        _ui.printLine(">START box_factory.exe");
        String busName = _ui.getBusName();
        String boxName = _ui.getBoxName();
        boolean success = (boxName.length() > 0)?_handler.createMessageBox(busName, boxName):false;
        if(success){
            _ui.printLine("INFO :\tbox creation success !");
            _ui.printLine("box_factory : process finished with exit code : 0");
        }else{
            _ui.printLine("ERROR :\tbox creation failed !");
            _ui.printLine("box_factory : process finished with exit code : 1");
        }
    }

    /**
     * Send a message
     */
    private void sendMessage(){
        _ui.printLine(">START mr_mailman.exe -s");
        String message = _ui.getMessageContent();
        String busName = _ui.getBusName();
        String boxName = _ui.getBoxName();

        boolean success = false;
        if(boxName.length() >- 0){
            success = _handler.sendMessage(busName, boxName, message);
        }else{
            success = _handler.sendMessage(busName, message);
        }

        if(success){
            _ui.printLine("INFO :\tmessage was sent !");
            _ui.printLine("mr_mailman : process finished with exit code : 0");
        }else{
            if(_handler.busExist(busName)){
                _ui.printLine("ERROR :\tmessage wasn't sent ! the box \'" + boxName +"\' doesn't exists !");
                _ui.printLine("mr_clean : process finished with exit code : 1");
            }else{
                _ui.printLine("ERROR :\tmessage wasn't sent ! the bus \'" + busName +"\' doesn't exists !");
                _ui.printLine("mr_clean : process finished with exit code : 2");
            }
        }
    }

    /**
     * Read messages
     */
    private void readMessages(){
        _ui.printLine(">START mr_mailman.exe -r");
        String busName = _ui.getBusName();
        String boxName = _ui.getBoxName();

        ArrayList<String> messages = null;
        if(boxName.length() == 0){
            messages = _handler.readMessages(busName, boxName);
        }else{
            messages = _handler.readMessages(busName);
        }

        if(messages != null){
            _ui.printLine("INFO :\tyou received messages !");
            for (String s: messages) {
                _ui.printLine("\t" + s);
            }
            _ui.printLine("mr_mailman : process finished with exit code : 0");
        }else{
            _ui.printLine("INFO :\tyou didn't received messages !");
            _ui.printLine("mr_mailman : process finished with exit code : 0");
        }
    }

    /**
     * Remove old messages
     */
    private void removeOldMessages(){
        _ui.printLine(">START max_remove.exe");
        int seconds = _ui.getSeconds();
        int removed = _handler.removeOldMessages(seconds);

        _ui.printLine("INFO :\t" + removed + " messages were removed !");
        _ui.printLine("max_remove : process finished with exit code : 0");
    }

    /**
     * Show list of bus in handler
     */
    private void listBus(){
        _ui.printLine(">START super_list.exe");
        ArrayList<String> names = _handler.listBus();

        _ui.printLine("Currently saved bus list :");
        for (String s: names) {
            _ui.printLine("\t" + s);
        }
        _ui.printLine("super_list : process finished with exit code : 0");
    }

    /**
     * Check if a bus exists
     */
    private void busExist(){
        _ui.printLine(">START existence.exe");
        String busName = _ui.getBusName();
        boolean success = _handler.busExist(busName);

        if(success){
            _ui.printLine("INFO :\tthe bus \'" + busName +"\' exists !");
        }else{
            _ui.printLine("INFO :\tthe bus \'" + busName +"\' doesn't exists !");
        }

        _ui.printLine("existence : process finished with exit code : 0");
    }

    /**
     * Cleans a box of a bus
     */
    private void cleanBus(){
        _ui.printLine(">START mr_clean.exe");
        String busName = _ui.getBusName();
        String boxName = _ui.getBoxName();
        boolean success = _handler.cleanBus(busName, boxName);

        if(success){
            _ui.printLine("INFO :\tthe box was cleaned !");
            _ui.printLine("mr_clean : process finished with exit code : 0");
        }else{
            if(_handler.busExist(busName)){
                _ui.printLine("ERROR :\tthe box \'" + boxName +"\' doesn't exists !");
                _ui.printLine("mr_clean : process finished with exit code : 1");
            }else{
                _ui.printLine("ERROR :\tthe bus \'" + busName +"\' doesn't exists !");
                _ui.printLine("mr_clean : process finished with exit code : 2");
            }
        }
    }
}
