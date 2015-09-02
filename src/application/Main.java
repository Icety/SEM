package application;

import java.io.File;
import java.io.IOException;

import application.core.Game;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;



public class Main extends Application {
    public static Stage primaryStage;
    public static BorderPane pane;
    public static Game game;
    protected static int tWidth = 512;
    protected static int tHeight = 512;


    @Override
    public void start(Stage primaryStage) {
        Main.primaryStage = primaryStage;
        Main.primaryStage.setTitle("Super Awesome Cool Fun Explosive Space Invaders");
        Main.primaryStage.setFullScreen(true);
        Main.primaryStage.setFullScreenExitHint("");
        Main.primaryStage.show();
        Main.primaryStage.setResizable(false);

        loadScene("main");

        Main.game = new Game();
    }

    /**
     * Method which loads a new scene
     */
    public static void loadScene(String fxml) {

        try {
            // Load new screen
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("templates/" + fxml + ".fxml"));
            BorderPane pane = null;
            pane = loader.load();

            //Set the id of the pane to be used by the css
            pane.setId("pane");
            pane.setPrefWidth(Main.tWidth);
            pane.setPrefHeight(Main.tHeight);

            // Load Style
            File f = new File("src/application/style.css");
            pane.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));

            if (Main.primaryStage.getScene() == null) {
                Main.primaryStage.setScene(new Scene(pane));
            }
            else {
                Main.primaryStage.getScene().setRoot(pane);
            }
            Main.primaryStage.show();
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
