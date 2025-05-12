package src.music;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import src.utils.My_Utils;

/**
 * The MusicLibrary class represents the music library that stores songs organized by genre.
 * It follows the Singleton design pattern to ensure that only one instance of the music library exists.
 */
public class MusicLibrary implements SongManagement {
    
    /**
     * The singleton instance of the MusicLibrary class.
     */
    private static MusicLibrary instance;
    
    /**
     * A map containing genres as keys and corresponding Genre objects as values.
     */
    private Map<String, Genre> genres;
    
    /**
     * A list containing all songs in the music library.
     */
    private List<Song> allSongs;
    
    /**
     * Private constructor to prevent external instantiation.
     * Initializes the music library with empty collections for genres and songs.
     */
    private MusicLibrary() {
        this.genres = new HashMap<>();
        this.allSongs = new ArrayList<>();
        initializeGenres();
    }
    
    /**
     * Static method to retrieve the singleton instance of the music library.
     * If the instance does not exist, it is created.
     * 
     * @return The singleton instance of the music library.
     */
    public static MusicLibrary getInstance() {
        if (instance == null) {
            instance = new MusicLibrary();
        }
        return instance;
    }
    
    /**
     * Initializes the music library with predefined genres.
     */
    private void initializeGenres() {
    	addGenre(Bachata.getInstance());
    	addGenre(Reggaeton.getInstance());
    	addGenre(Salsa.getInstance());
    	addGenre(Samba.getInstance()); 
    	addGenre(Merengue.getInstance()); 
    }
    /**
     * Add a new genre
     * @param genre
     */
    private void addGenre(Genre genre) {
        genres.put(genre.getName(), genre);
    }
    
    /**
     * Retrieves the genre with the specified name from the music library.
     * 
     * @param genreName The name of the genre.
     * @return The genre object, or null if the genre does not exist.
     */
    public Genre getGenre(String genreName) {
        return genres.get(genreName);
    }
    
    /**
     * Retrieves all songs for the specified genre.
     * 
     * @param genreName The name of the genre.
     * @return A list of songs belonging to the genre.
     */
    public List<Song> getSongsForGenre(String genreName) {
        Genre genre = genres.get(genreName);
        return genre.getAllSongs();
    }
    
    /**
     * Retrieves a song with the specified title for the specified genre.
     * 
     * @param genreName The name of the genre.
     * @param songTitle The title of the song.
     * @return The song object, or null if the song does not exist in the genre.
     */
    public Song getSongForGenre(String genreName, String songTitle) {
        Genre genre = genres.get(genreName);
        return genre.getSongByTitle(songTitle);
    }
    
    /**
     * Retrieves all songs stored in the music library.
     * 
     * @return A list of all songs in the music library.
     */
    public List<Song> getAllSongs() {
        return this.allSongs;
    }
    
    @Override
    public boolean addSong(String genreName, Song song) {
        Genre genre = genres.get(genreName);
        if (genre != null) {
            if (genre.containsSong(song)) {
                //My_Utils.print("The song " + song.getTitle() + " by " + song.getArtist() + " already exists in the " + genreName + " genre. Skipping...");
                return false; 
            }
            genre.addSong(song);
            allSongs.add(song);
            return true; 
        }
        return false;
    }
    @Override

    public void addSongFromSerialization(List<Song> songs) {
    	songs.forEach(song -> addSong(song.getGenre(), song));
    }

    @Override
    public void deleteSong(String genreName, Song song) {
        Genre genre = genres.get(genreName);
        if (genre != null) {
            genre.deleteSong(song);
        } else {
        	My_Utils.print("The genre " + genreName + " does not exist in the music library.");
        }
    }
    
    @Override
    public void addSongsFromFile(File filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String genreName = parts[0];
                    String songTitle = parts[1];
                    String artist = parts[2];
                    String album = parts[3];
                    Genre genre = genres.get(genreName);
                    if (genre != null) {
                        Song song = new Song(genreName, songTitle, artist, album);
                        if (genre.containsSong(song)) {
                            My_Utils.print("The song " + songTitle + " by " + artist + " already exists in the " + genreName + " genre. Skipping...");
                            continue; 
                        }
                        genre.addSong(song);
                        allSongs.add(song);                   
                    } else {
                    	My_Utils.print("The genre " + genreName + " does not exist in the music library.");
                    }
                } else {
                	My_Utils.print("Incorrect formatting in the line: " + line);
                }
            }
        } catch (IOException e) {
        	My_Utils.print("Error reading file: " + e.getMessage());
        }
        //printAllSongs();
    }
    /**
     * Prints all songs stored in the music library, organised by genre.
     */
    public void printAllSongs() {
        for (Map.Entry<String, Genre> entry : genres.entrySet()) {
            Genre genre = entry.getValue();
            My_Utils.print("Genre: " + genre.getName());
            for (Song song : genre.getAllSongs()) {
            	My_Utils.print("Title: " + song.getTitle());
            	My_Utils.print("Artist: " + song.getArtist());
            	My_Utils.print("Album: " + song.getAlbum());
            	My_Utils.print("---");
            }
        }
    }
}
