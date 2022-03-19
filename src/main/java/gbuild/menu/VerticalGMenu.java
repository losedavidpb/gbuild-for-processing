package gbuild.menu;

import gbuild.GComponent;
import gbuild.GPanel;
import gbuild.button.GButtonOption;
import processing.core.PApplet;

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
     * @see GMenu#GMenu(processing.core.PApplet, api.gbuild.component.GComponent) 
     */
    public VerticalGMenu(PApplet manager, GPanel parent) {
        super(manager, parent);
        this.diffPosY = super.spaceValue;
    }
    
    /**
     * Create a new instance of a vertical menu
     * 
     * @param manager Processing manager
     * @see GMenu#GMenu(processing.core.PApplet, api.gbuild.GComponent) 
     */
    public VerticalGMenu(PApplet manager) {
        this(manager, null);
    }
    
    @Override
    public void setSpace(int spaceValue) {
        super.setSpace(spaceValue);
        this.diffPosY = (int)this.pos().y;
    }
    
    @Override
    public void add(GComponent component) {
        if (component instanceof GButtonOption) {
            GButtonOption option = (GButtonOption)component;
            
            if (option.parent() == null) {
                option.pos(this.pos().x, (float)this.diffPosY);
                diffPosY += (int) (option.pos().y + option.dim().y + super.spaceValue); 
            } else {
                option.pos(0, this.diffPosY);
                diffPosY += (int) (option.dim().y + super.spaceValue); 
            }
            
            super.add(component);
        }
    }
    
    @Override
    public void draw() {
        if (this.isVisible()) {
            super.manager().pushMatrix();
      
            boolean optSelected = false;
            
            if (!this.isTransparent()) {
                super.color.applyFillColor(manager());
                manager().rect(this.pos().x, this.pos().y, this.dim().x, this.dim().y);
            }
            
            int offsetY = (int)this.pos().y;
            
            for (int i = 0; i < this.components.size(); i++) {
                GButtonOption option = (GButtonOption) this.components.get(i);
                
                if (option.parent() == null) {
                    option.pos(this.pos().x, (float)offsetY);
                } else {
                    option.pos(0, offsetY);
                }
                
                if (option.isVisible()) {
                    offsetY += (int) (option.dim().y + super.spaceValue);
                    
                    if (option.parent() == null)
                        offsetY += (int) option.pos().y;
                }
                
                option.draw();
                
                if (option.isSelected() && option.isVisible()) {
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