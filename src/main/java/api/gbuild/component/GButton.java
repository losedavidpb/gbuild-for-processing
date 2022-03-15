package api.gbuild.component;

import api.gbuild.GColor;
import api.gbuild.GComponent;
import processing.core.PApplet;
import static processing.core.PApplet.abs;

/**
 * Implementation of a clikable button
 * 
 * @author David Parreño Barbuzano
 */
public class GButton extends GComponent {
    private final GContainer content;
    private final GColor rawColor, hoverColor;
    private boolean isSelected;
    
    /**
     * Create a new instance of a button
     * 
     * @param manager Processing manager
     * @param parent parent component
     * @param x x component for location
     * @param y y coponent for location
     * @param value text value
     */
    public GButton(PApplet manager, GComponent parent, float x, float y, String value) {
        super(manager, parent);
        this.content = new GContainer(manager, parent, x, y, 0, 0);
        
        GText valueText = new GText(manager, this.content, value, 10, 10);
        this.content.add(valueText);
        
        this.rawColor = new GColor(255, 255, 255);
        this.hoverColor = new GColor(0, 0, 0);
        this.isSelected = false;
        
        super.pos(x, y);
        super.dim(manager.textWidth(value) + 20, (float)((GText)this.content.get(0)).size());
    }
    
    /**
     * Create a new instance of a button
     * 
     * @param manager Processing manager
     * @param x x component for location
     * @param y y coponent for location
     * @param value text value
     */
    public GButton(PApplet manager, float x, float y, String value) {
        this(manager, null, x, y, value);
    }
    
    /**
     * Return the text value of the button
     * 
     * @return text value
     */
    public String value() {
        return ((GText)this.content.get(0)).value();
    }
    
    @Override
    public void pos(Float ... coords) {
        super.pos(coords);
        this.content.pos(coords);
    }
    
    @Override
    public void pos(Integer ... coords) {
        super.pos(coords);
        this.content.pos(coords);
    }
    
    @Override
    public void dim(Float ... dim) {
        super.dim(dim);
        this.content.dim(dim);
    }
    
    @Override
    public void dim(Integer ... dim) {
        super.dim(dim);
        this.content.dim(dim);
    }
    
    /**
     * Get the text size
     * 
     * @return text size
     */
    public int size() {
        GText textValue = ((GText)this.content.get(0));
        return textValue.size();
    }
    
    /**
     * Return the alignment
     * 
     * @return text alignment
     */
    public int align() {
        GText textValue = ((GText)this.content.get(0));
        return textValue.align();
    }
    
    /**
     * Return the text mode
     * 
     * @return text mode
     */
    public int mode() {
        GText textValue = ((GText)this.content.get(0));
        return textValue.mode();
    }
    
    /**
     * Return if button is selected
     * 
     * @return selected state
     */
    public boolean isSelected() {
        return this.isSelected;
    }
    
    /**
     * Get the raw color
     * 
     * @return raw color
     */
    public float[] rawColor() {
        return this.rawColor.color();
    }
    
    /**
     * Get the hover color
     * 
     * @return hover color
     */
    public float[] hoverColor() {
        return this.hoverColor.color();
    }
    
    /**
     * Specify the text that will be drawn
     * 
     * @param value text string
     */
    public void setText(String value) {
        GText textValue = ((GText)this.content.get(0));
        textValue.setText(value);
    }
        
    /**
     * Specify the alignment of the text.
     * Available values are LEFT, RIGHT, and CENTER
     * 
     * @param talign alignment
     */
    public void setAlign(int talign) {
        GText textValue = ((GText)this.content.get(0));
        textValue.setAlign(talign);
    }
    
    /**
     * Specify the mode of the text.
     * Available values are MODEL and SHAPE
     * 
     * @param tmode text mode
     */
    public void setMode(int tmode) {
        GText textValue = ((GText)this.content.get(0));
        textValue.setMode(tmode);
    }
    
    /**
     * Set size for current button
     * 
     * @param size size for button
     */
    public void setSize(int size) {
        GText textValue = ((GText)this.content.get(0));
        textValue.setSize(size);
        
        float width = (size * (manager().textWidth(textValue.value()))) / 11;
        this.dim(width, manager().textAscent() + size);
        
        textValue.pos(null, (pos().y + dim().y) - textValue.pos().y - 20);
    }
    
    /**
     * Set the raw color for container
     * 
     * @param component red, green, and blue component
     */
    public void setRawColor(Float ... component) {
        this.rawColor.setColor(component);
    }
    
    /**
     * Set the raw color for container
     * 
     * @param component red, green, and blue component
     */
    public void setRawColor(Integer ... component) {
        this.rawColor.setColor(component);
    }
    
    /**
     * Set the hover color for container
     * 
     * @param component red, green, and blue component
     */
    public void setHoverColor(Float ... component) {
        this.hoverColor.setColor(component);
    }
    
    /**
     * Set the hover color for container
     * 
     * @param component red, green, and blue component
     */
    public void setHoverColor(Integer ... component) {
        this.hoverColor.setColor(component);
    }
    
    /**
     * Set the background color for container
     * 
     * @param color red, green, and blue component
     */
    public void setBackground(Float ... color) {
        this.content.setColor(color);
    }
    
    /**
     * Set the background color for container
     * 
     * @param color red, green, and blue component
     */
    public void setBackground(Integer ... color) {
        this.content.setColor(color);
    }
    
    /**
     * Specify if button is selected
     * 
     * @param isSelected selected state
     */
    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    @Override
    public void draw() {
        if (isVisible()) {
            float[] c = this.rawColor.color();
            ((GText)this.content.get(0)).setColor(c[0], c[1], c[2]);
            
            if (manager().mouseX > pos().x && manager().mouseX < pos().x + dim().x) {
                if (manager().mouseY > pos().y && manager().mouseY < pos().y + dim().y) {
                    c = this.hoverColor.color();
                    ((GText)this.content.get(0)).setColor(c[0], c[1], c[2]);
                } 
            }
            
            this.content.draw();
        }
    }
}