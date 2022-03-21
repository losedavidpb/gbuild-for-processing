package gbuild;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PFont;
import processing.core.PVector;

/**
 * <p>
 * Component defined with a text
 * </p>
 * 
 * <p>
 * Processing language evironment does not include an explicit data
 * type for texts, but it offers lots of functions that allows to
 * define properties for texts that will be drawn. This class was
 * designed in order to incorporate texts as a component that can
 * be modified with the equivalent Processing functions
 * </p>
 * 
 * <p>
 * This release does include the possibility to define the font for
 * texts, but this will be implemented for future revisions.
 * </p>
 * 
 * <p>
 *  For more information about text, click
 *  <a href="https://processing.org/reference/text_.html">here</a>
 * </p>
 * 
 * @author David Parreño Barbuzano
 */
public class GText extends GComponent {
    private int tsize, talign, tmode;
    private GColor textColor;
    private PFont fontValue;
    private String value;

    /**
     * Create a new instance of a text
     * 
     * @param manager Processing manager
     * @param parent component parent
     * @see GComponent#GComponent(processing.core.PApplet, api.gbuild.GComponent) 
     */
    public GText(PApplet manager, GComponent parent) {
        super(manager, parent);
        this.value = "Default";
        this.textColor = Globals.TEXT_COLOR;
        this.tsize = Globals.TEXT_SIZE;
        this.tmode = Globals.TEXT_MODE;
        this.talign = Globals.TEXT_ALIGN;
    }
    
    /**
     * Create a new instance of a text
     * 
     * @param manager Processing manager
     * @see GComponent#GComponent(processing.core.PApplet) 
     */
    public GText(PApplet manager) {
        this(manager, null);
    }
  
    @Override
    public void draw() {
        if (this.isVisible()) {
            manager().pushMatrix();
            manager().translate(this.pos().x, this.pos().y);
            manager().textAlign(this.talign);
            manager().textMode(this.tmode);
            manager().textSize(this.tsize);
            textColor.applyFillColor(manager());
            manager().text(this.value, 0, 0);
            manager().popMatrix();
        }
    }
    
    @Override
    public Object prop(String name) {
        Object propertyValue = super.prop(name);
        
        if (propertyValue == null) {
            switch ((String)name) {
                case "mode": return this.mode();
                case "align": return this.align();
                case "size": return this.size();
                case "value": return this.value();
                case "color": return this.textColor.clone();
                case "font": return this.fontValue;
                default: return null;
            }
        }
        
        return propertyValue;
    }
    
    @Override
    public boolean prop(Object name, Object value) {
        boolean cond = super.prop(name, value);
        
        if (name instanceof String) {
            switch ((String)name) {
                case "mode":
                    if (value instanceof Integer) {
                        return this.setMode((Integer)value);
                    }
                break;

                case "align":
                    if (value instanceof Integer) {
                        return this.setAlign((Integer)value);
                    }
                break;

                case "size":
                    if (value instanceof Integer) {
                        this.setSize((Integer)value);
                        return true;
                    }
                break;

                case "value":
                    if (value instanceof String) {
                        this.setText((String)value);
                        return true;
                    }
                break;
                
                case "color":
                    if (value instanceof GColor) {
                        this.setColor((GColor)value);
                        return true;
                    }
                break;
                
                case "font":
                    if (value instanceof PFont) {
                        this.setFont((PFont)value);
                        return true;
                    }
                break;
                
            }
            
            return false;
        }
        
        return cond;
    }
    
    // Deprecated
    
    @Deprecated
    @Override
    public PVector dim() {
        manager().pushMatrix();
        manager().textSize(tsize);
        float dimx = manager().textWidth(value());
        float dimy = manager().textAscent() + manager().textDescent();
        manager().popMatrix();
        return new PVector(dimx, dimy);
    }
    
    /**
     * Get the text that will be drawn
     * 
     * @return text string
     * @deprecated
     */
    public String value() {
        return this.value;
    }
    
    /**
     * Get the size for text component
     * 
     * <p>
     * Text size is measured in units of pixels
     * </p>
     * 
     * <p>
     *  Fore more information about text alignment, click
     *  <a href="https://processing.org/reference/textSize_.html">here</a>
     * </p>
     * 
     * @return text size
     * @deprecated
     */
    public int size() {
        return this.tsize;
    }
    
