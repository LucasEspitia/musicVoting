package src.gui;

import javafx.scene.control.TextField;

import java.io.File;
import java.util.List;
import java.util.Optional;

import javafx.stage.FileChooser;
import javafx.stage.Modality;
import src.application.AdminLogic;
import src.application.AdminLogic.SongLogic;
import src.application.AdminLogic.UserLogic;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import src.music.Song;
import src.utils.My_Utils;

/**
 * The AdminScreen class represents the graphical user interface for the admin user.
 * It provides functionality for adding songs, adding users, deleting songs, and finishing the program.
 **/
public class AdminScreen extends Application {

	 // Attributes
    private Stage primaryStage;
    private Stage addSongStage = new Stage();
    private Stage addUserStage = new Stage();
    private Label titleLabel = new Label("Log in as Admin");
    private Button logoutButton = new Button("Logout");
    private Button addSongButton = new Button("Add Song");
    private Button addSongsByFileButton = new Button("Add Songs by File");
    private Button addUserByNameButton = new Button("Add User");
    private Button addUsersByFileButton = new Button("Add Users by File");
    private Button deleteSongButton = new Button("Delete Song");
    private Button deleteUserButton = new Button("Delete User");
    private Button finishProgramButton = new Button("Finish Program");
    private Button saveButton = new Button("Save");
    private Button loadButton = new Button("Load");
    private ButtonType buttonTypeYes = new ButtonType("Yes");
    private ButtonType buttonTypeNo = new ButtonType("No");
    private double buttonWidth = 200;

    private AdminLogic adminLogic = new AdminLogic();
    private Alert alert = new Alert(AlertType.CONFIRMATION);
    private Label genreLabel = new Label("Genre:");
    private Label titleLabelSong = new Label("Title:");
    private Label artistLabel = new Label("Artist:");
    private Label albumLabel = new Label("Album:");
    private TextField genreField = new TextField();
    private TextField titleField = new TextField();
    private TextField artistField = new TextField();
    private TextField albumField = new TextField();

    private Label usernameLabel = new Label("Username:");
    private Label passwordLabel = new Label("Password:");
    private TextField usernameField = new TextField();
    private TextField passwordField = new TextField();

    private Button addSongB = new Button("Add Song");
    private Button deleteB = new Button("Delete");
    private Button addUserB = new Button("Confirm");
    private Button chooseFileButton = new Button("Choose File");
    private Button confirmButton = new Button("Confirm");

    
    private VBox adminLayout = new VBox(10);

    private HBox confirmationBox = new HBox(10);

    private Scene adminScene = new Scene(adminLayout, 750, 400);

    private File selectedFile;
    private boolean firstOpen = false;
    private AdminLogic.UserLogic userLogic = adminLogic.new UserLogic();
    private AdminLogic.SongLogic songLogic = adminLogic.new SongLogic();

    
    /**
     * Starts the Admin Interface.
     * @param primaryStage The primary stage of the application.
     */
    public void startAdminInterface(Stage primaryStage) {
        this.primaryStage = primaryStage;
        My_Utils.printLine();
        My_Utils.print("Log in as Admin");
        openAdminInterface();
    }

    /**
     * Opens the Admin Interface.
     */
    private void openAdminInterface() {
        startStyle();
        adminActions();
        createFirstTimeInterface();
    }
    /**
     * Controls the interface to not have an exception
     */
    private void createFirstTimeInterface(){
        if(firstOpen == false) {
            primaryStage.setScene(adminScene);
            primaryStage.setTitle("Admin Interface");
            firstOpen = true;
        }
    }

