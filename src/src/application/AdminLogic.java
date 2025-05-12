package src.application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import src.music.Genre;
import src.music.MusicLibrary;
import src.music.Song;
import src.users.Admin;
import src.users.Voter;
import src.utils.My_Utils;
import src.utils.SerializationUtils;

/**
 * The AdminLogic class manages the business logic for administrator-related operations.
 * It handles actions such as displaying dialogs, adding songs, processing files,
 * managing user votes, generating bottoms, and opening user interfaces.
 **/
public class AdminLogic {	
	/**
	 * Instance of Admin
	 */
	private Admin admin = Admin.getInstance();
	/**
	 * Instance of musicLibrary
	 */
	private MusicLibrary musicLibrary = MusicLibrary.getInstance();
	
	   public class UserLogic {
		   private Admin admin = Admin.getInstance();
		   /**
			 * Confirm add Users to the program from the selected file
			 * 
			 * @param selectedFile
			 */
			 public void confirmaddUserByFile(File selectedFile) {
			        admin.addAllUsers(selectedFile);
			 }

		   /**
		    * Delete the user from the HashMap of users.   
		    * @param username
		    */
		    public void confirmDeleteUser(String username) {
		    	if(username != null) {
		    			admin.deleteUser(username);
		    			My_Utils.printSuccessText("Username " + username + " deleted succesfully from users.");
		    			My_Utils.print("Username "  + username + " was succesfully deleted.");
		    	}else{
		    		My_Utils.printErrorText("Error: Please select the user you want to delete.");
		    	}
		    }
			/**
		     * Adds a new user with the specified username and password.
		     * 
		     * @param username The username of the user to add.
		     * @param password The password of the user to add.
		     * @return True if the user is successfully added, false otherwise.
		     */
		    public boolean showAddUserByNameDialog(String username, String password) {
		    	Voter user = new Voter(username, password);
		    	return admin.addUser(user);
				
		    }
		    /**
		     * Checks if the minimum number of users have voted.
		     * 
		     * @return True if the minimum number of users have voted, false otherwise.
		     */
		    public boolean checkUsersMinVoted() {
		    	System.out.println("Votes: " + admin.getVoteCount());
		    	if(admin.getVoteCount() < 3 ) {
		    		return false;
		    	}
		    	return true;
		    }
		    /**
		     * In this method, we get All users for the ComboBox.
		     * @return
		     */
		    public List<String> getAllUsernames() {
		    	List<String> listUsers = admin.getAllUsernames();
		    	if(listUsers != null) {
		    		return listUsers;
		    	}else {
		    		My_Utils.printErrorText("Error: Firstly, add users.");
		    		return null;
		    	}
		    }
	    }

	    // Lógica relacionada con canciones
	    public  class SongLogic {
	    	private MusicLibrary musicLibrary = MusicLibrary.getInstance();
	    	
