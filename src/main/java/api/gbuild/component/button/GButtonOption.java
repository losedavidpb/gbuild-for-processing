package api.gbuild.component.button;

import api.gbuild.component.GComponent;
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
    
    /**
     * Create a new instance of a button
     * 
     * @param manager Processing manager
     * @param parent component parent
     * @param value text value
     * @param keyValue key value
     * @see GButton#GButton(processing.core.PApplet, float, float) 
     */
    public GButtonOption(PApplet manager, GComponent parent, String value, char keyValue) {
        super(manager, parent, 0, 0, value);
        this.keyValue = keyValue;
    }
    
    /**
     * Create a new instance of a button
     * 
     * @param manager Processing manager
     * @param value text value
     * @param keyValue key value
     * @see GButton#GButton(processing.core.PApplet, float, float) 
     */
    public GButtonOption(PApplet manager, String value, char keyValue) {
        this(manager, null, value, keyValue);
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
     * Set the key value associated to the option
     * 
     * @param keyValue key value
     */
    public void setKeyValue(char keyValue) {
        this.keyValue = keyValue;
    }
}
