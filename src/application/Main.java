package application;

import gui.LoginScreen;

/**
 * The Main class is the entry point of the application.
 * It initializes the default setup and launches the login screen.
 */
public class Main {
    
    /**
     * The main method of the application.
     * It initializes the default setup and launches the login screen.
     * @param args The command-line arguments passed to the program.
     */
    public static void main(String[] args) {
        // Initialize the default setup
        SetUp defaultSetup = new DefaultSetUp(); 
        
        // Launch the login screen
        LoginScreen.launch(LoginScreen.class, args);
    }
}
