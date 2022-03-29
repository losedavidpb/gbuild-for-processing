package gbuild;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * <p>
 * Component type for images
 * </p>
 * 
 * <p>
 * Processing language environment manages images using PImage data
 * type, which can be customized using other utilities. This class
 * was designed to be the equivalent implementation of a PImage,
 * but incorporating easy customization
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
    private GColor tintColor;
    private boolean tintMode;
    
    /** 
     * Create a new instance of an image.
     * 
     * @param manager Processing manager
     * @param parent component parent
     * @see GComponent#GComponent(processing.core.PApplet, api.gbuild.component.GComponent) 
     */
    public GImage(PApplet manager, GComponent parent) {
        super(manager, parent);
        this.image = new PImage();
        this.tintColor = new GColor();
        this.tintMode = false;
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

    /**
     * Get the pixels for current image
     * 
     * @return integer pixels
     */
    public int[] pixels() {
        return this.image.pixels;
    }

    /**
     * Get state for tint mode
     * 
     * @return tint state
     */
    public boolean isTintMode() {
        return this.tintMode;
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
     * Get tint color for current image
     * 
     * @return color value
     * @deprecated will be deleted for future revisions
     */
    public GColor tintColor() {
        return this.tintColor.clone();
    }

    /**
     * Set a tint value for each RGB component
     * 
     * @param color tint value
     */
    public void setTint(GColor color) {
        this.tintColor.setColor(color);
    }
    
    /**
     * Set a tint value for each RGB component
     * 
     * @param component red, green, and blue float component
     * @deprecated will be deleted for future revisions
     */
    public void setTint(Integer ... component) {
        this.tintColor = new GColor(component);
    }

    /**
     * Create a new image
     */
    public void createImage() {
        this.image = new PImage();
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
     */
    public void setImage(String path) {
        if (path == null) {
            PApplet.println("error GImage.setImage: path cannot be null");
            System.exit(1);
        }
        
        this.image = manager().loadImage(path);
    }

    @Override
    public void draw() {
        if (this.isVisible()) {
            this.listenEvents();
            
            manager().pushStyle();
            this.tintColor.applyTintColor(manager());
            
            manager().image(this.image,
                super.pos().x, super.pos().y,
                super.dim().x, super.dim().y
            );
            
            manager().popStyle();
        }
    }
}