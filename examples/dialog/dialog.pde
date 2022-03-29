//
// Example of a movable dialog with a text
//
// All Copyright Reserved (C)
//

import gbuild.*;
import gbuild.dialog.*;

GDialogWithText dialog;

void setup() {
  size(800, 800, P2D);
  
  dialog = new GDialogWithText(this);
  dialog.setTitle("Title for Dialog");
  dialog.setMessage("This is an example");
  dialog.setVisible(true);
}

void draw() {
  background(0);
  dialog.draw();
}