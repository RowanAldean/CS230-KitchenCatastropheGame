package group44.game.scenes;

import group44.controllers.LevelManager;
import group44.game.layoutControllers.LevelEditorStartupLayoutController;
import group44.models.LevelInfo;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.ArrayList;

import static group44.Constants.WINDOW_HEIGHT;
import static group44.Constants.WINDOW_WIDTH;

public class LevelEditorStartupScene {
    private LevelEditorStartupLayoutController controller;
    private Stage stage;

    public LevelEditorStartupScene(Stage stage) {
        this.stage = stage;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "/group44/game/layouts/LevelEditorStartupLayout.fxml"));

        try {
            Parent root = fxmlLoader.load();

            root.setId("root");
            Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);

            // Instantiating the controller.
            this.controller = fxmlLoader.getController();

            this.loadLevels();

            // Adding the listeners for the buttons.
            setUpButtons();

            this.stage.setScene(scene);
            this.stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
        this.stage.setTitle("Kitchen Catastrophe");
    }

    private void loadLevels() {
        this.controller.getLevels().getItems().clear();
        ArrayList<LevelInfo> levelInfos = LevelManager.load();

        for (LevelInfo levelInfo : levelInfos) {
            this.controller.getLevels().getItems().add(levelInfo);
        }
    }

    private void setUpButtons() {
        this.controller.getCreate().setOnMouseClicked(this::createButtonOnClick);
        this.controller.getEdit().setOnMouseClicked(this::editButtonOnClick);
        this.controller.getDelete()
                .setOnMouseClicked(this::deleteButtonOnClick);
        this.controller.getBack().setOnMouseClicked(this::backButtonOnClick);
    }

    private void createButtonOnClick(MouseEvent e) {
        new LevelEditorScene(this.stage);
    }

    private void editButtonOnClick(MouseEvent e) {
        LevelInfo info = this.controller.getLevels().getSelectionModel()
                .getSelectedItem();
        if (info != null) {
            new LevelEditorScene(this.stage, info.getId());
        }
    }

    private void deleteButtonOnClick(MouseEvent e) {
        LevelInfo info = this.controller.getLevels().getSelectionModel()
                .getSelectedItem();
        if (info != null) {
            LevelManager.deleteLevel(info.getId());
            this.loadLevels();
        }
    }

    private void backButtonOnClick(MouseEvent e) {
        new MainMenuScene(this.stage);
    }
}
