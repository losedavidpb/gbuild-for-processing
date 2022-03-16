//
// Example of a movable dialog with a text
//
// All Copyright Reserved (C)
//

import api.gbuild.component.*;
import api.gbuild.component.dialog.*;

GDialog dialog;

void setup() {
  size(800, 800, P2D);
  
  dialog = new GDialogWithText(this, "Title for Dialog", "This is an example");
  ((GText)dialog.bottom().get(0)).setColor(0, 0, 0);
  dialog.bottom().setColor(255, 255, 255);
  dialog.setVisible(true);
  dialog.setMovable(true);
}

void draw() {
  background(0);
  dialog.draw();
}