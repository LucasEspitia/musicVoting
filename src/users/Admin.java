package users;

import java.util.*;

import utils.My_Utils;

import java.io.*;

/**
 * Public admin class
 * This class contains the logic and the attributes of the Admin
 * The principal function of the admin is add the people which is going to vote in the program
 * Add Songs or delete them from the program.
 * Use singletone Patron, because there's only one Admin
 */
public class Admin  extends User implements UserManagement{
	
	
	//For singleTone
	private static Admin instance;
	//Data 
	private Map<String, Voter> users; 
	private int numbersOfUsersvoted;
	
	/**
	 * Constructor encapsulated
	 * @param adminUsername
	 * @param adminPassword
	 */
	private Admin(String adminUsername, String adminPassword) {
		super(adminUsername, adminPassword);
		users = new HashMap<>();
		
	}
	
	/**
	 * Singletone, get the only instance of the admin class
	 * @return
	 */
	public static Admin getInstance() {
		if(instance == null) {
			instance = new Admin("Lucas", "123456789a");
		}
		return instance;
		
	}
	/**
	 * get the User as an object
	 * @param username
	 * @return
	 */
	public Voter getUser(String username) {
	    return users.get(username);
	}
	/**
	 * Get the username of the user in String
	 * @return
	 */
	public List<String> getAllUsernames() {
	        return new ArrayList<>(users.keySet());
	    }
	/**
	 * get the numbers of users that voted
	 * @return
	 */
	public int getNumbersOfUserVoted() {
		return numbersOfUsersvoted;
	}
	/**
	 * set the number of users that already voted
	 * @param number
	 */
	public void setNumbersOfUserVoted(int number) {
		this.numbersOfUsersvoted = number;
	}
	 /**
	  * count the number of users that already voted
	  */
	public void addNumbersOfUserVoted() {
		this.numbersOfUsersvoted += 1;
	}
	
	@Override
	public boolean addUser(Voter user) {
	    if (!users.containsKey(user.getUsername())) {
	        users.put(user.getUsername(), user);
            My_Utils.print("Username: " + user.getUsername()+ "||Password: " + user.getPassword());
	        return true;
	    } else {
	        return false;
	    }
	}
	@Override
	public void addAllUsers(String[][] usersAndPasswords) {
        for (String[] userData : usersAndPasswords) {
            if (userData.length == 2) {
                String username = userData[0];
                String password = userData[1];
                Voter user = new Voter(username, password);
                addUser(user);
            } else {
                System.out.println("Error: Incorrect user data format.");
            }
        }
    }
	@Override
	public void deleteUser(String userID) {
		users.remove(userID);
	}
	@Override
	public void importUserByFile(File file) {
	    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
	        String line;
	        while ((line = br.readLine()) != null) {
	            String[] data = line.split(",");
	            if (data.length == 2) {
	                String username = data[0].trim();
	                String password = data[1].trim();
	                Voter newUser = new Voter(username, password);
	                addUser(newUser);
	            } else {
	                System.out.println("Error: incorrect line format in the file: " + line);
	            }
	        }
	    } catch (IOException e) {
	        System.out.println("Error reading the file: " + e.getMessage());
	    }
	}
	/**
	 * Print in the console the users that exist to do the log in
	 */
	public void printAllUsers() {
	    System.out.println("List of users:");
	    for (Map.Entry<String, Voter> entry : users.entrySet()) {
	        String username = entry.getKey();
	        Voter user = entry.getValue();
	        System.out.println("Username: " + username + " Password: " + user.getPassword());
	    }
	}
	
}
