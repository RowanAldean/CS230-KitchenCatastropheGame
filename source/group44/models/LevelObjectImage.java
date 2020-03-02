package group44.models;

import javafx.scene.image.Image;

/**
 * An {@link Image} with a label.
 *
 * @author Tomas Svejnoha, Rowan Aldean
 * @version 1.0
 */
public class LevelObjectImage extends Image {
    private String label;
    private String path;

    public LevelObjectImage(String url, String label) {
        super(url);
        this.path = url;
        this.setLabel(label);
    }

    public LevelObjectImage(String url, String label, boolean backgroundLoading) {
        super(url, backgroundLoading);
        this.path = url;
        this.setLabel(label);
    }

    public LevelObjectImage(String url, String label, double requestedWidth, double requestedHeight, boolean preserveRatio, boolean smooth) {
        super(url, requestedWidth, requestedHeight, preserveRatio, smooth);
        this.path = url;
        this.setLabel(label);
    }

    public LevelObjectImage(String url, String label, double requestedWidth, double requestedHeight, boolean preserveRatio, boolean smooth, boolean backgroundLoading) {
        super(url, requestedWidth, requestedHeight, preserveRatio, smooth, backgroundLoading);
        this.path = url;
        this.setLabel(label);
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return this.getLabel();
    }
}
