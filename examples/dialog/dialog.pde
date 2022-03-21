//
// Example of a movable dialog with a text
//
// All Copyright Reserved (C)
//

import gbuild.*;
import gbuild.dialog.*;

GDialog dialog;

void setup() {
  size(800, 800, P2D);
  
  dialog = new GDialogWithText(this);
  
  dialog.prop("title", "Title for Dialog");
  dialog.prop("message", "This is an example");
  dialog.prop("isVisible", true);
}

void draw() {
  background(0);
  dialog.draw();
}