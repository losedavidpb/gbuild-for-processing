package api.gbuild.component.menu;

import api.gbuild.GComponent;
import api.gbuild.component.GContainer;
import processing.core.PApplet;

/**
 * Definition of a menu with clickable options
 * 
 * Since menus can be defined in different directions,
 * it is necessary to define subtypes of menus that
 * extends this model.
 * 
 * @author David
 */
public abstract class GMenu extends GContainer {
    protected int spaceValue = 50;

    public GMenu(PApplet manager, float x, float y) {
        super(manager, x, y, 0, 0);
    }

    GMenu(PApplet manager, GContainer parent, float x, float y) {
        super(manager, parent, x, y, 0, 0);
    }
  
    @Override
    public void add(GComponent component) {
        if (component instanceof GOption)
            super.components.add(component);
    }
    
    /**
     * Set space between options
     * 
     * @param spaceValue value for space width
     */
    void setSpace(int spaceValue) {
        this.spaceValue = spaceValue;
    }
    
    /**
     * Set the visible state for one of the options
     * 
     * @param name identifier for current option
     * @param isVisible visible state
     */
    void setVisible(String name, boolean isVisible) {
        for (int i = 0; i < this.components.size(); i++) {
            GOption option = (GOption)this.components.get(i);

            if (option.value().equals(name)) {
                option.setVisible(isVisible);
                return;
            }
        }
    }
}
