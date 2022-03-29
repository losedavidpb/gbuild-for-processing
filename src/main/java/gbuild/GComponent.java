package gbuild;

import gbuild.dialog.GDialog;
import gbuild.event.GEvent;
import java.util.ArrayList;
import java.util.List;
import processing.core.PApplet;
import processing.core.PVector;

/**
 * <p>
 * Definition of a UI component
 * </p>
 * 
 * <p>
 * Processing draws figures at a specific screen location and size.
 * Considering this, gBuild defines a component as a set of 2D/3D
 * primitives and Processing shapes that are located at a point of
 * the screen and has an anchor and length.
 * </p>
 * 
 * <p>
 * Relative to drawing, gBuild includes for each component a visible
 * property that specifies if that instance would be shown at the
 * screen. It is important to understand that all components must
 * check this property before starting the drawing phase.
 * </p>
 * 
 * <p>
 * Talking about location, gBuild situates a component considering
 * the parent of that element. This means that the location of a
 * component could be defined absolute or relative to its parent.
 * </p>
 * 
 * <p>
 * Since 4.1.0 release, gBuild includes event's features to manage
 * I/O interactions at graphical components. In other words, an UI
 * component could add events that would be listened whether draw
 * function is executed and the component is visible.
 * </p>
 * 
 * @author David Parreño Barbuzano
 * @since 4.1.0
 */
public abstract class GComponent {
    private final PApplet manager;
    private final GComponent parent;
    private final List<GEvent> events;
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
        this(manager, null);
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
        this.events = new ArrayList<>();
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
     * Add a new event for this component
     * 
     * @param event event instance
     */
    public void addEvent(GEvent event) {
        this.events.add(event);
    }
    
    /**
     * Get event located at passed index
     * 
     * @param index index associated to an event
     * @return event associated to index
     */
    public GEvent getEvent(int index) {
        if (!(index >= 0 && index <= this.events.size() - 1)) {
            PApplet.println("error: index for event is not valid");
            System.exit(1);
        }
        
        return this.events.get(index);
    }

    /**
     * Clear all events for current component
     */
    public void clearEvents() {
        this.events.clear();
    }
    
    /**
     * Execute events associated to this component
     * 
     * <p>
     * This function would be called when the component
     * is visible and drawing phase is executed. However,
     * it is possible to listen graphical events at any
     * time if it is necessary
     * </p>
     */
    public void listenEvents() {
        this.events.forEach(e -> e.execute());
    }

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
     * @param coords coordinates (x, y)
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


    /**
     * Draw component on the screen
     * 
     * <p>
     * This method would draw the component taking into
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
}