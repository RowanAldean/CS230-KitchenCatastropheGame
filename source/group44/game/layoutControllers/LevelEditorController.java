
package group44.game.layoutControllers;

import group44.entities.LevelObject;
import group44.Constants;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.*;

public class LevelEditorController {
    @FXML
    private ScrollPane dragPanel;
    private GridPane boardGame;
    @FXML
    private BorderPane root;
    @FXML
    private Button height;
    @FXML
    private TextField heightInput;
    @FXML
    private Button width;
    @FXML
    private TextField widthInput;
    @FXML
    private VBox container;
    @FXML
    private ScrollPane boardScroll;
    @FXML
    private Button confirm;
    private int numRows = 5;
    private int numColumns = 5;
    //Every time objects are dragged onto the board, also add them here because when the grid changes, they need to be redrawn.
    private LevelObject[] levelObjects;
    @FXML
    private GridPane propertiesGridPane;

    
    @FXML
    public void initialize() {
//        createBoardGame(5,5);
        confirm.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                createBoardGame(Integer.parseInt(widthInput.getText()),Integer.parseInt(heightInput.getText()));
            }
        });

        height.setMaxHeight(30);
        height.setMaxWidth(100);
        heightInput.setMaxSize(100, 30);
        heightInput.setText("5");
        width.setMaxHeight(30);
        width.setMaxWidth(100);
        widthInput.setMaxSize(100, 30);
        widthInput.setText("5");

    }
    public void createBoardGame(int width,int height){
        //TODO: The drag and drop need to be rebinded somehow
        numColumns=width;
        numRows=height;
        boardGame = new GridPane();
        boardGame.setVgap(0);
        boardGame.setHgap(0);
        boardGame.setGridLinesVisible(true);
        for(int i=0;i<height;++i){
            for(int j=0;j<width;++j){
                if(i==0||i==height-1){
                    boardGame.add(createWall(),j,i);
                }else if(j==0||j==width-1){
                    boardGame.add(createWall(),j,i);
                }else{
                    boardGame.add(createFloor(),j,i);
                }
            }
        }
        boardScroll.setContent(boardGame);
        boardGame.setOnDragOver(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                if (db.hasImage()) {
                    event.acceptTransferModes(TransferMode.COPY);
                }
                event.consume();
            }
        });
        boardGame.setOnDragDropped(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                boolean success = false;
                Image image = db.getImage();
                //Here you create a node so that you can see where your node drops, but that is what i know, it might not be that
                Node node = event.getPickResult().getIntersectedNode();
                if (db.hasImage()) {
                    Integer cIndex = GridPane.getColumnIndex(node);
                    Integer rIndex = GridPane.getRowIndex(node);
                    int x = cIndex == null ? 0 : cIndex;
                    int y = rIndex == null ? 0 : rIndex;
                    setBoardCell(image, x, y);
                    System.out.println("Dropped: " + db.getImage().toString());
                    success = true;
                }
                event.setDropCompleted(success);
                event.consume();
                //What I have here is a basic template for drag and drop and you might need to look into how drag and drop works
            }
        });

    }
    public GridPane getPropertiesGridPane() {
        return propertiesGridPane;
    }

    public void setPropertiesGridPane(GridPane propertiesGridPane) {
        this.propertiesGridPane = propertiesGridPane;
    }
    private ImageView createFloor() {
        ImageView floor = new ImageView(new Image("group44/resources/cells/floor.png"));
        floor.setFitHeight(Constants.GRID_CELL_HEIGHT);
        floor.setFitWidth(Constants.GRID_CELL_WIDTH);
        floor.setPreserveRatio(true);
        return floor;
    }

    private ImageView createWall() {
        ImageView wall = new ImageView(new Image("group44/resources/WallCounter.png"));
        wall.setPreserveRatio(true);
        wall.setFitHeight(Constants.GRID_CELL_HEIGHT);
        wall.setFitWidth(Constants.GRID_CELL_WIDTH);
        return wall;
    }

    public BorderPane getRoot() {
        return root;
    }

    public GridPane getBoardGame() {
        return boardGame;
    }

    public void setBoardCell(Image icon, int x , int y){
        ImageView oldObject = (ImageView) getNodeFromGridPane(boardGame, x, y);
        System.out.println(oldObject.getImage().impl_getUrl());
        oldObject.setImage(icon);
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

    public VBox getContainer() {
        return container;
    }

    private ImageView getNodeFromGridPane(GridPane gridPane, int col, int row) {
        Node result = null;
        ObservableList<Node> children = gridPane.getChildren();

        for (int i = 0; i < children.size(); ++i) {
            int nodeRow;
            int nodeCol;
            Node node = children.get(i);
            if (GridPane.getRowIndex(node) == null) {
                nodeRow = 0;
            } else {
                nodeRow = GridPane.getRowIndex(node);
            }
            if (GridPane.getColumnIndex(node) == null) {
                nodeCol = 0;
            } else {
                nodeCol = GridPane.getColumnIndex(node);
            }
            if (nodeRow == row && nodeCol == col) {
                result = node;
                break;
            }
        }

        return (ImageView) result;
    }

    public Button getConfirm() {
        return confirm;
    }

}