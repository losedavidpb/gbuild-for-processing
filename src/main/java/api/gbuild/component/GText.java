package api.gbuild.component;

import api.gbuild.GComponent;
import processing.core.PApplet;
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
 * @author David
 */
public class GText extends GComponent {
    private int tsize, talign, tmode;
    private String value;
    private int textColor;

    public GText(PApplet manager, String value, float x, float y) {
        super(manager);
        super.pos(x, y);
        this.textColor = manager.color(255, 255, 255);
        this.value = value;
        this.tsize = 11;
        this.tmode = PConstants.SHAPE;
        this.talign = PConstants.LEFT;
    }
    
    public GText(PApplet manager, GContainer parent, String value, float x, float y) {
        super(manager, parent);
        super.pos(x, y);
        this.textColor = manager.color(255, 255, 255);
        this.value = value;
        this.tsize = 11;
        this.tmode = PConstants.SHAPE;
        this.talign = PConstants.LEFT;
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
     * Specify the text that will be drawn
     * 
     * @param value text string
     */
    public void setText(String value) {
        this.value = value;
    }
    
    /**
     * Set the color for text
     * 
     * @param r red component
     * @param g green component
     * @param b blue component
     */
    public void setColor(float r, float g, float b) {
        this.textColor = manager().color(r, g, b);
    }
    
    /**
     * Set the color color for text
     * 
     * @param value integer value
     */
    public void setColor(float value) {
        this.textColor = manager().color(value);
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
            manager().pushMatrix();
            manager().translate(this.pos().x, this.pos().y);
            manager().textAlign(this.talign);
            manager().textMode(this.tmode);
            manager().textSize(this.tsize);
            manager().fill(this.textColor);
            manager().text(this.value, 0, 0);
            manager().popMatrix();
        }
    }
}