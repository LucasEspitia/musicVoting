package src.application;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import src.users.Admin;
import src.users.User;
import src.utils.InvalidCredentialsException;


/**
 * AuthenticationLogInAspect is an aspect-oriented class responsible for validating user credentials
 * before executing the login logic.
 */
@Aspect
public class AuthenticationLogInAspect {
	
    private Admin admin = Admin.getInstance();
    
    /**
     * Validates user credentials before executing the login logic.
     * Throws InvalidCredentialsException if the username or password is invalid.
     * 
     * @param username The username provided for login.
     * @param password The password provided for login.
     */
    @Before("execution(* src.application.LoginLogic.validateUserCredentials(..)) " +
            "&& args(username, password)")
    public void validateUserCredentials(String username, String password)  {
        if (!isValid(username, password)) {
            throw new InvalidCredentialsException("Invalid username or password");
        }
    }
    
    /**
     * Checks if the provided username and password are valid.
     * 
     * @param username The username provided for validation.
     * @param password The password provided for validation.
     * @return true if the username and password are valid, false otherwise.
     */
    private boolean isValid(String username, String password) {
    	 User user = admin.getUser(username);
         if (user != null) {
             if (user.getPassword().equals(password)) {
                 return true; 
             } else {
                 return false;
             }
         } else {
             return false;
         }
     }
}
