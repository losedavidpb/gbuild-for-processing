package api.gbuild.component;

import api.gbuild.GColor;
import api.gbuild.GComponent;
import java.util.ArrayList;
import java.util.List;
import processing.core.PApplet;
import static processing.core.PApplet.abs;
import processing.core.PConstants;
import processing.core.PShape;

/**
 * GComponent that store more components.
 * 
 * This is the simplest way to store components
 * on a graphical component, so it can be considered
 * as the equivalent of a JPanel Java class
 * 
 * Talking about the relevance of this component,
 * this implemnetation is used as a basic unit to build
 * complex containers or components
 * 
 * @author David
 */
public class GContainer extends GComponent {
    protected final List<GComponent> components;
    private final GColor color;
    private boolean noBackground;
    
    /**
     * Create a new instance of a container
     * 
     * @param manager Processing manager
     * @param parent parent component
     * @param x x component for location
     * @param y y component for location
     * @param w w component for dimension
     * @param h h component for dimension
     */
    public GContainer(PApplet manager, GComponent parent, float x, float y, float w, float h) {
        super(manager, parent);
        this.components = new ArrayList<>();
        this.color = new GColor(255, 255, 255);
        this.noBackground = false;
        super.pos(x, y);
        super.dim(w, h);
    }
  
    /**
     * Create a new instance of a container
     * 
     * @param manager Processing manager
     * @param x x component for location
     * @param y y component for location
     * @param w w component for dimension
     * @param h h component for dimension
     */
    public GContainer(PApplet manager, float x, float y, float w, float h) {
        this(manager, null, x, y, w, h);
    }
    
    /**
     * Return if container contains a background
     * 
     * @return background state
     */
    public boolean noBackground() {
        return this.noBackground;
    }
    
    /**
     * Get the background color for container
     * 
     * @return integer value for color
     */
    public int color() {
        float[] c = color.color();
        return manager().color(c[0], c[1], c[2]);
    }
    
    public void setNoBackground(boolean noBackground) {
        this.noBackground = noBackground;
    }
    
    /**
     * Set the background color for container
     * 
     * @param component red, green, and blue component
     */
    public void setColor(Float ... component) {
        this.color.setColor(component);
    }
    
    /**
     * Set the background color for container
     * 
     * @param component red, green, and blue component
     */
    public void setColor(Integer ... component) {
        this.color.setColor(component);
    }
    
    /**
     * Get number of components stored
     * 
     * @return size of container
     */
    public int size() {
        return this.components.size();
    }
    
    /**
     * Add a component to current container
     * 
     * @param component graphical component
     */
    public void add(GComponent component) {
        if (!this.components.contains(component))
            this.components.add(component);
    }
    
    /**
     * Get a component from current container
     * 
     * @param i index associated to component
     * @return component at i
     */
    public GComponent get(int i) {
        boolean cond = i >= 0 && i < this.components.size();
        return cond? this.components.get(i) : null;
    }
  
    @Override
    public void draw() {
        if (super.isVisible()) {
            manager().pushMatrix();
            
            if (!noBackground) {
                PShape rect = manager().createShape(
                    PConstants.RECT, this.pos().x, this.pos().y,
                    this.dim().x, this.dim().y
                );

                rect.setFill(this.color());
                manager().shape(rect);
            }

            for (int i = 0; i < this.components.size(); i++)
                this.components.get(i).draw();

            manager().popMatrix();
        }
    }
}