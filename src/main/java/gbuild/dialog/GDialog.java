package gbuild.dialog;

import gbuild.GColor;
import gbuild.GComponent;
import gbuild.GPanel;
import gbuild.GText;
import gbuild.button.GButton;
import gbuild.button.GButtonClose;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;

/**
 * <p>
 * Dialog component which is movable
 * </p>
 * 
 * <p>
 * This class was designed to offer to developers an easy way
 * to build dialogs that can incorporate to its content lots
 * of different components.
 * </p>
 * 
 * <p>
 * gBuild considers a dialog as a component formed by two
 * different areas, top and bottom. The first part of the
 * component is not customizable after the creation of new
 * instance, but bottom can be modified using bottom() method.
 * </p>
 * 
 * <p>
 * As it was mentioned before, dialogs can be moved by the user.
 * It is possible to enable or disable this feature after creating
 * a dialog, as well as the closed state, which will hide the
 * dialog from user if it's enable
 * </p>
 * 
 * @author David Parreño Barbuzano
 */
public class GDialog extends GComponent {
    private PVector firstMousePoint, lastMousePoint;
    private boolean isClosed, isMovable, isMoving;
    private String title;
    private final GPanel top, bottom;
    
    /**
     * Space between text stored at a dialog with text
     */
    public static final int SPACE_GDIALOG_TEXT = 20;
            
    /**
     * Specifies if a new dialog was created and it's
     * visible for user. This variables offers a way
     * to manage modal option for dialogs
     */
    public static boolean newDialog = false;
    
    /**
     * Create a new instance of a dialog
     * 
     * @param manager Processing manager
     * @see GComponent#GComponent(processing.core.PApplet) 
     */
    public GDialog(PApplet manager) {
        super(manager);
        super.setVisible(false);
        this.isClosed = false;
        this.isMovable = true;
        this.isMoving = false;
        
        this.top = new GPanel(manager);
        
        this.bottom = new GPanel(manager, this.top);
        this.bottom.setColor(255, 255, 255);
        this.bottom.setTransparent(false);
        this.bottom.setStrokeTransparent(true);
    }

    /**
     * Move the dialog when user is dragging the mouse
     * pointer on it whether can be moved
     * 
     * <p>
     * This method would not move the dialog whether the component
     * is not visible, user is not dragging the mouse on it, dialog
     * is closed, or it is not movable.
     * </p>
     * 
     * @see GDialog#isClosed() 
     * @see GDialog#isClosed()
     * @see GDialog#isVisible() 
     */
    public void move() {
        if (this.isMovable()) {
            // Check if user want's to move current dialog
            if (this.isVisible() && !manager().mousePressed) {
                if (this.isMoving) {
                  this.top.pos(this.lastMousePoint.x, this.lastMousePoint.y);
                }

                this.firstMousePoint = null;
                this.lastMousePoint = null;
                this.isMoving = false;
            }

            // Check if user is pressing a mouse button
            else if (this.isVisible() && manager().mousePressed) {
                // Check if dialog is not moving
                if (!this.isMoving) {
                    // First position pressed must be on dialog
                    if (manager().mouseX >= this.top.pos().x && manager().mouseX <= this.top.pos().x + this.top.dim().x) {
                        if (manager().mouseY >= this.top.pos().y && manager().mouseY <= this.top.pos().y + this.top.dim().y) {
                            this.firstMousePoint = new PVector(manager().mouseX, manager().mouseY);
                            this.lastMousePoint = new PVector(manager().mouseX, manager().mouseY);
                            this.isMoving = true;
                        }
                    }
                } else {
                    float px = manager().mouseX - this.firstMousePoint.x, py = manager().mouseY - this.firstMousePoint.y;
                    this.lastMousePoint = new PVector(this.top.pos().x + px, this.top.pos().y + py);
                    manager().translate(px, py);
                }
            }
        }
    }
    
    @Override
    public void draw() {
        this.bottom.pos(0, (int)this.top.dim().y);
        
        if (this.bottom.dim().x > this.top.dim().x)
            this.top.dim(this.bottom.dim().x);
        else
            this.bottom.dim(this.top.dim().x);
        
        ((GButtonClose)this.top.get(1)).pos(this.bottom.dim().x - 30);
        
        if (this.isVisible()) {
            newDialog = true;
            
            manager().pushMatrix();
            this.move();
            this.top.draw();
            this.bottom.draw();
            
            manager().popMatrix();
        } else newDialog = false;
    }
    
    @Override
    public Object prop(String name) {
        Object propertyValue = super.prop(name);
        
        if (propertyValue == null) {
            switch ((String)name) {
                case "closeButtonIsSelected": return this.closeButtonIsSelected();
                case "isMovable": return this.isMovable;
                case "isClosed": return this.isClosed();
                case "bottom": return this.bottom();
                case "title": return this.title;
            }
        }
        
        return propertyValue;
    }
    
