package api.gbuild.component;

import api.gbuild.GColor;
import api.gbuild.GComponent;
import api.gbuild.Globals;
import processing.core.PApplet;
import static processing.core.PApplet.abs;
import processing.core.PConstants;

/**
 * Component defined by a text.
 * 
 * This type of simple component draws at a
 * specific location a text.
 * 
 * Since Processing offers different functions
 * to customize a text, these class includes
 * properties for color, size, mode, and align.
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
     */
    public GText(PApplet manager, GComponent parent, String value, float x, float y) {
        super(manager, parent);
        super.pos(x, y);
        this.textColor = new GColor(255, 255, 255);
        this.value = value;
        this.tsize = Globals.TEXT_SIZE;
        this.tmode = PConstants.SHAPE;
        this.talign = PConstants.LEFT;
    }
    
    /**
     * Create a new instance of a text
     * 
     * @param manager Processing manager
     * @param value text value
     * @param x x component for location
     * @param y y component for location
     */
    public GText(PApplet manager, String value, float x, float y) {
        this(manager, null, value, x, y);
    }
    
    /**
     * Get the text value that will be drawn
     * 
     * @return text string
     */
    public String value() {
        return this.value;
    }
    
    /**
     * Get the text size
     * 
     * @return text size
     */
    public int size() {
        return this.tsize;
    }
    
    /**
     * Return the alignment
     * 
     * @return text alignment
     */
    public int align() {
        return this.talign;
    }
    
    /**
     * Return the text mode
     * 
     * @return text mode
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
     * Set the background color for container
     * 
     * @param component red, green, and blue component
     */
    public void setColor(Float ... component) {
        this.textColor.setColor(component);
    }
    
    /**
     * Set the background color for container
     * 
     * @param component red, green, and blue component
     */
    public void setColor(Integer ... component) {
        this.textColor.setColor(component);
    }
    
    /**
     * Specify the size of the text
     * 
     * @param tsize text size
     */
    public void setSize(int tsize) {
        this.tsize = tsize;
    }
    
    /**
     * Specify the alignment of the text.
     * Available values are LEFT, RIGHT, and CENTER
     * 
     * @param talign alignment
     */
    public void setAlign(int talign) {
        boolean cond = talign == PConstants.LEFT || talign == PConstants.RIGHT || talign == PConstants.CENTER;
        if (cond == true) this.talign = talign;
    }
    
    /**
     * Specify the mode of the text.
     * Available values are MODEL and SHAPE
     * 
     * @param tmode text mode
     */
    public void setMode(int tmode) {
        boolean cond = tmode == PConstants.MODEL || tmode == PConstants.SHAPE;
        if (cond == true) this.tmode = tmode;
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