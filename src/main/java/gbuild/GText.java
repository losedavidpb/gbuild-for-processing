package gbuild;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PFont;
import processing.core.PVector;

/**
 * <p>
 * Component for texts
 * </p>
 * 
 * <p>
 * Processing includes utilities to draw customized text, but
 * does not consider it as a data type. To solve this, gBuild
 * provides this class as the implementation of a text component
 * that has a font, sixe, aligment, and so on.
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
     * Default value for text string
     */
    public static final String TEXT_VALUE = "Hello, world!";

    /**
     * Default value for text size
     */
    public static final int TEXT_SIZE = 11;
    
    /**
     * Default vlaue for text color
     */
    public static final GColor TEXT_COLOR = new GColor(0, 0, 0);
    
    /**
     * Default value for text mode
     */
    public static final int TEXT_MODE = PConstants.MODEL;
    
    /**
     * Default value for text align
     */
    public static final int TEXT_ALIGN = PConstants.LEFT;
    
    /**
     * Create a new instance of a text
     * 
     * @param manager Processing manager
     * @param parent component parent
     * @see GComponent#GComponent(processing.core.PApplet, api.gbuild.GComponent) 
     */
    public GText(PApplet manager, GComponent parent) {
        super(manager, parent);
        this.value = TEXT_VALUE;
        this.textColor = TEXT_COLOR;
        this.tsize = TEXT_SIZE;
        this.tmode = TEXT_MODE;
        this.talign = TEXT_ALIGN;
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
     * Get the components for text component
     * 
     * @return components
     */
    public float[] color() {
        return this.textColor.clone().color();
    }
    
    /**
     * Get the font value for text component
     * 
     * @return font value
     */
    public PFont font() {
        return this.fontValue;
    }

    /**
     * Set the background color for text
     * 
     * @param color components
     * @see GColor#setColor(java.lang.Integer...)
     */
    public void setColor(GColor color) {
        this.textColor = new GColor(0, 0, 0);
        this.textColor.setColor(color);
    }
    
    /**
     * Set the background color for text
     * 
     * @param component components
     * @see GColor#setColor(java.lang.Integer...)
     */
    public void setColor(Integer ... component) {
        this.textColor = new GColor(0, 0, 0);
        this.textColor.setColor(component);
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
        boolean cond = talign == PConstants.LEFT || talign == PConstants.RIGHT;
        cond = cond || talign == PConstants.CENTER;
        
        if (!cond) {
            PApplet.println("error GText.setAlign: invalid text aligment");
            System.exit(1);
        }

        this.talign = talign;
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
     */
    public void setMode(int tmode) {
        boolean cond = tmode == PConstants.MODEL || tmode == PConstants.SHAPE;
        
        if (!cond) {
            PApplet.println("error GText.setMode: invalid text mode");
            System.exit(1);
        }

        this.tmode = tmode;
    }
    
    /**
     * Set font value for text component
     * 
     * @param font font value
     */
    public void setFont(PFont font) {
        this.fontValue = font;
    }

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
  
    @Override
    public void draw() {
        if (this.isVisible()) {
            this.listenEvents();
            
            manager().pushMatrix();
            manager().pushStyle();
            manager().translate(this.pos().x, this.pos().y);
            manager().textAlign(this.talign);
            manager().textMode(this.tmode);
            manager().textSize(this.tsize);
            textColor.applyFillColor(manager());
            manager().text(this.value, 0, 0);
            manager().popStyle();
            manager().popMatrix();
        }
    }
}