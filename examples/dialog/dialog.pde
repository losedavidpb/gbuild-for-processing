import api.gbuild.component.dialog.*;

GDialog dialog;

void setup() {
  size(800, 800, P2D);
  
  dialog = new GDialog(this, "Title for Dialog", "This is an example");
  dialog.setVisible(true);
}

void draw() {
  background(0);
  dialog.move();
}