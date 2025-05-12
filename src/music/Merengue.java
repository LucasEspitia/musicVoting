package music;

/**
 * The Merengue class represents the Merengue genre in the music library.
 * It is a subclass of the Genre class and follows the Singleton design pattern,
 * ensuring that only one instance of the Merengue genre exists.
 */
public class Merengue extends Genre {
    
    // Singleton instance of Merengue
    private static Merengue instance;

    /**
     * Private constructor to prevent external instantiation.
     * Initializes the Merengue genre with the specified genre ID and name.
     * 
     * @param idGenre The unique identifier for the Merengue genre.
     */
    private Merengue(int idGenre) {
        super(idGenre);
        setName("Merengue");
    }

    /**
     * Static method to retrieve the singleton instance of the Merengue genre.
     * If the instance does not exist, it is created.
     * 
     * @return The singleton instance of the Merengue genre.
     */
    public static Merengue getInstance() {
        if (instance == null) {
            instance = new Merengue(10005); // Merengue genre ID
        }
        return instance;
    }
}
