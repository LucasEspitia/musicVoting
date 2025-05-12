package music;

/**
 * The Song class represents a song.
 * It contains data such as the genre, title, artist, album, and number of votes for the song.
 * This class follows encapsulation principles.
 */
public class Song {
    // Attributes of the Song class
    private String genre;
    private String title;
    private String artist;
    private String album;
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

    // Getters
    public String getAlbum() {
        return album;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getGenre() {
        return genre;
    }

    public int getVotes() {
        return votes;
    }

    // Setters
    public void setTitle(String title) {
        this.title = title;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

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
