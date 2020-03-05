package group44.game.scenes;

import group44.Constants;
import group44.controllers.ILevelEditor;
import group44.controllers.IPropertyController;
import group44.controllers.LevelEditor;
import group44.controllers.PropertyController;
import group44.entities.LevelObject;
import group44.entities.cells.*;
import group44.entities.collectableItems.CollectableItem;
import group44.entities.collectableItems.FireBoots;
import group44.entities.collectableItems.Flippers;
import group44.entities.collectableItems.Key;
import group44.entities.collectableItems.Key.KeyType;
import group44.entities.collectableItems.Token;
import group44.entities.movableObjects.*;
import group44.exceptions.CollisionException;
import group44.exceptions.ParsingException;
import group44.exceptions.PositionTakenException;
import group44.game.Level;
import group44.game.layoutControllers.LevelEditorController;
import group44.models.LevelObjectImage;
import group44.models.PropertyInfo;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import static group44.Constants.WINDOW_HEIGHT;
import static group44.Constants.WINDOW_WIDTH;

/**
 * The scene for {@link LevelEditor}.
 *
 * @author Tomas Svejnoha, Bogdan Mihai, Rowan Aldean
 * @version 1.0
 */
public class LevelEditorScene {
    private static final int EDITOR_LEVEL_OBJECT_WIDTH = 40;

    // TODO: Add error messages
    private static final String ERROR_FILE_NOT_FOUND_EXCEPTION_MESSAGE = "";
    private static final String ERROR_ILLEGAL_ARGUMENT_EXCEPTION_MESSAGE = "";
    private static final String ERROR_COLLISION_EXCEPTION_MESSAGE = "";
    private static final String ERROR_PARSING_EXCEPTION_MESSAGE = "";

    private static final String ERROR_SAVING_FAILED_TITLE = "Level Editor";
    private static final String ERROR_SAVING_FAILED_HEADER = "Saving failed";


    /**
     * The controller associated with the scene.
     */
    private LevelEditorController controller;
    private Stage primaryStage;

    private ILevelEditor levelEditor;
    private LevelObjectImage draggedImage;

    private IPropertyController propertyController;

    // Current level displayed.
    private Integer height = Constants.LEVEL_DISPLAY_SIZE;
    private Integer width = Constants.LEVEL_DISPLAY_SIZE;

    /**
     * Creates a new {@link LevelEditorScene} to create new {@link Level}.
     *
     * @param primaryStage
     *            represents the window where the stages are displayed.
     */
    public LevelEditorScene(Stage primaryStage) {
        this.primaryStage = primaryStage;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass()
                .getResource("/group44/game/layouts/LevelEditor.fxml"));
        try {
            Parent root = fxmlLoader.load();

            root.getStylesheets().add("group44/resources/application.css");
            root.setId("root");
            Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);

            this.controller = fxmlLoader.getController();

            this.controller.getHeightInput().setText(String.valueOf(height));
            this.controller.getWidthInput().setText(String.valueOf(width));

            this.propertyController = new PropertyController();

