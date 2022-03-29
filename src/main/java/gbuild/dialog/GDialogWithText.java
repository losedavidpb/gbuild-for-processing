package gbuild.dialog;

import gbuild.GText;
import processing.core.PApplet;
import processing.core.PConstants;

/**
 * <p>
 * Dialog that contains a text as the content
 * </p>
 * 
 * <p>
 * This dialog includes at the bottom part of the component
 * a text passed by the developer. It is important to notice
 * that text will be added to the dialog splitting first when
 * a break line is founded.
 * </p>
 * 
 * @author David Parreño Barbuzano
 */
public class GDialogWithText extends GDialog {
    private int spaceValue = SPACE_GDIALOG_TEXT;
    
    /**
     * Create a new instance of a dialog with text
     * 
     * @param manager Processing manager
     * @see GDialog#GDialog(processing.core.PApplet) 
     */
    public GDialogWithText(PApplet manager) {
        super(manager);
    }
  
    /**
     * Set message to bottom area
     * 
     * <p>
     * Dimensions for dialog would be changed
     * considering the message as a priority
     * </p>
     * 
     * @param message message content
     */
    public void setMessage(String message) {
        int diffPosY = 20;
        super.bottom().clear();
        
        for (String line : message.split("\n")) {
            GText text = new GText(manager(), super.bottom());
            
            text.setText(line);
            text.pos(10, diffPosY);
            text.setColor(0, 0, 0);
            text.setSize(20);
            text.setMode(PConstants.SHAPE);
            super.bottom().add(text);
            diffPosY += spaceValue;
        }
    }
    
    /**
     * Return the space between each line of the text
     * 
     * @return space value
     */
    public int space() {
        return this.spaceValue;
    }
    
    /**
     * Set the space between each line of the text
     * 
     * @param spaceValue space value
     */
    public void setSpace(int spaceValue) {
        this.spaceValue = spaceValue;
    }
}