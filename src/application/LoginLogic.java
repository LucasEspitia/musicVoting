package application;

import users.Admin;
import users.User;

/**
 * The LoginLogic class handles the logic for user authentication and login.
 * It provides methods for validating admin and user credentials.
 */
public class LoginLogic {
    
    private Admin admin;
    
    /**
     * Constructs a new LoginLogic object.
     * Initializes the admin instance.
     */
    public LoginLogic() {
        this.admin = Admin.getInstance();
    }
    
    /**
     * Validates the admin credentials.
     * @param adminUsername The admin username to validate.
     * @param adminPassword The admin password to validate.
     * @return true if the admin credentials are valid, otherwise false.
     */
    public boolean validateAdminCredentials(String adminUsername, String adminPassword) {
        return isValidAdminCredentials(adminUsername, adminPassword);
    }
    
    /**
     * Checks if the admin credentials are valid.
     * @param adminUsername The admin username to validate.
     * @param adminPassword The admin password to validate.
     * @return true if the admin credentials are valid, otherwise false.
     */
    private boolean isValidAdminCredentials(String adminUsername, String adminPassword) {
        return adminUsername.equals(admin.getUsername()) && adminPassword.equals(admin.getPassword());
    }
    
    /**
     * Validates the user credentials.
     * @param username The username to validate.
     * @param password The password to validate.
     * @return a string indicating the validation result.
     *         "success" if the credentials are valid,
     *         "Password is incorrect" if the password is incorrect,
     *         "User does not exist" if the user does not exist.
     */
    public String validateUserCredentials(String username, String password) {
        return checkUserCredentials(username, password);
    }

    /**
     * Checks if the user credentials are valid.
     * @param username The username to validate.
     * @param password The password to validate.
     * @return a string indicating the validation result.
     *         "success" if the credentials are valid,
     *         "Password is incorrect" if the password is incorrect,
     *         "User does not exist" if the user does not exist.
     */
    private String checkUserCredentials(String username, String password) {
        User user = admin.getUser(username);
        if (user != null) {
            if (user.getPassword().equals(password)) {
                return "success"; 
            } else {
                return "Password is incorrect";
            }
        } else {
            return "User does not exist";
        }
    }
}
