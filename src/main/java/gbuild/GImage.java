package gbuild;

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
    private GColor tintColor = null;
    private boolean tintMode = false;
    
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
        if (this.isVisible()) {
            if (this.image != null) {
                manager().image(this.image,
                    super.pos().x, super.pos().y,
                    super.dim().x, super.dim().y
                );
            }
        }
    }
    
    @Override
    public Object getProp(String name) {
        Object value = super.getProp(name);
        
        if (value == null) {
            switch(name) {
                case "tintMode": return this.isTintMode();
                case "tintColor": return this.tintColor();
                case "image": return this.image;
                default:
                    System.out.printf("error: property %s was not found", name);
                    System.exit(-1);
                    return null;
            }
        }
        
        return value;
    }
    
    @Override
    public boolean setProp(Object name, Object value) {
        boolean cond = super.setProp(name, value);
        
        if (cond == false) {
            if (name instanceof String) {
                switch((String)name) {
                    case "image":
                        if (value instanceof String) {
                            return this.setImage((String)value);
                        }
                    break;
                    
                    case "tintMode":
                        if (value instanceof Boolean) {
                            this.setTintMode((Boolean)value);
                            return true;
                        }
                    break;
                    
                    case "tintColor":
                        if (value instanceof GColor) {
                            this.setTint((GColor)value);
                            return this.isTintMode();
                        }
                    break;
                }
            }
        }
        
        return cond;
    }
    
    // Deprecated
    
    /**
     * Get state for tint mode
     * 
     * @return tint state
     */
    public boolean isTintMode() {
        return this.tintMode;
    }
    
    /**
     * Get tint color for current image
     * 
     * @return color value
     */
    public GColor tintColor() {
        return this.tintColor.clone();
    }
    
    /**
     * Set if tint would be applied to current image
     * 
     * @param tintMode tint state
     */
    public void setTintMode(boolean tintMode) {
        this.tintMode = tintMode;
    }
    
    /**
     * Set a tint value for each RGB component
     * 
     * <p>
     * Tint color would be changed whether its mode
     * is available, so set tint mode to true before
     * calling this method
     * </p>
     * 
     * @param color tint value
     */
    public void setTint(GColor color) {
        if (this.tintMode) {
            this.tintColor = new GColor(0, 0, 0);
            this.tintColor.setColor(color);
        }
    }
    
    /**
     * Set a tint value for each RGB component
     * 
     * <p>
     * Tint color would be changed whether its mode
     * is available, so set tint mode to true before
     * calling this method
     * </p>
     * 
     * @param component red, green, and blue float component
     */
    public void setTint(Float ... component) {
        if (this.tintMode) {
            this.tintColor = new GColor(component);
        }
    }
    
    /**
     * Set a tint value for each RGB component
     * 
     * <p>
     * Tint color would be changed whether its mode
     * is available, so set tint mode to true before
     * calling this method
     * </p>
     * 
     * @param component red, green, and blue float component
     */
    public void setTint(Integer ... component) {
        if (this.tintMode) {
            this.tintColor = new GColor(component);
        }
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
     * @deprecated will be deleted for future revisions
     */
    public boolean setImage(String path) {
        boolean cond = path != null;
        if (cond) this.image = manager().loadImage(path);
        return cond;
    }
}