    /**
     * Contains all admin actions.
     */
    private void adminActions() {	
    	logoutButton.setOnAction(e -> {
    		disableButtons();
    		showConfirmationDialog();
    	});
        addSongButton.setOnAction(e -> {
            disableButtons();
            showAddSongDialog();
        });
        addSongsByFileButton.setOnAction(e -> {
        	disableButtons();
        	showAddSongsByFileDialog();	
        });
        addUserByNameButton.setOnAction(e -> {
        	disableButtons();
        	showAddUserByNameDialog();
        });
        addUsersByFileButton.setOnAction(e -> {
        	disableButtons();
        	showAddUserByFileDialog();
        });
        deleteSongButton.setOnAction(e -> {
        	disableButtons();
        	showDeleteSongDialog();	
        });
        deleteUserButton.setOnMouseClicked(e -> {
        	disableButtons();
        	showDeleteUserDialog();
        });
        finishProgramButton.setOnAction(e -> {
        	disableButtons();
        	showFinishProgramDialog();
        });
        saveButton.setOnAction(e -> {
        	showSaveDialog();
        });
        loadButton.setOnAction(e_-> {      
        	showLoadDialog();
        });
        
    }
    /**
     * Disable the buttons when we open another window
     */
    private void disableButtons() {   
        addSongButton.setDisable(true);
        addSongsByFileButton.setDisable(true);
        addUserByNameButton.setDisable(true);
        addUsersByFileButton.setDisable(true);
        deleteSongButton.setDisable(true);
        deleteUserButton.setDisable(true);
        finishProgramButton.setDisable(true);
    }
    /**
     * Enable the buttons when we close the window
     */
    private void enableButtons() {
        addSongButton.setDisable(false);
        addSongsByFileButton.setDisable(false);
        addUserByNameButton.setDisable(false);
        addUsersByFileButton.setDisable(false);
        deleteSongButton.setDisable(false);
        deleteUserButton.setDisable(false);
        finishProgramButton.setDisable(false);
    }

