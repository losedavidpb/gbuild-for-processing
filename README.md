# gBuild - Graphical library builder for Processing (v.2.0.0 [unreleased])
by David Parreño Barbuzano

gBuild is a Processing library that provides useful tools to construct UI components
such as dialogs, menus, and so on. To do this, this library includes basic classes
on which you can customize and build nice graphical components.

## Requirements

- __Platform:__ Windows 10 or 11
- __Environment:__ preferable Processing 4, but it could be compatible for older versions

## Examples

You can find some examples of use of this library at the following links:

- For panels: https://github.com/losedavidpb/gbuild-for-processing/examples/blob/main/panel
- For dialogs: https://github.com/losedavidpb/gbuild-for-processing/blob/main/examples/dialog
- For menus: https://github.com/losedavidpb/gbuild-for-processing/blob/main/examples/menu

The following code is a simple example of gBuild:

```
import api.gbuild.component.*;

GPanel panel;

void setup() {
  size(800, 800, P2D);

  panel = new GPanel(this, width / 2, height / 2);

  for (int i = 0; i < 100; i++) {
    if (random(-10, 10) >= 0) {
      panel.add(new GText(this, panel, "Hello", random(0, width), random(0, height)));
    } else {
      panel.add(new GText(this, panel, "data/example.png", random(10, 50), random(10, 50)));
    }
  }
}

void draw() {
  background(0);
  panel.draw();
}
```

## Installation

### Troubleshooting and Contribution

If you're having trouble, or want to contribute to this project, please contact me
with my email losedavidpb@gmail.com.
