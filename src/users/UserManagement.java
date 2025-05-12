package users;

import java.io.File;

/**
 * Interface for user management.
 */
public interface UserManagement {

    /**
     * Method to add a user.
     * @param user The user to add.
     * @return True if the user was added successfully, otherwise false.
     */
    boolean addUser(Voter user);

    /**
     * Method to delete a user.
     * @param userID The ID of the user to delete.
     */
    void deleteUser(String userID);

    /**
     * Method to import users from a file.
     * @param filePath The file path from which to import users.
     */
    void importUserByFile(File filePath);

    /**
     * Method to add all users from a 2D array.
     * @param usernameAndPassword The 2D array containing usernames and passwords.
     */
    void addAllUsers(String[][] usernameAndPassword);
    
}
