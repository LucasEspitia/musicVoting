package src.application;

import java.io.Serializable;
import java.util.List;

import src.music.Song;
import src.users.Voter;
import src.utils.My_Utils;
/**
 * DataContainer class represents a container for storing data related to users and songs.
 * It provides methods to access and manipulate user and song data.
 */
public class DataContainer implements Serializable {
    /**
     * Serial version UID for serialization.
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * List of users stored in the data container.
     */
    private List<Voter> users;
    
    /**
     * List of songs stored in the data container.
     */
    private List<Song> songs;

    /**
     * Instance of the voting Manager
     */
  
    private int voteCount;

	   
	/**
	 *  Constructs a new DataContainer with the specified lists of users and songs.
	 * @param users
	 * @param songs
	 * @param admin
	 * @param loadedFromFile
	 */
    public DataContainer(List<Voter> users, List<Song> songs, int votingCount) {
        this.users = users;
        this.songs = songs;
        this.voteCount = votingCount;
    }


    /**
     * Retrieves the list of users stored in the data container.
     * 
     * @return The list of users.
     */
    public List<Voter> getUsers() {
        return users;
    }

    /**
     * Retrieves the list of songs stored in the data container.
     * 
     * @return The list of songs.
     */
    public List<Song> getSongs() {
        return songs;
    }
    /**
     * Return the voteCount
     * @return
     */
    public int getVoteCount() {
    	return voteCount;
    }
    /**
     * Prints all the user and song data stored in the data container.
     */
    public void printAllData() {
        My_Utils.print("Users:");
        for (Voter user : users) {
        	My_Utils.print(user.getUsername() + " - " + user.getPassword());
        }
        
        My_Utils.print("Songs:");
        for (Song song : songs) {
        	My_Utils.print(song.getTitle() + " by " + song.getArtist() + " - Genre: " + song.getGenre());
        }
        My_Utils.print("Votes: " + voteCount);
    }

}
