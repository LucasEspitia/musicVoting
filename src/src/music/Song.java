package src.music;

import java.io.Serializable;

/**
 * The Song class represents a song.
 * It contains data such as the genre, title, artist, album, and number of votes for the song.
 * This class follows encapsulation principles.
 */
public class Song implements Serializable {
	    /**
	     * The serial version UID for serialization.
	     */
		private static final long serialVersionUID = 1L;
		
		/**
		 * The genre of the song.
		 */
	    private String genre;
	    
	    /**
	     * The title of the song.
	     */
	    private String title;
	    
	    /**
	     * The artist of the song.
	     */
	    private String artist;
	    
	    /**
	     * The album of the song.
	     */
	    private String album;
	    
	    /**
	     * The number of votes received by the song.
	     */
	    private int votes;

    /**
     * Constructor for creating a new Song object with the specified parameters.
     * 
     * @param genre The genre of the song.
     * @param title The title of the song.
     * @param artist The artist of the song.
     * @param album The album of the song.
     */
    public Song(String genre, String title, String artist, String album) {
        setGenre(genre);
        setTitle(title);
        setArtist(artist);
        setAlbum(album);
        setVotes(0);
    }
    /**
     * Constructor used for deserialization
     */
    public Song() {
    	
    }

    /**
     * Retrieves the album of the song.
     * 
     * @return The album of the song.
     */
    public String getAlbum() {
        return album;
    }

    /**
     * Retrieves the title of the song.
     * 
     * @return The title of the song.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Retrieves the artist of the song.
     * 
     * @return The artist of the song.
     */
    public String getArtist() {
        return artist;
    }

    /**
     * Retrieves the genre of the song.
     * 
     * @return The genre of the song.
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Retrieves the number of votes the song has received.
     * 
     * @return The number of votes for the song.
     */
    public int getVotes() {
        return votes;
    }

    /**
     * Sets the title of the song.
     * 
     * @param title The new title to set for the song.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Sets the genre of the song.
     * 
     * @param genre The new genre to set for the song.
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * Sets the artist of the song.
     * 
     * @param artist The new artist to set for the song.
     */
    public void setArtist(String artist) {
        this.artist = artist;
    }

    /**
     * Sets the album of the song.
     * 
     * @param album The new album to set for the song.
     */
    public void setAlbum(String album) {
        this.album = album;
    }

    /**
     * Sets the number of votes for the song.
     * 
     * @param votes The new number of votes to set for the song.
     */
    public void setVotes(int votes) {
        this.votes = votes;
    }

    /**
     * Method to increment the number of votes for the song by one.
     */
    public void addVote() {
        this.votes += 1;
    }
}
