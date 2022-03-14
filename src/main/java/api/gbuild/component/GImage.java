package api.gbuild.component;

import api.gbuild.GComponent;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * Component defined by an image.
 * 
 * This type of simple component draws at a
 * specific location and dimension an image
 * loaded by a valid path.
 * 
 * Path could be an URL or a local path for
 * a filesystem mounted at current machine
 * 
 * @author David
 */
public class GImage extends GComponent {
    private PImage image;

    public GImage(PApplet manager, String path, float x, float y, float w, float h) {
        super(manager);
        this.image = manager.loadImage(path);
        super.pos(x, y);
        super.dim(w, h);
    }
  
    public GImage(PApplet manager, GContainer parent, String path, float x, float y, float w, float h) {
        super(manager, parent);
        this.image = manager.loadImage(path);
        super.pos(x, y);
        super.dim(w, h);
    }
    
    /**
     * Specify a new image located at a different path
     * 
     * @param path local or external path
     */
    public void setImage(String path) {
        this.image = manager().loadImage(path);
    }
  
    @Override
    public void draw() {
        if (isVisible()) {
            if (this.image != null) {
                manager().image(this.image,
                    this.pos().x, this.pos().y,
                    this.dim().x, this.dim().y
                );
            }
        }
    }
}