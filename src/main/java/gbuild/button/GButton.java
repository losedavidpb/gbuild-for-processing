package gbuild.button;

import gbuild.GColor;
import gbuild.GComponent;
import gbuild.GPanel;
import gbuild.event.ClickAtGEvent;
import gbuild.event.HoverGEvent;
import processing.core.PApplet;

/**
 * <p>
 * Component for buttons
 * </p>
 * 
 * <p>
 * Processing language environment includes utilities to
 * manage mouse or key events. Taking this into account,
 * this class was designed to be a clickable component
 * that would execute an action if user has clicked it.
 * </p>
 * 
 * <p>
 * It is important to know that gBuild provides features
 * to implement the actions that will be executed if user
 * has clicked the button, but developers must define such
 * actions at the Processing sketch.
 * </p>
 * 
 * @author David Parreño Barbuzano
 */
public abstract class GButton extends GComponent {
    protected final GPanel content;
    protected GColor backgroundColor, rawColor;
    protected GColor hoverColor, strokeColor;
    private boolean isSelected, isHover;
    
    /**
     * Defalt color for background button
     */
    public static final GColor BUTTON_BACKGROUND = new GColor(255, 255, 255);
            
    /**
     * Default color for raw content of a button
     */
    public static final GColor BUTTON_RAW = new GColor(0, 0, 0);
    
    /**
     * Default color for hover content of a button
     */
    public static final GColor BUTTON_HOVER = new GColor(255, 255, 255);
    
    /**
     * Default color for stroke color of a button
     */
    public static final GColor BUTTON_STROKE = new GColor(255, 255, 255);
    
    /**
     * Create a new instance of a button
     * 
     * @param manager Processing manager
     * @param parent parent component
     * @see GComponent#GComponent(processing.core.PApplet, api.gbuild.component.GComponent) 
     */
    public GButton(PApplet manager, GComponent parent) {
        super(manager, parent);
        this.backgroundColor = BUTTON_BACKGROUND.clone();
        this.rawColor = BUTTON_RAW.clone();
        this.hoverColor = BUTTON_HOVER.clone();
        this.strokeColor = BUTTON_STROKE.clone();
        this.isSelected = false;
        
        this.content = new GPanel(manager, parent);
        super.dim((int)this.content.dim().x, (int)this.content.dim().y);
        super.addEvent(new HoverGEvent(this));
        super.addEvent(new ClickAtGEvent(this));
    }
    
    /**
     * Create a new instance of a button
     * 
     * @param manager Processing manager
     * @see GComponent#GComponent(processing.core.PApplet) 
     */
    public GButton(PApplet manager) {
        this(manager, null);
    }
    
    /**
     * Check if button is selected and update
     * its style for hovering effect
     */
    public abstract void updateButton();

    /**
     * Return if button is selected
     * 
     * <p>
     * This property was defined in order to offer to developers
     * a way to manage events associated to the button.
     * </p>
     * 
     * <p>
     * The following code is an example of use on which
     * you can incorporate an event for a button
     * </p>
     * 
     * <pre>
     * import gbuild.button.*;
     * 
     * GButton button;
     * 
     * void setup() {
     *  size(800, 800, P2D);
     *  button = new GButtonWithText(this, width / 2, height / 2);
     * }
     * 
     * void draw() {
     *  button.draw();
     * 
     *  if (button.isSelected()) {
     *      println("user has selected the button");
     *  }
     * }
     * </pre>
     * 
     * @return selected state
     */
    public boolean isSelected() {
        return this.isSelected;
    }
    
    /**
     * Return if button is hover
     * 
     * <p>
     * This property was defined in order to offer to developers
     * a way to manage events associated to the button.
     * </p>
     * 
     * <p>
     * The following code is an example of use on which
     * you can incorporate an event for a button
     * </p>
     * 
     * <pre>
     * import gbuild.button.*;
     * 
     * GButton button;
     * 
     * void setup() {
     *  size(800, 800, P2D);
     *  button = new GButtonWithText(this, width / 2, height / 2);
     * }
     * 
     * void draw() {
     *  button.draw();
     * 
     *  if (button.isHover()) {
     *      println("user has hover the button");
     *  }
     * }
     * </pre>
     * 
     * @return hover state
     */
    public boolean isHover() {
        return this.isHover;
    }

