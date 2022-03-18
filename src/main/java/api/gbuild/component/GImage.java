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
    private PImage image = null;

    /** 
     * Create a new instance of an image.
     * 
     * @param manager Processing manager
     * @param parent component parent
     * @see GComponent#GComponent(processing.core.PApplet, api.gbuild.component.GComponent) 
     */
    public GImage(PApplet manager, GComponent parent) {
        super(manager, parent);
    }
    
    /** 
     * Create a new instance of an image
     * 
     * @param manager Processing manager
     * @see GComponent#GComponent(processing.core.PApplet) 
     */
    public GImage(PApplet manager) {
        this(manager, null);
    }
  
    @Override
    public void draw() {
        if ((Boolean)this.getProperty("isVisible")) {
            if (this.image != null) {
                manager().image(this.image,
                    super.pos().x, super.pos().y,
                    super.dim().x, super.dim().y
                );
            }
        }
    }
    
    @Override
    public void setProperty(Object name, Object value) {
        super.setProperty(name, value);
        
        if (name instanceof String) {
            switch((String)name) {
                case "image":
                    if (value instanceof String)
                        this.setImage((String)value);
                break;
            }
        }
    }
    
    // Deprecated
    
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
     * @deprecated will be deleted for future revisions
     */
    public boolean setImage(String path) {
        boolean cond = path != null;
        if (cond) this.image = manager().loadImage(path);
        return cond;
    }
}