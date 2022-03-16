package api.gbuild.component;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * <p>
 * Data type defined by an PImage.
 * </p>
 * 
 * <p>
 * Processing language environment manages images using PImage
 * data type, and can be customized with other primitives. This
 * class is the equivalent implementation of this type of figure,
 * and is defined with a path, location, and dimension
 * </p>
 * 
 * <p>
 * On this release, this class does not allow to modify the appearance
 * of a PImage with other primitives, unless you manually use Processing
 * functions. In future versions, we will include more features
 * </p>
 * 
 * <p>
 * For more information about PImage, click
 * <a href="https://processing.org/reference/PImage.html">here</a>
 * </p>
 * 
 * @author David Parreño Barbuzano
 */
public class GImage extends GComponent {
    private PImage image;

    /** 
     * Create a new instance of an image.
     * 
     * @param manager Processing manager
     * @param parent component parent
     * @param path path for an image
     * @param x x component for location
     * @param y y component for location
     * @param w w component for dimension
     * @param h h component for dimension
     * @see GComponent#GComponent(processing.core.PApplet, api.gbuild.GComponent) 
     */
    public GImage(PApplet manager, GComponent parent, String path, float x, float y, float w, float h) {
        super(manager, parent);
        super.pos(x, y);
        super.dim(w, h);
        
        if (path != null)
            this.image = manager.loadImage(path);
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
     * @see GComponent#GComponent(processing.core.PApplet) 
     */
    public GImage(PApplet manager, String path, float x, float y, float w, float h) {
        this(manager, null, path, x, y, w, h);
    }
    
    /**
     * Specify a new image located at a different path
     * 
     * <p>
     * It is important to notice that image would not be
     * loaded if it is null, but other excepctions are not
     * managed. In future revisions we will fix this
     * </p>
     * 
     * @param path local or external path
     * @return if image was loaded
     */
    public boolean setImage(String path) {
        boolean cond = path != null;
        if (cond) this.image = manager().loadImage(path);
        return cond;
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