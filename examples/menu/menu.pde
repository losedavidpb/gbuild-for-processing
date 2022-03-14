import api.gbuild.component.menu.*;

GOption[] options;
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
  
  options = new GOption[]{
    new GOption(this, "Option 1", '1'),
    new GOption(this, "Option 2", '2'),
    new GOption(this, "Option 3", '3')
  };
  
  for (GOption option : options) {
    option.setColor(255, 255, 255);
    option.setAlign(LEFT);
    option.setMode(MODEL);
    option.setSize(20);
    barmenu.add(option);
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