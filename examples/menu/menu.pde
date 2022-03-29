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
  barmenu.pos(0, 0);
  barmenu.dim(width, 50);
  barmenu.setColor(new GColor(0, 0, 0));
  barmenu.setOpaque(true);
  barmenu.setStrokeOpaque(false);
  barmenu.setSpace(100);
  
  verticalmenu = new VerticalGMenu(this);
  verticalmenu.pos(width - 120, 0);
  verticalmenu.dim(120, height);
  verticalmenu.setColor(new GColor(0, 0, 0));
  verticalmenu.setOpaque(true);
  verticalmenu.setStrokeOpaque(false);
  verticalmenu.setSpace(70);
  
  for (int i = 0; i < 4; i++) {
    String name = "Option " + i;
    char keyValue = (char)i;

    GButtonOption option = new GButtonOption(this, barmenu);
    option.setTextValue(name);
    option.setKeyValue(keyValue);
    option.setOpaque(false);
    option.setSize(20);
    option.setRawColor(new GColor(255, 255, 255));
    option.setHoverColor(90, 155, 217);
    option.setStrokeOpaque(false);
    barmenu.add(option);
  }

  for (int i = 0; i < 4; i++) {
    String name = "Option " + i;
    char keyValue = (char)i;

    GButtonOption option = new GButtonOption(this, verticalmenu);
    option.setTextValue(name);
    option.setKeyValue(keyValue);
    option.setOpaque(false);
    option.setSize(20);
    option.setRawColor(new GColor(255, 255, 255));
    option.setHoverColor(90, 155, 217);
    option.setStrokeOpaque(false);
    verticalmenu.add(option);
  }
}

void draw() {
  background(0);
  
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