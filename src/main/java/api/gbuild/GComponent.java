package api.gbuild;

import processing.core.PApplet;
import processing.core.PVector;

/**
 * Definition of a graphical component.
 * 
 * In gBuild, graphical components are those figures which
 * are drawn in Processing using basic primitives. Normally,
 * each component will have a location and dimension defined,
 * but it is possible to define these characteristics based
 * on parent or other components.
 * 
 * @author David Parreño Barbuzano
 */
public abstract class GComponent {
    private final PApplet manager;
    private final GComponent parent;
    private final PVector pos, dim;
    private boolean isVisible;
    
    /**
     * Create a new default component
     * 
     * @param manager Processing manager
     */
    public GComponent(PApplet manager) {
        this.manager = manager;
        this.parent = null;
        this.isVisible = true;
        this.pos = new PVector(0, 0);
        this.dim = new PVector(0, 0);
    }
    
    /**
     * Create a new default component
     * 
     * @param manager Processing manager
     * @param parent parent of current component
     */
    public GComponent(PApplet manager, GComponent parent) {
        this.manager = manager;
        this.parent = parent;
        this.isVisible = true;
        this.pos = new PVector(0, 0);
        this.dim = new PVector(0, 0);
    }
    
    /**
     * Get the manager for current component.
     * 
     * @return Processing applet
     */
    public PApplet manager() {
        return this.manager;
    }
    
    /**
     * Get the component parent for current component
     * 
     * @return component parent
     */
    public GComponent parent() {
        return this.parent;
    }
    
    /**
     * Check if component is visible
     * 
     * @return visible state
     */
    public boolean isVisible() {
        return this.isVisible;
    }

    /**
     * Specify the location for current component
     * 
     * It is possible to define the location in
     * three different ways:
     * 
     * 1) pos(x, y) when (x, y) will be set
     * 2) pos(x) when x will be set
     * 3) pos(null, y) when y will be set
     * 
     * @param coords coordinates (x, y)
     */
    public void pos(Integer ... coords) {
        if (coords.length >= 1 && coords.length <= 2) {
            // Check if x value will only be set
            if (coords.length == 1) this.pos.x = coords[0];

            // Check if y value will only be set or
            // both components will be changed
            else {
                if (coords[0] != null) this.pos.x = coords[0];
                if (coords[1] != null) this.pos.y = coords[1];
            }
        }
    }

    /**
     * Specify the location for current component
     * 
     * It is possible to define the location in
     * three different ways:
     * 
     * 1) pos(x, y) when (x, y) will be set
     * 2) pos(x) when x will be set
     * 3) pos(null, y) when y will be set
     * 
     * @param coords coordinates (x, y)
     */
    public void pos(Float ... coords) {
        if (coords.length >= 1 && coords.length <= 2) {
            // Check if x value will only be set
            if (coords.length == 1) this.pos.x = coords[0];

            // Check if y value will only be set or
            // both components will be changed
            else {
                if (coords[0] != null) this.pos.x = coords[0];
                if (coords[1] != null) this.pos.y = coords[1];
            }
        }
    }

    /**
     * Specify the dimension for current component.
     * 
     * It is possible to define the dimension in
     * three different ways:
     * 
     *  1) dim(w, h) when (w, h) will be set
     *  2) dim(w) when w will be set
     *  3) dim(null, h) when h will be set
     * 
     * @param dimensions dimension (w, h)
     */
    public void dim(Integer ... dimensions) {
        if (dimensions.length >= 1 && dimensions.length <= 2) {
            // Check if w value will only be set
            if (dimensions.length == 1) this.dim.x = dimensions[0];

            // Check if h value will only be set or
            // both components will be changed
            else {
                if (dimensions[0] != null) this.dim.x = dimensions[0];
                if (dimensions[1] != null) this.dim.y = dimensions[1];
            }
        }
    }

    /**
     * Specify the dimension for current component.
     * 
     * It is possible to define the dimension in
     * three different ways:
     * 
     *  1) dim(w, h) when (w, h) will be set
     *  2) dim(w) when w will be set
     *  3) dim(null, h) when h will be set
     * 
     * @param dimensions dimension (w, h)
     */
    public void dim(Float ... dimensions) {
        if (dimensions.length >= 1 && dimensions.length <= 2) {
            // Check if w value will only be set
            if (dimensions.length == 1) this.dim.x = dimensions[0];

            // Check if h value will only be set or
            // both components will be changed
            else {
                if (dimensions[0] != null) this.dim.x = dimensions[0];
                if (dimensions[1] != null) this.dim.y = dimensions[1];
            }
        }
    }
    
    /**
     * Return the location for current component.
     * 
     * It is important to know that the vector that is
     * returned is a copy of current attribute, so you
     * cannot change the location using this variable
     * 
     * @return a new instance for current position
     */
    public PVector pos() {
        if (parent == null)
            return new PVector(this.pos.x, this.pos.y);

        return new PVector(
            this.parent.pos().x + this.pos.x,
            this.parent.pos().y + this.pos.y
        );
    }

    /**
     * Return the dimension for current component.
     * 
     * It is important to know that the vector that is
     * returned is a copy of current attribute, so you
     * cannot change the dimension using this variable
     * 
     * @return a new instance for current dimension 
     */
    public PVector dim() {
        return new PVector(
            this.dim.x, this.dim.y
        );
    }

    /**
     * Specify if current component will be visible.
     * 
     * The draw method would hide the component
     * whether is called setVisible(false)
     * 
     * @param isVisible variable to specify if component is visible
     */
    public void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    /**
     * Draw current component
     * 
     * The drawing would be affected by the
     * visible state of the component, that
     * could be customized using setVisible
     */
    public abstract void draw();
}
