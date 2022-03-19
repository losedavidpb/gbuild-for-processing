package gbuild;

import gbuild.dialog.GDialog;
import processing.core.PApplet;
import processing.core.PVector;

/**
 * <p>
 * General definition of a gBuild UI component
 * </p>
 * 
 * <p>
 * Since Processing figures are drawn at a specific location
 * and dimension, this class considers a component as the set of
 * 2D/3D primitives or figures located at a position, with an anchor
 * and length, that can be visible or hided for user.
 * </p>
 * 
 * <p>
 * Location is defined as a (x, y) position which depends on the parent
 * of the component. This means that the position would always be relative
 * to the parent, so (x, y) location is equal to (x_p + x_c, y_p + y_c),
 * where (x_p, y_p) is the parent's position and (x_c, y_c) is the initial
 * position of the component.
 * </p>
 * 
 * @author David Parreño Barbuzano
 */
public abstract class GComponent {
    private final PApplet manager;
    private final GComponent parent;
    private final PVector pos, dim;
    private boolean isVisible;
    
    /**
     * Create a new gBuild component
     * 
     * <p>
     * Normally, components use basic Processing functions
     * to draw or customize its properties. In order to have
     * this control, it is necessary to pass the PApplet that
     * includes some of these features.
     * </p>
     * 
     * <p>
     * As an use example, you can create a component at a
     * Processing sketch passing "this" for the PApplet.
     * </p>
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
     * Create a new gBuild component
     * 
     * <p>
     * Components on gBuild can be related to another one which
     * will be named as its parent. This means that some properties,
     * such as the location, would depend on the parent.
     * </p>
     * 
     * @param manager Processing manager
     * @param parent parent for current component
     * @see GComponent#GComponent(processing.core.PApplet) 
     */
    public GComponent(PApplet manager, GComponent parent) {
        this.manager = manager;
        this.parent = parent;
        this.isVisible = true;
        this.pos = new PVector(0, 0);
        this.dim = new PVector(0, 0);
    }
    
    /**
     * Get the PApplet for current component
     * 
     * <p>
     * Normally, the PApplet associated to a component is
     * the same for other ones, so this variable cannot be
     * modified by the developer.
     * </p>
     * 
     * @return Processing manager
     * @see GComponent#GComponent(processing.core.PApplet) 
     */
    public PApplet manager() {
        return this.manager;
    }
    
    /**
     * Get the parent for current component
     * 
     * @return component parent
     * @see GComponent#GComponent(processing.core.PApplet) 
     */
    public GComponent parent() {
        return this.parent;
    }

    /**
     * Draw component on the screen
     * 
     * <p>
     * This method would drawn the component taking into
     * account all the customization done before.
     * </p>
     * 
     * <p>
     * It is important to notice that component would not
     * be drawn if visible state is not true.
     * </p>
     * 
     * @see GComponent#isVisible()
     */
    public abstract void draw();
    
    /**
     * Set value to passed property.
     * 
     * <p>
     * If a component has more properties defined, it is
     * necessary to override this method including them.
     * </p>
     * 
     * @param params tuples (name, value) where name is
     *               the property's name and value the
     *               new definition for the property
     */
    public void setProp(Object ... params) {
        for (int i = 0; i < params.length - 1; i += 2)
            this.setProp(params[i], params[i + 1]);
    }
    
    /**
     * Get the value of passed property
     * 
     * @param name property's name
     * @return property's value
     */
    public Object getProp(String name) {
        switch(name) {
            case "isVisible": return this.isVisible();
            case "xp": return this.pos().x;
            case "yp": return this.pos().y;
            case "x": return this.pos(true).x;
            case "y": return this.pos(true).y;
            case "w": return this.dim().x;
            case "h": return this.dim().y;
            default:
                System.out.printf("error: property %s was not found", name);
                System.exit(-1);
                return null;
        }
    }
    
    /**
     * Set a new value to passed property
     * 
     * @param name property's name
     * @param value property's value
     * @return specifies if property was changed
     */
    public boolean setProp(Object name, Object value) {
        if (name instanceof String) {
            switch((String)name) {
                case "x":
                    if (value instanceof Float) {
                        this.pos((Float)value);
                        return true;
                    } else if (value instanceof Integer) {
                        this.pos((Integer)value);
                        return true;
                    }
                break;

                case "y":
                    if (value instanceof Float) {
                        this.pos(null, (Float)value);
                        return true;
                    } else if (value instanceof Integer) {
                        this.pos(null, (Integer)value);
                        return true;
                    }
                break;

                case "w":
                    if (value instanceof Float) {
                        this.dim((Float)value);
                        return true;
                    } else if (value instanceof Integer) {
                        this.dim((Integer)value);
                        return true;
                    }
                break;

                case "h":
                    if (value instanceof Float) {
                        this.dim(null, (Float)value);
                        return true;
                    } else if (value instanceof Integer) {
                        this.dim(null, (Integer)value);
                        return true;
                    }
                break;
                
                case "isVisible":
                    if (value instanceof Boolean) {
                        this.isVisible = (Boolean)value;
                        return true;
                    }
                break;
            }
        }
        
        return false;
    }
    
    // ----------------------------------------------------
    // Deprecated
    // ----------------------------------------------------
    