    /**
     * Get the alignment for text component
     * 
     * <p>
     *  For more information about text alignment, click
     *  <a href="https://processing.org/reference/textAlign_.html">here</a>
     * </p>
     * 
     * @return text alignment
     * @deprecated
     */
    public int align() {
        return this.talign;
    }
    
    /**
     * Get the mode for text component
     * 
     * <p>
     *  For more information about text mode, click
     *  <a href="https://processing.org/reference/textMode_.html">here</a>
     * </p>
     * 
     * @return text mode
     * @see GText#setMode(int)
     * @deprecated
     */
    public int mode() {
        return this.tmode;
    }
    
    /**
     * Get the RGB components for text component
     * 
     * @return RGB components
     * @deprecated
     */
    public float[] color() {
        return this.textColor.clone().color();
    }
    
    /**
     * Get the font value for text component
     * 
     * @return font value
     * @deprecated
     */
    public PFont font() {
        return this.fontValue;
    }
    
    /**
     * Set the background color for text
     * 
     * @param color red, green, and blue component
     * @see GColor#setColor(java.lang.Float...)
     * @see GColor#setColor(java.lang.Integer...)
     * @deprecated
     */
    public void setColor(GColor color) {
        this.textColor = new GColor(0, 0, 0);
        this.textColor.setColor(color);
    }
    
    /**
     * Set the background color for text
     * 
     * @param component red, green, and blue component
     * @see GColor#setColor(java.lang.Float...)
     * @deprecated
     */
    public void setColor(Float ... component) {
        this.textColor = new GColor(0, 0, 0);
        this.textColor.setColor(component);
    }
    
    /**
     * Set the background color for text
     * 
     * @param component red, green, and blue component
     * @see GColor#setColor(java.lang.Integer...)
     * @deprecated
     */
    public void setColor(Integer ... component) {
        this.textColor = new GColor(0, 0, 0);
        this.textColor.setColor(component);
    }
    
    /**
     * Specify the text that will be drawn
     * 
     * @param value text string
     * @deprecated
     */
    public void setText(String value) {
        this.value = value;
    }
    
    /**
     * Specify the size of the text
     * 
     * <p>
     *  Text size is measured in units of pixels
     * </p>
     * 
     * <p>
     *  Fore more information about text alignment, click
     *  <a href="https://processing.org/reference/textSize_.html">here</a>
     * </p>
     * 
     * @param tsize text size
     * @deprecated
     */
    public void setSize(int tsize) {
        this.tsize = tsize;
    }
    
    /**
     * Specify the alignment of the text.
     * 
     * <p>
     * Available values are LEFT, RIGHT, and CENTER, so the modification
     * would not be done if the passed value is not one of these values
     * </p>
     * 
     * <p>
     *  For more information about text alignment, click
     *  <a href="https://processing.org/reference/textAlign_.html">here</a>
     * </p>
     * 
     * @param talign text alignment
     * @return if alignment was changed
     * @deprecated
     */
    public boolean setAlign(int talign) {
        boolean cond = talign == PConstants.LEFT || talign == PConstants.RIGHT || talign == PConstants.CENTER;
        if (cond == true) this.talign = talign;
        return cond;
    }
    
    /**
     * Specify the mode of the text.Available values are MODEL and SHAPE<p>
     * Available values are MODEL and SHAPE, so the modification
     * would not be done if the passed value is not one of these values 
     * </p>
     * 
     * <p>
     *  For more information about text alignment, click
     *  <a href="https://processing.org/reference/textMode_.html">here</a>
     * </p>
     * 
     * @param tmode text mode
     * @return if mode was changed
     * @deprecated
     */
    public boolean setMode(int tmode) {
        boolean cond = tmode == PConstants.MODEL || tmode == PConstants.SHAPE;
        if (cond == true) this.tmode = tmode;
        return cond;
    }
    
    /**
     * Set font value for text component
     * 
     * @param font font value
     */
    public void setFont(PFont font) {
        this.fontValue = font;
    }
}