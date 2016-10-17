package org.alfer.iut.unice.cpoo.td3;

import java.io.*;

/**
 * Based on http://www.jmdoudoux.fr/java/dej/chap-serialisation.htm
 * Used to load and save objects to file
 * @author Blay
 */
public class Memory {

    /**
     * Saves an object at filename
     * @param o the object to save
     * @param fileName the save location
     */
    public static void save(Object o, String fileName){
        ObjectOutputStream oos = null;
        try {
            final FileOutputStream file = new FileOutputStream(fileName);
            oos = new ObjectOutputStream(file);
            oos.writeObject(o);
            oos.flush();
        } catch (final java.io.IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (oos != null) {
                    oos.flush();
                    oos.close();
                }
            } catch (final IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Loads and object at filename
     * @param fileName the file where object is saved
     * @return the loaded object
     */
    public static Object read(String fileName){
        ObjectInputStream ois = null;
        Object o = null;
        try {
            final FileInputStream file = new FileInputStream(fileName);
            ois = new ObjectInputStream(file);
            o = ois.readObject();
            System.out.println(o);
        } catch (final java.io.FileNotFoundException e) {
            System.out.println("No previous saves");
        } catch (final java.io.IOException e) {
            e.printStackTrace();
        } catch (final ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (final IOException ex) {
                ex.printStackTrace();
            }
        }
        return o;
    }
}
