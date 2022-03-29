package gbuild.event;

import gbuild.GComponent;
import processing.core.PApplet;
import processing.core.PVector;

import static processing.core.PApplet.abs;

/**
 * <p>
 * Event to drag components
 * </p>
 * 
 * <p>
 * This event is designed to provide developers a way
 * to drag components such as dialogs
 * </p>
 * 
 * @author David Parreño Barbuzano
 */
public class DragGEvent implements GEvent {
    private final GComponent component;
    private PVector mouseFirst, mouseLast;
    private boolean isMoving;
    
    /**
     * Create a new event for drag mode
     * 
     * @param component component
     */
    public DragGEvent(GComponent component) {
        this.component = component;
        this.mouseFirst = null;
        this.mouseLast = null;
        this.isMoving = false;
    }
    
    @Override
    public void execute() {
        PApplet manager = component.manager();
        PVector pos = this.component.pos();
        PVector dim = this.component.dim();
        
        // Check if user want's to finish to
        // drag the component
        if (!manager.mousePressed) {
            if (this.isMoving) {
                int x = (int)this.mouseLast.x;
                int y = (int)this.mouseLast.y;
                this.component.pos(x, y);
            }
            
            this.isMoving = false;
            this.mouseFirst = null;
            this.mouseLast = null;
        }
        
        // Check if user want's to drag the component
        // while pressing the mouse button
        else {
            // Drag process has just been started
            if (!this.isMoving) {
                if (manager.mouseX >= pos.x && manager.mouseX <= pos.x + dim.x) {
                    if (manager.mouseY >= pos.y && manager.mouseY <= pos.y + dim.y) {
                        float diffX = abs(this.component.pos(true).x  - manager.mouseX);
                        float diffY = abs(this.component.pos(true).y - manager.mouseY);
                        
                        this.mouseFirst = new PVector(diffX, diffY);
                        this.isMoving = true;
                    }
                }
                
            // Continue drag process
            } else {
                float diffX = abs(this.mouseFirst.x  - manager.mouseX);
                float diffY = abs(this.mouseFirst.y - manager.mouseY);
                
                this.mouseLast = new PVector(diffX, diffY);
                this.component.pos((int)this.mouseLast.x, (int)this.mouseLast.y);
            }
        }
    }
}