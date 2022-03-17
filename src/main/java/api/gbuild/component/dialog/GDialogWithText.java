package api.gbuild.component.dialog;

import api.gbuild.Globals;
import api.gbuild.component.GText;
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
    private int spaceValue = Globals.SPACE_GDIALOG_TEXT;
    private boolean textModified;
    
    /**
     * Create a new instance of a dialog with text
     * 
     * @param manager Processing manager
     * @param title title dialog
     * @param message message dialog
     * @see GDialog#GDialog(processing.core.PApplet, java.lang.String) 
     */
    public GDialogWithText(PApplet manager, String title, String message) {
        super(manager, title);
        
        this.textModified = false;
        int diffPosY = 20;
        
        for (String line : message.split("\n")) {
            GText text = new GText(manager, super.bottom(), line, 10, diffPosY);
            text.setColor(0, 0, 0);
            text.setSize(20);
            text.setMode(PConstants.SHAPE);
            super.bottom().add(text);
            diffPosY += spaceValue;
        }
    }
    
    /**
     * Return an example of one of the lines of the text
     *
     * <p>
     * This method was designed in order to allow the
     * possibility to modify the text properties of
     * the dialog by just adjusting one of them.
     * </p>
     * 
     * <p>
     * The changes set at this text would be also be
     * applied for the rest of component when draw
     * function is called
     * </p>
     * 
     * @return instance of one of the texts
     */
    public GText customizeContent() {
        this.textModified = true;
        return bottom().numComponents() > 1? (GText)bottom().get(0) : null;
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
    
    @Override
    public void draw() {
        if (this.textModified) {
            this.textModified = false;
            
            if (this.bottom().numComponents() > 0) {
                GText parentText = (GText)this.bottom().get(0);
            
                for (int i = 1; i < this.bottom().numComponents(); i++) {
                    GText textValue = (GText)this.bottom().get(i);
                    textValue.setVisible(parentText.isVisible());
                    textValue.setAlign(parentText.align());
                    textValue.setMode(parentText.mode());
                    textValue.setSize(parentText.size());
                    
                    float[] c = textValue.color();
                    textValue.setColor(c[0], c[1], c[2]);
                }
            }
        }
        
        super.draw();
    }
}
