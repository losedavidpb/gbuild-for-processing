package gbuild.button;

import gbuild.GComponent;
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
     * @see GButton#GButton(processing.core.PApplet) 
     */
    public GButtonOption(PApplet manager, GComponent parent) {
        super(manager, parent);
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
    
    @Override
    public Object getProp(String name) {
        Object propertyValue = super.getProp(name);
        
        if (propertyValue == null) {
            switch ((String)name) {
                case "keyValue": return this.keyValue();
                default:
                    System.out.printf("error: property %s was not found", name);
                    System.exit(-1);
                    return null;
            }
        }
        
        return propertyValue;
    }
    
    @Override
    public boolean setProp(Object name, Object value) {
        boolean cond = super.setProp(name, value);
        
        if (cond == false) {
            if (name instanceof String) {
                switch ((String)name) {
                    case "keyValue":
                        if (value instanceof Character) {
                            this.setKeyValue((Character)value);
                            return true;
                        }
                    break;
                }
            }
        }
        
        return cond;
    }
    
    // Deprecated
    
    /**
     * Get the key value associated to the option
     * 
     * @return key value
     * @deprecated
     */
    public char keyValue() {
        return this.keyValue;
    }
    
    /**
     * Set the key value associated to the option
     * 
     * @param keyValue key value
     * @deprecated
     */
    public void setKeyValue(char keyValue) {
        this.keyValue = keyValue;
    }
}
