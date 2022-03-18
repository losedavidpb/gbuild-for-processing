package api.gbuild.component.button;

import api.gbuild.component.GComponent;
import api.gbuild.component.GText;
import processing.core.PApplet;

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
     * @see GButton#GButton(processing.core.PApplet, api.gbuild.component.GComponent) 
     */
    public GButtonWithText(PApplet manager, GComponent parent) {
        super(manager, parent);
        GText textValue = new GText(manager, super.content);
        
        textValue.pos(10, 10);
        textValue.setColor(super.rawColor);
        super.content.add(textValue);
        super.content.dim(super.content.dim().x + 10);
    }
    
    /**
     * Create a new instance of a button
     * 
     * @param manager Processing manager
     * @see GButton#GButton(processing.core.PApplet) 
     */
    public GButtonWithText(PApplet manager) {
        this(manager, null);
    }
    
    @Override
    public Object getProperty(String name) {
        Object propertyValue = super.getProperty(name);
        
        if (propertyValue == null) {
            switch ((String)name) {
                case "size": return this.dim().x;
                case "value": return this.value();
                case "mode": return this.mode();
                case "text": return this.text();
                default: return null;
            }
        }
        
        return propertyValue;
    }
    
    @Override
    public void setProperty(Object name, Object value) {
        super.setProperty(name, value);
        
        if (name instanceof String) {
            switch ((String)name) {
                case "size":
                    if (value instanceof Integer)
                        this.setSize((Integer)value);
                break;

                case "value":
                    if (value instanceof String)
                        this.setValue((String)value);
                break;

                case "mode":
                    if (value instanceof Integer)
                        this.setMode((Integer)value);
                break;
            }
        }
    }
    
    @Override
    public void draw() {
        if (isVisible()) {
            this.setSelected(false);
            
            GText textValue = (GText)(this.content.get(0));
            
            textValue.setColor(super.rawColor);
            content.setStrokeColor(super.rawColor);
            
            if (manager().mouseX >= pos().x && manager().mouseX <= pos().x + dim().x) {
                if (manager().mouseY >= pos().y && manager().mouseY <= pos().y + dim().y) {
                    textValue.setColor(super.hoverColor);
                    content.setStrokeColor(super.hoverColor);
                    this.setSelected(true);
                }
            }
            
            this.content.draw();
        }
    }
    
    // Deprecated
    
    /**
     * Return the text of the button
     * 
     * @return text component
     * @deprecated
     */
    public GText text() {
        return (GText)this.content.get(0);
    }
    
    /**
     * Get the value of the button
     * 
     * @return text value of the button
     * @deprecated
     */
    public String value() {
        return ((GText)this.content.get(0)).value();
    }
    
    /**
     * Set the value of the button
     * 
     * @param value value of the button
     * @deprecated
     */
    public void setValue(String value) {
        ((GText)this.content.get(0)).setText(value);
        super.content.dim(super.content.dim().x + 10);
    }
    
    /**
     * Get the mode for text component
     * 
     * <p>
     *  For more information about text mode, click
     *  <a href="https://processing.org/reference/textMode_.html">here</a>
     * </p>
     * 
     * @return text mode
     * @see GText#setMode(int)
     * @deprecated
     */
    public int mode() {
        return ((GText)this.content.get(0)).mode();
    }
    
    /**
     * Specify the mode of the text contained at the button.
     * Available values are MODEL and SHAPE
     * 
     * <p>
     * Available values are MODEL and SHAPE, so the modification
     * would not be done if the passed value is not one of these values 
     * </p>
     * 
     * <p>
     *  For more information about text alignment, click
     *  <a href="https://processing.org/reference/textMode_.html">here</a>
     * </p>
     * 
     * @param tmode text mode
     * @deprecated
     */
    public void setMode(int tmode) {
        ((GText)this.content.get(0)).setMode(tmode);
    }
    
    @Deprecated
    @Override
    public void setSize(int size) {
        GText textValue = (GText)(this.content.get(0));
        textValue.setSize(size);
        content.updateComponents();
        super.content.dim(super.content.dim().x + textValue.pos(true).x);
    }
   
}