package api.gbuild.component.menu;

import api.gbuild.component.GComponent;
import api.gbuild.component.GPanel;
import api.gbuild.component.button.GButtonOption;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PShape;

/**
 * <p>
 * Vertical Menu with clickable options
 * </p>
 * 
 * <p>
 * This class is the implementation of a menu with
 * clickable options that are distributed vertically.
 * </p>
 * 
 * @author David Parreño Barbuzano
 */
public class VerticalGMenu extends GMenu {
    private int diffPosY;
    
    /**
     * Create a new instance of a vertical menu
     * 
     * @param manager Processing manager
     * @param parent component parent
     * @param x x component for location
     * @param y y component for location
     * @see GMenu#GMenu(processing.core.PApplet, api.gbuild.GComponent, float, float) 
     */
    public VerticalGMenu(PApplet manager, GPanel parent, float x, float y) {
        super(manager, parent, x, y);
        this.diffPosY = super.spaceValue;
    }
    
    /**
     * Create a new instance of a vertical menu
     * 
     * @param manager Processing manager
     * @param x x component for location
     * @param y y component for location
     * @see GMenu#GMenu(processing.core.PApplet, api.gbuild.GComponent, float, float) 
     */
    public VerticalGMenu(PApplet manager, float x, float y) {
        this(manager, null, x, y);
    }
    
    @Override
    public void add(GComponent component) {
        if (component instanceof GButtonOption) {
            GButtonOption option = (GButtonOption)component;
            
            if (option.parent() == null) {
                option.pos(this.pos().x + 35, (float)this.diffPosY);
                diffPosY = (int) (option.pos().y + option.dim().y + super.spaceValue); 
            } else {
                option.pos(35, this.diffPosY);
                diffPosY = (int) (option.dim().y + super.spaceValue); 
            }
            
            super.add(component);
        }
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
            
            float[] c = color();
            rect.setFill(manager().color(c[0], c[1], c[2]));
            manager().shape(rect);
    
            for (int i = 0; i < this.components.size(); i++) {
                GButtonOption option = (GButtonOption) this.components.get(i);
                option.draw();
                
                if (option.isSelected()) {
                    optSelected = true;
                    setColorOption(i);
                }
            }
    
            // Any option has been selected by the mouse.
            // So we prevent the color selection by restarting
            // current option marked as hovered
            if (!optSelected) setColorOption(-1);
    
            manager().popMatrix();
        }
    }
}