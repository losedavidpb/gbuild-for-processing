package gbuild.button;

import gbuild.GComponent;
import gbuild.event.KeyTypedGEvent;
import processing.core.PApplet;

/**
 * <p>
 * Button which is a clikable option
 * </p>
 * 
 * <p>
 * This type of button is the equivalent of a clickable button
 * with a text inside of it that has associated a key value. In
 * other words, an option is like a GButtonWithText instance but
 * with an additional parameter which is the key value.
 * </p>
 * 
 * <p>
 * Talking about the key value, options offer to developers a way
 * to associate an action of an option to a key event, so in this
 * case a button can active an event by clicking on it or typing
 * a specific key which is associated to the option.
 * </p>
 * 
 * @author David Parreño Barbuzano
 */
public class GButtonOption extends GButtonWithText {
    private char keyValue;
    private boolean isTyped;
    
    /**
     * Create a new instance of a button
     * 
     * @param manager Processing manager
     * @param parent component parent
     * @see GButton#GButton(processing.core.PApplet) 
     */
    public GButtonOption(PApplet manager, GComponent parent) {
        super(manager, parent);
        this.isTyped = false;
        super.addEvent(new KeyTypedGEvent(this));
    }
    
    /**
     * Create a new instance of a button
     * 
     * @param manager Processing manager
     * @see GButton#GButton(processing.core.PApplet) 
     */
    public GButtonOption(PApplet manager) {
        this(manager, null);
    }
    
    /**
     * Get if key value has been typed
     * 
     * @return if key has been typed
     */
    public boolean isTyped() {
        return this.isTyped;
    }
    
    /**
     * Get the key value associated to the option
     * 
     * @return key value
     */
    public char keyValue() {
        return this.keyValue;
    }
    
    /**
     * Set if key value has been typed
     * 
     * @param isTyped key typed state
     */
    public void setIsTyped(boolean isTyped) {
        this.isTyped = isTyped;
    }
    
    /**
     * Set the key value associated to the option
     * 
     * @param keyValue key value
     */
    public void setKeyValue(char keyValue) {
        this.keyValue = keyValue;
    }
}