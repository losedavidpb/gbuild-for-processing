package gbuild.button;

import gbuild.GComponent;
import static gbuild.GPanel.PANEL_DIM_X;
import static gbuild.GPanel.PANEL_DIM_Y;
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
     * @see GButton#GButton(processing.core.PApplet)
     */
    public GButtonEmpty(PApplet manager) {
        this(manager, null);
    }
    
    /**
     * Create a new instance of an empty button
     * 
     * @param manager Processing manager
     * @param parent parent component
     * @see GButton#GButton(processing.core.PApplet, api.gbuild.component.GComponent) 
     */
    public GButtonEmpty(PApplet manager, GComponent parent) {
        super(manager, parent);
        super.content.setColor(255, 255, 25);
        super.content.dim(PANEL_DIM_X, PANEL_DIM_Y);
    }
    
    @Override
    public void draw() {
        if (isVisible()) {
            content.setColor(super.rawColor);
            this.setSelected(false);
            
            if (manager().mouseX >= pos().x && manager().mouseX <= pos().x + dim().x) {
                if (manager().mouseY >= pos().y && manager().mouseY <= pos().y + dim().y) {
                    content.setColor(super.hoverColor);
                    this.setSelected(true);
                }
            }
            
            this.content.draw();
        }
    }
}