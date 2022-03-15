package api.gbuild.component.menu;

import api.gbuild.GComponent;
import api.gbuild.component.GOption;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PShape;

/**
 * This is the implementation of a vertical menu
 * 
 * The options included at this menu would be colored differently
 * when user hover the mouse pointer over them.
 * 
 * Since this component does not incorporate any action when user
 * click one of the options, it is necessary to control this state
 */
public class HorizontalGMenu extends GMenu {
    private int colorOption, diffPosX;
    
    /**
     * Create a new instance of a horizontal menu
     * 
     * @param manager Processing manager
     * @param x x component for location
     * @param y y component for location
     */
    public HorizontalGMenu(PApplet manager, float x, float y) {
        super(manager, x, y);
        colorOption = -1;
        diffPosX = super.spaceValue;
    }
    
    /**
     * Create a new instance of a menu
     * 
     * @param manager Processing manager
     * @param parent component parent
     * @param x x component for location
     * @param y y component for location
     */
    public HorizontalGMenu(PApplet manager, GComponent parent, float x, float y) {
        super(manager, parent, x, y);
        colorOption = -1;
        diffPosX = super.spaceValue;
    }
    
    @Override
    public void add(GComponent component) {
        super.add(component);

        if (component instanceof GOption) {
            GOption option = (GOption)component;
            option.pos((float)diffPosX, this.pos().y + 20);
            diffPosX = (int) (option.pos().x + option.dim().x + super.spaceValue); 
        }
    }
    
    /**
     * Return the index of current option
     * 
     * @return index of option
     */
    public int colorOption() {
        return this.colorOption;
    }
    
    @Override
    public void draw() {
        if (this.isVisible()) {
            super.manager().pushMatrix();
      
            boolean optSelected = false;
      
            PShape rect = manager().createShape(
                PConstants.RECT, this.pos().x, this.pos().y,
                this.dim().x, this.dim().y
            );
        
            rect.setFill(color());
            manager().shape(rect);
    
            for (int i = 0; i < this.components.size(); i++) {
                GOption option = (GOption) this.components.get(i);
                option.draw();
                
                if (option.isSelected()) {
                    optSelected = true;
                    this.colorOption = i;
                }
            }
    
            // Any option has been selected by the mouse.
            // So we prevent the color selection by restarting
            // current option marked as hovered
            if (!optSelected) this.colorOption = -1;
    
            manager().popMatrix();
        }
    }
}