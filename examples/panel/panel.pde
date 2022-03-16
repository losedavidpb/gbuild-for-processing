//
// Example of a panel with text component
//
// All Copyright Reserved (C)
//

import api.gbuild.component.*;

int numText = 50;
GPanel panel;

void setup() {
    size(800, 800);
    noStroke();

    panel = new GPanel(this, 0, 0);
    panel.setColor(0);
    
    for (int i = 0; i < numText; i++) {
        GText text = new GText(this, "Hello!", random(0, width), random(0, height));
        text.setColor(random(255), random(255), random(255));
        text.setSize((int)random(5, 40));
        panel.add(text);
    }
}

void draw() {
    background(255);
    panel.draw();
    
    for (int i = 0; i < numText; i++) {
      GText text = (GText)panel.get(i);
      text.pos(text.pos().x + random(-10, 10));
      text.pos(null, text.pos().y + random(-10, 10));
      text.setSize((int)random(5, 40));
    }
}