    @Override
    public boolean prop(Object name, Object value) {
        if (name instanceof String) {
            switch ((String)name) {
                case "isClosed":
                    if (value instanceof Boolean) {
                        this.setClosed((Boolean)value);
                        return true;
                    }
                break;

                case "isMovable":
                    if (value instanceof Boolean) {
                        this.setMovable((Boolean)value);
                        return true;
                    }
                break;

                case "title":
                    if (value instanceof String) {
                        this.setTitle((String)value);
                        return true;
                    }
                break;
            }
        }
        
        return super.prop(name, value);
    }
    
    // Deprecated
    
    @Deprecated        
    @Override
    public void pos(Integer ... coords) {
        this.top.pos(coords);
        this.bottom.pos(0, (int)this.top.dim().y);
        
        super.pos(this.top.pos().x, this.top.pos().y);
        super.dim(this.top.dim().x, this.top.dim().y + this.bottom.dim().y);
    }
    
    @Deprecated
    @Override
    public void pos(Float ... coords) {
        this.top.pos(coords);
        this.bottom.pos(0, (int)this.top.dim().y);
        
        super.pos(this.top.pos().x, this.top.pos().y);
        super.dim(this.top.dim().x, this.top.dim().y + this.bottom.dim().y);
    }
    
    /**
     * Create dialog based on title
     * 
     * <p>
     * Each time the title is changed, dimensions
     * for top and bottom are would be changed.
     * </p>
     * 
     * @param title title for dialog
     * @deprecated
     */
    public final void setTitle(String title) {
        this.title = title;
        
        this.top.clear();
        this.top.pos(manager().width / 2, 50);
        this.top.setStrokeTransparent(true);
        this.top.setTransparent(false);
        this.top.setColor(90, 155, 217);
        
        GText textValue = new GText(manager(), this.top);
        
        textValue.setText(this.title);
        textValue.pos(10, (int)manager().textAscent() + (int)manager().textDescent() + 20);
        textValue.setMode(PConstants.SHAPE);
        textValue.setColor(255, 255, 255);
        textValue.setSize(30);
        
        this.top.add(textValue);
        
        GButtonClose closeButton = new GButtonClose(manager(), this.top);
        closeButton.pos(10 + textValue.dim().x + 10, textValue.dim().y - 20);
        closeButton.dim(20, 20);
        
        closeButton.setBackgroundColor((GColor)closeButton.prop("rawColor"));
        
        this.top.add(closeButton);
        
        this.top.dim(this.top.dim().x + 10);
        super.pos(this.top.pos().x, this.top.pos().y);
        super.dim(this.top.dim().x, this.top.dim().y);
        
        this.bottom.pos(0, (int)this.top.dim().y);
        this.bottom.dim(this.top.dim().x, this.top.dim().y);
    }
    
    /**
     * Return if close button is been hovering
     * 
     * @return state selected
     * @deprecated
     */
    public boolean closeButtonIsSelected() {
        return ((GButton)this.top.get(1)).isSelected();
    }
    
    /**
     * Get the content of the dialog which can be customized
     * 
     * <p>
     * This method allows developers to customize the content
     * of the dialog, so it is possible to incorporate other
     * components such as text, image, button, etc.
     * </p>
     * 
     * <p>
     * It is important to notice that all components that will
     * be added would have it's parent as the bottom panel, so
     * location should be defined as a relative position. This
     * restriction is enable when draw method of a dialog apply
     * this modifications, but it is possible to customize the
     * dialog differently whether draw method does not do that.
     * </p>
     * 
     * @return bottom content
     * @deprecated
     */
    public GPanel bottom() {
        return this.bottom;
    }
    
    @Deprecated
    @Override
    public void setVisible(boolean isVisible) {
        super.setVisible(isVisible);
    
        if (!this.isVisible()) {
            this.isClosed = false;
            this.isMoving = false;
        }
    }
    
    /**
     * Get if panel is closed
     * 
     * @return closed state
     * @deprecated
     */
    public boolean isClosed() {
        return this.isClosed;
    }
    
    /**
     * Get if panel can be moved
     * 
     * @return movable state
     * @deprecated
     */
    public boolean isMovable() {
        return this.isMovable;
    }
    
    /**
     * Specify if dialog is closed
     * 
     * @param isClosed closed state
     * @deprecated
     */
    public void setClosed(boolean isClosed) {
        this.isClosed = isClosed;
    }
    
    /**
     * Specify if dialog can be moved
     * 
     * @param isMovable movable state
     * @deprecated
     */
    public void setMovable(boolean isMovable) {
        this.isMovable = isMovable;
    }
}