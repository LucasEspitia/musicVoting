package src.music;

/**
 * The Samba class represents the Samba genre.
 * It follows the Singleton design pattern to ensure that only one instance of the Samba genre exists.
 */
public class Samba extends Genre {
    
	 /**
     *  Singleton instance of Samba
     */
    private static Samba instance;

    /**
     * Private constructor to prevent external instantiation.
     * Initializes the Samba genre with the specified genre ID and name.
     * 
     * @param idGenre The unique identifier for the Samba genre.
     */
    private Samba(int idGenre) {
        super(idGenre);
        setName("Samba");
    }

    /**
     * Static method to retrieve the singleton instance of the Samba genre.
     * If the instance does not exist, it is created.
     * 
     * @return The singleton instance of the Samba genre.
     */
    public static Samba getInstance() {
        if (instance == null) {
            instance = new Samba(10004); // Samba genre ID
        }
        return instance;
    }
}
