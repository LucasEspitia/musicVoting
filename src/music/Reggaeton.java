package music;

/**
 * The Reggaeton class represents the Reggaeton genre.
 * It follows the Singleton design pattern to ensure that only one instance of the Reggaeton genre exists.
 */
public class Reggaeton extends Genre {
    
    // Singleton instance of Reggaeton
    private static Reggaeton instance;

    /**
     * Private constructor to prevent external instantiation.
     * Initializes the Reggaeton genre with the specified genre ID and name.
     * 
     * @param idGenre The unique identifier for the Reggaeton genre.
     */
    private Reggaeton(int idGenre) {
        super(idGenre);
        setName("Reggaeton");
    }

    /**
     * Static method to retrieve the singleton instance of the Reggaeton genre.
     * If the instance does not exist, it is created.
     * 
     * @return The singleton instance of the Reggaeton genre.
     */
    public static Reggaeton getInstance() {
        if (instance == null) {
            instance = new Reggaeton(10002); // Reggaeton genre ID
        }
        return instance;
    }
}
