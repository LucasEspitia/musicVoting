package utils;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * Utility class for printing messages and alerts.
 */
public class My_Utils {
    
    /**
     * Prints a message to the console.
     * @param message The message to be printed.
     */
    public static void print(String message) {
        System.out.println(message);
    }
    
    /**
     * Displays an error alert with the specified message.
     * @param text The text to be displayed in the error alert.
     */
    public static void printErrorText(String text) {
        Alert errorAlert = new Alert(AlertType.ERROR);
        errorAlert.setTitle("Error");
        errorAlert.setHeaderText(null);
        errorAlert.setContentText(text);
        errorAlert.showAndWait();    
    }
    
    /**
     * Displays a success alert with the specified message.
     * @param text The text to be displayed in the success alert.
     */
    public static void printSuccessText(String text) {
        Alert successAlert = new Alert(AlertType.INFORMATION);
        successAlert.setTitle("Success");
        successAlert.setHeaderText(null);
        successAlert.setContentText(text);
        successAlert.showAndWait();
    }
    /**
     * Displays a line in the console
     */
    public static void printLine() {
    	System.out.println("***&&&-----------------------------------------&&&***");
    }
}
