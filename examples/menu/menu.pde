//
// Example of horizonal and vertical menu
//
// All Copyright Reserved (C)
//

import api.gbuild.component.menu.*;
import api.gbuild.component.button.*;

GMenu barmenu, verticalmenu;
float angle = 0;

void setup() {
  size(800, 800, P3D);
  noSmooth();
  
  barmenu = new HorizontalGMenu(this, 0, 0);
  verticalmenu = new VerticalGMenu(this, width - 100, 40);
  verticalmenu.setColor(0);
  verticalmenu.dim(100, height);
  barmenu.setColor(0);
  barmenu.dim(width, 50);
  
  for (int i = 0; i < 4; i++) {
    String name = "Option " + i;
    char keyValue = (char)i;
    GButtonOption option = new GButtonOption(this, name, keyValue);

    option.setTransparency(true);
    option.setHoverColor(90, 155, 217);
    option.setRawColor(255, 255, 255);
    option.setSize(20);
    barmenu.add(option);
  }

  for (int i = 0; i < 4; i++) {
    String name = "Option " + i;
    char keyValue = (char)i;
    GButtonOption option = new GButtonOption(this, name, keyValue);

    option.setTransparency(true);
    option.setHoverColor(90, 155, 217);
    option.setRawColor(255, 255, 255);
    option.setSize(20);
    verticalmenu.add(option);
  }
}

void draw() {
  background(random(0, 10), random(0, 10), random(0, 10));
  
  noStroke();
  barmenu.draw();
  verticalmenu.draw();
  
  stroke(255);
  fill(0);
  translate(width / 2, height / 2, -100);
  rotate(radians(angle+=0.5));
  sphere(100);
  
  if (angle > 360) angle = 0;
}