    /**
     * Opens the logout confirmation dialog.
     */
    private void showConfirmationDialog() {
        alert.setTitle("EXIT OR CONTINUE");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to log out?");
        if(adminLogic.showConfirmationDialog(alert, buttonTypeYes, buttonTypeNo)) {
        	openLogInInterface();
        }else {
        	enableButtons();
        	openAdminInterface();
        }
    }
    /**
     * Open the save program Dialog
     */
    private void showSaveDialog() {
    	FileChooser fileChooser = new FileChooser();
    	fileChooser.setTitle("Save Data");
    	fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Serialized Files", "*.dat"));
    	File file = fileChooser.showSaveDialog(null);
    	 if (file != null) {  
    		 String filePath = file.getAbsolutePath();
    		 adminLogic.saveProgram(filePath); 
    		 My_Utils.print("Saving data in:" + file.getAbsolutePath());
         } else {
             My_Utils.print("Save file canceled dialog.");
         }
    
    }
    /**
     * Open the Load program Dialog  
     */
    private void showLoadDialog() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Load Data");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Serialized Files", "*.dat"));
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {  
            String filePath = file.getAbsolutePath();
            adminLogic.loadProgram(filePath); 
        } else {
            My_Utils.print("Load file canceled dialog.");
        }
    }    
    /**
     *  Opens the add user dialog by name.
     */
    private void showAddUserByNameDialog() {
    	VBox userAddLayout = new VBox(10);
    	Scene userAddScene = new Scene(userAddLayout);
        userAddLayout.getChildren().addAll(
                usernameLabel, usernameField,
                passwordLabel, passwordField,
                addUserB
        );
     
        addUserB.setOnAction(e -> {
            String username = usernameField.getText().trim();
            String password = passwordField.getText().trim();

            if(username.isEmpty() || password.isEmpty()) {
                My_Utils.printErrorText("Error: Please fill all the fields");
                return;
            }

            if(userLogic.showAddUserByNameDialog(username, password)) {
                My_Utils.printSuccessText("Username " + username + " was successfully added");
            } else {
                My_Utils.printErrorText("Error: username already exists");
            }
        });
        addUserStage.setOnCloseRequest(e -> {
            usernameField.clear();
            passwordField.clear();
            userAddLayout.getChildren().clear();
            enableButtons();
            openAdminInterface();
        });
        
        userAddLayout.setPrefSize(400, 300);
        addUserStage.setTitle("Add User");
        addUserStage.setScene(userAddScene);
        addUserStage.show();
        
    }
    
    /**
     * Opens the add song dialog.
     */
    private void showAddSongDialog() {
    	ComboBox<String> genreComboBox = new ComboBox<>();
        VBox songaddLayout= new VBox(10);
        Scene songScene = new Scene(songaddLayout);
    	
    	genreComboBox.getItems().addAll("Bachata", "Salsa", "Merengue", "Reggaeton", "Samba");
        songaddLayout.getChildren().addAll(
                genreLabel, genreComboBox,
                titleLabelSong, titleField,
                artistLabel, artistField,
                albumLabel, albumField,
                addSongB
        );
        songaddLayout.setPrefSize(400, 300);
        addSongStage.setTitle("Add Song");
        addSongStage.setScene(songScene);
        addSongStage.show();
        addSongB.setOnAction(e -> {
            String genre = genreComboBox.getValue();
            String title = titleField.getText().trim();
            String artist = artistField.getText().trim();
            String album = albumField.getText().trim();
            if (genre.isEmpty() || title.isEmpty() || artist.isEmpty() || album.isEmpty()) {
                My_Utils.printErrorText("Error: Please fill all the fields");
                return;
            }
            String errorMessage = songLogic.addSongDialog(genre, title, artist, album);
            if (errorMessage == null) {
                My_Utils.printSuccessText("Song " + title + " was added successfully");
            } else {
                My_Utils.printErrorText("Error: " + errorMessage);
            }
        });
        addSongStage.setOnCloseRequest(e -> {
            genreField.clear();
            titleField.clear();
            artistField.clear();
            albumField.clear();
            songaddLayout.getChildren().clear();
            enableButtons();
            openAdminInterface();
        });
    }
    /**
     * Opens the add Users by file Dialog
     */
    private void showAddUserByFileDialog() {
		GridPane gridPane = new GridPane();
	    TextArea fileContentArea = new TextArea();
	    VBox root = new VBox(10);
	    fileContentArea.setEditable(false);
	    fileContentArea.setPrefSize(400, 200);
	    gridPane.setHgap(10);
	    gridPane.setVgap(10);
	
	    gridPane.add(chooseFileButton, 1, 0);
	    gridPane.add(confirmButton, 1, 1);
	        
        root.setAlignment(Pos.CENTER_LEFT);
        root.getChildren().addAll(gridPane, fileContentArea);

        root.setPrefSize(600, 600);

        Scene scene = new Scene(root);
        addSongStage.setTitle("Add Users By File");
        addSongStage.setScene(scene);
        addSongStage.show();

        chooseFileButton.setOnAction(e -> {
            readAndOpenFile(fileContentArea);
        });

        confirmButton.setOnAction(e -> {
            userLogic.confirmaddUserByFile(selectedFile);
        });

        addSongStage.setOnCloseRequest(e -> {
            root.getChildren().clear();
            openAdminInterface();
        });
        addSongStage.setOnCloseRequest(e -> {     
            fileContentArea.clear();
            enableButtons();
            openAdminInterface();
        });
        
    }
    /**
     * Opens the add songs by file dialog.
     */
    private void showAddSongsByFileDialog() {
        GridPane gridPane = new GridPane();
        TextArea fileContentArea = new TextArea();
        VBox root = new VBox(10);
        Scene scene = new Scene(root);
        
        fileContentArea.setEditable(false);
        fileContentArea.setPrefSize(400, 200);
        
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.add(chooseFileButton, 1, 0);
        gridPane.add(confirmButton, 1, 1);  
        
        root.setAlignment(Pos.CENTER_LEFT);
        root.getChildren().addAll(gridPane, fileContentArea);
        root.setPrefSize(600, 600);
   
        addSongStage.setTitle("Add Songs By File");
        addSongStage.setScene(scene);
        addSongStage.show();

        chooseFileButton.setOnAction(e -> {
            readAndOpenFile(fileContentArea);
        });

        confirmButton.setOnAction(e -> {
            songLogic.confirmFileAddSongs(selectedFile);
        });

        addSongStage.setOnCloseRequest(e -> {
            root.getChildren().clear();
            enableButtons();
            openAdminInterface();
        });
    }
    /**
     * Method to read and open the file.
     * @param fileContentArea
     */
    private void readAndOpenFile(TextArea fileContentArea) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        selectedFile = fileChooser.showOpenDialog(addSongStage);
        adminLogic.readAndProcessFile(selectedFile, fileContentArea);
    }

    /**
     * Shows the dialog to delete a song.    
     */
    private void showDeleteSongDialog() {
        ComboBox<String> genreComboBox = new ComboBox<>();
        ComboBox<String> songComboBox = new ComboBox<>();
        VBox root = new VBox(10);
        Stage deleteSongStage = new Stage();
        Scene deleteSongScene = new Scene(root);

        genreComboBox.getItems().addAll("Bachata", "Salsa", "Merengue", "Reggaeton", "Samba");  
        genreComboBox.setMinWidth(200);
        songComboBox.setMinWidth(200);
        genreComboBox.setOnAction(e -> {
            String selectedGenre = genreComboBox.getValue();
            fillSongComboBox(selectedGenre, songComboBox);
           
        });
        root.setAlignment(Pos.CENTER);
        root.setPrefSize(400, 300);
        root.getChildren().addAll(
                genreLabel, genreComboBox,
                titleLabelSong, songComboBox,
                deleteB
        );
        
        deleteB.setText("Delete Song");
        deleteB.setOnAction(e -> {
            String selectedGenre = genreComboBox.getValue();
            String selectedSong = songComboBox.getValue();
            songLogic.confirmDeleteSong(selectedGenre, selectedSong);          
            fillSongComboBox(selectedGenre, songComboBox);
        });
        deleteSongStage.setOnCloseRequest(e -> {
        	root.getChildren().clear();
        	enableButtons();
        	openAdminInterface();
        });
       
        
        
        deleteSongStage.setTitle("Delete Songs");
        deleteSongStage.setScene(deleteSongScene);
        deleteSongStage.show();        
    }
    /**
     * Helps to generate the songs of each genre, it is used to reuse code.
     * @param selectedGenre
     * @param songComboBox
     */
    private void fillSongComboBox(String selectedGenre, ComboBox<String> songComboBox) {
    	 if (selectedGenre != null) {        
             List<Song> songsForGenre = songLogic.getSongsByGenre(selectedGenre);           
             songComboBox.getItems().clear();
             for (Song song : songsForGenre) {
                 songComboBox.getItems().add(song.getTitle());
             }
         }	
    }
    /**
     * Shows the delete dialog to delete an user.
     */
     
     private void showDeleteUserDialog() {
         Stage deleteUserStage = new Stage();
         TableView<String> table = new TableView<>();
         TableColumn<String, String> usernameColumn = new TableColumn<>("Username");
         usernameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()));
         List<String> usernames = userLogic.getAllUsernames();
         ObservableList<String> usernameList = FXCollections.observableArrayList(usernames);

         Button confirmButton = new Button("Confirm");
         confirmButton.setOnAction(e -> {
             String selectedUsername = table.getSelectionModel().getSelectedItem();
             userLogic.confirmDeleteUser(selectedUsername);
             usernameList.remove(selectedUsername);
           
         });
         HBox buttonBox = new HBox(confirmButton);
         BorderPane root = new BorderPane(table);
         table.getColumns().add(usernameColumn);

       
         table.setItems(usernameList);

         root.setBottom(buttonBox);

         Scene scene = new Scene(root);
         deleteUserStage.setScene(scene);
         deleteUserStage.setTitle("Delete user");
         deleteUserStage.show();
         
         deleteUserStage.setOnCloseRequest(e -> {
         	root.getChildren().clear();
         	enableButtons();
         	openAdminInterface();
         });       
     }

     /**
      * Opens the finish program dialog.
      */
     private void showFinishProgramDialog() {
         if (!userLogic.checkUsersMinVoted()) {
             My_Utils.printErrorText("Error: At least three users must vote before finishing the program.");
             enableButtons();
             return;
         }

         alert.setTitle("Finish Program");
         alert.setHeaderText(null);
         alert.setContentText("Are you sure you want to finish the program?");

         alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

         Optional<ButtonType> result = alert.showAndWait();
         if (result.isPresent() && result.get() == buttonTypeYes) {
             List<Song> podium = songLogic.generatePodium();
             printPodium(podium);
             displayPodium(podium);
             System.exit(0);
         }
         
         alert.setOnCloseRequest(e->{
         	enableButtons();
         	openAdminInterface();
         });
     }
     /**
      * Displays the podium.
      * @param podium
      */
      
     public void displayPodium(List<Song> podium) {
         Stage window = new Stage();
         window.initModality(Modality.APPLICATION_MODAL);
         window.setTitle("Podium");

         VBox layout = new VBox(10);
         layout.setPadding(new Insets(10));

         for (int i = 0; i < podium.size(); i++) {
             Song song = podium.get(i);
             String labelText = String.format("Top %d: %s - %s (Genre: %s, Votes: %d)",
                 i + 1, song.getTitle(), song.getArtist(), song.getGenre(), song.getVotes());
             Label label = new Label(labelText);
             layout.getChildren().add(label);
         }

         Scene scene = new Scene(layout);
         window.setScene(scene);
         window.showAndWait();
     }
     /**
      * Prints the podium to the console.
      * @param podium
      */
     private void printPodium(List<Song> podium) {
         if (podium.isEmpty()) {
             My_Utils.printErrorText("No songs available to generate a podium.");
         } else {
         	My_Utils.print("-----------------------  PODIUM  ------------------------------");
             My_Utils.print("Podiun");
             for (int i = 0; i < podium.size(); i++) {
                 Song song = podium.get(i);
                 System.out.println((i + 1) + ". " + song.getTitle() + " - " + song.getArtist() +
                         " (Genre: " + song.getGenre() + ", Votes: " + song.getVotes() + ")");
             }
             My_Utils.print("-----------------------  PODIUM  ------------------------------");
         }
     }
     /**
      * Styles the elements.
      */
     private void startStyle() {
         if(firstOpen == false) {
             adminLayout.setAlignment(Pos.CENTER);
             adminLayout.getChildren().addAll(
                     titleLabel,
                     logoutButton,
                     addUserByNameButton,
                     addUsersByFileButton,
                     addSongButton,
                     addSongsByFileButton,
                     deleteSongButton,
                     deleteUserButton,
                     finishProgramButton
                
             );
             confirmationBox.setAlignment(Pos.TOP_RIGHT );
             confirmationBox.getChildren().addAll(saveButton, loadButton, logoutButton);
             adminLayout.getChildren().add(0, confirmationBox);
             titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
             confirmationBox.setStyle("-fx-padding: 20px;");
             addUserByNameButton.setMinWidth(buttonWidth);
             addUsersByFileButton.setMinWidth(buttonWidth);
             addSongButton.setMinWidth(buttonWidth);
             addSongsByFileButton.setMinWidth(buttonWidth);
             deleteSongButton.setMinWidth(buttonWidth);
             deleteUserButton.setMinWidth(buttonWidth);
             finishProgramButton.setMinWidth(buttonWidth);
         }
     }
     /**
      * open the Log in interface
      * @param primaryStage
      */
      
     public void openLogInInterface() {
         LoginScreen loginInterface = new LoginScreen();
         try {
         	My_Utils.printLine();
             loginInterface.start(primaryStage);
         } catch (Exception e) {
             e.printStackTrace();
         }
     }
     @Override
     public void start(Stage arg0) throws Exception {
     }
      
}
