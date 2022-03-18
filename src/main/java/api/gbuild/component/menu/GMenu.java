
package api.gbuild.component.menu;

import api.gbuild.Globals;
import api.gbuild.component.GComponent;
import api.gbuild.component.GPanel;
import api.gbuild.component.button.GButtonOption;
import processing.core.PApplet;

/**
 * <p>
 * Menu formed by a set of options
 * </p>
 * 
 * <p>
 * This is the implementation of a menu that contains
 * lots of options that are clickable and are associated
 * to a key value which actives an event.
 * </p>
 * 
 * @author David Parreño Barbuzano
 */
public abstract class GMenu extends GPanel {
    protected int spaceValue = Globals.MENU_SPACE;
    private int colorOption;
    
    /**
     * Create a new instance of a menu
     * 
     * @param manager Processing manager
     * @param parent component parent
     * @see GPanel#GPanel(processing.core.PApplet, api.gbuild.component.GComponent) 
     */
    public GMenu(PApplet manager, GComponent parent) {
        super(manager, parent);
        this.colorOption = -1;
    }
    
    /**
     * Create a new instance of a menu
     * 
     * @param manager Processing manager
     * @see GPanel#GPanel(processing.core.PApplet) 
     */
    public GMenu(PApplet manager) {
        this(manager, null);
    }
    
    /**
     * Set the visible state for one of the options
     * 
     * @param name identifier for current option
     * @param isVisible visible state
     */
    public void setVisible(String name, boolean isVisible) {
        for (int i = 0; i < this.components.size(); i++) {
            GButtonOption option = (GButtonOption)this.components.get(i);
                       
            if (option.value().equals(name)) {
                option.setVisible(isVisible);
                return;
            }
        }
    }
  
    @Override
    public void add(GComponent component) {
        if (component instanceof GButtonOption)
            super.components.add(component);
    }
    
    @Override
    public Object getProperty(String name) {
        Object propertyValue = super.getProperty(name);
        
        if (propertyValue == null) {
            switch ((String)name) {
                case "colorOption": return this.colorOption();
                case "space": return this.space();
                default: return null;
            }
        }
        
        return propertyValue;
    }
    
    @Override
    public void setProperty(Object name, Object value) {
        super.setProperty(name, value);
        
        if (name instanceof String) {
            switch ((String)name) {
                case "colorOption":
                    if (value instanceof Integer)
                        this.setColorOption((Integer)value);
                break;

                case "space":
                    if (value instanceof Integer)
                        this.setSpace((Integer)value);
                break;
            }
        }
    }
    
    @Override
    public abstract void draw();
    
    // Deprecated
    
    /**
     * Get the space between options
     * 
     * @return spaceValue value for space between options
     * @deprecated
     */
    public int space() {
        return this.spaceValue;
    }
    
    /**
     * Return the index of current option
     * 
     * @return index of option
     * @deprecated
     */
    public int colorOption() {
        return this.colorOption;
    }
    
    /**
     * Set the space between options
     * 
     * @param spaceValue value for space between options
     * @deprecated
     */
    public void setSpace(int spaceValue) {
        this.spaceValue = spaceValue;
    }
    
    /**
     * Set the index of current option
     * 
     * @param colorOption index of option
     * @deprecated
     */
    protected void setColorOption(int colorOption) {
        this.colorOption = colorOption;
    }
}
