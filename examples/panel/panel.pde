//
// Example of a panel with text component
//
// All Copyright Reserved (C)
//

import gbuild.*;

int numText = 50;
GPanel panel;

void setup() {
    size(800, 800);
    noStroke();

    panel = new GPanel(this);
    panel.prop("x", 0, "y", 0, "isTransparent", true);
    
    for (int i = 0; i < numText; i++) {
        GText text = new GText(this);
        text.prop("value", "Hello!", "x", random(0, width), "y", random(0, height));
        panel.add(text);
    }
}

void draw() {
    background(0);
    panel.draw();
    
    for (int i = 0; i < numText; i++) {
      GText text = (GText)panel.get(i);
      text.prop("x", (Float)text.prop("x") + random(-10, 10));
      text.prop("y", (Float)text.prop("y") + random(-10, 10));
      text.prop("size", (int)random(5, 40));
      text.prop("color", new GColor(random(0, 255), random(0, 255), random(0, 255)));
    }
}