    /**
     * Specify if button is selected
     * 
     * @param isSelected selected state
     */
    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }
    
    /**
     * Specify if button is hover
     * 
     * @param isHover hover state
     */
    protected void setHover(boolean isHover) {
        this.isHover = isHover;
    }

    @Deprecated
    @Override
    public void pos(Integer ... coords) {
        super.pos(coords);
        this.content.pos(coords);
    }
    
    @Deprecated
    @Override
    public void dim(Integer ... dim) {
        super.dim(dim);
        this.content.dim(dim);
    }
    
    /**
     * Specify size for current button
     * 
     * <p>
     * Since the shape of the button would be considered
     * as a square, the dimension (w, h) would be equal
     * </p>
     * 
     * @param size button size
     */
    public void setSize(int size) {
        this.dim(size, size);
    }
    
    /**
     * Detect if button has an opaque background
     * 
     * @return opaque state
     */
    public boolean isOpaque() {
        return this.content.isOpaque();
    }
    
    /**
     * Detect if button has a stroke opaque background
     * 
     * @return opaque state
     */
    public boolean isStrokeOpaque() {
        return this.content.isStrokeOpaque();
    }
    
    /**
     * Specify if button has a transparent background
     * 
     * @param opaque opaque state
     */
    public void setOpaque(boolean opaque) {
        this.content.setOpaque(opaque);
        this.backgroundColor.setOpaque(opaque);
    }
    
    /**
     * Specify if button has a transparent stroke background
     * 
     * @param opaque opaque state
     */
    public void setStrokeOpaque(boolean opaque) {
        this.content.setStrokeOpaque(opaque);
        this.strokeColor.setOpaque(opaque);
    }
    
    /**
     * Get the background color for the button
     * 
     * @return background color
     */
    public GColor setBackgroundColor() {
        return this.backgroundColor.clone();
    }
    
    /**
     * Set the background color for the button
     * 
     * @param color components
     */
    public void setBackgroundColor(GColor color) {
        float[] c = color.color();
        this.backgroundColor.setColor(c[0], c[1], c[2]);
        this.content.setColor(this.backgroundColor);
    }
    
    /**
     * Set the background color for container
     * 
     * @param color components
     */
    public void setBackgroundColor(Integer ... color) {
        this.backgroundColor = new GColor(color);
        this.content.setColor(this.backgroundColor);
    }
    
    /**
     * Set the stroke color for the button
     * 
     * @param color components
     */
    public void setStrokeColor(GColor color) {
        float[] c = color.color();
        this.strokeColor.setColor(c[0], c[1], c[2]);
        this.content.setStrokeColor(this.strokeColor);
    }

    /**
     * Set the stroke color for container
     * 
     * @param color components
     */
    public void setStrokeColor(Integer ... color) {
        this.strokeColor = new GColor(color);
        this.content.setStrokeColor(this.strokeColor);
    }
    
    /**
     * Get the raw color for the button
     * 
     * @return raw color
     */
    public GColor rawColor() {
        return this.rawColor.clone();
    }

    /**
     * Set the raw color for the button
     * 
     * @param component components
     */
    public void setRawColor(GColor component) {
        this.rawColor.setColor(component);
    }
    
    /**
     * Set the raw color for the button
     * 
     * @param component components
     */
    public void setRawColor(Integer ... component) {
        this.rawColor.setColor(component);
    }
    
    /**
     * Get the hover color for the button
     * 
     * @return hover color
     */
    public GColor hoverColor() {
        return this.hoverColor.clone();
    }
    
    /**
     * Set the hover color for the button
     * 
     * @param component components
     */
    public void setHoverColor(GColor component) {
        this.hoverColor.setColor(component);
    }
    
    /**
     * Set the hover color for the button
     * 
     * @param component components
     */
    public void setHoverColor(Integer ... component) {
        this.hoverColor.setColor(component);
    }
}