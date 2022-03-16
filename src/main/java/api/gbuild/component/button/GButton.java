package api.gbuild.component.button;

import api.gbuild.GColor;
import api.gbuild.component.GComponent;
import api.gbuild.component.GPanel;
import processing.core.PApplet;

/**
 * <p>
 * Component defined as a clickable button
 * </p>
 * 
 * <p>
 * This class is the definition of a button that can
 * be selected and incorporates an effect on which
 * the color of a component of the button would
 * change wether user is hovering it.
 * </p>
 * 
 * @author David Parreño Barbuzano
 */
public abstract class GButton extends GComponent {
    protected final GPanel content;
    protected final GColor backgroundColor, rawColor, hoverColor;
    protected boolean isSelected;
    
    /**
     * Create a new instance of a button
     * 
     * @param manager Processing manager
     * @param parent parent component
     * @param x x component for location
     * @param y y coponent for location
     * @see GComponent#GComponent(processing.core.PApplet, api.gbuild.GComponent) 
     */
    public GButton(PApplet manager, GComponent parent, float x, float y) {
        super(manager, parent);
        this.backgroundColor = new GColor(0, 0, 0);
        this.rawColor = new GColor(255, 255, 255);
        this.hoverColor = new GColor(0, 0, 0);
        this.isSelected = false;
        
        this.content = new GPanel(manager, parent, x, y);
        
        super.pos(x, y);
        super.dim(this.content.dim().x, this.content.dim().y);
    }
    
    /**
     * Create a new instance of a button
     * 
     * @param manager Processing manager
     * @param x x component for location
     * @param y y coponent for location
     * @see GComponent#GComponent(processing.core.PApplet) 
     */
    public GButton(PApplet manager, float x, float y) {
        this(manager, null, x, y);
    }
    
    @Override
    public void pos(Float ... coords) {
        super.pos(coords);
        this.content.pos(coords);
    }
    
    @Override
    public void pos(Integer ... coords) {
        super.pos(coords);
        this.content.pos(coords);
    }
    
    @Override
    public void dim(Float ... dim) {
        super.dim(dim);
        this.content.dim(dim);
    }
    
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
     * import api.gbuild.component.button.*;
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
     * }
     * 
     * void mouseCliked() {
     *  if (button.isSelected()) {
     *      println("User has selected the button");
     *  }
     * }
     * 
     * 
     * </pre>
     * 
     * @return selected state
     */
    public boolean isSelected() {
        return this.isSelected;
    }
    
    /**
     * Get the background color for the button
     * 
     * @return background color
     */
    public float[] backgroundColor() {
        return this.backgroundColor.color();
    }
    
    /**
     * Get the raw color for the button
     * 
     * <p>
     * Raw color is the default color that a component
     * stored at the button will have when user is not
     * hovering the mouse pointer on it.
     * </p>
     * 
     * @return raw color
     */
    public float[] rawColor() {
        return this.rawColor.color();
    }
    
    /**
     * Get the hover color for the button
     * 
     * <p>
     * Hover color is the default color that a component
     * stored at the button will have when user is
     * hovering the mouse pointer on it.
     * </p>
     * 
     * @return hover color
     */
    public float[] hoverColor() {
        return this.hoverColor.color();
    }
    
    /**
     * Return if button contains a background
     * 
     * @return background state
     */
    public boolean isTransparent() {
        return this.content.isTransparent();
    }
    
    /**
     * Specify if button has a transparent background
     * 
     * @param transparency state of the transparency
     * @see GButton#isTransparent() 
     */
    public void setTransparency(boolean transparency) {
        this.content.setTransparency(transparency);
    }
    
    /**
     * Set the background color for the button
     * 
     * @param color red, green, and blue component
     * @see GButton#backgroundColor()
     */
    public void setBackgroundColor(Float ... color) {
        this.content.setColor(color);
    }
    
    /**
     * Set the background color for container
     * 
     * @param color red, green, and blue component
     * @see GButton#backgroundColor()
     */
    public void setBackgroundColor(Integer ... color) {
        this.content.setColor(color);
    }

    /**
     * Set the raw color for the button
     * 
     * @param component red, green, and blue component
     * @see GButton#rawColor()
     */
    public void setRawColor(Float ... component) {
        this.rawColor.setColor(component);
    }
    
    /**
     * Set the raw color for the button
     * 
     * @param component red, green, and blue component
     * @see GButton#rawColor()
     */
    public void setRawColor(Integer ... component) {
        this.rawColor.setColor(component);
    }
    
    /**
     * Set the hover color for the button
     * 
     * @param component red, green, and blue component
     * @see GButton#hoverColor() 
     */
    public void setHoverColor(Float ... component) {
        this.hoverColor.setColor(component);
    }
    
    /**
     * Set the hover color for the button
     * 
     * @param component red, green, and blue component
     * @see GButton#hoverColor() 
     */
    public void setHoverColor(Integer ... component) {
        this.hoverColor.setColor(component);
    }
    
    /**
     * Specify if button is selected
     * 
     * @param isSelected selected state
     * @see GButton#isSelected()
     */
    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }
}