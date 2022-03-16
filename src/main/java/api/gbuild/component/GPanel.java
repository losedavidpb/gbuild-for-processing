package api.gbuild.component;

import api.gbuild.GColor;
import java.util.ArrayList;
import java.util.List;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PShape;
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
    private GColor color;
    
    /**
     * Create a new instance of a panel
     * 
     * @param manager Processing manager
     * @param parent parent component
     * @param x x component for location
     * @param y y component for location
     * @see GComponent#GComponent(processing.core.PApplet, api.gbuild.GComponent) 
     */
    public GPanel(PApplet manager, GComponent parent, float x, float y) {
        super(manager, parent);
        this.components = new ArrayList<>();
        this.color = new GColor(255, 255, 255);
        super.pos(x, y);
        super.dim(50, 50);
    }
  
    /**
     * Create a new instance of a panel
     * 
     * @param manager Processing manager
     * @param x x component for location
     * @param y y component for location
     * @see GComponent#GComponent(processing.core.PApplet) 
     */
    public GPanel(PApplet manager, float x, float y) {
        this(manager, null, x, y);
    }
    
    /**
     * Return if panel contains a background
     * 
     * <p>
     * GPanel can be used as a simple or container or a visible
     * graphical component. In order to include these two states,
     * you can customize its transparency, so it won't have color
     * for background if transparency was set
     * </p>
     * 
     * @return background state
     */
    public boolean isTransparent() {
        return this.color == null;
    }
    
    /**
     * Get the background color for panel
     * 
     * <p>
     * This method would call color Processing function,
     * so it is not necessary to define at a sketch a
     * color passing each RGB component.
     * </p>
     * 
     * @return integer value for color
     * @see GColor#color()
     */
    public float[] color() {
        return color.color();
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
     * Specify if panel has a transparent background
     * 
     * @param transparency state of the transparency
     * @see GPanel#isTransparent() 
     */
    public void setTransparency(boolean transparency) {
        if (transparency) this.color = null;
        else if (this.color == null) this.color = new GColor(255, 255, 255);
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
     */
    public void setColor(Float ... component) {
        if (!isTransparent()) {
            this.color = new GColor(255, 255, 255);
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
     */
    public void setColor(Integer ... component) {
        if (!isTransparent()) {
            this.color = new GColor(255, 255, 255);
            this.color.setColor(component);
        }
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
        this.pos(limitsX.x, limitsY.x);
        this.dim(dimx, dimy);
    }
    
    @Override
    public void draw() {
        if (super.isVisible()) {
            manager().pushMatrix();
            
            if (!this.isTransparent()) {
                manager().noStroke();
                
                PShape rect = manager().createShape(
                    PConstants.RECT, this.pos().x, this.pos().y,
                    this.dim().x, this.dim().y
                );
                
                float[] c = this.color();
                rect.setFill(manager().color(c[0], c[1], c[2]));
                manager().shape(rect);
            }

            manager().popMatrix();
            
            for (int i = 0; i < this.components.size(); i++)
                this.components.get(i).draw();
        }
    }
    
    // -----------------------------------------------
    // Private methods
    // -----------------------------------------------
    
    private PVector getLimitsX() {
        PVector limits = new PVector(Float.MAX_VALUE, 0, 0);
        
        for (int i = 0; i < this.components.size(); i++) {
            GComponent component = this.components.get(i);
            limits.x = min(limits.x, component.pos().x);
            limits.y = max(limits.y, component.pos().x + component.dim().x);
            
            if (limits.x == component.pos().x)
                limits.z = component.pos(false).x;
        }
        
        return limits;
    }
    
    private PVector getLimitsY() {
        PVector limits = new PVector(Float.MAX_VALUE, 0, 0);
        
        for (int i = 0; i < this.components.size(); i++) {
            GComponent component = this.components.get(i);
            limits.x = min(limits.x, component.pos().y);
            limits.y = max(limits.y, component.pos().y + component.dim().y);
            
            if (limits.x == component.pos().y)
                limits.z = component.pos(false).y;
        }
        
        return limits;
    }
}