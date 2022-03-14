package api.gbuild.component;

import api.gbuild.GComponent;
import java.util.ArrayList;
import java.util.List;
import processing.core.PApplet;
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
    private int fillColor;
  
    public GContainer(PApplet manager, GContainer parent, float x, float y, float w, float h) {
        super(manager, parent);
        this.components = new ArrayList<>();
        this.fillColor = manager.color(255, 255, 255);
        super.pos(x, y);
        super.dim(w, h);
    }
  
    public GContainer(PApplet manager, float x, float y, float w, float h) {
        super(manager);
        this.components = new ArrayList<>();
        this.fillColor = manager.color(255, 255, 255);
        super.pos(x, y);
        super.dim(w, h);
    }
    
    /**
     * Get the background color for container
     * 
     * @return integer value for color
     */
    public int color() {
        return this.fillColor;
    }
    
    /**
     * Set the background color for container
     * 
     * @param r red component
     * @param g green component
     * @param b blue component
     */
    public void setColor(float r, float g, float b) {
        this.fillColor = manager().color(r, g, b);
    }
    
    /**
     * Set the background color for container
     * 
     * @param value integer value
     */
    public void setColor(float value) {
        this.fillColor = manager().color(value);
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
            
            PShape rect = manager().createShape(
                PConstants.RECT, this.pos().x, this.pos().y,
                this.dim().x, this.dim().y
            );

            rect.setFill(this.fillColor);
            manager().shape(rect);

            for (int i = 0; i < this.components.size(); i++)
                this.components.get(i).draw();

            manager().popMatrix();
        }
    }
}
