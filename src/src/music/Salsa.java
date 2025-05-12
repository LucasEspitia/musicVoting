package src.music;

/**
 * The Salsa class represents the Salsa genre.
 * It follows the Singleton design pattern to ensure that only one instance of the Salsa genre exists.
 */
public class Salsa extends Genre {
    
	 /**
     *  Singleton instance of Salsa
     */
    private static Salsa instance;

    /**
     * Private constructor to prevent external instantiation.
     * Initializes the Salsa genre with the specified genre ID and name.
     * 
     * @param idGenre The unique identifier for the Salsa genre.
     */
    private Salsa(int idGenre) {
        super(idGenre);
        setName("Salsa");
    }

    /**
     * Static method to retrieve the singleton instance of the Salsa genre.
     * If the instance does not exist, it is created.
     * 
     * @return The singleton instance of the Salsa genre.
     */
    public static Salsa getInstance() {
        if (instance == null) {
            instance = new Salsa(10003); // Salsa genre ID
        }
        return instance;
    }
}
