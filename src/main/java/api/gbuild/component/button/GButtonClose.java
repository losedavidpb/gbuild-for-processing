package api.gbuild.component.button;

import api.gbuild.component.GComponent;
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
     * @param x x component for location
     * @param y y component for location
     * @see GButton#GButton(processing.core.PApplet, api.gbuild.GComponent, float, float) 
     */
    public GButtonClose(PApplet manager, GComponent parent, float x, float y) {
        super(manager, parent, x, y);
        super.setHoverColor(226, 19, 19);
        super.content.setColor(255, 255, 25);
        super.content.setTransparency(true);
        super.content.dim(50, 50);
        this.content.clear();
    }
    
    /**
     * Create a new instance of a button
     * 
     * @param manager Processing manager
     * @param x x component for location
     * @param y y component for location
     * @see GButton#GButton(processing.core.PApplet, float, float) 
     */
    public GButtonClose(PApplet manager, float x, float y) {
        this(manager, null, x, y);
    }
    
    @Override
    public void setSize(int size) {
        this.content.dim(size, size);
        this.content.clear();
    }
    
    @Override
    public void draw() {
        if (isVisible()) {
            content.setTransparency(true);
            float[] c = super.hoverColor.color();
            this.setSelected(false);
            
            if (manager().mouseX >= pos().x && manager().mouseX <= pos().x + dim().x) {
                if (manager().mouseY >= pos().y && manager().mouseY <= pos().y + dim().y) {
                    content.setTransparency(false);
                    content.setColor(c[0], c[1], c[2]);
                    this.setSelected(true);
                }
            }
            
            this.content.draw();
            int offset = 3;
            
            manager().pushMatrix();
            manager().translate(content.pos().x, content.pos().y);
            
            manager().strokeWeight(5);
            manager().stroke(255, 255, 255);
            manager().line(offset, offset, this.content.dim().x - offset, this.content.dim().y - offset);
            manager().line(offset, this.content.dim().y - offset, this.content.dim().x - offset, offset);
            
            if (!content.isTransparent()) {
                manager().strokeWeight(3);
                manager().stroke(manager().color(c[0], c[1], c[2]));
                manager().line(0, 0, this.content.dim().x, 0);
                manager().line(this.content.dim().x, 0, this.content.dim().x, this.content.dim().y);
                manager().line(this.content.dim().x, this.content.dim().y, 0, this.content.dim().y);
                manager().line(0, this.content.dim().y, 0, 0);
            }
            
            manager().popMatrix();
        }
    }
}
