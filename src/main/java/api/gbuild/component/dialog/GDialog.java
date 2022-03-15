package api.gbuild.component.dialog;

import api.gbuild.GComponent;
import api.gbuild.Globals;
import api.gbuild.component.GContainer;
import api.gbuild.component.GImage;
import api.gbuild.component.GText;
import processing.core.PApplet;
import processing.core.PVector;

public abstract class GDialog extends GComponent {
    protected PVector firstMousePoint, lastMousePoint;
    protected boolean isClosed;
    protected String title, message;
    public GContainer top, bottom;
    
    /**
     * Create a new instance of a dialog
     * 
     * @param manager Processing manager
     * @param title title value
     */
    public GDialog(PApplet manager, String title) {
        super(manager);
        super.setVisible(false);
        this.title = title;
        this.isClosed = false;
        
        float px = manager.width / 2 - title.length() - 150, py = 70;
        float dx = title.length() + 340, dy = title.length() + 50;
    
        this.top = new GContainer(
            manager, px, py, dx,
            dy + 40 + 100
        );
        
        this.top.setColor(90, 155, 217);
        
        GText textValue = new GText(
            manager, this.top, this.title,
            10, title.length() * 6
        );
        
        textValue.setColor(255, 255, 255);
        textValue.setSize(30);
        this.top.add(textValue);
    
        this.top.add(new GImage(
          manager, this.top,
          Globals.CLOSE_NO_ACTIVE_BUTTON,
          this.top.dim().x - 40, 5, 30, 30
        ));
    }
    
    @Override
    public void setVisible(boolean isVisible) {
        super.setVisible(isVisible);
    
        if (!this.isVisible())
            this.isClosed = false;
    }
}