package group44.game.scenes;

import static group44.Constants.WINDOW_HEIGHT;
import static group44.Constants.WINDOW_WIDTH;

import java.util.ArrayList;

import group44.controllers.LevelManager;
import group44.game.layoutControllers.LevelEditorStartupLayoutController;
import group44.models.LevelInfo;
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

            this.initialise();

            // Adding the listeners for the buttons.
            setUpButtons();

            this.stage.setScene(scene);
            this.stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
        this.stage.setTitle("Kitchen Catastrophe");
    }

    private void initialise() {
        ArrayList<LevelInfo> levelInfos = LevelManager.load();

        for (LevelInfo levelInfo : levelInfos) {
            this.controller.getLevels().getItems().add(levelInfo);
        }
    }

    private void setUpButtons() {
        Button createButton = this.controller.getCreate();
        createButton.setOnMouseClicked(this::createButtonOnClick);
        this.controller.getEdit().setOnMouseClicked(this::editButtonOnClick);
        this.controller.getDelete().setOnMouseClicked(this::deleteButtonOnClick);
    }

    private void createButtonOnClick(MouseEvent e) {
        LevelInfo info = this.controller.getLevels().getSelectionModel().getSelectedItem();
        if (info != null) {
            System.out.println("Create button clicked\n" + info);
        }
    }

    private void editButtonOnClick(MouseEvent e) {
        LevelInfo info = this.controller.getLevels().getSelectionModel().getSelectedItem();
        if (info != null) {
            System.out.println("Edit button clicked\n" + info);
        }
    }

    private void deleteButtonOnClick(MouseEvent e) {
        LevelInfo info = this.controller.getLevels().getSelectionModel().getSelectedItem();
        if (info != null) {
            System.out.println("Delete button clicked\n" + info);
        }
    }
}
