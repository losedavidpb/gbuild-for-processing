package api.gbuild.component;

import api.gbuild.GColor;
import api.gbuild.Globals;
import processing.core.PApplet;
import processing.core.PConstants;
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
    private String value;
    private final GColor textColor;

    /**
     * Create a new instance of a text
     * 
     * @param manager Processing manager
     * @param parent component parent
     * @param value text value
     * @param x x component for location
     * @param y y component for location
     * @see GComponent#GComponent(processing.core.PApplet, api.gbuild.GComponent) 
     */
    public GText(PApplet manager, GComponent parent, String value, float x, float y) {
        super(manager, parent);
        super.pos(x, y);
        this.textColor = new GColor(255, 255, 255);
        this.value = value;
        this.tsize = Globals.TEXT_SIZE;
        this.tmode = PConstants.MODEL;
        this.talign = PConstants.LEFT;
    }
    
    /**
     * Create a new instance of a text
     * 
     * @param manager Processing manager
     * @param value text value
     * @param x x component for location
     * @param y y component for location
     * @see GComponent#GComponent(processing.core.PApplet) 
     */
    public GText(PApplet manager, String value, float x, float y) {
        this(manager, null, value, x, y);
    }
    
    /**
     * Get the text that will be drawn
     * 
     * @return text string
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
     */
    public int mode() {
        return this.tmode;
    }
    
    /**
     * Specify the text that will be drawn
     * 
     * @param value text string
     */
    public void setText(String value) {
        this.value = value;
    }
    
    /**
     * Set the background color for text
     * 
     * @param component red, green, and blue component
     * @see GColor#setColor(java.lang.Float...)
     */
    public void setColor(Float ... component) {
        this.textColor.setColor(component);
    }
    
    /**
     * Set the background color for text
     * 
     * @param component red, green, and blue component
     * @see GColor#setColor(java.lang.Integer...)
     */
    public void setColor(Integer ... component) {
        this.textColor.setColor(component);
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
     */
    public void setAlign(int talign) {
        boolean cond = talign == PConstants.LEFT || talign == PConstants.RIGHT || talign == PConstants.CENTER;
        if (cond == true) this.talign = talign;
    }
    
    /**
     * Specify the mode of the text.
     * Available values are MODEL and SHAPE
     * 
     * <p>
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
     */
    public void setMode(int tmode) {
        boolean cond = tmode == PConstants.MODEL || tmode == PConstants.SHAPE;
        if (cond == true) this.tmode = tmode;
    }
    
    @Override
    public PVector dim() {
        manager().pushMatrix();
        manager().textSize(tsize);
        float x = manager().textWidth(value());
        float y = manager().textAscent() + manager().textDescent();
        manager().popMatrix();
        return new PVector(x, y);
    }
  
    @Override
    public void draw() {
        if (this.isVisible()) {
            float[] c = textColor.color();
            
            manager().pushMatrix();
            manager().translate(this.pos().x, this.pos().y);
            manager().textAlign(this.talign);
            manager().textMode(this.tmode);
            manager().textSize(this.tsize);
            manager().fill(manager().color(c[0], c[1], c[2]));
            manager().text(this.value, 0, 0);
            manager().popMatrix();
        }
    }
}