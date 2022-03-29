package gbuild.event;

import gbuild.GComponent;
import processing.core.PApplet;

/**
 * <p>
 * Event to implement hover effect
 * </p>
 * 
 * <p>
 * This event is used to check if a component is been
 * hovering by mouse device. Since each component could
 * customize its properties in a different manner, this
 * event would no apply those changes.
 * </p>
 * 
 * @author David Parreño Barbuzano
 * @since  4.1.0
 */
public class HoverGEvent implements GEvent {
    protected final GComponent component;
    private boolean isHover;
    
    /**
     * Create a new instance of a HoverGEvent
     * 
     * @param component UI component
     */
    public HoverGEvent(GComponent component) {
        this.component = component;
        this.isHover = false;
    }
 
    public boolean isHover() {
        return this.isHover;
    }

    @Override
    public void execute() {
        this.isHover = false;
        
        PApplet manager = this.component.manager();
        
        int rightx = (int) (component.pos().x + component.dim().x);
        int righty = (int) (component.pos().y + component.dim().y);
        
        if (manager.mouseX >= component.pos().x && manager.mouseX <= rightx) {
            if (manager.mouseY >= component.pos().y && manager.mouseY <= righty) {
                this.isHover = true;
            }
        }
    }
}
