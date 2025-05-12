package src.users;

import java.util.*;
import java.util.function.Consumer;

import src.utils.My_Utils;

import java.io.*;

/**
 * Public admin class
 * This class contains the logic and the attributes of the Admin
 * The principal function of the admin is add the people which is going to vote in the program
 * Add Songs or delete them from the program.
 * Use singletone Patron, because there's only one Admin
 */
public class Admin  extends User implements UserManagement{
	
    /**
     * A unique identifier for serialization purposes.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The single instance of the Admin class (Singleton pattern).
     */
    private static Admin instance;

    /**
     * A map containing usernames as keys and corresponding Voter objects as values.
     */
    private Map<String, Voter> users;

	
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
			instance = new Admin("L", "1");
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
	 * Get the List of the users class
	 * @return
	 */
	public List<Voter> getAllVoters(){
		return new ArrayList<Voter>(users.values());
	}
	/**
	 * Method to count the vote
	 */
	public void vote() {
	    VotingManager.vote();
	}
	/**
	 * Get the votes from VotingManager
	 * @return
	 */
	public int getVoteCount() {
	    return VotingManager.getVoteCount();
	}
	public void setVoteCount(int n) {
		VotingManager.setVoteCount(n);
	}
	/**
	 *Lambda expression that allows me to print my users more efficiently
	 * @param action
	 */
	public void forEachUser(Consumer<Voter> action) {
	        users.values().forEach(action);
	}

	@Override
	public boolean addUser(Voter user) {
	    if (!users.containsKey(user.getUsername())) {
	        users.put(user.getUsername(), user);
	        My_Utils.print("Username: " + user.getUsername() + "|||Password: " + user.getPassword());
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
                My_Utils.print("Error: Incorrect user data format.");
            }
        }
    } 
	@Override
	public void addAllUsers(List<Voter> voters) {
	    voters.forEach(voter -> addUser(voter));
	}

	@Override
	public void deleteUser(String userID) {
		users.remove(userID);
	}
	@Override
	public void addAllUsers(File file) {
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
	            	My_Utils.print("Error: incorrect line format in the file: " + line);
	            }
	        }
	    } catch (IOException e) {
	    	My_Utils.print("Error reading the file: " + e.getMessage());
	    }
	}	
}
