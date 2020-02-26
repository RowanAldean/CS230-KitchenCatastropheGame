package group44.game.layoutControllers;

import group44.models.LevelInfo;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class LevelEditorStartupLayoutController {
    @FXML
    private ListView<LevelInfo> levels;
    @FXML
    private Button create;
    @FXML
    private Button edit;
    @FXML
    private Button delete;

    public ListView<LevelInfo> getLevels() {
        return levels;
    }
    public void setLevels(ListView<LevelInfo> levels) {
        this.levels = levels;
    }
    public Button getCreate() {
        return create;
    }
    public void setCreate(Button create) {
        this.create = create;
    }
    public Button getEdit() {
        return edit;
    }
    public void setEdit(Button edit) {
        this.edit = edit;
    }
    public Button getDelete() {
        return delete;
    }
    public void setDelete(Button delete) {
        this.delete = delete;
    }
}
