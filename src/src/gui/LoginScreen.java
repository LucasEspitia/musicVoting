package src.gui;
import src.application.LoginLogic;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import src.users.Admin;
import src.users.Voter;
import src.utils.My_Utils;

/**
 * The LoginScreen class represents the graphical user interface for the login screen.
 * It allows users and admins to log in using their credentials.
 */
public class LoginScreen extends Application {

	// Attributes
	  private Button userButton = new Button("Login as User");
	  private Button loginButton = new Button("Login");
	  private Button adminButton = new Button("Login as Admin");
	  private Button backButton = new Button("X");
	  private Label titleLabelUser = new Label("Login as User");
	  private Label usernameLabel = new Label("User:");
	  private Label passwordUserLabel = new Label("Password:");
	  private Label titleLabelAdmin = new Label("Login as Admin");
	  private Label AdminLabel = new Label("Admin:");
	  private Label passwordAdminLabel = new Label("Password:");
	  private Label errorMessage = new Label("");
	  private TextField usernameField = new TextField();
	  private PasswordField passwordField = new PasswordField();
	  private HBox backButtonBox = new HBox(backButton);
	  private VBox root = new VBox(20);
	  private Scene scene = new Scene(root, 750, 600);
	  private LoginLogic loginLogic = new LoginLogic();
	  private Stage primaryStage;
	  
	  private Admin admin = Admin.getInstance();

	  /**
	     * Starts the Login Interface.
	     * @param primaryStage The primary stage of the application.
	     */
    @Override
    public void start(Stage primaryStage) {
    	this.primaryStage = primaryStage;
    	configureSelectButtons();
    	startStyle();
        // Configure layout of the login screen
        // Set up the scene and stage
        primaryStage.setScene(scene);
        primaryStage.setTitle("Login Screen");
        primaryStage.show();
    }
    /**
     * Method to display the user login interface
     */
    private void showUserLogin() {
        deleteStartButtons();
        loginButton.setOnAction(e -> {
            String enteredUsername = usernameField.getText();
            String enteredPassword = passwordField.getText();       
            String validationMessage = loginLogic.validateUserCredentials(enteredUsername, enteredPassword);       
            if (validationMessage.equals("success")) {
                openUserInterface(enteredUsername);
            } else {
                insertErrorMessage(validationMessage);
            }
        });
        configureBackButton();   
        root.getChildren().addAll(backButtonBox, titleLabelUser, usernameLabel, usernameField, passwordUserLabel, passwordField, loginButton, errorMessage);
    }

    /**
     *  Method to display the admin login interface
     */
    private void showAdminLogin() {
    	deleteStartButtons();
        loginButton.setOnAction(e -> {
            String enteredUsername = usernameField.getText();
            String enteredPassword = passwordField.getText();      
            if(loginLogic.validateAdminCredentials(enteredUsername, enteredPassword)) {
            	openAdminInterface();
            }else{
            	insertErrorMessage("Admin credentials are incorrect");
           }            
        });
  
        configureBackButton();
        root.getChildren().addAll(backButtonBox, titleLabelAdmin, AdminLabel, usernameField, passwordAdminLabel, passwordField, loginButton, errorMessage);
    }
    /**
     * Method to remove start buttons
     */
    private void deleteStartButtons() {
    	 root.getChildren().removeAll(userButton, adminButton);	
    }
    /**
     *  Method to insert error message
     * @param errorStringMessage
     */
    private void insertErrorMessage(String errorStringMessage) {
    	errorMessage.setText(errorStringMessage);
    	errorMessage.setTextFill(Color.RED);
    } 
    /**
     *  Method to configure select buttons
     */
    private void configureSelectButtons() {
        // Handle click events on the buttons
        userButton.setOnAction(e -> showUserLogin());
        adminButton.setOnAction(e -> showAdminLogin());
    }
    /**
     *  Method to configure the back button
     */
    private void configureBackButton() {
         backButton.setOnAction(e -> {
        	 usernameField.clear();
             passwordField.clear();
             errorMessage.setText(" ");
        	 root.getChildren().setAll(userButton, adminButton);
        
         });
         backButtonBox.setAlignment(Pos.TOP_RIGHT);
         backButtonBox.setPadding(new Insets(10));
    }
    /**
     *  Method to open the admin interface
     */
    private void openAdminInterface() {
    	AdminScreen adminInterface = new AdminScreen();
        adminInterface.startAdminInterface(primaryStage);
    }
    /**
     *  Method to open the user interface
     * @param username
     */
    private void openUserInterface(String username) {
    	Voter user  = admin.getUser(username);
    	if(user.getDoVoteAlready() != true) {
	    	UserScreen userInterface = new UserScreen();
	    	userInterface.startUserInterface(primaryStage, user);
    	}else {
    		My_Utils.printErrorText("The username: " + user.getUsername() + " has already voted");
    	}
    }
    /**
     *  Method to style the elements
     */
    private void startStyle() {
    	// Set styles for the buttons
    	root.setPadding(new Insets(50));
        root.setAlignment(Pos.CENTER);
        root.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        root.getChildren().addAll(userButton, adminButton);
        userButton.setStyle("-fx-font-size: 18px; -fx-padding: 10px 20px;");
        adminButton.setStyle("-fx-font-size: 18px; -fx-padding: 10px 20px;");
        // Set style for the backButton
        backButton.setStyle("-fx-font-size: 18px;");
        	
    }  
    /**
     * Main method to launch the application.
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
