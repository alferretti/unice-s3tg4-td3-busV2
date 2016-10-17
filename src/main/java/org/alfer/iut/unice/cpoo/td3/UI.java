package org.alfer.iut.unice.cpoo.td3;

import java.util.Scanner;

/**
 * Input/Output for the user to interact with the app.
 * @author Ferretti - Kacem
 */
public class UI {

    private Scanner _scanner;

    /**
     * UI constructor
     */
    public UI(){
        _scanner = new Scanner(System.in);
    }

    /**
     * Ask the user for the input of a command code.
     * @return the command entered
     */
    public String readInput(){
        System.out.flush();
        printLine("------------------------------");
        printLine("Please enter a a code...");
        printLine("These are the code you can use :\n" +
                "\t-c :\tCreate a new bus\n" +
                "\t-b :\tCreate a new box in a bus\n" +
                "\t-s :\tSend a message to a bus\n" +
                "\t-r :\tRead a message from a bus\n" +
                "\t-e :\tCheck if a bus exists\n" +
                "\t-l :\tShow current bus\n" +
                "\t-d :\tClear a box...\n" +
                "\t-o :\tRemove obsolete message\n" +
                "\t-q :\tExit program...");
        return _scanner.nextLine().trim();
    }

    /**
     * Ask the user for a bus name.
     * @return the bus name
     */
    public String getBusName(){
        printLine("------------------------------");
        printLine("Please enter a bus name...");
        return _scanner.nextLine().trim();
    }

    /**
     * Ask the user for a box name.
     * @return the box name
     */
    public String getBoxName(){
        printLine("------------------------------");
        printLine("Please enter a box name...");
        return _scanner.nextLine().trim();
    }

    /**
     * Ask the user for a message to send.
     * @return the message entered
     */
    public String getMessageContent(){
        printLine("------------------------------");
        printLine("Please enter a message to send...");
        return _scanner.nextLine().trim();
    }

    /**
     * Ask the user for a time in seconds.
     * @return
     */
    public int getSeconds(){
        printLine("------------------------------");
        printLine("Please enter a number of seconds...");
        return _scanner.nextInt();
    }

    /**
     * Prints a line to system output.
     * @param line the line to print
     */
    public void printLine(String line){
        System.out.println(line);
    }
}
