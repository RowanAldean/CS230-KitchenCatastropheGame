package group44.game.layoutControllers;

import group44.Constants;
import group44.entities.collectableItems.Flippers;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.io.File;

/**
 * This class does the FXML injection of the widgets from the main game window
 * layout in the code. It also contains the appropriate getters and setters for
 * the widgets.
 *
 * @author Bogdan Mihai
 * @version 1.0
 */
public class MainGameWindowController {
    @FXML
    private Button homeButton;
    @FXML
    private Button restartButton;
    @FXML
    private Button resumeButton;
    @FXML
    private StackPane root;
    @FXML
    private Canvas canvas;
    @FXML
    private Label onScreenMessage;
    @FXML
    private Label timeLabel;
    @FXML
    private Label tokenAmount;
    @FXML
    private VBox menuBox;
    @FXML
    private VBox inventoryBox;
    @FXML
    private FlowPane keysInventory;
    @FXML
    private Pane movableObjects;

    public MainGameWindowController() {

    }

    @FXML
    public void initialize() {
        menuBox.setVisible(false);
        onScreenMessage.setVisible(false);
    }

    public void updateInventory(String itemURL){
        ImageView inventoryImage = new ImageView(itemURL);
        inventoryImage.setPreserveRatio(true);
        if(itemURL.contains("keys")){
            inventoryImage.setFitHeight(25);
            inventoryImage.setFitHeight(25);
            keysInventory.getChildren().add(0, inventoryImage);
        }
        else {
            inventoryImage.setFitHeight(50);
            inventoryImage.setFitHeight(50);
            inventoryBox.getChildren().add(0, inventoryImage);
        }
    }



    public Button getHomeButton() {
        return homeButton;
    }

    public void setHomeButton(Button homeButton) {
        this.homeButton = homeButton;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void setRoot(StackPane root) {
        this.root = root;
    }

    public StackPane getRoot() {
        return root;
    }

    public void setRestartButton(Button restartButton) {
        this.restartButton = restartButton;
    }

    public Button getRestartButton() {
        return restartButton;
    }

    public void setResumeButton(Button resumeButton) {
        this.resumeButton = resumeButton;
    }

    public Button getResumeButton() {
        return resumeButton;
    }

    public void setMenuBox(VBox menuBox) {
        this.menuBox = menuBox;
    }

    public VBox getMenuBox() {
        return menuBox;
    }

    public void setTimeLabel(Label timeLabel) {
        this.timeLabel = timeLabel;
    }

    public Label getTimeLabel() {
        return timeLabel;
    }

    public Label getOnScreenMessage() {
        return onScreenMessage;
    }

    public void setOnScreenMessage(Label onScreenMessage) {
        this.onScreenMessage = onScreenMessage;
    }

    public Label getTokenAmount() {
        return tokenAmount;
    }

    public void setTokenAmount(int tokenAmount) {
        this.tokenAmount.textProperty().setValue(Integer.toString(tokenAmount));
    }

    public void setMovableObjects(Pane movableObjects) {
        this.movableObjects = movableObjects;
    }

    public Pane getMovableObjects() {
        return movableObjects;
    }
}
