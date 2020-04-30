package group44;

import group44.game.scenes.MainMenuScene;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * This class just redirects the player to the main menu stage.
 *
 * @author Mihai
 *
 */
public class Main extends Application {
    /**
     * This method instantiates the MainMenuScene class.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        MainMenuScene mainMenuStage = new MainMenuScene(primaryStage);

        //Ensure the program is terminated on window close.
        primaryStage.setOnCloseRequest(t -> {
            //Shutsdown JavaFx Toolkit and this makes launch return thus ending program.
            //System.exit terminates the entire JVM.
            Platform.exit();
            System.exit(0);
        });
    }

    /**
     * This method starts the program.
     *
     * @param args
     *            These are the launch arguments of the program.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
