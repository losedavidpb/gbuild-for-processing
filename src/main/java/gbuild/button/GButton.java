package gbuild.button;

import gbuild.GColor;
import gbuild.GComponent;
import gbuild.GPanel;
import gbuild.Globals;
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
    protected GColor backgroundColor, rawColor;
    protected GColor hoverColor, strokeColor;
    protected boolean isSelected;
    
    /**
     * Create a new instance of a button
     * 
     * @param manager Processing manager
     * @param parent parent component
     * @see GComponent#GComponent(processing.core.PApplet, api.gbuild.component.GComponent) 
     */
    public GButton(PApplet manager, GComponent parent) {
        super(manager, parent);
        this.backgroundColor = Globals.BUTTON_BACKGROUND.clone();
        this.rawColor = Globals.BUTTON_RAW.clone();
        this.hoverColor = Globals.BUTTON_HOVER.clone();
        this.strokeColor = Globals.BUTTON_STROKE.clone();
        this.isSelected = false;
        
        this.content = new GPanel(manager, parent);
        super.dim(this.content.dim().x, this.content.dim().y);
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
    
    @Override
    public Object getProp(String name) {
        Object propertyValue = super.getProp(name);
        
        if (propertyValue == null) {
            switch ((String)name) {
                case "size": return this.dim().x;
                case "isSelected": return this.isSelected();
                case "isTransparent": return this.isTransparent();
                case "isStrokeTransparent": return this.isStrokeTransparent();
                case "backgroundColor": return this.backgroundColor.clone();
                case "strokeColor": return this.strokeColor.clone();
                case "rawColor": return this.rawColor.clone();
                case "hoverColor": return this.hoverColor.clone();
                default:
                    System.out.printf("error: property %s was not found", name);
                    System.exit(-1);
                    return null;
            }
        }
        
        return propertyValue;
    }
    
    @Override
    public boolean setProp(Object name, Object value) {
        boolean cond = super.setProp(name, value);
        
        if (cond == false) {
            if (name instanceof String) {
                switch ((String)name) {
                    case "size":
                        if (value instanceof Integer) {
                            this.setSize((Integer)value);
                            return true;
                        }
                    break;

                    case "isSelected":
                        if (value instanceof Boolean) {
                            this.setSelected((Boolean)value);
                            return true;
                        }
                    break;

                    case "isTransparent":
                        if (value instanceof Boolean) {
                            this.setTransparent((Boolean)value);
                            return true;
                        }
                    break;
                
                    case "isStrokeTransparent":
                        if (value instanceof Boolean) {
                            this.setStrokeTransparent((Boolean)value);
                            return true;
                        }
                    break;

                    case "backgroundColor":
                        if (value instanceof GColor) {
                            this.setBackgroundColor((GColor)value);
                            return true;
                        }
                    break;
                
                    case "rawColor":
                        if (value instanceof GColor) {
                            this.setRawColor((GColor)value);
                            return true;
                        }
                    break;
                
                    case "hoverColor":
                        if (value instanceof GColor) {
                            this.setHoverColor((GColor)value);
                            return true;
                        }
                    break;
                }
            }
        }
        
        return cond;
    }
    
    // Deprecated
    
    @Deprecated
    @Override
    public void pos(Float ... coords) {
        super.pos(coords);
        this.content.pos(coords);
    }
    
    @Deprecated
    @Override
    public void pos(Integer ... coords) {
        super.pos(coords);
        this.content.pos(coords);
    }
    
    @Deprecated
    @Override
    public void dim(Float ... dim) {
        super.dim(dim);
        this.content.dim(dim);
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
     * @deprecated
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
     * @deprecated
     */
    public boolean isSelected() {
        return this.isSelected;
    }
    
    /**
     * Detect if button has a transparent background
     * 
     * @return transparency state
     * @deprecated
     */
    public boolean isTransparent() {
        return this.content.isTransparent();
    }
    
    /**
     * Detect if button has a transparent stroke background
     * 
     * @return transparent state
     * @deprecated
     */
    public boolean isStrokeTransparent() {
        return this.content.isStrokeTransparent();
    }
    
    /**
     * Specify if button has a transparent background
     * 
     * @param transparency state of the transparency
     * @see GButton#isTransparent()
     * @deprecated
     */
    public void setTransparent(boolean transparency) {
        this.content.setTransparent(transparency);
    }
    
    /**
     * Specify if button has a transparent stroke background
     * 
     * @param transparency state of the transparency
     * @see GButton#isTransparent()
     * @deprecated
     */
    public void setStrokeTransparent(boolean transparency) {
        this.content.setStrokeTransparent(transparency);
    }
    
    /**
     * Set the background color for the button
     * 
     * @param color red, green, and blue component
     * @deprecated
     */
    public void setBackgroundColor(GColor color) {
        this.backgroundColor = color.clone();
        this.content.setColor(color);
    }
    
    /**
     * Set the background color for the button
     * 
     * @param color red, green, and blue component
     * @deprecated
     */
    public void setBackgroundColor(Float ... color) {
        this.backgroundColor = new GColor(color);
        this.content.setColor(color);
    }
    
    /**
     * Set the background color for container
     * 
     * @param color red, green, and blue component
     * @deprecated
     */
    public void setBackgroundColor(Integer ... color) {
        this.backgroundColor = new GColor(color);
        this.content.setColor(color);
    }
    
    /**
     * Set the stroke color for the button
     * 
     * @param color red, green, and blue component
     * @deprecated
     */
    public void setStrokeColor(GColor color) {
        this.strokeColor = color.clone();
        this.content.setStrokeColor(color);
    }
    
    /**
     * Set the stroke color for the button
     * 
     * @param color red, green, and blue component
     * @deprecated
     */
    public void setStrokeColor(Float ... color) {
        this.strokeColor = new GColor(color);
        this.content.setStrokeColor(color);
    }
    
    /**
     * Set the stroke color for container
     * 
     * @param color red, green, and blue component
     * @deprecated
     */
    public void setStrokeColor(Integer ... color) {
        this.strokeColor = new GColor(color);
        this.content.setStrokeColor(color);
    }

    /**
     * Set the raw color for the button
     * 
     * @param component red, green, and blue component
     * @deprecated
     */
    public void setRawColor(GColor component) {
        this.rawColor = component.clone();
        this.rawColor.setColor(component);
    }
    
    /**
     * Set the raw color for the button
     * 
     * @param component red, green, and blue component
     * @deprecated
     */
    public void setRawColor(Float ... component) {
        this.rawColor = new GColor(component);
        this.rawColor.setColor(component);
    }
    
    /**
     * Set the raw color for the button
     * 
     * @param component red, green, and blue component
     * @deprecated
     */
    public void setRawColor(Integer ... component) {
        this.rawColor = new GColor(component);
        this.rawColor.setColor(component);
    }
    
    /**
     * Set the hover color for the button
     * 
     * @param component red, green, and blue component
     * @deprecated
     */
    public void setHoverColor(GColor component) {
        this.hoverColor = component.clone();
        this.hoverColor.setColor(component);
    }
    
    /**
     * Set the hover color for the button
     * 
     * @param component red, green, and blue component
     * @deprecated
     */
    public void setHoverColor(Float ... component) {
        this.hoverColor = new GColor(component);
        this.hoverColor.setColor(component);
    }
    
    /**
     * Set the hover color for the button
     * 
     * @param component red, green, and blue component
     * @deprecated
     */
    public void setHoverColor(Integer ... component) {
        this.hoverColor = new GColor(component);
        this.hoverColor.setColor(component);
    }
    
    /**
     * Specify if button is selected
     * 
     * @param isSelected selected state
     * @deprecated
     */
    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }
}