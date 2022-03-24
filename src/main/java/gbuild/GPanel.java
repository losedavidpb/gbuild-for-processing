package gbuild;

import java.util.ArrayList;
import java.util.List;
import processing.core.PApplet;
import processing.core.PVector;

import static processing.core.PApplet.abs;
import static processing.core.PApplet.max;
import static processing.core.PApplet.min;

/**
 * <p>
 * Component that is a set of components
 * </p>
 * 
 * <p>
 * Processing language environment does not include an implementation
 * of a container that store primitives or figures, so it can be difficult
 * to define a set of components that are associated between them. This
 * class offers this possibility and its design is inspired in JPanel
 * class implemented at Java Swing library.
 * </p>
 * 
 * <p>
 * GPanel was designed in order to be the basic element to construct
 * complex components composed by others. On this library, they are
 * defined some examples such as menus or dialogs, but developers
 * are able to create new components with this class.
 * </p>
 * 
 * <p>
 * If you are interested in this library and wants to contribute,
 * please do not hesitate to contact me at losedavidpb@gmail.com
 * </p>
 * 
 * @author David Parreño Barbuzano
 */
public class GPanel extends GComponent {
    protected final List<GComponent> components;
    protected GColor color, strokeColor;
    
    /**
     * Defaulkt value for panel color
     */
    public static final GColor PANEL_COLOR = new GColor(255, 255, 255);
    
    /**
     * Default value for panel stroke color
     */
    public static final GColor PANEL_STROKE = new GColor(255, 255, 255);
    
    /**
     * Default with for a panel
     */
    public static final int PANEL_DIM_X = 50;
    
    /**
     * Default height for a panel
     */
    public static final int PANEL_DIM_Y = 50;
    
    /**
     * Create a new instance of a panel
     * 
     * @param manager Processing manager
     * @param parent parent component
     * @see GComponent#GComponent(processing.core.PApplet, api.gbuild.component.GComponent) 
     */
    public GPanel(PApplet manager, GComponent parent) {
        super(manager, parent);
        this.components = new ArrayList<>();
        this.color = PANEL_COLOR.clone();
        this.strokeColor = PANEL_STROKE.clone();
        super.dim(PANEL_DIM_X, PANEL_DIM_Y);
    }
  
    /**
     * Create a new instance of a panel
     * 
     * @param manager Processing manager
     * @see GComponent#GComponent(processing.core.PApplet) 
     */
    public GPanel(PApplet manager) {
        this(manager, null);
    }
    
    /**
     * Get number of components stored
     * 
     * @return size of panel
     */
    public int numComponents() {
        return this.components.size();
    }
    
    /**
     * Get a component from current panel
     * 
     * <p>
     * If index is not a valid position, the method would
     * return null as a component, so do not forget to
     * check the returned value after calling it.
     * </p>
     * 
     * @param i index associated to component
     * @return component at passed index
     */
    public GComponent get(int i) {
        boolean cond = i >= 0 && i < this.components.size();
        return cond? this.components.get(i) : null;
    }
    
    /**
     * Remove all the components of the panel
     */
    public void clear() {
        this.components.clear();
    }
    
    /**
     * Add a component to current panel
     * 
     * <p>
     * Panel won't allow repetitions on elements, so it is not
     * possible to include two elements which are the same. If
     * you want to include repetitions, create a new instance
     * or change the clone method of the component.
     * </p>
     * 
     * <p>
     * Talking about the addition, if a component is included
     * on the panel, the position and dimension of it would
     * be updated so that all components are truly inside
     * the area of the panel.
     * </p>
     * 
     * <p>
     * In future revisions, the panel would incoporate a way
     * to customize if it will allow repetitions. Besides, we
     * will improve the update of the location and dimensions
     * of the panel, since it is possible that developers use
     * the panel as a container with no location and dimension
     * </p>
     * 
     * @param component graphical component
     * @see GPanel#updateComponents() 
     */
    public void add(GComponent component) {
        if (!this.components.contains(component)) {
            this.components.add(component);
            this.updateComponents();
        }
    }
    
    /**
     * Update the location and dimension of the panel
     * based on the components stored on it.
     * 
     * <p>
     * This method is called each time a component is
     * included on the panel, but you can call it any
     * time you desire.
     * </p>
     */
    public void updateComponents() {
        PVector limitsX = getLimitsX(), limitsY = getLimitsY();
        float dimx = abs(pos().x - limitsX.y);
        float dimy = abs(pos().y - limitsY.y);
        this.pos(this.pos().x + limitsX.x, this.pos().y + limitsY.x);
        this.dim(dimx, dimy);
    }
    
    @Override
    public void draw() {
        if (super.isVisible()) {
            manager().pushMatrix();
            manager().pushStyle();
            
            this.strokeColor.applyStrokeColor(manager());
            this.color.applyFillColor(manager());
            manager().rect(this.pos().x, this.pos().y, this.dim().x, this.dim().y);
            manager().popMatrix();
            manager().popStyle();
            
            for (int i = 0; i < this.components.size(); i++)
                this.components.get(i).draw();
        }
    }
    
    @Override
    public Object prop(String name) {
        switch ((String)name) {
            case "isTransparent": return this.isTransparent();
            case "color": return this.color.clone();
            case "strokeColor": return this.strokeColor.clone();
            case "isStrokeTransparent": return this.isStrokeTransparent();
        }
        
        return super.prop(name);
    }
    
