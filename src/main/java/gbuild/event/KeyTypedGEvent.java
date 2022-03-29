package gbuild.event;

import gbuild.button.GButtonOption;
import processing.core.PApplet;

/**
 * <p>
 * Event for buttons with key values
 * <p>
 * 
 * @author David Parreño Barbuzano
 */
public class KeyTypedGEvent implements GEvent {
    private final GButtonOption button;
    
    /**
     * Create a new instance of a key typed event
     * 
     * @param button button component
     */
    public KeyTypedGEvent(GButtonOption button) {
        this.button = button;
    }
    
    public boolean isTyped() {
        return button.isTyped();
    }

    @Override
    public void execute() {
        PApplet manager = button.manager();
        button.setIsTyped(false);
        
        if (manager.keyPressed)
            button.setIsTyped(manager.key == button.keyValue());
    }
    
}
