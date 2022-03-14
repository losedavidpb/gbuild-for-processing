import api.gbuild.*;

int numText = 50;
GText[] texts;

void setup() {
    size(800, 800);
    noStroke();

    texts = new GText[numText];

    for (int i = 0; i < numText; i++) {
        texts[i] = new GText(this, "Hello!", random(0, width), random(0, height));
        texts[i].setColor(random(255), random(255), random(255));
        texts[i].setSize(random(5, 20));
    }
}

void draw() {
    background(0);

    for (int i = 0; i < numText; i++) {
        texts[i].draw();
    }
}