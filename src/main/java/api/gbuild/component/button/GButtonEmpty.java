package api.gbuild.component.button;

import api.gbuild.component.GComponent;
import processing.core.PApplet;

/**
 * <p>
 * Button which has no content inside
 * </p>
 * 
 * <p>
 * This is an implementation of a button which does
 * not include any additional component inside it.
 * In order to apply the effect that happens when
 * mouse pointer is hovering the button, the raw and
 * hover color would affect the background of the button
 * </p>
 * 
 * @author David Parreño Barbuzano
 */
public class GButtonEmpty extends GButton {
    /**
     * Create a new instance of an empty button
     * 
     * @param manager Processing manager
     * @param x x component for location
     * @param y y component for location
     * @see GButton#GButton(processing.core.PApplet, float, float)
     */
    public GButtonEmpty(PApplet manager, float x, float y) {
        this(manager, null, x, y);
    }
    
    /**
     * Create a new instance of an empty button
     * 
     * @param manager Processing manager
     * @param parent parent component
     * @param x x component for location
     * @param y y component for location
     * @see GButton#GButton(processing.core.PApplet, api.gbuild.GComponent, float, float) 
     */
    public GButtonEmpty(PApplet manager, GComponent parent, float x, float y) {
        super(manager, parent, x, y);
        super.content.setColor(255, 255, 25);
        super.content.dim(50, 50);
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
        }
    }
}