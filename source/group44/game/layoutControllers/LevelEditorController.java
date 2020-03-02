package group44.game.layoutControllers;

import group44.Constants;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class LevelEditorController {
    @FXML
    private BorderPane root;
    @FXML
    private Button confirm;
    @FXML
    private Button height;
    @FXML
    private Button width;
    @FXML
    private GridPane propertiesGridPane;
    @FXML
    private ScrollPane boardScroll;
    @FXML
    private ScrollPane dragPanel;
    @FXML
    private TextField heightInput;
    @FXML
    private TextField widthInput;
    @FXML
    private VBox leftControlBox;
    @FXML
    private GridPane container;
    @FXML
    private Button save;

    private GridPane boardGame;

    @FXML
    public void initialize() {
        this.heightInput.setText(Integer.toString(Constants.LEVEL_DISPLAY_SIZE));
        this.widthInput.setText(Integer.toString(Constants.LEVEL_DISPLAY_SIZE));
    }

    public BorderPane getRoot() {
        return root;
    }

    public Button getConfirm() {
        return confirm;
    }

    public Button getHeight() {
        return height;
    }

    public Button getWidth() {
        return width;
    }

    public GridPane getPropertiesGridPane() {
        return propertiesGridPane;
    }

    public ScrollPane getBoardScroll() {
        return boardScroll;
    }

    public ScrollPane getDragPanel() {
        return dragPanel;
    }

    public TextField getHeightInput() {
        return heightInput;
    }

    public TextField getWidthInput() {
        return widthInput;
    }

    public VBox getLeftControlBox() {
        return leftControlBox;
    }

    public Button getSave() {
        return save;
    }

    public GridPane getContainer() {
        return container;
    }

    public GridPane getBoardGame() {
        return boardGame;
    }

    public void setBoardGame(GridPane boardGame) {
        this.boardGame = boardGame;
    }


}
