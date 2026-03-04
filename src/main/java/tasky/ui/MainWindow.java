package tasky.ui;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import tasky.Tasky;

/**
 * Controller for the main GUI window.
 */
public class MainWindow {

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox dialogContainer;

    @FXML
    private TextField userInput;

    private Tasky tasky;

    private final Image userImage =
            new Image(this.getClass().getResourceAsStream("/images/User.png"));

    private final Image taskyImage =
            new Image(this.getClass().getResourceAsStream("/images/Tasky.jpg"));

    @FXML
    public void initialize() {

        scrollPane.setFitToWidth(true);

        dialogContainer.heightProperty().addListener((observable) ->
                scrollPane.setVvalue(1.0));
    }

    /**
     * Injects the Tasky instance.
     */
    public void setTasky(Tasky t) {
        tasky = t;

        dialogContainer.getChildren().add(
                DialogBox.getTaskyDialog(
                        "Hello! I'm Tasky.\nWhat can I do for you?",
                        taskyImage
                )
        );
    }

    /**
     * Handles user input from the GUI.
     */
    @FXML
    private void handleUserInput() {

        String input = userInput.getText();

        String response = tasky.getResponse(input);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getTaskyDialog(response, taskyImage)
        );

        userInput.clear();
    }
}