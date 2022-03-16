package api.gbuild.component.button;

import api.gbuild.GComponent;
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
        super.content.setColor(255, 255, 25);
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
            float[] c = super.rawColor.color();
            content.setColor(c[0], c[1], c[2]);
            
            if (manager().mouseX >= pos().x && manager().mouseX <= pos().x + dim().x) {
                if (manager().mouseY >= pos().y && manager().mouseY <= pos().y + dim().y) {
                    c = super.hoverColor.color();
                    content.setColor(c[0], c[1], c[2]);
                }
            }
            
            this.content.draw();
            
            manager().pushMatrix();
            manager().translate(content.pos().x, content.pos().y);
            manager().stroke(0, 0, 0);
            manager().line(0, 0, this.content.dim().x, this.content.dim().y);
            manager().line(0, this.content.dim().y, 0, this.content.dim().y);
            manager().popMatrix();
        }
    }
}
