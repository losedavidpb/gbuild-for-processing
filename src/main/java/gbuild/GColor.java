package gbuild;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PShape;

/**
 * <p>
 * Data type for storing colors
 * </p>
 * 
 * <p>
 * Processing language environment provides utilities to
 * define and store color values. The class <i>GColor</i>
 * includes methods to declare colors for UI components.
 * </p>
 * 
 * <p>
 * Since Processing has already got a data type for storing
 * color values, <i>GColor</i> incorporates the great majority
 * of methods that Processing has. However, the use of this
 * class is better than Processing methods, because of the
 * implementation of UI components of gBuild.
 * </p>
 * 
 * <p>
 * Talking about the declaration, color values could be defined
 * using two different formats: RGB and HSB. For those cases,
 * it is possible to define a color with hexadecimal format, or
 * passing the values for each component. It is important to
 * notice that each component that forms a color is fixed at
 * a defined range, so color could not be created if values
 * are outside the specific limits
 * </p>
 * 
 * <p>
 * Relative to color declaration, here there are some examples
 * that shows a demostration to define a color at this class
 * </p>
 * 
 * <pre>
 *  GColor c;
 *
 *  c = new GColor();
 *  c.setMode(RGB, 255, 255, 255);
 *  c.setColor(255, 255, 255);
 * 
 *  c = new GColor();
 *  c.setMode(HSB, 255, 255, 255);
 *  c.setColor(255, 255, 255);
 * </pre>
 * 
 * <p>
 * For more information about Processing colors, click
 * <a href="https://processing.org/reference/color_datatype.html">here</a>.
 * </p>
 * 
 * @author David Parreño Barbuzano
 * @version 4.1.0
 * @since   2.0.0
 */
public final class GColor {
    private float[] components;
    private int[] maxMode;
    private boolean isOpaque;
    private int mode;
    
    /**
     * Enumerator for color formats
     */
    public enum COLOR_MODE { RGB, HSB };
    
    /**
     * Default value for each component for colors
     */
    public static final int DEFAULT_COLOR_VALUE = 0;
    
    /**
     * Default value for each maximum component for colors
     */
    public static final int DEFAULT_COLOR_MAX = 255;
    
    /**
     * Default format for colors
     */
    public static final COLOR_MODE DEFAULT_COLOR_MODE = COLOR_MODE.RGB;

    /**
     * Create a new instance for a color
     */
    public GColor() {
        this.components = new float[3];
        this.maxMode = new int[4];
        this.isOpaque = true;

        this.setColor(DEFAULT_COLOR_VALUE, DEFAULT_COLOR_VALUE, DEFAULT_COLOR_VALUE);
        this.setMode(DEFAULT_COLOR_MODE);
    }

    /**
     * Create a new instance of a color
     * 
     * @param mode color mode (RGB or HSB)
     */
    public GColor(COLOR_MODE mode) {
        this();
        this.setMode(mode);
    }

    /**
     * Create a new instance of a color
     * 
     * @param mode color mode (RGB or HSB)
     * @param components value for each component 
     */
    public GColor(COLOR_MODE mode, Integer ... components) {
        this(mode);
        this.setColor(components);
    }

    /**
     * Create a new instance of a color
     * 
     * @param components value for each component 
     */
    public GColor(Integer ... components) {
        this();
        this.setColor(components);
    }
    
    /**
     * Create a new instance of a color
     * 
     * @param mode color mode (RGB or HSB)
     * @param components value for each component 
     */
    public GColor(COLOR_MODE mode, Float ... components) {
        this(mode);
        this.setColor(components);
    }

    /**
     * Create a new instance of a color
     * 
     * @param components value for each component 
     */
    public GColor(Float ... components) {
        this();
        this.setColor(components);
    }

    /**
     * Get components for current color
     * 
     * <p>
     * This method would return all components that are
     * necessary to define a color at RGB or HSB format.
     * </p>
     * 
     * @return numeric components
     */
    public float[] color() {
        return this.components;
    }

    /**
     * Set a value for each component
     * 
     * @param color color value
     * @see GColor#setColor(java.lang.Integer...) 
     */
    public void setColor(GColor color) {
        this.setMode(color.mode, color.maxMode[0],
            color.maxMode[1], color.maxMode[2], color.maxMode[3]
        );

        float[] c = new float[3];
        c[0] = color.color()[0];
        c[1] = color.color()[1];
        c[2] = color.color()[2];
        
        setColor(c[0], c[1], c[2]);
    }
    
    /**
     * Set a value for each component
     * 
     * <p>
     * This method defines each component of a color using RGB
     * or HSB format. It is possible to create a color passing
     * from one to three values, so there are lots of ways to
     * call this method to define a color.
     * </p>
     * 
     * <p>
     * Talking about parameters, null values would be assigned to
     * zero, so you can construct a color passing one, two, or three
     * values, and each of them can be a number or null
     * </p>
     * 
     * @param component numeric components
     */
    public void setColor(Float ... component) {
        if (component.length == 0) return;
        
        if (component.length >= 1 && component.length <= 3) {
            for (int i = 0; i < components.length; i++)
                this.components[i] = 0;
        
            for (int i = 0; i < component.length; i++) {
                if (component[i] != null) {
                    components[i] = component[i];

                    if (component[i] > maxMode[i]) {
                        PApplet.println("error: component is greater than max value");
                        System.exit(1);
                    }
                }
            }
        }
    }
    
