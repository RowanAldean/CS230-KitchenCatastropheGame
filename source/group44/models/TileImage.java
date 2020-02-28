package group44.models;

import javafx.scene.image.Image;

public class TileImage extends Image {
    private String TileName;

    public TileImage(String url) {
        super(url);
    }

    public TileImage(String url, boolean backgroundLoading) {
        super(url, backgroundLoading);
    }

    public TileImage(String url, double requestedWidth, double requestedHeight, boolean preserveRatio, boolean smooth) {
        super(url, requestedWidth, requestedHeight, preserveRatio, smooth);
    }

    public TileImage(String url, double requestedWidth, double requestedHeight, boolean preserveRatio, boolean smooth, boolean backgroundLoading) {
        super(url, requestedWidth, requestedHeight, preserveRatio, smooth, backgroundLoading);
    }

    public String getTileName() {
        return TileName;
    }

    public void setTileName(String tileName) {
        TileName = tileName;
    }

}