            this.primaryStage.setScene(scene);
            this.primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
        this.initializeGUI();
        this.primaryStage.setTitle("Kitchen Catastrophe");
    }

    /**
     * Creates a new {@link LevelEditorScene} to edit existing {@link Level}.
     *
     * @param primaryStage
     *            the stage to display window.
     * @param levelId
     *            an Id of a {@link Level} to edit.
     */
    public LevelEditorScene(Stage primaryStage, int levelId) {
        this(primaryStage);
        try {
            this.levelEditor = new LevelEditor(levelId);
            this.controller.getWidthInput()
                    .setText(Integer.toString(this.levelEditor.getGridWidth()));
            this.controller.getHeightInput().setText(
                    Integer.toString(this.levelEditor.getGridHeight()));
            this.disableUserResizeInput();
            this.controller.getSave().setDisable(false);
            this.displayLevel();
            this.registerEventHandlersForGridPane();
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        } catch (CollisionException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        } catch (ParsingException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Initialises GUI.
     */
    private void initializeGUI() {
        this.initialiseGUIDragAndDrop();

        this.registerEventHandlers();
    }

    /**
     * Adds drag & drop objects to the GUI.
     */
    private void initialiseGUIDragAndDrop() {
        // TODO: Add more Cells
        LevelObjectImage fireImage = new LevelObjectImage(
                new File(Constants.FIRE_PATH).toURI().toString(),
                Fire.class.getName());
        LevelObjectImage waterImage = new LevelObjectImage(
                new File(Constants.WATER_PATH).toURI().toString(),
                Water.class.getName());
        LevelObjectImage wallImage = new LevelObjectImage(
                new File(Constants.WALL_PATH).toURI().toString(),
                Wall.class.getName());
        LevelObjectImage groundImage = new LevelObjectImage(
                new File(Constants.GROUND_PATH).toURI().toString(),
                Ground.class.getName());
        LevelObjectImage goalImage = new LevelObjectImage(
                new File(Constants.GOAL_PATH).toURI().toString(),
                Goal.class.getName());
        LevelObjectImage teleporterImage = new LevelObjectImage(
                new File(Constants.TELEPORTER_PATH).toURI().toString(),
                Teleporter.class.getName());
        LevelObjectImage fireBootsImage = new LevelObjectImage(
                new File(Constants.LEVEL_EDITOR_FIRE_BOOTS_PATH).toURI().toString(),
                FireBoots.class.getName());
        LevelObjectImage flippersImage = new LevelObjectImage(
                new File(Constants.LEVEL_EDITOR_FLIPPERS_PATH).toURI().toString(),
                Flippers.class.getName());
        LevelObjectImage tokenImage = new LevelObjectImage(
                new File(Constants.LEVEL_EDITOR_TOKEN_PATH).toURI().toString(),
                Token.class.getName());
        LevelObjectImage keyImage = new LevelObjectImage(
                new File(Constants.LEVEL_EDITOR_KEY).toURI().toString(),
                Key.class.getName());
        LevelObjectImage closedKeyDoorImage = new LevelObjectImage(
                new File(String.format(Constants.CLOSED_KEY_DOOR_PATH, KeyType.RED.name())).toURI().toString(),
                KeyDoor.class.getName());
        LevelObjectImage closedTokenDoorImage = new LevelObjectImage(
                new File(Constants.CLOSED_TOKEN_DOOR_PATH).toURI().toString(),
                TokenDoor.class.getName());
        LevelObjectImage playerImage = new LevelObjectImage(
                new File(Constants.LEVEL_EDITOR_PLAYER_PATH).toURI().toString(),
                Player.class.getName());
        LevelObjectImage dumbEnemeyImage = new LevelObjectImage(
                new File(Constants.LEVEL_EDITOR_DUMB_TARGETING_ENEMY_PATH).toURI()
                        .toString(),
                DumbTargetingEnemy.class.getName());
        LevelObjectImage smartEnemyImage = new LevelObjectImage(
                new File(Constants.LEVEL_EDITOR_SMART_TARGETING_ENEMY_PATH).toURI()
                        .toString(),
                SmartTargetingEnemy.class.getName());
        LevelObjectImage straightEnemyImage = new LevelObjectImage(
                new File(Constants.LEVEL_EDITOR_STRAIGHT_WALKING_ENEMY_PATH).toURI()
                        .toString(),
                StraightWalkingEnemy.class.getName());
        LevelObjectImage wallEnemyImage = new LevelObjectImage(
                new File(Constants.LEVEL_EDITOR_WALL_FOLLOWING_ENEMY_PATH).toURI()
                        .toString(),
                WallFollowingEnemy.class.getName());
        ImageView fire = createImageViewForLevelObjectImage(fireImage);
        ImageView water = createImageViewForLevelObjectImage(waterImage);
        ImageView wall = createImageViewForLevelObjectImage(wallImage);
        ImageView ground = createImageViewForLevelObjectImage(groundImage);
        ImageView goal = createImageViewForLevelObjectImage(goalImage);
        ImageView teleporter = createImageViewForLevelObjectImage(
                teleporterImage);
        ImageView fireBoots = createImageViewForLevelObjectImage(
                fireBootsImage);
        ImageView flippers = createImageViewForLevelObjectImage(flippersImage);
        ImageView token = createImageViewForLevelObjectImage(tokenImage);
        ImageView key = createImageViewForLevelObjectImage(keyImage);
        ImageView closedKeyDoor = createImageViewForLevelObjectImage(
                closedKeyDoorImage);
        ImageView closedTokenDoor = createImageViewForLevelObjectImage(
                closedTokenDoorImage);
        ImageView player = createImageViewForLevelObjectImage(playerImage);
        ImageView dumbTargetingEnemy = createImageViewForLevelObjectImage(
                dumbEnemeyImage);
        ImageView smartTargetingEnemy = createImageViewForLevelObjectImage(
                smartEnemyImage);
        ImageView straightTargetingEnemy = createImageViewForLevelObjectImage(
                straightEnemyImage);
        ImageView wallTargetingEnemy = createImageViewForLevelObjectImage(
                wallEnemyImage);

        this.controller.getContainer().add(fire, 0, 0);
        this.controller.getContainer().add(water, 1, 0);
        this.controller.getContainer().add(wall, 0, 1);
        this.controller.getContainer().add(ground, 1, 1);
        this.controller.getContainer().add(goal, 0, 2);
        this.controller.getContainer().add(teleporter, 1, 2);
        this.controller.getContainer().add(fireBoots, 0, 3);
        this.controller.getContainer().add(flippers, 1, 3);
        this.controller.getContainer().add(token, 0, 4);
        this.controller.getContainer().add(key, 1, 4);
        this.controller.getContainer().add(closedKeyDoor, 0, 5); //HERE
        this.controller.getContainer().add(closedTokenDoor, 1, 5);
        this.controller.getContainer().add(player, 0, 6);
        this.controller.getContainer().add(dumbTargetingEnemy, 1, 6);
        this.controller.getContainer().add(smartTargetingEnemy, 0, 7);
        this.controller.getContainer().add(straightTargetingEnemy, 1, 7);
        this.controller.getContainer().add(wallTargetingEnemy, 0, 8);

        this.registerEventHandlerForImageView(fire);
        this.registerEventHandlerForImageView(water);
        this.registerEventHandlerForImageView(wall);
        this.registerEventHandlerForImageView(ground);
        this.registerEventHandlerForImageView(goal);
        this.registerEventHandlerForImageView(teleporter);
        this.registerEventHandlerForImageView(fireBoots);
        this.registerEventHandlerForImageView(flippers);
        this.registerEventHandlerForImageView(token);
        this.registerEventHandlerForImageView(key);
        this.registerEventHandlerForImageView(closedKeyDoor);
        this.registerEventHandlerForImageView(closedTokenDoor);
        this.registerEventHandlerForImageView(player);
        this.registerEventHandlerForImageView(dumbTargetingEnemy);
        this.registerEventHandlerForImageView(smartTargetingEnemy);
        this.registerEventHandlerForImageView(straightTargetingEnemy);
        this.registerEventHandlerForImageView(wallTargetingEnemy);
    }

    /**
     * Creates an {@link ImageView} for specified {@link LevelObjectImage}.
     *
     * @param image
     *            the {@link LevelObjectImage}.
     * @return the created {@link ImageView}.
     */
    private ImageView createImageViewForLevelObjectImage(
            LevelObjectImage image) {
        ImageView view = new ImageView(image);
        view.setFitHeight(EDITOR_LEVEL_OBJECT_WIDTH);
        view.setFitWidth(EDITOR_LEVEL_OBJECT_WIDTH);

        return view;
    }

    /**
     * Creates new GridPane and displays edited {@link Level} inside it.
     */
    private void displayLevel() {
        this.controller.setBoardGame(new GridPane());
        this.controller.getBoardGame().setVgap(0);
        this.controller.getBoardGame().setHgap(0);
        this.controller.getBoardGame().setGridLinesVisible(true);

        // Fill level with default cells
        for (int x = 0; x < this.levelEditor.getGridWidth(); ++x) {
            for (int y = 0; y < this.levelEditor.getGridHeight(); ++y) {
                Cell cell = this.levelEditor.get(x, y);

                Canvas canvas = new Canvas(Constants.GRID_CELL_WIDTH,
                        Constants.GRID_CELL_HEIGHT);
                GraphicsContext gc = canvas.getGraphicsContext2D();
                this.registerEventHandlerForCanvas(canvas);

                cell.draw(gc, 0, 0, Constants.GRID_CELL_WIDTH,
                        Constants.GRID_CELL_HEIGHT);

                this.controller.getBoardGame().add(canvas, x, y);
            }
        }

        this.controller.getBoardScroll()
                .setContent(this.controller.getBoardGame());
    }

    /**
     * Registers event handlers.
     */
    private void registerEventHandlers() {
        this.controller.getConfirm().setOnMouseClicked(event -> {
            int width = Integer.parseInt(controller.getWidthInput().getText());
            int height = Integer
                    .parseInt(controller.getHeightInput().getText());

            createLevelEditor(width, height);
            displayLevel();
            registerEventHandlersForGridPane();
            disableUserResizeInput();
            controller.getSave().setDisable(false);
        });

        // Validating user input - level dimension
        this.controller.getHeightInput().textProperty()
                .addListener((observable, oldValue,
                        newValue) -> validateLevelDimension(observable,
                                oldValue, newValue, height));
        this.controller.getWidthInput().textProperty()
                .addListener((observable, oldValue,
                        newValue) -> validateLevelDimension(observable,
                                oldValue, newValue, width));
        this.controller.getSave().setOnMouseClicked(event -> {
            try {
                levelEditor.save();
            } catch (IOException e) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle(ERROR_SAVING_FAILED_TITLE);
                alert.setHeaderText(ERROR_SAVING_FAILED_HEADER);
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        });
    }

    /**
     * Disables user input to resize level.
     */
    private void disableUserResizeInput() {
        this.controller.getConfirm().setDisable(true);
        this.controller.getHeightInput().setDisable(true);
        this.controller.getWidthInput().setDisable(true);
        this.controller.getSave().setDisable(false);
    }

    /**
     * Registers event handlers for the GridPane in which the {@link Level} is
     * displayed.
     */
    private void registerEventHandlersForGridPane() {

        this.controller.getBoardGame().setOnDragOver(event -> {
            Dragboard db = event.getDragboard();
            if (db.hasImage()) {
                event.acceptTransferModes(TransferMode.COPY);
            }
            event.consume();
        });

        this.controller.getBoardGame().setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            boolean success = false;

            Canvas canvas = (Canvas) event.getPickResult().getIntersectedNode();
            if (db.hasImage()) {
                Integer cIndex = GridPane.getColumnIndex(canvas);
                Integer rIndex = GridPane.getRowIndex(canvas);
                int x = cIndex == null ? 0 : cIndex;
                int y = rIndex == null ? 0 : rIndex;

                // Prevent user from replacing walls
                if (x > 0 && y > 0 && x < levelEditor.getGridWidth() - 1
                        && y < levelEditor.getGridHeight() - 1) {

                    LevelObject levelObject = createLevelObjectByName(
                            draggedImage.getLabel(), x, y);
                    try {
                        if (levelObject instanceof Cell) {
                            if (levelEditor.get(x, y) != null) {
                                levelEditor.remove(x, y);
                            }

                            levelEditor.add(x, y, (Cell) levelObject);

                        } else if (levelObject instanceof CollectableItem
                                && this.levelEditor.get(x,
                                        y) instanceof Ground) {
                            ((Ground) this.levelEditor.get(x, y))
                                    .setCollectableItem(
                                            (CollectableItem) levelObject);
                        } else if (levelObject instanceof MovableObject
                                && this.levelEditor.get(x,
                                        y) instanceof StepableCell) {
                            ((StepableCell) this.levelEditor.get(x, y))
                                    .setMovableObject(
                                            (MovableObject) levelObject);
                        }

                        this.levelEditor.get(x, y).draw(
                                canvas.getGraphicsContext2D(), 0, 0,
                                Constants.GRID_CELL_WIDTH,
                                Constants.GRID_CELL_HEIGHT);

                        success = true;
                    } catch (PositionTakenException e) {
                        System.err.println(e.getMessage());
                        e.printStackTrace();
                    }

                    registerEventHandlerForCanvas(canvas);
                }

            }
            event.setDropCompleted(success);
            event.consume();
        });
    }

    /**
     * Registers event handlers for {@link Canvas} displaying currently edited
     * {@link Level}.
     *
     * @param canvas
     *            the {@link Canvas} to work with.
     */
    private void registerEventHandlerForCanvas(Canvas canvas) {
        canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton().equals(MouseButton.PRIMARY)) {
                    if (event.getClickCount() == 2) {
                        Node node = (Node) event.getSource();
                        int x = controller.getBoardGame().getColumnIndex(node);
                        int y = controller.getBoardGame().getRowIndex(node);

                        Cell cell = levelEditor.get(x, y);

                        boolean isSteppedOn = (cell instanceof Ground
                                && ((Ground) cell).isSteppedOn());
                        boolean hasCollectable = (cell instanceof Ground
                                && ((Ground) cell).hasCollectableItem());
                        boolean isDoor = (cell instanceof Door);

                        if (isDoor) {
                            propertyController.setActiveObject(cell);
                        }
                        if (isSteppedOn) {
                            propertyController.setActiveObject(
                                    ((StepableCell) cell).getMovableObject());
                        }
                        if (hasCollectable && !isSteppedOn) {
                            propertyController.setActiveObject(
                                    ((Ground) cell).getCollectableItem());
                        }


                        if (isDoor | isSteppedOn | hasCollectable) {
                            generatePropertiesWindow(
                                    propertyController.getProperties());
                        }
                    }
                }
            }
        });
    }

    /**
     * Generates Property window.
     *
     * @param infos
     *            properties to be displayed in the window.
     */
    private void generatePropertiesWindow(PropertyInfo[] propertyInfos) {
        // 1) Add save button to FXML to rewrite values in fields
        // 2) When generating GUI, you can use "id" (not fx:id) to map
        // TextFields to PropertyInfos
        // 2.1) You will need to do it manually (loop) - right now, there is no
        // methods in PropertiesController that would do that for you

        for (PropertyInfo info : propertyInfos) {
            System.out.println(String.format("%s (%s): %s", info.getKey(),
                    info.getTypeInfo(),
                    info.getValue() == null ? "null" : info.getValue()));
        }
        System.out.println();
    }

    /**
     * Registers event handlers for {@link ImageView}.
     *
     * @param view
     *            the {@link ImageView} to use.
     */
    private void registerEventHandlerForImageView(ImageView view) {
        view.setOnDragDetected(event -> {
            draggedImage = (LevelObjectImage) view.getImage();

            Dragboard db = view.startDragAndDrop(TransferMode.COPY);
            db.setDragView(draggedImage);

            // This is the shown image
            ClipboardContent content = new ClipboardContent();
            content.putImage(new LevelObjectImage(draggedImage.getPath(),
                    draggedImage.getLabel()));
            db.setContent(content);

            event.consume();
        });
    }

    /**
     * Validates user input for {@link Level} width and height.
     *
     * @param target
     *            the field in which to write the value.
     * @param observable
     *            the {@link TextField} in which the value has changed.
     * @param oldValue
     *            the old value.
     * @param newValue
     *            the new value.
     */
    private void validateLevelDimension(
            ObservableValue<? extends String> observable, String oldValue,
            String newValue, Integer target) {
        if (!newValue.matches("\\d*")) {
            ((StringProperty) observable).setValue(oldValue.toUpperCase());
        } else if (oldValue.equals("")) {
            ((StringProperty) observable).setValue(newValue.toUpperCase());
        } else if (newValue.equals("")) {
            ((StringProperty) observable).setValue("0");
            this.controller.getConfirm().setVisible(false);
        } else if (newValue.equals("00")) {
            ((StringProperty) observable).setValue(oldValue.toUpperCase());
            this.controller.getConfirm().setVisible(false);
        } else if (oldValue.equals("0")) {
            ((StringProperty) observable)
                    .setValue(Character.toString(newValue.charAt(1)));
        } else if (Integer.parseInt(newValue) > 25) {
            ((StringProperty) observable).setValue("25");
            this.controller.getConfirm().setVisible(false);
        } else {
            if (!(Integer.parseInt(newValue) == width)) {
                if (Integer
                        .parseInt(newValue) >= Constants.LEVEL_DISPLAY_SIZE) {
                    target = Integer.parseInt(newValue);
                    this.controller.getConfirm().setVisible(true);
                } else {
                    this.controller.getConfirm().setVisible(false);
                }
            }
            ((StringProperty) observable).setValue(newValue.toUpperCase());
        }
    }

    /**
     * Creates a new {@link LevelEditor} with default {@link Level}.
     *
     * @param width
     *            the width of the new {@link Level}.
     * @param height
     *            the height of the new {@link Level}.
     */
    private void createLevelEditor(int width, int height) {
        this.levelEditor = new LevelEditor(width, height);

        // Fill level with default cells
        for (int x = 0; x < width; ++x) {
            for (int y = 0; y < height; ++y) {
                Cell cell = null;

                if (y == 0 || y == height - 1) {
                    cell = new Wall(this.levelEditor.getLevel(), x, y);
                } else if (x == 0 || x == width - 1) {
                    cell = new Wall(this.levelEditor.getLevel(), x, y);
                } else {
                    cell = new Ground(this.levelEditor.getLevel(), x, y);
                }

                // Add Cell to the ILevelEditor + display image in GUI
                try {
                    if (this.levelEditor.get(x, y) != null) {
                        this.levelEditor.remove(x, y);
                    }

                    this.levelEditor.add(x, y, cell);
                } catch (PositionTakenException e) {
                    System.err.println(e.getMessage());
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Creates a new {@link LevelObject} from its name.
     *
     * @param name
     *            name of the {@link LevelObject}.
     * @return the created {@link LevelObject}.
     */
    private LevelObject createLevelObjectByName(String name, int x, int y) {
        System.out.print(name);
        LevelObject cell = null;
        String[] className = name.split("\\.");

        // TODO: Use Contants instead of Strings
        switch (className[className.length - 1]) {
        case "Fire": {
            cell = new Fire(this.levelEditor.getLevel(), x, y);
            break;
        }
        case "Water": {
            cell = new Water(this.levelEditor.getLevel(), x, y);
            break;
        }
        case "Wall": {
            cell = new Wall(this.levelEditor.getLevel(), x, y);
            break;
        }
        case "Goal": {
            cell = new Goal(this.levelEditor.getLevel(), x, y);
            break;
        }
        case "Ground": {
            cell = new Ground(this.levelEditor.getLevel(), x, y);
            break;
        }
        case "Token": {
            cell = new Token(this.levelEditor.getLevel());
            break;
        }
        case "Teleporter": {
            cell = new Teleporter(this.levelEditor.getLevel(),
                    Constants.TITLE_TELEPORTER, x, y);
            break;
        }
        case "TokenDoor": {
            cell = new TokenDoor(this.levelEditor.getLevel(),
                    Constants.TITLE_TOKEN_DOOR, x, y);
            break;
        }
        case "Key": {
            cell = new Key(this.levelEditor.getLevel(), KeyType.RED);
            break;
        }
        case "KeyDoor": {
            cell = new KeyDoor(this.levelEditor.getLevel(),
                    Constants.TITLE_KEY_DOOR, x, y, KeyType.RED, false);
            break;
        }
        case "DumbTargetingEnemy": {
            cell = new DumbTargetingEnemy(this.levelEditor.getLevel(),
                    Constants.TITLE_DUMB_TARGETING_ENEMY, x, y);
            break;
        }
        case "SmartTargetingEnemy": {
            cell = new SmartTargetingEnemy(this.levelEditor.getLevel(),
                    Constants.TITLE_SMART_TARGETING_ENEMY, x, y);
            break;
        }
        case "StraightWalkingEnemy": {
            cell = new StraightWalkingEnemy(this.levelEditor.getLevel(),
                    Constants.TITLE_STRAIGHT_WALKING_ENEMY, x, y, 1, 0);
            break;
        }
        case "WallFollowingEnemy": {
            cell = new WallFollowingEnemy(this.levelEditor.getLevel(),
                    Constants.TITLE_WALL_FOLLOWING_ENEMY, x, y);
            break;
        }
        case "Flippers": {
            cell = new Flippers(this.levelEditor.getLevel());
            break;
        }
        case "FireBoots": {
            cell = new FireBoots(this.levelEditor.getLevel());
            break;
        }
        case "Player": {
            cell = new Player(this.levelEditor.getLevel(),
                    Constants.TITLE_PLAYER, x, y, 0, 0);
            break;
        }
        default: {
            break;
        }
        }

        // TODO: throw new UnknownCellException(name)
        if (cell != null) {
            System.out.println(cell);
        } else {
            System.err.print(name);
            System.err.println(
                    "LevelEditorScene.createCellByName(String, int, int) failed!");
        }

        return cell;
    }
}
