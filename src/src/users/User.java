package src.users;

import java.io.Serializable;

/**
 * The User class represents a user in the system.
 * It serves as the parent class for both voters and administrators.
 * This class provides encapsulated attributes for username and password,
 * along with getter and setter methods to access and modify these attributes.
 */
public abstract class User implements Serializable {

    /**
     * A unique identifier for serialization purposes.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The username of the user.
     */
    private String username;

    /**
     * The password of the user.
     */
    private String password;
    
    /**
     * Constructs a new User with the specified username and password.
     * 
     * @param username The username of the user.
     * @param password The password of the user.
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    /**
     * Constructor used for deserialization
     */
    public User() {
    	
    }

    /**
     * Gets the username of the user.
     * 
     * @return The username of the user.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the user.
     * 
     * @param username The new username to set.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the password of the user.
     * 
     * @return The password of the user.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user.
     * 
     * @param password The new password to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
