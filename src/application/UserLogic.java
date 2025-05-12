package application;

import gui.UserScreen;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import music.MusicLibrary;
import music.Song;
import users.Voter;

/**
 * The UserLogic class contains the logic for user-related actions in the application.
 * It provides methods for displaying confirmation dialogs and processing user votes.
 */
public class UserLogic {
    
    // Instance of the MusicLibrary class
    private MusicLibrary musicLibrary = MusicLibrary.getInstance();
    
    /**
     * Displays a confirmation dialog with Yes and No buttons.
     * @param alert The alert dialog to display.
     * @param buttonTypeYes The button type representing 'Yes'.
     * @param buttonTypeNo The button type representing 'No'.
     * @param primaryStage The stage for the primary window.
     */
    public void showConfirmationDialog(Alert alert, ButtonType buttonTypeYes, ButtonType buttonTypeNo, Stage primaryStage) {
        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);
        alert.showAndWait().ifPresent(buttonType -> {
            if (buttonType == buttonTypeYes) {    
                alert.getButtonTypes().removeAll(buttonTypeYes, buttonTypeNo);
                UserScreen.openLogInInterface(primaryStage);    
            } else if (buttonType == buttonTypeNo) {
                alert.getButtonTypes().removeAll(buttonTypeYes, buttonTypeNo);
                alert.close();
            }
        });     
    }
    
    /**
     * Confirms the vote for a specific song by a user.
     * @param user The voter casting the vote.
     * @param genre The genre of the song being voted for.
     * @param songSelected The title of the song being voted for.
     */
    public void confirmVote(Voter user, String genre, String songSelected) {
        Song song = musicLibrary.getSongForGenre(genre, songSelected);
        user.doVote(song);
    }
}
