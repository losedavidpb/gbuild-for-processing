package api.gbuild.component.menu;

import api.gbuild.component.GText;
import processing.core.PApplet;

/**
 * Option stored at menus
 * 
 * @author David
 */
public class GOption extends GText {
    private char keyValue;
  
    public GOption(PApplet manager, String name, char keyValue) {
        super(manager, name, 0, 0);
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
}