    @Override
    public boolean prop(Object name, Object value) {
        if (name instanceof String) {
            switch ((String)name) {
                case "isTransparent":
                    if (value instanceof Boolean) {
                        this.setTransparent((Boolean)value);
                        return true;
                    }
                break;

                case "color":
                    if (value instanceof GColor) {
                        this.setColor((GColor)value);
                        return true;
                    }
                break;

                case "strokeColor":
                    if (value instanceof GColor) {
                        this.setStrokeColor((GColor)value);
                        return true;
                    }
                break;

                case "isStrokeTransparent":
                    if (value instanceof Boolean) {
                        this.setStrokeTransparent((Boolean)value);
                        return true;
                    }
                break;
            }
        }
        
        return super.prop(name, value);
    }
    
    // Deprecated
    
    /**
     * Return if panel is transparent
     * 
     * @return transparent value
     * @deprecated
     */
    public boolean isTransparent() {
        return this.color.isTransparent();
    }
    
    /**
     * Return if stroke panel is transparent
     * 
     * @return transparent value
     * @deprecated
     */
    public boolean isStrokeTransparent() {
        return this.strokeColor.isTransparent();
    }
    
    /**
     * Specify if panel has a transparent background
     * 
     * @param transparency state of the transparency
     * @see GPanel#isTransparent()
     * @deprecated
     */
    public void setTransparent(boolean transparency) {
        this.color.setTransparent(transparency);
    }
    
    /**
     * Set transparency for stroke color
     * 
     * @param transparency transparent value
     * @deprecated
     */
    public void setStrokeTransparent(boolean transparency) {
        this.strokeColor.setTransparent(transparency);
    }
    
    /**
     * Set the background color for panel
     * 
     * <p>
     * It is important to know that the modification would
     * not be done whether panel is transparent.
     * </p>
     * 
     * @param color red, green, and blue component
     * @see GPanel#isTransparent() 
     * @see GPanel#setColor(java.lang.Integer...) 
     * @see GPanel#setColor(java.lang.Float...)
     * @deprecated
     */
    public void setColor(GColor color) {
        if (!this.color.isTransparent()) {
            this.color = new GColor(0, 0, 0);
            this.color.setColor(color);
        }
    }
    
    /**
     * Set the background color for panel
     * 
     * <p>
     * It is important to know that the modification would
     * not be done whether panel is transparent.
     * </p>
     * 
     * @param component red, green, and blue component
     * @see GPanel#isTransparent() 
     * @see GPanel#setColor(java.lang.Integer...)
     * @deprecated
     */
    public void setColor(Float ... component) {
        if (!this.color.isTransparent()) {
            this.color = new GColor(0, 0, 0);
            this.color.setColor(component);
        }
    }
    
    /**
     * Set the background color for panel
     * 
     * <p>
     * It is important to know that the modification would
     * not be done whether panel is transparent.
     * </p>
     * 
     * @param component red, green, and blue component
     * @see GPanel#isTransparent()
     * @see GPanel#setColor(java.lang.Float...)
     * @deprecated
     */
    public void setColor(Integer ... component) {
        if (!this.color.isTransparent()) {
            this.color = new GColor(0, 0, 0);
            this.color.setColor(component);
        }
    }
    
    /**
     * Set the stroke color for panel
     * 
     * <p>
     * It is important to know that the modification would
     * not be done whether panel is transparent.
     * </p>
     * 
     * @param color red, green, and blue component
     * @see GPanel#isTransparent() 
     * @see GPanel#setColor(java.lang.Integer...) 
     * @see GPanel#setColor(java.lang.Float...)
     * @deprecated
     */
    public void setStrokeColor(GColor color) {
        if (!this.strokeColor.isTransparent()) {
            this.strokeColor = new GColor(0, 0, 0);
            this.strokeColor.setColor(color);
        }
    }
    
    /**
     * Set the stroke color for panel
     * 
     * <p>
     * It is important to know that the modification would
     * not be done whether panel is transparent.
     * </p>
     * 
     * @param component red, green, and blue component
     * @see GPanel#isTransparent() 
     * @see GPanel#setColor(java.lang.Integer...)
     * @deprecated
     */
    public void setStrokeColor(Float ... component) {
        if (!this.strokeColor.isTransparent()) {
            this.strokeColor = new GColor(0, 0, 0);
            this.strokeColor.setColor(component);
        }
    }
    
    /**
     * Set the stroke color for panel
     * 
     * <p>
     * It is important to know that the modification would
     * not be done whether panel is transparent.
     * </p>
     * 
     * @param component red, green, and blue component
     * @see GPanel#isTransparent()
     * @see GPanel#setColor(java.lang.Float...)
     * @deprecated
     */
    public void setStrokeColor(Integer ... component) {
        if (!this.strokeColor.isTransparent()) {
            this.strokeColor = new GColor(0, 0, 0);
            this.strokeColor.setColor(component);
        }
    }
    
    // -----------------------------------------------
    // Private methods
    // -----------------------------------------------
    
    private PVector getLimitsX() {
        PVector limits = new PVector(Float.MAX_VALUE, 0, 0);
        
        for (int i = 0; i < this.components.size(); i++) {
            GComponent component = this.components.get(i);
            limits.x = min(limits.x, component.pos(true).x);
            limits.y = max(limits.y, component.pos().x + component.dim().x);
            
            if (limits.x == component.pos(true).x)
                limits.z = component.pos(true).x;
        }
        
        return limits;
    }
    
    private PVector getLimitsY() {
        PVector limits = new PVector(Float.MAX_VALUE, 0, 0);
        
        for (int i = 0; i < this.components.size(); i++) {
            GComponent component = this.components.get(i);
            limits.x = min(limits.x, component.pos(true).y);
            limits.y = max(limits.y, component.pos().y + component.dim().y);
            
            if (limits.x == component.pos(true).y)
                limits.z = component.pos(true).y;
        }
        
        return limits;
    }
}