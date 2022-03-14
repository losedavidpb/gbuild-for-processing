import api.gbuild.component.*;

int numText = 50;
GContainer texts;

void setup() {
    size(800, 800);
    noStroke();

    texts = new GContainer(this, 0, 0, width, height);
    texts.setColor(0);
    
    for (int i = 0; i < numText; i++) {
        GText text = new GText(this, "Hello!", random(0, width), random(0, height));
        text.setColor(random(255), random(255), random(255));
        text.setSize((int)random(5, 40));
        text.setMode(MODEL);
        texts.add(text);
    }
}

void draw() {
    background(255);
    texts.draw();
    
    for (int i = 0; i < numText; i++) {
      GText text = (GText)texts.get(i);
      text.pos(text.pos().x + random(-10, 10));
      text.pos(null, text.pos().y + random(-10, 10));
      text.setSize((int)random(5, 40));
    }
}