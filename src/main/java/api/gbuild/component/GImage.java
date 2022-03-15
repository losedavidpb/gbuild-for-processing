package api.gbuild.component;

import api.gbuild.GComponent;
import processing.core.PApplet;
import static processing.core.PApplet.abs;
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
 * @author David Parreño Barbuzano
 */
public class GImage extends GComponent {
    private PImage image;
    private String path;

    /** 
     * Create a new instance of an image
     * 
     * @param manager Processing manager
     * @param parent component parent
     * @param path path for image
     * @param x x component for location
     * @param y y component for location
     * @param w w component for dimension
     * @param h h component for dimension
     */
    public GImage(PApplet manager, GComponent parent, String path, float x, float y, float w, float h) {
        super(manager, parent);
        super.pos(x, y);
        super.dim(w, h);
        
        if (path != null) {
            this.path = path;
            this.image = manager.loadImage(path);
        }
            
    }
    
    /** 
     * Create a new instance of an image
     * 
     * @param manager Processing manager
     * @param path path for image
     * @param x x component for location
     * @param y y component for location
     * @param w w component for dimension
     * @param h h component for dimension
     */
    public GImage(PApplet manager, String path, float x, float y, float w, float h) {
        this(manager, null, path, x, y, w, h);
    }
    
    /**
     * Specify a new image located at a different path
     * 
     * @param path local or external path
     */
    public void setImage(String path) {
        if (path != null)
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