    /**
     * Set a value for each component
     * 
     * <p>
     * This method defines each component of a color using RGB
     * or HSB format. It is possible to create a color passing
     * from one to three values, so there are lots of ways to
     * call this method to define a color.
     * </p>
     * 
     * <p>
     * Talking about parameters, null values would be assigned to
     * zero, so you can construct a color passing one, two, or three
     * values, and each of them can be a number or null
     * </p>
     * 
     * @param component numeric components
     */
    public void setColor(Integer ... component) {
        if (component.length == 0) return;
        
        if (component.length >= 1 && component.length <= 3) {
            for (int i = 0; i < components.length; i++)
                this.components[i] = 0;
        
            for (int i = 0; i < component.length; i++) {
                if (component[i] != null) {
                    components[i] = component[i];

                    if (component[i] > maxMode[i]) {
                        PApplet.println("error: component is greater than max value");
                        System.exit(1);
                    }
                }
            }
        }
    }

    /**
     * Define a color using hexadecimal notacion
     * 
     * <p>
     * Hexadecimal notacion is restricted to 0x
     * format, so it is not possible to define
     * a value with web format (#...)
     * </p>
     * 
     * @param manager Processing manager
     * @param hexValue hexadecimal value
     */
    public void setColor(PApplet manager, byte hexValue) {
        if (manager == null) {
            PApplet.println("error: undefined sketch");
            System.exit(1);
        }
        
        this.setColor(
            (int)manager.red(hexValue),
            (int)manager.green(hexValue),
            (int)manager.blue(hexValue)
        );
    }

    /**
     * Check if color is opaque
     * 
     * @return opaque state
     */
    public boolean isOpaque() {
        return this.isOpaque;
    }

    /**
     * Set opaque state for color
     * 
     * @param isOpaque opaque state
     */
    public void setOpaque(boolean isOpaque) {
        this.isOpaque = isOpaque;
    }

    /**
     * Get the format for current color
     * 
     * @return RGB or HSB format
     */
    public int mode() {
        return this.mode;
    }
    
    /**
     * Adjust color mode
     * 
     * <p>
     * Since max values are not defined, color would
     * be defined by its default number
     * </p>
     * 
     * @param mode color mode (RGB or HSB format)
     */
    public void setMode(int mode) {
        this.setMode(mode, DEFAULT_COLOR_MAX);
    }
    
    /**
     * Adjust color mode
     * 
     * <p>
     * Since max values are not defined, color would
     * be defined by its default number
     * </p>
     * 
     * @param mode color mode (RGB or HSB format)
     */
    public void setMode(COLOR_MODE mode) {
        int _mode = -1;
        if (mode == COLOR_MODE.RGB) _mode = PConstants.RGB;
        if (mode == COLOR_MODE.HSB) _mode = PConstants.HSB;
        this.setMode(_mode, DEFAULT_COLOR_MAX);
    }

    /**
     * Adjust color mode and max value for each component
     * 
     * @param mode color mode (RGB or HSB format)
     * @param max max value for all components
     */
    public void setMode(int mode, int max) {
        if (mode != PConstants.RGB && mode != PConstants.HSB) {
            PApplet.println("error GColor.setMode: invalid mode for colors");
            System.exit(1);
        }

        this.mode = mode;
        for (int i = 0; i < 4; i++) maxMode[i] = max;
    }
    
    /**
     * Adjust color mode and max value for each component
     * 
     * @param mode color mode (RGB or HSB format)
     * @param max max value for all components
     */
    public void setMode(COLOR_MODE mode, int max) {
        if (mode != COLOR_MODE.RGB && mode != COLOR_MODE.HSB) {
            PApplet.println("error GColor.setMode: invalid mode for colors");
            System.exit(1);
        }

        if (mode == COLOR_MODE.RGB) this.mode = PConstants.RGB;
        if (mode == COLOR_MODE.HSB) this.mode = PConstants.HSB;
        for (int i = 0; i < 4; i++) maxMode[i] = max;
    }

    /**
     * Adjust color mode and max value for each component
     * 
     * @param mode color mode (RGB or HSB format)
     * @param max1 max value for first component
     * @param max2 max value for second component
     * @param max3 max value for third component
     */
    public void setMode(int mode, int max1, int max2, int max3) {
        if (mode != PConstants.RGB && mode != PConstants.HSB) {
            PApplet.println("error: invalid mode for colors");
            System.exit(1);
        }

        this.mode = mode;
        this.maxMode[0] = max1;
        this.maxMode[1] = max2;
        this.maxMode[2] = max3;
    }
    
