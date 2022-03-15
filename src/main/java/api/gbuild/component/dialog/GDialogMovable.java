package api.gbuild.component.dialog;

import api.gbuild.Globals;
import processing.core.PApplet;
import processing.core.PVector;

/**
 *
 * @author David Parreño Barbuzano
 */
public class GDialogMovable extends GDialog implements GMovable {
    private boolean isMoving;
    
    public GDialogMovable(PApplet manager, String title) {
        super(manager, title);
        this.isMoving = false;
    }
    
    @Override
    public void setVisible(boolean isVisible) {
        super.setVisible(isVisible);
    
        if (!this.isVisible())
            this.isMoving = false;
    }
    
    @Override
    public void move() {
        // Check if user want's to move current dialog
        if (this.isVisible() && !manager().mousePressed) {
            if (this.isMoving) {
              this.top.pos(this.lastMousePoint.x, this.lastMousePoint.y);
              
              if (this.bottom != null)
                this.bottom.pos(this.top.pos().x, this.top.pos().y + title.length() + 50);
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

    @Override
    public void draw() {
        if (this.isVisible()) {
            Globals.newDialog = true;
            
            manager().pushMatrix();
            this.move();
            
            if (this.top != null) this.top.draw();
            if (this.bottom != null) this.bottom.draw();
            
            manager().popMatrix();
        } else Globals.newDialog = false;
    }
}
