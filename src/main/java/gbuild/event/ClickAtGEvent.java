package gbuild.event;

import gbuild.GComponent;
import processing.core.PApplet;
import processing.core.PConstants;

/**
 * <p>
 * Event to define click event on a component
 * </p>
 * 
 * <p>
 * This event is an extension of a hover event but
 * it will also check if user has clicked mouse button.
 * It is possible to define what button user needs to
 * want in order to activate this event.
 * </p>
 * 
 * @author David Parreño Barbuzano
 */
public class ClickAtGEvent extends HoverGEvent {
    private boolean isClicked;
    private final int mouseButton;
    
    /**
     * Create a new instance of a event
     * 
     * @param component component
     */
    public ClickAtGEvent(GComponent component) {
        super(component);
        this.isClicked = false;
        this.mouseButton = -1;
    }
    
    /**
     * Create a new instance of a event
     * 
     * @param component component
     * @param mouseButton mouse button
     */
    public ClickAtGEvent(GComponent component, int mouseButton) {
        super(component);
        this.isClicked = false;
        this.mouseButton = mouseButton;
        
        boolean cond = mouseButton != PConstants.LEFT && mouseButton != PConstants.RIGHT;
        cond = cond && mouseButton != PConstants.CENTER;
        
        if (cond == true) {
            PApplet.println("error ClickOnComponentGEvent: invalid mouse button");
            System.exit(1);
        }
    }
    
    /**
     * Check if component has been clicked
     * 
     * @return click state
     */
    public boolean isClicked() {
        return this.isClicked;
    }

    @Override
    public void execute() {
        this.isClicked = false;
        super.execute();
        
        if ((Boolean)super.isHover()) {
            PApplet manager = super.component.manager();
            this.isClicked = manager.mousePressed;
            
            if (this.mouseButton == -1) return;
            this.isClicked = manager.mouseButton == this.mouseButton;
        }
    }
}
