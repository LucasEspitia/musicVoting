package src.utils;

import java.io.*;
public class SerializationUtils {
    /**
     * Method to save the Data of the program in the file
     * 
     * @param object
     * @param fileName
     */
    public static void saveToFile(Object object, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(object);
           My_Utils.print("Object saved successfully.");
        } catch (FileNotFoundException e) {
        	My_Utils.print("File not found: " + fileName);
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to Load the data from the file to the program.
     * @param fileName
     * @return
     */
    public static Object loadFromFile(String fileName) {
        Object object = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            object = ois.readObject();
            My_Utils.print("Object loaded successfully.");
        } catch (FileNotFoundException e) {
        	My_Utils.print("File not found: " + fileName);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return object;
    }
}

