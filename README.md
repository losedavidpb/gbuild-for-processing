# gBuild - UI builder for Processing
by David Parreño Barbuzano

## Index

* [Introduction](#introduction)
* [Requirements](#requirements)
* [Examples](#examples)
* [Instalation](#instalation)
* [Sketchbook path](#sketchbook-path)
* [Documentation](#documentation)
* [Troubleshooting and Contribution](#troubleshooting-and-contribution)

## Introduction

gBuild is a Processing library that provides useful tools to construct UI components
such as dialogs, menus, and so on. To do this, this library includes basic classes
on which you can customize and build nice graphical components.

## Requirements

- __Platform:__ Windows 10 or 11
- __Environment:__ preferable Processing 4, but it could be compatible version 3

## Examples

You can find some examples of use of this library at the following links:

- For panels: https://github.com/losedavidpb/gbuild-for-processing/blob/main/examples/panel
- For dialogs: https://github.com/losedavidpb/gbuild-for-processing/blob/main/examples/dialog
- For menus: https://github.com/losedavidpb/gbuild-for-processing/blob/main/examples/menu

The following code is a simple example of gBuild:

```
import gbuild.*;

GPanel panel;

void setup() {
  size(800, 800, P2D);

  panel = new GPanel(this);
  panel.pos(width / 2, height / 2);

  for (int i = 0; i < 100; i++) {
    if (random(-10, 10) >= 0) {
      GText text = new GText(this, panel);
      text.setText("value");
      text.pos(random(0, width), random(0, height));
      panel.add(text);
    } else {
      GImage image = new GImage(this, panel);
      image.setImage("data/example.png");
      image.pos(ramdom(10, 50), random(10, 50));
      panel.add(image);
    }
  }
}

void draw() {
  background(0);
  panel.draw();
}
```

## Installation

1. Download one of the releases of this library
2. Unzip the file that you have download
3. Move the folder to libraries folder which is at your sketchbook path
3. Restart Processing language environment
4. Enjoy!

## Sketchbook path

To know what is your Processing sketchbook location, open the Preferences window
from the Processing application (PDE) and look for the "Sketchbook location" item at the top.

By default the following locations are used for your sketchbook folder:

- For Mac users, the sketchbook folder is located inside ~/Documents/Processing
- For Windows users, the sketchbook folder is located inside My Documents/Processing

## Documentation

To see more information about this library, download this repository and open the file
[index.html](https://github.com/losedavidpb/gbuild-for-processing/blob/main/javadoc) using
a web browser to see the Javadoc documentation.

Relative to versions and releases, you can view CHANGELOG.md file
[here](https://github.com/losedavidpb/gbuild-for-processing/blob/main/CHANGELOG.md).

## Troubleshooting and Contribution

If you're having trouble, or want to contribute,
please contact losedavidpb@gmail.com