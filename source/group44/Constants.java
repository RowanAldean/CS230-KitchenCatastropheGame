package group44;

/**
 * This file contains all the constants used throughout the project.
 *
 * @author Tomas Svejnoha, Oliver Morris.
 * @version 1.0
 */
public final class Constants {
    private Constants() {
    }

    public static final String FILE_SOURCE = "source/group44/";

    // Data - Folders
    public static final String FOLDER_LEVELS = FILE_SOURCE + "data/levels/";

    // Data - Files
    public static final String FILE_RECORDS = FILE_SOURCE + "data/records.txt";
    public static final String FILE_PROFILES = FILE_SOURCE + "data/profiles.txt";
    public static final String FILE_LEVEL = "level_%d.txt";
    public static final String FILE_LEVEL_TEMP_PATTERN = "%d-%d.txt"; // "LEVEL_ID-PROFILE_ID.txt"

    //Music paths
    public static final String MENU_MUSIC = FILE_SOURCE + "resources/Music/menu.mp3";
    public static final String GAME_MUSIC = FILE_SOURCE + "resources/Music/game.mp3";
    public static final String DIED_MUSIC = FILE_SOURCE + "resources/Music/died.mp3";

    //Sound paths
    public static final String FOOTSTEP_SOUND = FILE_SOURCE + "resources/Sounds/footstep.mp3";
    public static final String DEATH_SOUND = FILE_SOURCE + "resources/Sounds/death.mp3";
    public static final String WIN_SOUND = FILE_SOURCE + "resources/Sounds/applause.mp3";
    public static final String COLLECT_SOUND = FILE_SOURCE + "resources/Sounds/collect.mp3";
    public static final String TOKEN_SOUND = FILE_SOURCE + "resources/Sounds/token.mp3";
    public static final String LOCKED_SOUND = FILE_SOURCE + "resources/Sounds/locked.wav";
    public static final String SPLASH_SOUND = FILE_SOURCE + "resources/Sounds/water.mp3";
    public static final String FIRE_SOUND = FILE_SOURCE + "resources/Sounds/fire.mp3";
    public static final String TELEPORT_SOUND = FILE_SOURCE + "resources/Sounds/teleport.mp3";

    //Minigame sound paths
    public static final String MINIGAME_NAV = FILE_SOURCE + "resources/Sounds/minigame_navigate.wav";
    public static final String MINIGAME_SELECT = FILE_SOURCE + "resources/Sounds/minigame_select.wav";
    public static final String MINIGAME_FAIL = FILE_SOURCE + "resources/Sounds/minigame_fail.wav";
    public static final String MINIGAME_WIN = FILE_SOURCE + "resources/Sounds/minigame_success.wav";

    // Tile Paths
    public static final String WALL_PATH = FILE_SOURCE + "resources/WallCounter.png";
    public static final String GROUND_PATH = FILE_SOURCE + "resources/cells/floor.png";
    public static final String WATER_PATH = FILE_SOURCE + "resources/cells/water.png";
    public static final String FIRE_PATH = FILE_SOURCE + "resources/cells/fire.png";
    public static final String GOAL_PATH = FILE_SOURCE + "resources/cells/goal.png";
    public static final String TELEPORTER_PATH = FILE_SOURCE + "resources/cells/teleporter.png";

    // Item paths
    public static final String FIRE_BOOTS_PATH = FILE_SOURCE + "resources/cells/fireBoots.png";
    public static final String FLIPPERS_PATH = FILE_SOURCE + "resources/cells/flippers.png";
    public static final String TOKEN_PATH = FILE_SOURCE + "resources/cells/token.png";
    public static final String KEY_PATH = FILE_SOURCE + "resources/keys/%s.png";

    //Minigame ingredient paths
    public static final String MINIGAME_BOTTOM_BUN_PATH = FILE_SOURCE + "resources/minigame/bottomBun.png";
    public static final String MINIGAME_TOMATO_PATH = FILE_SOURCE + "resources/minigame/tomato.png";
    public static final String MINIGAME_LETTUCE_PATH = FILE_SOURCE + "resources/minigame/lettuce.png";
    public static final String MINIGAME_BURGER_PATH = FILE_SOURCE + "resources/minigame/burger.png";
    public static final String MINIGAME_TOP_BUN_PATH = FILE_SOURCE + "resources/minigame/topBun.png";
    //Minigame selected ingredient paths
    public static final String MINIGAME_SELECTED_BOTTOM_BUN_PATH = FILE_SOURCE + "resources/minigame/bottomBunHighlighted.png";
    public static final String MINIGAME_SELECTED_TOMATO_PATH = FILE_SOURCE + "resources/minigame/tomatoHighlighted.png";
    public static final String MINIGAME_SELECTED_LETTUCE_PATH = FILE_SOURCE + "resources/minigame/lettuceHighlighted.png";
    public static final String MINIGAME_SELECTED_BURGER_PATH = FILE_SOURCE + "resources/minigame/burgerHighlighted.png";
    public static final String MINIGAME_SELECTED_TOP_BUN_PATH = FILE_SOURCE + "resources/minigame/topBunHighlighted.png";

    // Door paths
    public static final String CLOSED_KEY_DOOR_PATH = FILE_SOURCE + "resources/cells/closed%sDoor.png";
    public static final String OPEN_KEY_DOOR_PATH = FILE_SOURCE + "resources/cells/open%sDoor.png";
    public static final String CLOSED_TOKEN_DOOR_PATH = FILE_SOURCE + "resources/cells/closedTokenDoor.png";
    public static final String OPEN_TOKEN_DOOR_PATH = FILE_SOURCE + "resources/cells/openTokenDoor.png";

