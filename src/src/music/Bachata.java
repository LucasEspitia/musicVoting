package src.music;

/**
 * The Bachata class represents the bachata genre in the music library.
 * It extends the Genre class and follows the Singleton pattern to ensure
 * that only one instance of Bachata exists.
 */
public class Bachata extends Genre {
    /**
     * Used for the singletone pattern
     */
    private static Bachata instance;

    /**
     * Constructs a new Bachata object with the specified genre ID.
     * 
     * @param idGenre The unique identifier for the Bachata genre.
     */
    private Bachata(int idGenre) {
        super(idGenre);
        setName("Bachata");
    }

    /**
     * Returns the singleton instance of the Bachata class.
     * If the instance does not exist, it creates a new one with a default ID.
     * 
     * @return The singleton instance of the Bachata class.
     */
    public static Bachata getInstance() {
        if (instance == null) {
            instance = new Bachata(10001);
        }
        return instance;
    }
}
