package tasky.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Represents a chat dialog box in the GUI.
 */
public class DialogBox extends HBox {

    private final Label text;
    private final ImageView displayPicture;

    private DialogBox(String message, Image img) {

        text = new Label(message);
        displayPicture = new ImageView(img);

        text.setWrapText(true);

        displayPicture.setFitWidth(50);
        displayPicture.setFitHeight(50);

        this.setAlignment(Pos.TOP_RIGHT);

        getChildren().addAll(text, displayPicture);
    }

    /**
     * Flips dialog for chatbot responses.
     */
    private void flip() {

        setAlignment(Pos.TOP_LEFT);

        ObservableList<Node> tmp =
                FXCollections.observableArrayList(getChildren());

        FXCollections.reverse(tmp);

        getChildren().setAll(tmp);
    }

    public static DialogBox getUserDialog(String message, Image img) {
        return new DialogBox(message, img);
    }

    public static DialogBox getTaskyDialog(String message, Image img) {
        DialogBox db = new DialogBox(message, img);
        db.flip();
        return db;
    }
}