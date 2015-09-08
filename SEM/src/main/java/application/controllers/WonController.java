package application.controllers;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Created by Thomas on 01-09-15.
 */
public class WonController {

    @FXML
    private Button newGameButton;		// Start new game

    @FXML
    private Button exitButton;			// Quit the game

    @FXML
    private void initialize() {

        newGameButton.setOnAction((event) -> {
            Main.game.newGame();
        });

        exitButton.setOnAction((event) -> {
            System.exit(0);
        });

    }
}
