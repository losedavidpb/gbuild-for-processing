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
 * Component for panels
 * </p>
 * 
 * <p>
 * Processing language environment does not have an explicit
 * implementation of a list of components that are associated
 * with a container. To solve this, gBuild provides this class
 * that acts as a panel that contains UI components.
 * </p>
 * 
 * <p>
 * This component is based on the JPanel class defined at Java
 * Swing library, so the great majority of the utilities that
 * has are the same methods that include this external source.
 * </p>
 * 
 * <p>
 * GPanel was designed as the main component used to construct
 * complex UI components that are formed by simple ones, but
 * can be used just as a container of components.
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
        if (i < 0 || i > this.components.size()) {
            PApplet.println("error GPanel.get: index out of bounds");
            System.exit(1);
        }
        
        return this.components.get(i);
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
        if (components.contains(component)) {
            PApplet.println("error GPanel.add: component has already been added");
            System.exit(1);
        }
        
        this.components.add(component);
        this.updateComponents();
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
        int dimx = (int)abs(pos().x - limitsX.y);
        int dimy = (int)abs(pos().y - limitsY.y);
        this.pos((int)this.pos().x + (int)limitsX.x, (int)this.pos().y + (int)limitsY.x);
        this.dim(dimx, dimy);
    }

    /**
     * Return if panel is transparent
     * 
     * @return transparent value
     */
    public boolean isOpaque() {
        return this.color.isOpaque();
    }
    
    /**
     * Return if stroke panel is opaque
     * 
     * @return opaque value
     */
    public boolean isStrokeOpaque() {
        return this.strokeColor.isOpaque();
    }

    /**
     * Specify if panel has a transparent background
     * 
     * @param isOpaque opaque state
     * @see GPanel#isOpaque()
     */
    public void setOpaque(boolean isOpaque) {
        this.color.setOpaque(isOpaque);
    }
    
    /**
     * Set opaque state for stroke color
     * 
     * @param isOpaque opaque value
     */
    public void setStrokeOpaque(boolean isOpaque) {
        this.strokeColor.setOpaque(isOpaque);
    }

    /**
     * Set the background color for panel
     * 
     * @param color components
     */
    public void setColor(GColor color) {
        this.color.setColor(color);
    }
    
    
    /**
     * Set the background color for panel
     * 
     * @param component components
     */
    public void setColor(Integer ... component) {
        this.color.setColor(component);
    }

    /**
     * Set the stroke color for panel
     * 
     * @param color components
     */
    public void setStrokeColor(GColor color) {
        this.strokeColor.setColor(color);
    }
    
    /**
     * Set the stroke color for panel
     * 
     * @param component components
     */
    public void setStrokeColor(Integer ... component) {
        this.strokeColor.setColor(color);
    }

    @Override
    public void draw() {
        if (super.isVisible()) {
            this.listenEvents();
            
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