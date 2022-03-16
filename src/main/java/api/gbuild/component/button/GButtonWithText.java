package api.gbuild.component.button;

import api.gbuild.component.GComponent;
import api.gbuild.component.GText;
import processing.core.PApplet;
import processing.core.PConstants;

/**
 * <p>
 * Button with a text component
 * </p>
 * 
 * <p>
 * This is the implementation of a button which
 * contains a text component inside it
 * </p>
 * 
 * @author David Parreño Barbuzano
 */
public class GButtonWithText extends GButton {
    /**
     * Create a new instance of a button
     * 
     * @param manager Processing manager
     * @param parent parent component
     * @param x x component for location
     * @param y y component for location
     * @param value text value
     * @see GButton#GButton(processing.core.PApplet, api.gbuild.GComponent, float, float) 
     */
    public GButtonWithText(PApplet manager, GComponent parent, float x, float y, String value) {
        super(manager, parent, x, y);
        GText textValue = new GText(manager, super.content, value, 10, 10);
        
        float[] c = super.rawColor.color();
        textValue.setColor(c[0], c[1], c[2]);
        textValue.setAlign(PConstants.CENTER);
        super.content.add(textValue);
    }
    
    /**
     * Create a new instance of a button
     * 
     * @param manager Processing manager
     * @param x x component for location
     * @param y y component for location
     * @param value text value
     * @see GButton#GButton(processing.core.PApplet, float, float) 
     */
    public GButtonWithText(PApplet manager, float x, float y, String value) {
        this(manager, null, x, y, value);
    }
    
    /**
     * Get the value of the button
     * 
     * @return text value of the button
     */
    public String value() {
        return ((GText)this.content.get(0)).value();
    }
    
    @Override
    public void setSize(int size) {
        GText textValue = (GText)(this.content.get(0));
        textValue.setSize(size);
        content.updateComponents();
    }
    
    @Override
    public void draw() {
        if (isVisible()) {
            GText textValue = (GText)(this.content.get(0));
            
            float[] c = super.rawColor.color();
            textValue.setColor(c[0], c[1], c[2]);
            
            if (manager().mouseX >= pos().x && manager().mouseX <= pos().x + dim().x) {
                if (manager().mouseY >= pos().y && manager().mouseY <= pos().y + dim().y) {
                    c = super.hoverColor.color();
                    textValue.setColor(c[0], c[1], c[2]);
                }
            }
            
            this.content.draw();
        }
    }
}