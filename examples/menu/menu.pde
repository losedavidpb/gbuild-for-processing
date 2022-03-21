//
// Example of horizonal and vertical menu
//
// All Copyright Reserved (C)
//

import gbuild.menu.*;
import gbuild.button.*;
import gbuild.*;

GMenu barmenu, verticalmenu;
float angle = 0;

void setup() {
  size(800, 800, P3D);
  noSmooth();
  
  barmenu = new HorizontalGMenu(this);
  barmenu.prop("x", 0, "y", 0);
  barmenu.prop("color", new GColor(0, 0, 0));
  barmenu.prop("w", width, "h", 50);

  verticalmenu = new VerticalGMenu(this);
  verticalmenu.prop("x", width - 150, "y", 0, "color", new GColor(0, 0, 0));
  verticalmenu.prop("w", 150, "h", height);

  for (int i = 0; i < 4; i++) {
    String name = "Option " + i;
    char keyValue = (char)i;

    GButton option = new GButtonOption(this);
    option.prop("value", name, "keyValue", keyValue);
    option.prop("isTransparent", true, "size", 20);
    option.prop("rawColor", new GColor(255, 255, 255));
    option.prop("hoverColor", new GColor(90, 155, 217));
    barmenu.add(option);
  }

  for (int i = 0; i < 4; i++) {
    String name = "Option " + i;
    char keyValue = (char)i;

    GButton option = new GButtonOption(this);
    option.prop("value", name, "keyValue", keyValue);
    option.prop("isTransparent", true, "size", 20);
    option.prop("rawColor", new GColor(255, 255, 255));
    option.prop("hoverColor", new GColor(90, 155, 217));
    verticalmenu.add(option);
  }
}

void draw() {
  background(random(0, 10), random(0, 10), random(0, 10));
  
  noStroke();
  barmenu.draw();
  verticalmenu.draw();
  
  pushMatrix();
  stroke(255);
  fill(0);
  translate(width / 2, height / 2, -100);
  rotate(radians(angle+=0.5));
  sphere(100);
  popMatrix();
  
  if (angle > 360) angle = 0;
}