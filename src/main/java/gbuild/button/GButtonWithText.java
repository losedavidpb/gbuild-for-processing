package gbuild.button;

import gbuild.GComponent;
import gbuild.GText;
import gbuild.event.ClickAtGEvent;
import gbuild.event.HoverGEvent;
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
        super.content.dim((int)super.content.dim().x + 10);
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
    
    /**
     * Return the text of the button
     * 
     * @return text component
     */
    public GText text() {
        return (GText)this.content.get(0);
    }
    
    /**
     * Get the value of the button
     * 
     * @return text value of the button
     */
    public String textValue() {
        return text().value();
    }
    
    /**
     * Set the value of the button
     * 
     * @param value value of the button
     */
    public void setTextValue(String value) {
        text().setText(value);
        super.content.dim((int)super.content.dim().x + 10);
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
     */
    public int mode() {
        return text().mode();
    }
    
    /**
     * Specify the mode of the text contained at the button.Available values are MODEL and SHAPE<p>
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
     */
    public void setMode(int tmode) {
        text().setMode(tmode);
    }
    
    @Override
    public void setSize(int size) {
        GText textValue = (GText)(this.content.get(0));
        textValue.setSize(size);
        content.updateComponents();
        super.content.dim((int)super.content.dim().x + (int)textValue.pos(true).x);
    }

    @Override
    public void draw() {
        if (isVisible()) {
            this.listenEvents();
            this.content.draw();
            this.updateButton();
        }
    }

    @Override
    public void updateButton() {
        this.setHover(false);
        GText textValue = text();
        textValue.setColor(super.rawColor);
        content.setStrokeColor(super.rawColor);
        
        HoverGEvent event1 = (HoverGEvent)this.getEvent(0);
        ClickAtGEvent event2 = (ClickAtGEvent)this.getEvent(1);
        
        if (event1.isHover()) {
            textValue.setColor(super.hoverColor);
            content.setStrokeColor(super.hoverColor);
            this.setHover(true);
        }
        
        this.setSelected(event2.isClicked());
    }
}