package group44.game.scenes;


import group44.Constants;
import group44.entities.LevelObject;
import group44.game.layoutControllers.LevelEditorController;
import javafx.beans.property.StringProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import static group44.Constants.WINDOW_HEIGHT;
import static group44.Constants.WINDOW_WIDTH;

import java.io.File;

public class LevelEditorScene {

    private LevelObject[] levelObject;
    // The controller associated with the specific fxml file.
    private LevelEditorController myController;
    // The window itself.
    private Stage primaryStage;
    // Current level displayed.
    private int height = 5;
    private int width = 5;

    /**
     * This is the main method that loads everything required to draw the scene.
     *
     * @param primaryStage represents the window where the stages are displayed.
     */
    public LevelEditorScene(Stage primaryStage) {
        this.primaryStage = primaryStage;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass()
                .getResource("/group44/game/layouts/levelEditor.fxml"));
        try {
            Parent root = fxmlLoader.load();
            // Setting the stage and adding my custom style to it.
            root.getStylesheets().add("group44/resources/application.css");
            root.setId("root");
            Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
            // Loading the controller
            LevelEditorController tempController = fxmlLoader
                    .getController();
            myController = tempController;
            primaryStage.setScene(scene);
            primaryStage.show();
            myController.getHeightInput().setText(String.valueOf(height));
            myController.getWidthInput().setText(String.valueOf(width));
            myController.getHeightInput().textProperty().addListener(
                    (observable, oldValue, newValue) -> {
                        // Your validation rules, anything you like
                        // (! note 1 !) make sure that empty string (newValue.equals(""))
                        //   or initial text is always valid
                        //   to prevent inifinity cycle
                        // do whatever you want with newValue
                        if (!newValue.matches("\\d*")) {
                            ((StringProperty) observable).setValue(oldValue.toUpperCase());
                        } else if (oldValue.equals("")) {
                            ((StringProperty) observable).setValue(newValue.toUpperCase());
                        } else if (newValue.equals("")) {
                            ((StringProperty) observable).setValue("0");
                            myController.getConfirm().setVisible(false);
                        } else if (newValue.equals("00")) {
                            ((StringProperty) observable).setValue(oldValue.toUpperCase());
                            myController.getConfirm().setVisible(false);
                        } else if (oldValue.equals("0")) {
                            ((StringProperty) observable).setValue(Character.toString(newValue.charAt(1)));
                        } else if (Integer.parseInt(newValue) > 25) {
                            ((StringProperty) observable).setValue("25");
                            myController.getConfirm().setVisible(false);

                        } else {
                            if (!(Integer.parseInt(newValue) == width)) {
                                if (Integer.parseInt(newValue) >= 5) {
                                    height = Integer.parseInt(newValue);
                                    myController.getConfirm().setVisible(true);
                                }else{
                                    myController.getConfirm().setVisible(false);
                                }
                            }
                            ((StringProperty) observable).setValue(newValue.toUpperCase());
                        }
                        // If newValue is not valid for your rules
                        // (! note 2 !) do not bind textProperty (textProperty().bind(someProperty))
                        //   to anything in your code.  TextProperty implementation
                        //   of StringProperty in TextFieldControl
                        //   will throw RuntimeException in this case on setValue(string) call.
                        //   Or catch and handle this exception.

                        // If you want to change something in text
                        // When it is valid for you with some changes that can be automated.
                        // For example change it to upper case
                    }
            );
            myController.getWidthInput().textProperty().addListener(
                    (observable, oldValue, newValue) -> {
                        // Your validation rules, anything you like
                        // (! note 1 !) make sure that empty string (newValue.equals(""))
                        //   or initial text is always valid
                        //   to prevent inifinity cycle
                        // do whatever you want with newValue
                        if (!newValue.matches("\\d*")) {
                            ((StringProperty) observable).setValue(oldValue.toUpperCase());
                        } else if (oldValue.equals("")) {
                            ((StringProperty) observable).setValue(newValue.toUpperCase());
                        } else if (newValue.equals("")) {
                            ((StringProperty) observable).setValue("0");
                            myController.getConfirm().setVisible(false);
                        } else if (newValue.equals("00")) {
                            ((StringProperty) observable).setValue(oldValue.toUpperCase());
                            myController.getConfirm().setVisible(false);
                        } else if (oldValue.equals("0")) {
                            ((StringProperty) observable).setValue(Character.toString(newValue.charAt(1)));
                        } else if (Integer.parseInt(newValue) > 25) {
                            ((StringProperty) observable).setValue("25");
                            myController.getConfirm().setVisible(false);

                        } else {
                            if (!(Integer.parseInt(newValue) == width)) {
                                if (Integer.parseInt(newValue) >= 5) {
                                    width = Integer.parseInt(newValue);
                                    myController.getConfirm().setVisible(true);
                                }else{
                                    myController.getConfirm().setVisible(false);
                                }
                            }
                            ((StringProperty) observable).setValue(newValue.toUpperCase());
                        }
                        // If newValue is not valid for your rules
                        // (! note 2 !) do not bind textProperty (textProperty().bind(someProperty))
                        //   to anything in your code.  TextProperty implementation
                        //   of StringProperty in TextFieldControl
                        //   will throw RuntimeException in this case on setValue(string) call.
                        //   Or catch and handle this exception.

                        // If you want to change something in text
                        // When it is valid for you with some changes that can be automated.
                        // For example change it to upper case
                    }
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        initializeNodes();
        primaryStage.setTitle("Kitchen Catastrophe");
    }

    public void initializeNodes() {
        ImageView fire = new ImageView(new Image(new File(Constants.FIRE_PATH).toURI().toString()));
        fire.setFitWidth(40);
        fire.setFitHeight(40);
        ImageView water = new ImageView(new Image(new File(Constants.WATER_PATH).toURI().toString()));
        water.setFitHeight(40);
        water.setFitWidth(40);
        myController.getContainer().getChildren().add(fire);
        myController.getContainer().getChildren().add(water);

        //This is a listener that sets the behaviour for the test object regarding the action of dragging
        fire.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //The dragboard keeps the information about the drag so that you can pass it later
                //to the other events
                //Transfermode means what you will do with the object, and from what i know
                //it can be copy move and some more but we will focus on copy
                Dragboard db = fire.startDragAndDrop(TransferMode.COPY);
                //It will drag the image along
                db.setDragView(fire.getImage());
                ClipboardContent content = new ClipboardContent();
                content.putImage(new Image(new File(Constants.FIRE_PATH).toURI().toString()));
                db.setContent(content);
                event.consume();
            }
        });

        //This is a listener that sets the behaviour for the test object regarding the action of dragging
        water.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //The dragboard keeps the information about the drag so that you can pass it later
                //to the other events
                //Transfermode means what you will do with the object, and from what i know
                //it can be copy move and some more but we will focus on copy
                Dragboard db = water.startDragAndDrop(TransferMode.COPY);
                //It will drag the image along
                db.setDragView(water.getImage());
                ClipboardContent content = new ClipboardContent();
                content.putImage(new Image(new File(Constants.WATER_PATH).toURI().toString()));
                db.setContent(content);
                event.consume();
            }
        });
    }
}
