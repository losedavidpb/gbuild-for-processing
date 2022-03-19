package gbuild.menu;

import gbuild.GComponent;
import gbuild.button.GButtonOption;
import processing.core.PApplet;

/**
 * <p>
 * Horizontal Menu with clickable options
 * </p>
 * 
 * <p>
 * This class is the implementation of a menu with
 * clickable options that are distributed horizontally.
 * </p>
 * 
 * @author David Parreño Barbuzano
 */
public class HorizontalGMenu extends GMenu {
    private int diffPosX;
    
    /**
     * Create a new instance of a horizontal menu
     * 
     * @param manager Processing manager
     * @param parent component parent
     * @see GMenu#GMenu(processing.core.PApplet, api.gbuild.component.GComponent) 
     */
    public HorizontalGMenu(PApplet manager, GComponent parent) {
        super(manager, parent);
        diffPosX = super.spaceValue;
    }
    
    /**
     * Create a new instance of a horizontal menu
     * 
     * @param manager Processing manager
     * @see GMenu#GMenu(processing.core.PApplet) 
     */
    public HorizontalGMenu(PApplet manager) {
        this(manager, null);
    }
    
    @Override
    public void setSpace(int spaceValue) {
        super.setSpace(spaceValue);
        diffPosX = (int) this.pos().x;
    }
    
    @Override
    public void add(GComponent component) {
        if (component instanceof GButtonOption) {
            GButtonOption option = (GButtonOption)component;
            
            if (option.parent() == null) {
                option.pos((float)diffPosX, this.pos().y + 20);
                diffPosX += (int) (option.pos().x + option.dim().x + super.spaceValue); 
            } else {
                option.pos(diffPosX, 20);
                diffPosX += (int) (option.dim().x + super.spaceValue); 
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
            
            int offsetX = (int)this.pos().x;
            
            for (int i = 0; i < this.components.size(); i++) {
                GButtonOption option = (GButtonOption) this.components.get(i);
                
                if (option.parent() == null) {
                    option.pos((float)diffPosX, this.pos().y + 20);
                } else {
                    option.pos(offsetX, 20);
                }
                
                if (option.isVisible()) {
                    offsetX += (int) (option.dim().x + super.spaceValue);
                    
                    if (option.parent() == null)
                        offsetX += (int) option.pos().x;
                }
                
                option.draw();
                
                if (option.isSelected()) {
                    optSelected = true;
                    super.setColorOption(i);
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