    // Entity paths
    public static final String PLAYER_PATH = FILE_SOURCE + "resources/ChefDownWalk/Front1.png";
    public static final String DUMB_TARGETING_ENEMY_PATH = FILE_SOURCE + "resources/Enemies/Pickle/MrPickleFront.png";
    public static final String SMART_TARGETING_ENEMY_PATH = FILE_SOURCE + "resources/Enemies/Egg/MrEggFront.png";
    public static final String MINIGAME_ENEMY_PATH = FILE_SOURCE + "resources/Enemies/Waiter/WaiterFront.png";
    public static final String STRAIGHT_WALKING_ENEMY_PATH = FILE_SOURCE + "resources/Enemies/Carrot/MrCarrotFront.png";
    public static final String WALL_FOLLOWING_ENEMY_PATH = FILE_SOURCE + "resources/Enemies/Hotdog/MrHotDogFront.png";

    // Level editor - tile paths
    public static final String LEVEL_EDITOR_KEY = FILE_SOURCE + "resources/LevelEditor/GreyKey.png";
    public static final String LEVEL_EDITOR_FIRE_BOOTS_PATH = FILE_SOURCE + "resources/LevelEditor/fireBoots.png";
    public static final String LEVEL_EDITOR_FLIPPERS_PATH = FILE_SOURCE + "resources/LevelEditor/flippers.png";
    public static final String LEVEL_EDITOR_TOKEN_PATH = FILE_SOURCE + "resources/LevelEditor/token.png";
    public static final String LEVEL_EDITOR_PLAYER_PATH = FILE_SOURCE + "resources/LevelEditor/PlayerFront.png";
    public static final String LEVEL_EDITOR_DUMB_TARGETING_ENEMY_PATH = FILE_SOURCE + "resources/LevelEditor/MrPickleFront.png";
    public static final String LEVEL_EDITOR_SMART_TARGETING_ENEMY_PATH = FILE_SOURCE + "resources/LevelEditor/MrEggFront.png";
    public static final String LEVEL_EDITOR_MINIGAME_ENEMY_PATH = FILE_SOURCE + "resources/LevelEditor/WaiterEditor.png";
    public static final String LEVEL_EDITOR_WALL_FOLLOWING_ENEMY_PATH = FILE_SOURCE + "resources/LevelEditor/MrHotDogFront.png";
    public static final String LEVEL_EDITOR_STRAIGHT_WALKING_ENEMY_PATH = FILE_SOURCE + "resources/LevelEditor/MrCarrotFront.png";

    public static final int WINDOW_WIDTH = 800;
    public static final int WINDOW_HEIGHT = 600;

    public static final int CANVAS_WIDTH = 725;
    public static final int CANVAS_HEIGHT = 550;

    public static final String LEVEL_OBJECT_DELIMITER = ",";

    // Parsing constants
    public static final String TYPE_WALL = "wall";
    public static final String TYPE_GROUND = "ground";
    public static final String TYPE_WATER = "water";
    public static final String TYPE_FIRE = "fire";
    public static final String TYPE_GOAL = "goal";
    public static final String TYPE_PLAYER = "player";
    public static final String TYPE_FIRE_BOOTS = "fireBoots";
    public static final String TYPE_FLIPPERS = "flippers";
    public static final String TYPE_KEY = "key";
    public static final String TYPE_TOKEN = "token";
    public static final String TYPE_DUMB_TARGETING_ENEMY = "dumbTargetingEnemy";
    public static final String TYPE_STRAIGHT_WALKING_ENEMY = "straightWalkingEnemy";
    public static final String TYPE_WALL_FOLLOWING_ENEMY = "wallFollowingEnemy";
    public static final String TYPE_SMART_TARGETING_ENEMY = "smartTargetingEnemy";
    public static final String TYPE_MINIGAME_ENEMY = "minigameEnemy";
    public static final String TYPE_TOKEN_DOOR = "tokenDoor";
    public static final String TYPE_KEY_DOOR = "keyDoor";
    public static final String TYPE_TELEPORTER = "teleporter";
    public static final String TYPE_TELEPORTER_LINK = "teleporterLink";

    // Titles
    public static final String TITLE_DUMB_TARGETING_ENEMY = "Dumb Targeting Enemy";
    public static final String TITLE_FIRE = "Fire";
    public static final String TITLE_FIREBOOTS = "Fireboots";
    public static final String TITLE_FLIPPERS = "Flippers";
    public static final String TITLE_GOAL = "Goal";
    public static final String TITLE_GROUND = "Ground";
    public static final String TITLE_KEY_DOOR = "Key Door";
    public static final String TITLE_PLAYER = "Player";
    public static final String TITLE_SMART_TARGETING_ENEMY = "Smart Targeting Enemy";
    public static final String TITLE_MINIGAME_ENEMY = "Minigame Enemy";
    public static final String TITLE_STRAIGHT_WALKING_ENEMY = "Straight Walking Enemy";
    public static final String TITLE_TELEPORTER = "Teleporter";
    public static final String TITLE_TOKEN = "Token";
    public static final String TITLE_TOKEN_ACCUMULATOR = "Token Accumulator";
    public static final String TITLE_TOKEN_DOOR = "Token Door";
    public static final String TITLE_WALL = "Wall";
    public static final String TITLE_WALL_FOLLOWING_ENEMY = "Wall Following Enemy";
    public static final String TITLE_WATER = "Water";

    // The size of each cell
    public static final int GRID_CELL_WIDTH = 60;
    public static final int GRID_CELL_HEIGHT = 60;

    // Drawing constants
    public static final int LEVEL_DISPLAY_SIZE = 7; // Must be odd and greater
    // or equal 3
}