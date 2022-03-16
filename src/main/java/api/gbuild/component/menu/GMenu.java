package api.gbuild.component.menu;

import api.gbuild.component.GComponent;
import api.gbuild.Globals;
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
     * @param x x component for location
     * @param y y component for location
     * @see GPanel#GPanel(processing.core.PApplet, api.gbuild.GComponent, float, float) 
     */
    public GMenu(PApplet manager, GComponent parent, float x, float y) {
        super(manager, parent, x, y);
        this.colorOption = -1;
    }
    
    /**
     * Create a new instance of a menu
     * 
     * @param manager Processing manager
     * @param x x component for location
     * @param y y component for location
     * @see GPanel#GPanel(processing.core.PApplet, float, float) 
     */
    public GMenu(PApplet manager, float x, float y) {
        this(manager, null, x, y);
    }
  
    @Override
    public void add(GComponent component) {
        if (component instanceof GButtonOption)
            super.components.add(component);
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
     */
    protected void setColorOption(int colorOption) {
        this.colorOption = colorOption;
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
    public abstract void draw();
}
