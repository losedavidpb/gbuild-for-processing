package gbuild.button;

import gbuild.GComponent;
import static gbuild.GPanel.PANEL_DIM_X;
import static gbuild.GPanel.PANEL_DIM_Y;
import processing.core.PApplet;

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
        super.content.setStrokeTransparent(true);
        super.setTransparent(true);
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
            this.setTransparent(true);
            this.setSelected(false);
            
            if (manager().mouseX >= pos().x && manager().mouseX <= pos().x + dim().x) {
                if (manager().mouseY >= pos().y && manager().mouseY <= pos().y + dim().y) {
                    this.setTransparent(false);
                    this.setBackgroundColor(super.hoverColor);
                    this.setSelected(true);
                }
            }
            
            this.content.draw();
            int offset = 3;
            
            manager().pushMatrix();
            manager().pushStyle();
            manager().translate(content.pos().x, content.pos().y);
            
            manager().strokeWeight(5);
            manager().stroke(255, 255, 255);
            manager().line(offset, offset, this.content.dim().x - offset, this.content.dim().y - offset);
            manager().line(offset, this.content.dim().y - offset, this.content.dim().x - offset, offset);
            
            if (!this.isTransparent()) {
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
}
