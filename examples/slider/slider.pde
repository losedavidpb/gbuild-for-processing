//
// Example of horizonal and vertical slider
//
// All Copyright Reserved (C)
//

import gbuild.slider.*;
import gbuild.*;

GSlider horizontalSlider, verticalSlider;

void setup() {
  size(800, 800);
  noSmooth();
  
  horizontalSlider = new HorizontalGSlider(this);
  horizontalSlider.pos(10, 30);
  horizontalSlider.setLimits(0, 100);
  horizontalSlider.dim(100, 50);

  verticalSlider = new VerticalGSlider(this);
  verticalSlider.pos(50, 100);
  verticalSlider.setLimits(0, 100);
  verticalSlider.dim(50, 100);
}

void draw() {
  background(0, 0, 0);
  horizontalSlider.draw();
  verticalSlider.draw();
}