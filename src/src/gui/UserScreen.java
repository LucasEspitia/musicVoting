package src.gui;

import java.util.List;

import src.application.UserLogic;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import src.music.MusicLibrary;
import src.music.Song;
import src.users.Voter;
import src.utils.My_Utils;
/**
 * The UserScreen class represents the graphical user interface for the voter user.
 */
public class UserScreen extends Application {

    private Button logoutButton = new Button("Log Out");
    private Button confirmButton = new Button("Confirm Vote");
    private HBox topLayout = new HBox(10);
    private VBox centerLayout = new VBox(10); 
    private ComboBox<String> genreComboBox = new ComboBox<>();
    private ComboBox<String> songComboBox = new ComboBox<>();
    private Label remainingVotesLabel;
    private Label usernameLabel;
    private Alert alert = new Alert(AlertType.CONFIRMATION);
	private ButtonType buttonTypeYes = new ButtonType("Yes");
	private ButtonType buttonTypeNo = new ButtonType("No");

    private BorderPane userLayout = new BorderPane();
    private Scene userScene = new Scene(userLayout, 800, 600);
    private Stage primaryStage;

    private UserLogic userLogic = new UserLogic();
    private MusicLibrary musicLibrary = MusicLibrary.getInstance();
    
    private String selectedGenre;
    private String selectedSong;
    /**
     * Starts the User Interface.
     * @param primaryStage The primary stage of the application.
     * @param user The user logging in.
     */
    public void startUserInterface(Stage primaryStage, Voter user) {
        this.primaryStage = primaryStage;
        My_Utils.printLine();
        My_Utils.print("Log in as " + user.getUsername());
        openUserInterface(user);
    }
    /**
     *  Method to open the user interface
     * @param user
     */
    private void openUserInterface(Voter user) {
  
        primaryStage.setScene(userScene);
        usernameLabel = new Label("Log in as: " + user.getUsername());
        topLayout.getChildren().addAll(usernameLabel, logoutButton);
        
        topLayout.setAlignment(Pos.CENTER_RIGHT);
        centerLayout.setAlignment(Pos.CENTER); 
        genreComboBox.setPrefWidth(150);
        songComboBox.setPrefWidth(150);
        // Add layouts to the border pane
        userLayout.setTop(topLayout);
        userLayout.setCenter(centerLayout);
        remainingVotesLabel = new Label("Remaining Votes of the user " + user.getUsername() + ": " + user.getRemainingVotes());
        centerLayout.getChildren().addAll(remainingVotesLabel, genreComboBox, songComboBox, confirmButton);

        primaryStage.setTitle("Log In as User: " + user.getUsername());

        actionsUser(user);
    }
    /**
     *  Method to handle user actions
     * @param user
     */
    private void actionsUser(Voter user) {
        logoutButton.setOnAction(event -> handleLogout());
        configureGenreComboBox(user);
        confirmButton.setOnAction(event -> handleConfirmVote(user));
    }
    /**
     *  Method to handle logout action
     */
    private void handleLogout() {
    	alert.setTitle("EXIT OR CONTINUE");
		alert.setHeaderText(null);
		alert.setContentText("Are you sure you want to log out?");
		
	userLogic.showConfirmationDialog(alert, buttonTypeYes, buttonTypeNo, primaryStage);
       
    }
    /**
     *  Method to configure genre selection combo box
     *  @param user
     */
    private void configureGenreComboBox(Voter user) {
    	genreComboBox.getItems().addAll("Salsa", "Bachata", "Reggaeton", "Merengue", "Samba");
    	genreComboBox.setOnAction(event -> {
            selectedGenre = genreComboBox.getValue();
            if (selectedGenre != null) {
                handleGenreSelection(user, selectedGenre);
            }
        });
    }
    /**
     *  Method to handle genre selection
     * @param user
     * @param selectedGenre
     */
    private void handleGenreSelection(Voter user, String selectedGenre) {
        if (selectedGenre != null) {
            List<Song> songsForGenre = musicLibrary.getSongsForGenre(selectedGenre);    
            songComboBox.getItems().clear();
            for (Song song : songsForGenre) {
                if (!user.hasVotedFor(song)) { 
                    songComboBox.getItems().add(song.getTitle());
                }
            }
            songComboBox.setOnAction(event -> {
                selectedSong = songComboBox.getValue();
            });
        }
    }
    /**
     *  Method to handle confirming a vote
     * @param user
     */
    private void handleConfirmVote(Voter user) {
    	if(selectedGenre == null) {
    		My_Utils.printErrorText("Please select a genre and song before confirming your vote.");	
    		return;
    	} 	
        if (selectedSong == null) {
        	 My_Utils.printErrorText("Please select a song before confirming your vote.");
        	 return;
        } 
        if(user.getDoVoteAlready() == true) {
         	My_Utils.printErrorText("You do not have more votes left.\nPlease log out and let other user vote");
         	return;
         }
        userLogic.confirmVote(user,selectedGenre ,selectedSong);
        updateRemainingVotesLabel(user);   
    }
    /**
     *  Method to open the login interface
     * @param primaryStage
     */
    public static void openLogInInterface(Stage primaryStage) {
        LoginScreen loginInterface = new LoginScreen();
        try {
        	 My_Utils.print("Log out succesfully");
        	 My_Utils.printLine();
            loginInterface.start(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }   
    }
    
    /**
     *  Method to update the remaining votes label
     * @param remainingVotes
     */
    private void updateRemainingVotesLabel(Voter user) {
        remainingVotesLabel.setText("Remaining Votes of the user " + user.getUsername() + ": " + user.getRemainingVotes());
    }
    
    /**
     * Placeholder for the start method, not used in this implementation.
     * @param primaryStage The primary stage of the application.
     * @throws Exception An exception that may occur during the start process.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Placeholder for the start method, not used in this implementation
    }
}


