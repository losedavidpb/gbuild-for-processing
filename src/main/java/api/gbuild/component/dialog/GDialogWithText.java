package api.gbuild.component.dialog;

import api.gbuild.component.GText;
import processing.core.PApplet;

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
        
        int diffPosY = 20;
        
        for (String line : message.split("\n")) {
            GText text = new GText(manager, super.bottom(), line, 0, diffPosY);
            text.setColor(0, 0, 0);
            text.setSize(20);
            super.bottom().add(text);
            diffPosY += 10;
        }
    }
}
