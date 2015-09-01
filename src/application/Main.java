package application;

import java.io.File;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
    public static Stage primaryStage;
    public static BorderPane pane;


    @Override
    public void start(Stage primaryStage) {
        Main.primaryStage = primaryStage;
        Main.primaryStage.setTitle("Super Awesome Cool Fun Explosive Space Invaders");

        initRootLayout();
    }

    /**
     * Methode waarmee de root layout wordt ingeladen vanuit een FXML file, samen met een CSS stylesheet
     * Scene wordt in de primary stage geladen.
     */
    public void initRootLayout() {

        try {
            FXMLLoader loader = new FXMLLoader();
            pane = FXMLLoader.load(getClass().getResource("main.fxml"));

            //Set het Id van de pane voor de style
            pane.setId("pane");

            // Show the scene
            Scene scene = new Scene(pane, 800, 600);
            primaryStage.setScene(scene);
            //primaryStage.setFullScreen(true);
            primaryStage.setFullScreenExitHint("");
            primaryStage.show();
            primaryStage.setResizable(false);

            //Load Style
            File f = new File("src/application/style.css");
            scene.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
        } catch(Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
