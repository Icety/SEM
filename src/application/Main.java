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
            primaryStage.setFullScreen(true);
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



    /**
     * Method which loads a new fxml file
     * @param sceneFile - String that represents the filename of the fxml file to be used
     * @param styleFile - String that represents the filename of the style file to be used
     */
    public static void loadScreen(String sceneFile, String styleFile){
        try{
            // Load new screen
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource(sceneFile));
            Main.pane = (BorderPane) loader.load();

            //Set the id of the pane to be used by the css
            pane.setId("pane");

            // Show the scene
            // Scene newScene = new Scene(Main.rootLayout);
            Main.primaryStage.getScene().setRoot(Main.pane);
            Main.primaryStage.show();

            // Load Style
            File f = new File("src/application/" + styleFile);
            Main.pane.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
