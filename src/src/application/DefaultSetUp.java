package src.application;

/**
 * The DefaultSetUp class is responsible for setting up the default configuration of the application,
 * including adding users and songs to the system.
 * It extends the SetUp class and initializes default users and songs.
 */
public class DefaultSetUp extends SetUp {
	
	private String[][] usersAndPasswords;
	private String[][] songs;

	/**
     * Constructs a new DefaultSetUp object.
     * Initializes the default users and songs arrays.
     */
	public DefaultSetUp() {
		// Initialization of default users and songs
		usersAndPasswords = new String[][] {
            {"user1", "a12345"},
            {"user2", "b12345"},
            {"user3", "c12345"},
            {"user4","d12345"},
            {"user5", "e12345"}
        };
        songs = new String[][] {
            // Genre: Salsa
            {"Salsa", "Vivir mi vida", "Marc Anthony", "3.0"},
            {"Salsa", "La vida es un carnaval", "Celia cruzova", "My life is a song"},
            {"Salsa", "Detalles", "Oscar D Leon", "Unknown"},

            // Genre: Bachata
            {"Bachata", "Eres mia", "Romeo Santos", "Formula Vol1"},
            {"Bachata", "Propuesta indecente", "Romeo Santos", "Formula Vol2"},
            {"Bachata", "Darte un beso", "Prince Royce", "I am the same"},

            // Genre: Reggaeton
            {"Reggaeton", "Despacito", "Luis Fonsi", "Life"},
            {"Reggaeton", "Gasolina", "Daddy Yankee", "Hood"},
            {"Reggaeton", "Moscow Mule", "Bad Bunny", "A summer without you"},

            // Genre: Merengue
            {"Merengue", "Suavemente", "Elvis Crespo", "Unknown"},
            {"Merengue", "Abusadora", "Wilfrido Vargas", "Unknown"},
            {"Merengue", "Guallando", "Fulanito", "The Most Famous Man On Earth"},

            // Género: Samba
            {"Samba", "Samba", "Gloria Estefan", "Brazil305"},
            {"Samba", "Maricaipinha", "Carlinhos Brown", "Unknown"},
            {"Samba", "Aquiarela Brasileira", "Martinho de Vila", "Aquiarela Brasileira 4"}
        };
       addAllSongs(songs);
       addAllUsers(usersAndPasswords);
       admin.setVoteCount(0);
	}
}