	    	 /**
	         * Confirms the addition of songs from the selected file.
	         * 
	         * @param selectedFile2 The selected file containing songs to add.
	         */
	    	public void confirmFileAddSongs(File selectedFile2) {
	    			musicLibrary.addSongsFromFile(selectedFile2);	
	    	}
	    	/**
	         * Checks if a song with the specified details already exists in the library,
	         * and adds the song to the specified genre if it doesn't exist.
	         * 
	         * @param genreName The name of the genre to add the song to.
	         * @param title The title of the song.
	         * @param artist The artist of the song.
	         * @param album The album of the song.
	         * @return An error message if the song already exists, null otherwise.
	         */
	    	public String addSongDialog(String genreName, String title, String artist, String album) {
	    	    Genre genre = musicLibrary.getGenre(genreName);
	    	    if (genre != null) {
	    	        Song song = new Song(genreName,title, artist, album);
	    	        if (genre.containsSong(song)) {
	    	            return "The song " + title + " by " + artist + " already exists in the library.";
	    	        } else {
	    	            genre.addSong(song);
	    	            return null; 
	    	        }
	    	    } else {
	    	        return "The genre " + genreName + " does not exist in the music library.";
	    	    }
	    	}
	        /**
	         * With this method, we get the songs of each genre for the ComboBox, so we know which ones to eliminate.
	         * 
	         * @param selectedGenre
	         * @return
	         */
	        public List<Song> getSongsByGenre(String selectedGenre) {
	        	if(selectedGenre != null) {
	        		return musicLibrary.getSongsForGenre(selectedGenre);
	        	}
	        	return null;
	       }
	        /**
	         * Generates the podium based on the number of votes for each song.
	         * 
	         * @return The list of songs in the podium.
	         */
	        public List<Song> generatePodium() {
	        	List<Song> allSongs = musicLibrary.getAllSongs();
	            Collections.sort(allSongs, Comparator.comparingInt(Song::getVotes).reversed());
	            List<Song> podium = new ArrayList<>();
	            for (int i = 0; i < Math.min(3, allSongs.size()); i++) {
	                podium.add(allSongs.get(i));
	            }
	            
	            return podium;
	        }
	        /**
	         * With this method we confirm the elimination of the song
	         * 
	         * @param selectedGenre
	         * @param selectedSong
	         */
	       public void confirmDeleteSong(String selectedGenre, String selectedSong) {
	    	   if (selectedGenre != null && selectedSong != null) {
	               
	               Song song = musicLibrary.getSongForGenre(selectedGenre, selectedSong);
	                if (song != null) {
	                    musicLibrary.deleteSong(selectedGenre, song);           
	                    My_Utils.printSuccessText("Song " + selectedSong + " deleted successfully from genre " + selectedGenre);
	                } else {
	                   
	                    My_Utils.printErrorText("Error: Song " + selectedSong + " not found in genre " + selectedGenre);
	                }
	            } else {
	                My_Utils.printErrorText("Error: Please select a genre and a song");
	            }   
	       }
	    }
	
	
	
	/**
     * Displays a confirmation dialog with the specified options.
     * 
     * @param alert The alert dialog to display.
     * @param buttonTypeYes The button type for confirming the action.
     * @param buttonTypeNo The button type for cancelling the action.
	 * @return 
     */
	public boolean showConfirmationDialog(Alert alert, ButtonType buttonTypeYes, ButtonType buttonTypeNo) {
	    alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);
	    boolean result = false;
	    Optional<ButtonType> buttonClicked = alert.showAndWait();
	    if (buttonClicked.isPresent()) {
	        if (buttonClicked.get() == buttonTypeYes) {
	            result = true;
	        } else if (buttonClicked.get() == buttonTypeNo) {
	            alert.close();
	        }
	    }
	    return result;
	}

	
	
	 /**
     * Reads and processes the content of the selected file.
     * 
     * @param selectedFile The selected file to read.
     * @param fileContentArea The text area to display the file content.
     */
	public void readAndProcessFile(File selectedFile, TextArea fileContentArea) {
        if (selectedFile != null) {
            try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {
                StringBuilder content = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line).append("\n");
                }
                fileContentArea.setText(content.toString());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

  
    /**
     * Save the Data in the class DataContainer
     * @param filename
     */
    public void saveProgram(String filename) {
        List<Voter> voterSaver = admin.getAllVoters();
        List<Song> musicSaver = musicLibrary.getAllSongs();
        DataContainer data = new DataContainer(voterSaver, musicSaver, admin.getVoteCount());
        SerializationUtils.saveToFile(data, filename);
        My_Utils.printSuccessText("Program saved successfully");
    }

  
 
    /**
     * Load the data from the file
     * @param filename
     */
    public void loadProgram(String filename) {
        System.out.println("Loading program from file: " + filename);
        DataContainer data = (DataContainer) SerializationUtils.loadFromFile(filename);
        if (data != null) {
            My_Utils.print("Loaded " + data.getUsers().size() + " users and " + data.getSongs().size() + " songs.");
            musicLibrary.addSongFromSerialization(data.getSongs());
            admin.addAllUsers(data.getUsers());
            
            // Establecer la instancia de VotingManager
            admin.setVoteCount(data.getVoteCount());
            
            My_Utils.printSuccessText("Load successfully");
            My_Utils.print("Program loaded successfully.");
        } else {
            My_Utils.print("Failed to load program. Data container is null.");
        }
    }



   
}
