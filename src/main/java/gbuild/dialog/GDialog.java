package gbuild.dialog;

import gbuild.GColor;
import gbuild.GComponent;
import gbuild.GPanel;
import gbuild.GText;
import gbuild.button.GButton;
import gbuild.button.GButtonClose;
import gbuild.event.DragGEvent;
import processing.core.PApplet;
import processing.core.PConstants;

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
    private boolean isClosed, isMovable;
    private final GPanel top, bottom;
    private String title;
    
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
        
        this.top = new GPanel(manager);
        this.top.setOpaque(true);
        this.top.addEvent(new DragGEvent(this.top));
        
        this.bottom = new GPanel(manager, this.top);
        this.bottom.setOpaque(true);
        this.bottom.setColor(255, 255, 255);
        this.bottom.setStrokeOpaque(true);
    }
    
    @Override
    public void pos(Integer ... coords) {
        this.top.pos(coords);
        this.bottom.pos(0, (int)this.top.dim().y);
        
        super.pos((int)this.top.pos().x, (int)this.top.pos().y);
        super.dim((int)this.top.dim().x, (int)this.top.dim().y + (int)this.bottom.dim().y);
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
     */
    public final void setTitle(String title) {
        this.title = title;
        
        this.top.clear();
        this.top.pos(manager().width / 2, 50);
        this.top.setStrokeOpaque(false);
        this.top.setOpaque(true);
        this.top.setColor(90, 155, 217);
        
        GText textValue = new GText(manager(), this.top);
        
        textValue.setText(this.title);
        textValue.pos(10, (int)manager().textAscent() + (int)manager().textDescent() + 20);
        textValue.setMode(PConstants.SHAPE);
        textValue.setColor(255, 255, 255);
        textValue.setSize(30);
        
        this.top.add(textValue);
        
        GButtonClose closeButton = new GButtonClose(manager(), this.top);
        closeButton.setOpaque(false);
        closeButton.pos(10 + (int)textValue.dim().x + 10, (int)textValue.dim().y - 20);
        closeButton.dim(20, 20);
        
        closeButton.setBackgroundColor((GColor)closeButton.rawColor());
        
        this.top.add(closeButton);
        
        this.top.dim((int)this.top.dim().x + 10);
        super.pos((int)this.top.pos().x, (int)this.top.pos().y);
        super.dim((int)this.top.dim().x, (int)this.top.dim().y);
        
        this.bottom.pos(0, (int)this.top.dim().y);
        this.bottom.dim((int)this.top.dim().x, (int)this.top.dim().y);
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
     */
    public GPanel bottom() {
        return this.bottom;
    }
    
    @Override
    public void setVisible(boolean isVisible) {
        super.setVisible(isVisible);
        if (!this.isVisible()) this.isClosed = false;
    }
    
    /**
     * Get if panel is closed
     * 
     * @return closed state
     */
    public boolean isClosed() {
        return this.isClosed;
    }
    
    /**
     * Get if panel can be moved
     * 
     * @return movable state
     */
    public boolean isMovable() {
        return this.isMovable;
    }
    
    /**
     * Specify if dialog is closed
     * 
     * @param isClosed closed state
     */
    public void setClosed(boolean isClosed) {
        this.isClosed = isClosed;
        
        if (this.isClosed) {
            this.setVisible(false);
            GDialog.newDialog = false;
        }
    }
    
    /**
     * Specify if dialog can be moved
     * 
     * @param isMovable movable state
     */
    public void setMovable(boolean isMovable) {
        if (this.isMovable != isMovable)
            this.top.clearEvents();
        
        this.isMovable = isMovable;
        if (isMovable) top.addEvent(new DragGEvent(this.top));
    }
    
    @Override
    public void draw() {
        if (!this.isClosed) {
            if (this.isVisible()) {
                this.fixSize();
                ((GButton)this.top.get(1)).pos((int)this.bottom.dim().x - 30);
            
                this.listenEvents();
                this.top.draw();
                this.bottom.draw();
                this.setClosed(((GButton)this.top.get(1)).isSelected());
            }
        }
    }

    // -------------------------------
    // Private methods
    // -------------------------------
    
    private void fixSize() {
        this.bottom.pos(0, (int)this.top.dim().y);
        
        if (this.bottom.dim().x > this.top.dim().x)
            this.top.dim((int)this.bottom.dim().x);
        else
            this.bottom.dim((int)this.top.dim().x);
    }
}