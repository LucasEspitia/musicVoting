package music;

import java.util.ArrayList;
import java.util.List;

/**
 * The Genre class represents a musical genre in the music library.
 * It serves as the abstract superclass for specific genres and provides
 * common functionality shared by all genres.
 */
public abstract class Genre {
    
    private String name;
    private int idGenre;
    private List<Song> songs;
   
 
    
    /**
     * Constructs a new Genre object with the specified genre ID.
     * 
     * @param idGenre The unique identifier for the genre.
     */
    public Genre(int idGenre) {
        setName(name);
        setIdGenre(idGenre);
        this.songs = new ArrayList<Song>();
        
    }
    
    /**
     * Returns the unique identifier for the genre.
     * 
     * @return The genre's unique identifier.
     */
    public int getIdGenre() {
        return idGenre;
    }
    
    /**
     * Returns the name of the genre.
     * 
     * @return The name of the genre.
     */
    public String getName() {
        return name;
    }
    
    /**
     * Returns a list of all songs belonging to the genre.
     * 
     * @return A list of songs belonging to the genre.
     */
    public List<Song> getAllSongs() {
        return songs;
    }
    
    /**
     * Returns the song with the specified title belonging to the genre.
     * 
     * @param title The title of the song to retrieve.
     * @return The song object with the specified title, or null if not found.
     */
    public Song getSongByTitle(String title) {
        for (Song song : songs) {
            if (song.getTitle().equals(title)) {
                return song;
            }
        }
        return null;
    }

    /**
     * Sets the unique identifier for the genre.
     * 
     * @param idGenre The unique identifier to set.
     */
    public void setIdGenre(int idGenre) {
        this.idGenre = idGenre;
    }

    /**
     * Sets the name of the genre.
     * 
     * @param name The name of the genre to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Adds a song to the list of songs belonging to the genre.
     * 
     * @param song The song to add.
     */
    public void addSong(Song song) {
        	this.songs.add(song);
    }

    /**
     * Adds a list of songs to the genre.
     * 
     * @param listOfSongs The list of songs to add.
     */
    public void addAllSongs(List<Song> listOfSongs) {
        this.songs.addAll(listOfSongs); 
    }

    /**
     * Deletes a song from the list of songs belonging to the genre.
     * 
     * @param song The song to delete.
     */
    public void deleteSong(Song song) {
        this.songs.remove(song);
    }

    /**
     * Checks if the genre contains a specific song.
     * 
     * @param song The song to check.
     * @return true if the genre contains the song, false otherwise.
     */
    public boolean containsSong(Song song) {
        for (Song s : songs) {
            if (s.getTitle().equals(song.getTitle()) && s.getArtist().equals(song.getArtist())) {
                return true; 
            }
        }
        return false; 
    }
}
