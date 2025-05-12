package src.application;

import java.time.LocalDateTime;
/**
 * LoginAuditAspect is an aspect class that performs audit logging for login attempts.
 * It logs login attempts for both administrators and users.
 */
public aspect LoginAuditAspect {  
    /**
     * Pointcut for admin login attempt.
     * It captures the execution of the validateAdminCredentials method in LoginLogic.
     */
    pointcut adminLoginAttempt(String adminUsername, String adminPassword):
        execution(boolean LoginLogic.validateAdminCredentials(String, String)) &&
        args(adminUsername, adminPassword);
    
    /**
     * Pointcut for user login attempt.
     * It captures the execution of the validateUserCredentials method in LoginLogic.
     */
    pointcut userLoginAttempt(String username, String password):
        execution(String LoginLogic.validateUserCredentials(String, String)) &&
        args(username, password);
    
    /**
     * Advice executed before an admin login attempt.
     * Logs the timestamp and admin username for the login attempt.
     */
    before(String adminUsername, String adminPassword): adminLoginAttempt(adminUsername, adminPassword) {
        LocalDateTime dateTime = LocalDateTime.now();
        System.out.println("Admin login attempt at " + dateTime + " by admin: " + adminUsername);
    }
    
    /**
     * Advice executed before a user login attempt.
     * Logs the timestamp and username for the login attempt.
     */
    before(String username, String password): userLoginAttempt(username, password) {
        LocalDateTime dateTime = LocalDateTime.now();
        System.out.println("User login attempt at " + dateTime + " by user: " + username);
    }
    
}

