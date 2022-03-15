package api.gbuild.component;

import processing.core.PApplet;
import static processing.core.PApplet.abs;

/**
 * Option stored at menus
 * 
 * @author David Parreño Barbuzano
 */
public class GOption extends GButton {
    private char keyValue;
    
    /**
     * Create a new instance of a option
     * 
     * @param manager Processing manager
     * @param x x component for location
     * @param y y component for location
     * @param name text value
     * @param keyValue key associated
     */
    public GOption(PApplet manager, float x, float y, String name, char keyValue) {
        super(manager, x, y, name);
        this.keyValue = keyValue;
    }
    
    /**
     * Return the key associated to option
     * 
     * @return key value for option
     */
    public char keyValue() {
        return this.keyValue;
    }
    
    /**
     * Specify the value for the option's key
     * 
     * @param keyValue key value for option
     */
    public void setKeyValue(char keyValue) {
        this.keyValue = keyValue;
    }
    
    @Override
    public GOption clone() {
        float x = pos().x, y = pos().y;
        
        if (parent() != null) {
            x = abs(parent().pos().x - x);
            y = abs(parent().pos().y - y);
        }
        
        GOption optionCopy = new GOption(
            manager(), x, y,
            value(), keyValue
        );
        
        optionCopy.setSelected(isSelected());
        optionCopy.setSize(size());
        optionCopy.setAlign(align());
        optionCopy.setMode(mode());
        optionCopy.setVisible(isVisible());
        
        float[] c = this.hoverColor();
        optionCopy.setHoverColor(c[0], c[1], c[2]);
        
        c = this.rawColor();
        optionCopy.setRawColor(c[0], c[1], c[2]);
        
        return optionCopy;
    }
}