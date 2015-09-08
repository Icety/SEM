package application;

import java.io.File;
import java.io.IOException;

import application.core.Game;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;



public class OldMain extends Application {
    public static Stage primaryStage;
    public static BorderPane pane;
    public static Game game;
    protected static int tWidth = 512;
    protected static int tHeight = 512;


    @Override
    public void start(Stage primaryStage) {
        OldMain.primaryStage = primaryStage;
        OldMain.primaryStage.setTitle("Super Awesome Cool Fun Explosive Space Invaders");
        OldMain.primaryStage.setFullScreen(true);
        OldMain.primaryStage.setFullScreenExitHint("");
        OldMain.primaryStage.show();
        OldMain.primaryStage.setResizable(false);

        loadScene("main");

        OldMain.game = new Game();
    }

    /**
     * Method which loads a new scene
     */
    public static void loadScene(String fxml) {

        try {
            // Load new screen
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(OldMain.class.getResource("templates/" + fxml + ".fxml"));
            BorderPane pane = null;
            pane = loader.load();

            //Set the id of the pane to be used by the css
            pane.setId("pane");
            pane.setPrefWidth(OldMain.tWidth);
            pane.setPrefHeight(OldMain.tHeight);

            // Load Style
            File f = new File("src/application/style.css");
            pane.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));

            if (OldMain.primaryStage.getScene() == null) {
                OldMain.primaryStage.setScene(new Scene(pane));
            }
            else {
                OldMain.primaryStage.getScene().setRoot(pane);
            }
            OldMain.primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static int getWidth() {
        return tWidth;
    }

    public static int getHeight() {
        return tHeight;
    }
}
