package api.gbuild.component.dialog;

import api.gbuild.GComponent;
import api.gbuild.Globals;
import api.gbuild.component.GContainer;
import api.gbuild.component.GImage;
import api.gbuild.component.GText;
import processing.core.PApplet;
import processing.core.PVector;

public class GDialog extends GComponent {
    PVector firstMousePoint, lastMousePoint;
    boolean isMoving, isClosed;
    GContainer top, bottom;
    String title, message;
    
    public GDialog(PApplet manager, String title, String message) {
        super(manager);
        super.setVisible(false);
        this.title = title;
        this.isClosed = false;
        
        float px = manager.width / 2 - title.length() - 150, py = 70;
        float dx = title.length() + 340, dy = title.length() + 50;
    
        this.top = new GContainer(
            manager, px, py, dx,
            dy + 40 + message.length() + 10
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
          (int)this.top.dim().x - 40,
          5, 30, 30
        ));
    
        this.bottom = new GContainer(
            manager, px, py + dy,
            dx, dy + 40 + message.length()
        );
        
        this.bottom.setColor(255, 255, 255);
    }
    
    @Override
    public void setVisible(boolean isVisible) {
        super.setVisible(isVisible);
    
        if (!this.isVisible()) {
            this.isMoving = false;
            this.isClosed = false;
        }
    }
    
    public void setMessage(int x, int y, int size) {
        if (this.bottom.size() <= 0) {
            GText textBottom = new GText(
                manager(), this.bottom,
                this.message, x, y
            );
            
            textBottom.setSize(size);
            textBottom.setColor(0, 0, 0);
            this.bottom.add(textBottom);
        }
    }
  
    public void setMessage(int x1, int y1, int size, String iconPath, int x2, int y2, int w, int h) {
        if (this.bottom.size() <= 0) {
            GText textBottom = new GText(
                manager(), this.bottom,
                this.message, x1, y1
            );
            
            textBottom.setSize(size);
            textBottom.setColor(0, 0, 0);
            bottom.add(textBottom);
            
            this.bottom.add(new GImage(
                manager(),  this.bottom,
                iconPath, x2, y2, w, h
            ));
        }
    }
    
    /**
     * Move current dialog if user is pressing mouse
     * button while is moving the pointer at one of
     * the areas displayed of the dialog
     */
    public void move() {
        manager().pushMatrix();
    
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
    
        this.draw();
        manager().popMatrix();
    }
  
    @Override
    public void draw() {
        if (this.isVisible()) {
            Globals.newDialog = true;
            
            manager().pushMatrix();
            this.top.draw();
            if (this.bottom != null) this.bottom.draw();
            manager().popMatrix();
        } else Globals.newDialog = false;
    }
}

