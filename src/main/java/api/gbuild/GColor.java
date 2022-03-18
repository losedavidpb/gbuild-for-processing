package api.gbuild;

import processing.core.PApplet;
import processing.core.PShape;

/**
 * <p>
 * Data type which stores a RGB color
 * </p>
 * 
 * <p>
 * Processing language environment has utilities which can be
 * used to define colors using RGB or HSV format. Considering
 * this, this class was designed to offer to developers am easy
 * way to define on this library colors for components.
 * </p>
 * 
 * <p>
 * It is important to notice that the implementation of this
 * class only considers the RGB format, so transparency and other
 * additional properties are not included. However, this utilities
 * would be added in future releases.
 * </p>
 * 
 * <p>
 * Have a look at the Processing reference
 * <a href="https://processing.org/reference/color_datatype.html">here</a>
 * for more information
 * </p>
 * 
 * @author David Parreño Barbuzano 
 */
public final class GColor {
    private float[] colorValue;
    private boolean isTransparent;
    
    /**
     * Create a new instance of a RGB color
     * 
     * <p>
     * Since RGB colors have three component (red, green, blue),
     * this class can define a color passing one, two, or three
     * of these components, so, when a value is not defined, will
     * be equal to zero or null.
     * </p>
     * 
     * @param color red, green, and blue integer component
     * @see GColor#setColor(java.lang.Integer...)
     * @see GColor#setColor(java.lang.Float...)
     */
    public GColor(GColor color) {
        this.setColor(color);
        this.isTransparent = false;
    }
    
    /**
     * Create a new instance of a RGB color
     * 
     * <p>
     * Since RGB colors have three component (red, green, blue),
     * this class can define a color passing one, two, or three
     * of these components, so, when a value is not defined, will
     * be equal to zero or null.
     * </p>
     * 
     * @param component red, green, and blue float components
     * @see GColor#setColor(java.lang.Float...) 
     */
    public GColor(Float ... component) {
        this.setColor(component);
        this.isTransparent = false;
    }
    
    /**
     * Create a new instance of a RGB color
     * 
     * <p>
     * Since RGB colors have three component (red, green, blue),
     * this class can define a color passing one, two, or three
     * of these components, so, when a value is not defined, will
     * be equal to zero or null.
     * </p>
     * 
     * @param component red, green, and blue integer component
     * @see GColor#setColor(java.lang.Integer...) 
     */
    public GColor(Integer ... component) {
        this.setColor(component);
        this.isTransparent = false;
    }
    
    /**
     * Check if current color is transparent
     * 
     * @return transparency value
     */
    public boolean isTransparent() {
        return this.isTransparent;
    }
    
    /**
     * Apply to current sketch the fill color
     * 
     * <p>
     * Since the color value might not be important
     * to know, it is preferable to have a method
     * that applies the RGB color into sketch
     * without the knowledge of its value
     * </p>
     * 
     * @param manager Processing manager
     */
    public void applyFillColor(PApplet manager) {
        if (manager != null) {
            if (!isTransparent) {
                float[] c = this.colorValue;
                manager.fill(c[0], c[1], c[2]);
                return;
            }
            
            manager.noFill();
        }
    }
    
    /**
     * Apply to current shape the fill color
     * 
     * <p>
     * Since the color value might not be important
     * to know, it is preferable to have a method
     * that applies the RGB color into sketch
     * without the knowledge of its value
     * </p>
     * 
     * @param figure shape to fill
     */
    public void applyFillColor(PShape figure) {
        if (figure != null) {
            if (!isTransparent) {
                float[] c = this.colorValue;
                figure.fill(c[0], c[1], c[2]);
                return;
            }
            
            figure.noFill();
        }
    }
    
    /**
     * Apply to current sketch the stroke color
     * 
     * <p>
     * Since the color value might not be important
     * to know, it is preferable to have a method
     * that applies the RGB color into sketch
     * without the knowledge of its value
     * </p>
     * 
     * @param manager Processing manager
     */
    public void applyStrokeColor(PApplet manager) {
        if (manager != null) {
            if (!isTransparent) {
                float[] c = this.colorValue;
                manager.stroke(c[0], c[1], c[2]);
                return;
            }
        
            manager.noStroke();
        }
    }
    
