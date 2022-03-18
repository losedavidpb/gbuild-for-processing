//
// Example of a movable dialog with a text
//
// All Copyright Reserved (C)
//

import api.gbuild.component.*;
import api.gbuild.component.dialog.*;
import api.gbuild.*;

GDialog dialog;

void setup() {
  size(800, 800, P2D);
  
  dialog = new GDialogWithText(this);
  
  dialog.setProperty("title", "Title for Dialog");
  dialog.setProperty("message", "This is an example");
  dialog.setProperty("isVisible", true);
}

void draw() {
  background(0);
  dialog.draw();
}