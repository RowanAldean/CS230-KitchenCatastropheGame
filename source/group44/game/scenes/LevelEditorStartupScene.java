package group44.game.scenes;

import static group44.Constants.WINDOW_HEIGHT;
import static group44.Constants.WINDOW_WIDTH;

import group44.controllers.Leaderboard;
import group44.controllers.LevelManager;
import group44.game.layoutControllers.LevelEditorStartupLayoutController;
import group44.game.layoutControllers.LevelSelectorController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class LevelEditorStartupScene {
    private LevelEditorStartupLayoutController controller;
    private Stage stage;

    public LevelEditorStartupScene(Stage stage){
        this.stage = stage;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass()
                .getResource("/group44/game/layouts/LevelEditorStartupLayout.fxml"));

        try {
            Parent root = fxmlLoader.load();

            root.setId("root");
            Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);

            // Instantiating the controller.
            this.controller = fxmlLoader.getController();

            Leaderboard.load();
            LevelManager.load();

            // Adding the listeners for the buttons.
            setUpButtons();

            this.stage.setScene(scene);
            this.stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
        this.stage.setTitle("Kitchen Catastrophe");
    }

    private void setUpButtons() {
        Button createButton = this.controller.getCreate();
        createButton.setOnMouseClicked(this::createButtonOnClick);
        this.controller.getEdit().setOnMouseClicked(this::editButtonOnClick);
        this.controller.getDelete().setOnMouseClicked(this::deleteButtonOnClick);
    }

    private void createButtonOnClick(MouseEvent e) {
        System.out.println("Create button clicked");
    }

    private void editButtonOnClick(MouseEvent e) {
        System.out.println("Edit button clicked");
    }

    private void deleteButtonOnClick(MouseEvent e) {
        System.out.println("Delete button clicked");
    }
}
