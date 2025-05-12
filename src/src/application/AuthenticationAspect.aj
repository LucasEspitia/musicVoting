import src.application.InvalidCredentialsException;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import src.users.Admin;
import src.users.User;

@Aspect
public class AuthenticationAspect {
	
	private Admin admin = Admin.getInstance();
    
    @Before("execution(* src.application.LoginLogic.validateUserCredentials(..)) " +
            "&& args(username, password)")
    public void validateUserCredentials(String username, String password)  {
        // Aquí puedes realizar la validación de credenciales
        if (!isValid(username, password)) {
            throw new InvalidCredentialsException("Invalid username or password");
        }
    }
    
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