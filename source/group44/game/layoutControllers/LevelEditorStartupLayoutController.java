package group44.game.layoutControllers;

import group44.controllers.LevelManager;
import group44.controllers.parsers.LevelLoader;
import group44.exceptions.CollisionException;
import group44.exceptions.ParsingException;
import group44.models.LevelInfo;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class LevelEditorStartupLayoutController {
    @FXML
    private ListView<LevelInfo> levels;
    @FXML
    private Button create;
    @FXML
    private Button edit;
    @FXML
    private Button delete;
    @FXML
    private Button back;

    public ListView<LevelInfo> getLevels() {
        return levels;
    }

    public Button getCreate() {
        return create;
    }

    public Button getEdit() {
        return edit;
    }

    public Button getDelete() {
        return delete;
    }

    public Button getBack() {
        return back;
    }

}
