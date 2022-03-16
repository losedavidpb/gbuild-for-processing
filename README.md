# gBuild - UI builder for Processing (v.2.0.0)
by David Parreño Barbuzano

## Index

* [Introduction](#introduction)
* [Requirements](#requirements)
* [Examples](#examples)
* [Instalation](#instalation)
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

Follow these instruction in order to prepare this library for Processing:

```
git clone https://github.com/losedavidpb/gbuild-for-processing
cd gbuild-for-processing

mkdir <<PROCESSING_PATH>>/libraries
mkdir <<PROCESSING_PATH>>/libraries/gBuild
mkdir <<PROCESSING_PATH>>/libraries/gBuild/library
cp library.properties <<PROCESSING_PATH>>/libraries/gBuild
cp bin/gBuild.jar <<PROCESSING_PATH>/libraries/gBuild/library

mkdir <<PROCESSING_PATH>>/libraries/gBuild/examples  # optional
cp -r examples/ <<PROCESSING_PATH>>/libraries/gBuild/examples # optional
```

## Documentation

To see more information about this library, download this repository and open the file
[index.html](https://github.com/losedavidpb/gbuild-for-processing/blob/main/javadoc) using
a web browser to see the Javadoc documentation.

Relative to versions and releases, you can view CHANGELOG.md file
[here](https://github.com/losedavidpb/gbuild-for-processing/blob/main/CHANGELOG.md).

## Troubleshooting and Contribution

If you're having trouble, or want to contribute,
please contact losedavidpb@gmail.com