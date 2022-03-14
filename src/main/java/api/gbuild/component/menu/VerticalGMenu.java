package api.gbuild.component.menu;

import api.gbuild.Globals;
import api.gbuild.component.GContainer;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PShape;
import processing.core.PVector;

/**
 * This is the implementation of a vertical menu
 * 
 * The options included at this menu would be colored differently
 * when user hover the mouse pointer over them.
 * 
 * Since this component does not incorporate any action when user
 * click one of the options, it is necessary to control this state
 */
public class VerticalGMenu extends GMenu {
    private int colorOption = -1, prevColorOption = -1;

    public VerticalGMenu(PApplet manager, float x, float y) {
        super(manager, x, y);
        colorOption = -1;
        prevColorOption = -1;
    }

    public VerticalGMenu(PApplet manager, GContainer parent, float x, float y) {
        super(manager, parent, x, y);
        colorOption = -1;
        prevColorOption = -1;
    }
  
    @Override
    public void draw() {
        if (this.isVisible()) {
            super.manager().pushMatrix();
      
            boolean optSelected = false;
            float diffPosY = this.pos().y;
      
            PShape rect = manager().createShape(
                PConstants.RECT, this.pos().x, this.pos().y,
                this.dim().x, this.dim().y
            );
        
            rect.setFill(color());
            manager().shape(rect);
    
            for (int i = 0; i < this.components.size(); i++) {
                GOption option = (GOption) this.components.get(i);
                if (option.isVisible()) option.pos(this.pos().x + 10, diffPosY);
        
                String name = option.value();
                PVector pos = option.pos();
        
                option.setColor(255, 255, 255);
        
                // Check if current option has been selected
                if (!Globals.newDialog) {
                    float vy = pos.y * name.length();

                    if (manager().mouseX >= pos.x && manager().mouseX <= pos.x + manager().textWidth(name)) {
                        if (manager().mouseY >= pos.y && manager().mouseY <= vy) {
                            this.colorOption = i;
                            optSelected = true;
                            option.setColor(90, 155, 217);
              
                            /*if (prevColorOption != colorOption) {
                                if (this.transition != null) {
                                    //transition.amp(0.4);
                                    //transition.play();
                                }
                            }*/
                
                            prevColorOption = colorOption;
                        }
                    }
                }
        
                option.draw();
                diffPosY += 10 * name.length() + this.spaceValue;
            }
    
            // Any option has been selected by the mouse.
            // So we prevent the color selection by restarting
            // current option marked as hovered
            if (!optSelected) {
                this.prevColorOption = -1;
                this.colorOption = -1;
            }
    
            manager().popMatrix();
        }
    }
}