    /**
     * Apply to current shape the stroke color
     * 
     * <p>
     * Since the color value might not be important
     * to know, it is preferable to have a method
     * that applies the RGB color into sketch
     * without the knowledge of its value
     * </p>
     * 
     * @param figure shape to stroke
     */
    public void applyStrokeColor(PShape figure) {
        if (figure != null) {
            if (!isTransparent) {
                float[] c = this.colorValue;
                figure.stroke(c[0], c[1], c[2]);
                return;
            }
            
            figure.noStroke();
        }
    }
    
    /**
     * Return the float values for each RGB component
     * 
     * <p>
     * This method would give all the values that needs
     * to be passed to Processing color function in order
     * to define a RGB color. 
     * </p>
     * 
     * @return RGB components
     */
    public float[] color() {
        return this.clone().colorValue;
    }
    
    /**
     * Set transparency for current color
     * 
     * <p>
     * If transparency is true, color will not
     * be applied to current sketch, at the Processing
     * method wich disable that property would be executed
     * </p>
     * 
     * @param isTransparent transparent state
     */
    public void setTransparent(boolean isTransparent) {
        this.isTransparent = isTransparent;
    }
    
    /**
     * Set a value for each RGB component
     * 
     * @param color color value
     * @see GColor#setColor(java.lang.Float...) 
     * @see GColor#setColor(java.lang.Integer...) 
     */
    public void setColor(GColor color) {
        float[] c = new float[3];
        c[0] = color.color()[0];
        c[1] = color.color()[1];
        c[2] = color.color()[2];
        
        setColor(c[0], c[1], c[2]);
    }
    
    /**
     * Set a value for each RGB component
     * 
     * <p>
     * This method defines each component that defines a color using
     * RGB format. It is possible to create a color passing from one
     * to three values, so there are lots of ways to call this method.
     * </p>
     * 
     * <p>
     * Talking about how to pass parameters, null values would be
     * assigned to zero, so you can construct a RGB color passing
     * one, two, or three values, and each of them can be a numeric
     * value or null.
     * </p>
     * 
     * @param component red, green, and blue float component
     * @see GColor#GColor(java.lang.Float...) 
     */
    public void setColor(Float ... component) {
        if (component.length == 0) return;
        
        if (component.length >= 1 && component.length <= 3) {
            this.colorValue = new float[3];
            for (int i = 0; i < colorValue.length; i++) colorValue[i] = 0;
            
            for (int i = 0; i < component.length; i++) {
                if (component[i] != null)
                    colorValue[i] = component[i];
            }
        }
    }
    
    /**
     * Set a value for each RGB component
     * 
     * <p>
     * This method defines each component that defines a color using
     * RGB format. It is possible to create a color passing from one
     * to three values, so there are lots of ways to call this method.
     * </p>
     * 
     * <p>
     * Talking about how to pass parameters, null values would be
     * assigned to zero, so you can construct a RGB color passing
     * one, two, or three values, and each of them can be a numeric
     * value or null.
     * </p>
     * 
     * @param component red, green, and blue float component
     * @see GColor#GColor(java.lang.Integer...) 
     */
    public void setColor(Integer ... component) {
        if (component.length == 0) return;
        
        if (component.length >= 1 && component.length <= 3) {
            this.colorValue = new float[3];
            for (int i = 0; i < colorValue.length; i++) colorValue[i] = 0;
        
            for (int i = 0; i < component.length; i++) {
                if (component[i] != null)
                    colorValue[i] = component[i];
            }
        }
    }
    
    @Override
    public GColor clone() {
        GColor color = new GColor(0, 0, 0);
        color.colorValue[0] = this.colorValue[0];
        color.colorValue[1] = this.colorValue[1];
        color.colorValue[2] = this.colorValue[2];
        return color;
    }
}