    /**
     * Return the location for component.
     * 
     * <p>
     * The location of the component is always relative
     * to the parent's location, so define it's position
     * taking this into account.
     * </p>
     * 
     * <p>
     * In order to avoid problem for pointer reference, the
     * vector returned would be a new instance of the attribute,
     * since you cannot change the location unless you use setters
     * </p>
     * 
     * @return a new instance for position
     * @see GComponent#pos(java.lang.Float...)
     * @see GComponent#pos(java.lang.Integer...)
     * @deprecated
     */
    public PVector pos() {
        if (parent == null)
            return new PVector(this.pos.x, this.pos.y);

        if (parent instanceof GPanel || parent instanceof GDialog) {
            return new PVector(
                this.parent.pos().x + this.pos.x,
                this.parent.pos().y + this.pos.y
            );
        }
        
        return new PVector(
            this.parent.pos().x + this.parent.dim().x + this.pos.x,
            this.parent.pos().y + this.parent.dim().y + this.pos.y
        );
    }
    
    /**
     * Return the location for component.
     * 
     * <p>
     * This method is an extension of the other getter for location,
     * since it offers to developers the possibility to get the
     * initial value for the position without the consideration
     * of the component's parent.
     * </p>
     * 
     * @param noParent check if parent would be considered
     * @return a new instance for position
     * @see GComponent#pos()
     * @deprecated
     */
    public PVector pos(boolean noParent) {
        return !noParent? pos() : new PVector(this.pos.x, this.pos.y);
    }

    /**
     * Return the dimension for component.
     * 
     * <p>
     * In order to avoid problem for pointer reference, the
     * vector returned would be a new instance of the attribute,
     * since you cannot change the dimension unless you use setters
     * </p>
     * 
     * @return a new instance for dimension
     * @deprecated 
     */
    public PVector dim() {
        return new PVector(
            this.dim.x, this.dim.y
        );
    }
    
    /**
     * Check if component is visible for user
     * 
     * <p>
     * A component would be draw whether it's visible
     * for user, for do not forget to check this state
     * before drawing a set of components.
     * </p>
     * 
     * @return visible state
     * @deprecated 
     */
    public boolean isVisible() {
        return this.isVisible;
    }
    
    /**
     * Specify if component will be visible.
     * 
     * <p>
     * This method offers the possibility to indicate if
     * component would be visible for user when it is drawn
     * on the screen.
     * </p>
     * 
     * @param isVisible visible state
     * @deprecated 
     */
    public void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    /**
     * Specify the location for component
     * 
     * <p>
     * It is possible to define the location in
     * three different ways:
     * 
     *  <ol>
     *      <li>pos(x, y) when (x, y) will be set</li>
     *      <li>pos(x) when x will be set</li>
     *      <li>pos(null, y) when y will be set</li>
     *  </ol>
     * 
     * @param coords integer coordinates (x, y)
     * @deprecated
     */
    public void pos(Integer ... coords) {
        if (coords.length >= 1 && coords.length <= 2) {
            // Check if x value will only be set
            if (coords.length == 1) {
                if (coords[0] != null)
                    this.pos.x = coords[0];
            }

            // Check if y value will only be set or
            // both components will be changed
            else {
                if (coords[0] != null) this.pos.x = coords[0];
                if (coords[1] != null) this.pos.y = coords[1];
            }
        }
    }

    /**
     * Specify the location for component
     * 
     * <p>
     * It is possible to define the location in
     * three different ways:
     * 
     *  <ol>
     *      <li>pos(x, y) when (x, y) will be set</li>
     *      <li>pos(x) when x will be set</li>
     *      <li>pos(null, y) when y will be set</li>
     *  </ol>
     * 
     * @param coords float coordinates (x, y)
     * @deprecated
     */
    public void pos(Float ... coords) {
        if (coords.length >= 1 && coords.length <= 2) {
            // Check if x value will only be set
            if (coords.length == 1) {
                if (coords[0] != null)
                    this.pos.x = coords[0];
            }

            // Check if y value will only be set or
            // both components will be changed
            else {
                if (coords[0] != null) this.pos.x = coords[0];
                if (coords[1] != null) this.pos.y = coords[1];
            }
        }
    }

    /**
     * Specify the dimension for component.
     * 
     * <p>
     * It is possible to define the dimension in
     * three different ways:
     * 
     *  <ol>
     *      <li>dim(w, h) when (w, h) will be set</li>
     *      <li>dim(w) when w will be set</li>
     *      <li>dim(null, h) when h will be set</li>
     *  </ol>
     * 
     * @param dimensions integer dimensions (w, h)
     * @deprecated
     */
    public void dim(Integer ... dimensions) {
        if (dimensions.length >= 1 && dimensions.length <= 2) {
            // Check if w value will only be set
            if (dimensions.length == 1) {
                if (dimensions[0] != null)
                    this.dim.x = dimensions[0];
            }

            // Check if h value will only be set or
            // both components will be changed
            else {
                if (dimensions[0] != null) this.dim.x = dimensions[0];
                if (dimensions[1] != null) this.dim.y = dimensions[1];
            }
        }
    }

    /**
     * Specify the dimension for component.
     * 
     * <p>
     * It is possible to define the dimension in
     * three different ways:
     * 
     *  <ol>
     *      <li>dim(w, h) when (w, h) will be set</li>
     *      <li>dim(w) when w will be set</li>
     *      <li>dim(null, h) when h will be set</li>
     *  </ol>
     * 
     * @param dimensions float dimensions (w, h)
     * @deprecated
     */
    public void dim(Float ... dimensions) {
        if (dimensions.length >= 1 && dimensions.length <= 2) {
            // Check if w value will only be set
            if (dimensions.length == 1) {
                if (dimensions[0] != null)
                    this.dim.x = dimensions[0];
            }

            // Check if h value will only be set or
            // both components will be changed
            else {
                if (dimensions[0] != null) this.dim.x = dimensions[0];
                if (dimensions[1] != null) this.dim.y = dimensions[1];
            }
        }
    }
}
