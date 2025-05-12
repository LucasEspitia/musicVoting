package src.application;

import src.music.MusicLibrary;
import src.music.Song;
import src.users.Admin;

/**
 * The SetUp class is responsible for initializing the application setup.
 * It provides methods to add users and songs to the system.
 */
public abstract class SetUp {
    
    // Instance of the Admin class
    protected Admin admin = Admin.getInstance();
    
    // Instance of the MusicLibrary class
    protected MusicLibrary musicLibrary = MusicLibrary.getInstance();

    /**
     * Adds multiple users to the system based on the provided data.
     * @param usersAndPasswords A 2D array containing usernames and passwords for each user.
     */
    protected void addAllUsers(String[][] usersAndPasswords) {
        admin.addAllUsers(usersAndPasswords);
    }
    
    /**
     * Adds multiple songs to the music library based on the provided data.
     * @param songs A 2D array containing song data including genre, title, artist, and album.
     */
    protected void addAllSongs(String[][] songs) {
        for (String[] songData : songs) {
            String genre = songData[0];
            String title = songData[1];
            String artist = songData[2];
            String album = songData[3];

            Song song = new Song(genre, title, artist, album);
            musicLibrary.addSong(genre, song);
        }
    }
}

