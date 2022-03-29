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

    panel = new GPanel(this);
    panel.pos(0, 0);
    panel.setOpaque(false);
    
    for (int i = 0; i < numText; i++) {
        GText text = new GText(this);
        text.pos(random(0, width), random(0, height));
        text.setText("Hello!");
        panel.add(text);
    }
}

void draw() {
    background(0);
    panel.draw();
    
    for (int i = 0; i < numText; i++) {
      GText text = (GText)panel.get(i);
      text.pos(text.pos().x + random(-10, 10));
      text.pos(null, text.pos().y + random(-10, 10));
      text.setSize((int)random(5, 40));
      text.setColor(new GColor(random(0, 255), random(0, 255), random(0, 255)));
    }
}