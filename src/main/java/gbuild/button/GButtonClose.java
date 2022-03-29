package gbuild.button;

import gbuild.GComponent;
import gbuild.event.ClickAtGEvent;
import gbuild.event.HoverGEvent;
import processing.core.PApplet;

import static gbuild.GPanel.PANEL_DIM_X;
import static gbuild.GPanel.PANEL_DIM_Y;

/**
 * <p>
 * Button with the shape of a close button
 * </p>
 * 
 * <p>
 * This button is a set of components that formed an square
 * rectangle with an x inside it that should be associated to
 * an event for closing the component.
 * </p>
 * 
 * @author David Parreño Barbuzano
 */
public class GButtonClose extends GButton {
    /**
     * Create a new instance of a button
     * 
     * @param manager Processing manager
     * @param parent parent component
     * @see GButton#GButton(processing.core.PApplet, api.gbuild.component.GComponent) 
     */
    public GButtonClose(PApplet manager, GComponent parent) {
        super(manager, parent);
        super.setHoverColor(226, 19, 19);
        super.setStrokeOpaque(false);
        super.setOpaque(false);
        super.content.dim(PANEL_DIM_X, PANEL_DIM_Y);
        this.content.clear();
    }
    
    /**
     * Create a new instance of a button
     * 
     * @param manager Processing manager
     * @see GButton#GButton(processing.core.PApplet) 
     */
    public GButtonClose(PApplet manager) {
        this(manager, null);
    }
    
    @Override
    public void setSize(int size) {
        this.content.dim(size, size);
        this.content.clear();
    }
    
    @Override
    public void draw() {
        if (isVisible()) {
            this.listenEvents();
            this.updateButton();

            this.content.draw();
            int offset = 3;
            
            manager().pushMatrix();
            manager().pushStyle();
            manager().translate(content.pos().x, content.pos().y);
            
            manager().strokeWeight(5);
            manager().stroke(255, 255, 255);
            manager().line(offset, offset, this.content.dim().x - offset, this.content.dim().y - offset);
            manager().line(offset, this.content.dim().y - offset, this.content.dim().x - offset, offset);
            
            if (this.isHover()) {
                float[] c = super.hoverColor.color();
                
                manager().strokeWeight(3);
                manager().stroke(c[0], c[1], c[2]);
                manager().line(0, 0, this.content.dim().x, 0);
                manager().line(this.content.dim().x, 0, this.content.dim().x, this.content.dim().y);
                manager().line(this.content.dim().x, this.content.dim().y, 0, this.content.dim().y);
                manager().line(0, this.content.dim().y, 0, 0);
            }
            
            manager().popStyle();
            manager().popMatrix();
        }
    }

    @Override
    public void updateButton() {
        super.setOpaque(false);
        super.setBackgroundColor(super.rawColor);
        super.setHover(false);
        super.setSelected(false);
        
        HoverGEvent event1 = (HoverGEvent)this.getEvent(0);
        ClickAtGEvent event2 = (ClickAtGEvent)this.getEvent(1);
        
        if (event1.isHover()) {
            super.setOpaque(true);
            super.setBackgroundColor(super.hoverColor);
            super.setHover(true);
        }
        
        super.setSelected(event2.isClicked());
    }
}