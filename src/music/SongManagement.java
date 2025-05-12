package music;

import java.io.File;

/**
 * The SongManagement interface defines methods for managing songs.
 * It provides functionality to add songs to a genre, delete songs from a genre, 
 * and add songs from a file to the music library.
 */
public interface SongManagement {
    /**
     * Adds a song to the specified genre.
     * 
     * @param genreName The name of the genre to which the song will be added.
     * @param song The song to be added.
     * @return true if the song was successfully added, false otherwise.
     */
    public boolean addSong(String genreName, Song song);

    /**
     * Reads songs from a file and adds them to the music library.
     * 
     * @param filePath The path to the file containing songs to be added.
     */
    public void addSongsFromFile(File filePath);

    /**
     * Deletes a song from the specified genre.
     * 
     * @param genreName The name of the genre from which the song will be deleted.
     * @param song The song to be deleted.
     */
    public void deleteSong(String genreName, Song song);
}