    /**
     * Adjust color mode and max value for each component
     * 
     * @param mode color mode (RGB or HSB format)
     * @param max1 max value for first component
     * @param max2 max value for second component
     * @param max3 max value for third component
     */
    public void setMode(COLOR_MODE mode, int max1, int max2, int max3) {
        if (mode != COLOR_MODE.RGB && mode != COLOR_MODE.HSB) {
            PApplet.println("error: invalid mode for colors");
            System.exit(1);
        }

        if (mode == COLOR_MODE.RGB) this.mode = PConstants.RGB;
        if (mode == COLOR_MODE.HSB) this.mode = PConstants.HSB;
        this.maxMode[0] = max1;
        this.maxMode[1] = max2;
        this.maxMode[2] = max3;
    }

    /**
     * Adjust color mode and max value for each component
     * 
     * @param mode color mode (RGB or HSB format)
     * @param max1 max value for first component
     * @param max2 max value for second component
     * @param max3 max value for third component
     * @param maxA max value for alpha component
     */
    public void setMode(int mode, int max1, int max2, int max3, int maxA) {
        if (mode != PConstants.RGB && mode != PConstants.HSB) {
            PApplet.println("error: invalid mode for colors");
            System.exit(1);
        }

        this.mode = mode;
        this.maxMode[0] = max1;
        this.maxMode[1] = max2;
        this.maxMode[2] = max3;
        this.maxMode[3] = maxA;
    }
    
    /**
     * Adjust color mode and max value for each component
     * 
     * @param mode color mode (RGB or HSB format)
     * @param max1 max value for first component
     * @param max2 max value for second component
     * @param max3 max value for third component
     * @param maxA max value for alpha component
     */
    public void setMode(COLOR_MODE mode, int max1, int max2, int max3, int maxA) {
        if (mode != COLOR_MODE.RGB && mode != COLOR_MODE.HSB) {
            PApplet.println("error: invalid mode for colors");
            System.exit(1);
        }

        if (mode == COLOR_MODE.RGB) this.mode = PConstants.RGB;
        if (mode == COLOR_MODE.HSB) this.mode = PConstants.HSB;
        this.maxMode[0] = max1;
        this.maxMode[1] = max2;
        this.maxMode[2] = max3;
        this.maxMode[3] = maxA;
    }
    
    /**
     * Apply to current sketch the fill color
     * 
     * @param manager Processing manager
     */
    public void applyFillColor(PApplet manager) {
        if (manager != null) {
            if (isOpaque) {
                float[] c = this.components;
                
                manager.colorMode(mode, maxMode[0], maxMode[1], maxMode[2], maxMode[3]);
                manager.fill(c[0], c[1], c[2]);
                return;
            }
            
            manager.noFill();
        }
    }
    
    /**
     * Apply to current shape the fill color
     * 
     * @param figure shape to fill
     */
    public void applyFillColor(PShape figure) {
        if (figure != null) {
            if (isOpaque) {
                float[] c = this.components;
                figure.colorMode(mode, maxMode[0], maxMode[1], maxMode[2], maxMode[3]);
                figure.fill(c[0], c[1], c[2]);
                return;
            }
            
            figure.noFill();
        }
    }
    
    /**
     * Apply to current sketch the stroke color
     * 
     * @param manager Processing manager
     */
    public void applyStrokeColor(PApplet manager) {
        if (manager != null) {
            if (isOpaque) {
                float[] c = this.components;
                manager.colorMode(mode, maxMode[0], maxMode[1], maxMode[2], maxMode[3]);
                manager.stroke(c[0], c[1], c[2]);
                return;
            }
        
            manager.noStroke();
        }
    }
    
    /**
     * Apply to current shape the stroke color
     * 
     * @param figure shape to stroke
     */
    public void applyStrokeColor(PShape figure) {
        if (figure != null) {
            if (isOpaque) {
                float[] c = this.components;
                figure.colorMode(mode, maxMode[0], maxMode[1], maxMode[2], maxMode[3]);
                figure.stroke(c[0], c[1], c[2]);
                return;
            }
            
            figure.noStroke();
        }
    }
    
    /**
     * Apply to current sketch the stroke color
     * 
     * @param manager Processing manager
     */
    public void applyTintColor(PApplet manager) {
        if (manager != null) {
            if (isOpaque) {
                float[] c = this.components;
                manager.colorMode(mode, maxMode[0], maxMode[1], maxMode[2], maxMode[3]);
                manager.tint(c[0], c[1], c[2]);
                return;
            }
            
            manager.noTint();
        }
    }
    
    @Override
    public GColor clone() {
        GColor cloneColor = new GColor();
        cloneColor.setMode(this.mode, maxMode[0],
            maxMode[1], maxMode[2], maxMode[3]
        );
        
        cloneColor.setColor(this);
        return cloneColor;
    }

    /**
     * Get the integer for passed color
     * 
     * @param manager Processing manager
     * @param c color at RGB or HSB format
     * @return float equivalency
     */
    public static float color(PApplet manager, GColor c) {
        if (manager == null) {
            PApplet.println("error: manager undefined");
            System.exit(1);
        }
        
        float red = manager.red((int) c.components[0]);
        float green = manager.red((int) c.components[1]);
        float blue = manager.red((int) c.components[2]);
        return red + green + blue;
    }
}