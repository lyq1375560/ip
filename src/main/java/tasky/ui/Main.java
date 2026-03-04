package tasky.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tasky.Tasky;

import java.io.IOException;

/**
 * Main GUI entry point.
 */
public class Main extends Application {

    private final Tasky tasky = new Tasky("data", "data/tasky.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    Main.class.getResource("/view/MainWindow.fxml")
            );
            
            Parent root = loader.load();

            Scene scene = new Scene(root);

            stage.setTitle("Tasky");
            stage.setScene(scene);

            loader.<MainWindow>getController().setTasky(tasky);

            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}