package gbuild.menu;

import gbuild.GComponent;
import gbuild.GPanel;
import gbuild.button.GButtonOption;
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
    protected int spaceValue = MENU_SPACE;
    private int colorOption;
    
    /**
     * Space between each option of a menu
     */
    public static final int MENU_SPACE = 50;
    
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
     * Get the space between options
     * 
     * @return spaceValue value for space between options
     */
    public int space() {
        return this.spaceValue;
    }
    
    /**
     * Return the index of current option
     * 
     * @return index of option
     */
    public int colorOption() {
        return this.colorOption;
    }
    
    /**
     * Set the space between options
     * 
     * @param spaceValue value for space between options
     */
    public void setSpace(int spaceValue) {
        this.spaceValue = spaceValue;
    }
    
    /**
     * Set the index of current option
     * 
     * @param colorOption index of option
     * @return if color option was changed
     */
    public boolean setColorOption(int colorOption) {
        if (this.components.size() > colorOption && colorOption >= 0) {
            this.colorOption = colorOption;
            return true;
        }
        
        return false;
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
                       
            if (option.textValue().equals(name)) {
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
}