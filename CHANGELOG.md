# CHANGELOG for gBuild

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

## [2.0.1] - 17/03/2022

### Added

- Include a stroke color option for panels
- Provide a method that allow disable stroke color option
- Provide a method that allow developers to customize text properties
- Include a property to define the vertical space between each text of a dialog with text
- Include a method to access to top component of a dialog
- Provide a method to know if close button is been hovering
- Include a tool to define text mode for buttons with text

### Changed

- Update position for panels to be relative to its position
- Set text for dialogs to have the text mode SHAPE

### Fixed

- Fix menu's drawing mode when transparency is enable
- Fix buttom's drawing mode to update selected option
- Improve calculation of relative position for components
- Set dimensions for dialog to be bottom's dimension the priority for width 
- Fix text position for buttons to be at the middle
- Fix calculation of positions for each option of menus
- Fix position calculation for dialogs

## [Released]

## [2.0.0] - 16/03/2022

### Added

- Include at each package a package-info file
- Include a new method that return the absolute/relative location based on an option
- Include an override for the method dim at GText to consider textWidth, textAscent, and textDescent
- Include more getters and setters for the great majority of values for each component
- Include update javadoc at the the folder javadoc

### Changed

- Update CHANGELOG.md to include all the modifications until this version
- Modify pom.xml and library.properties to specify the new version
- Update README with customized information about gBuild
- Update setImage at PImage to return if the new image was loaded
- Update constructor for component to be more compact
- Improve close button to have a better appearance
- Update javadoc documentation to be more complete
- Move GComponent to api/gbuild/component

### Removed

- Remove all clone and copy implementation for each class

### Fixed

- Fix previous examples based on changes for new version
- Fix documentation at GComponent for pos and dim methods
- Fix draw method at GPanel to consider transparency
- Fix the method that updates the location and dimension of a panel to work correctly
- Fix add method for menus to consider if parent is defined
- Fix definition of a dialog for abstraction and GDialogWithText to set dimensions correctly

## [1.0.4] - 16/03/2022

### Added

- Include a new constant that indicates the space between each option
- Include selected, transparent, raw color, and hover color for buttons
- Add a button with no content that changes its background if user is selecting it
- Add a button to draw a close button based on the button implementation with no content
- Include a button that has inside of it text defined with GText

### Changed

- Update the documentation for all classes in order to be complete
- Rename GContainer to GPanel
- Position and dimension would change based on the contents of a panel
- Set GButton as an abstraction of a button
- Update option implement for menus to be as a button

### Removed

- Delete the folder data and its contents
- Remove constants at Globals related to data folder
- Remove dimensions at GPanel (GContainer) 
- Remove setters related to GText at GButton

## [1.0.3] - 15/03/2022

### Added

- Include a button implementation
- Add the class GColor to manage RGB colors more easily
- Include this file with a simple format
- Include a new constant that defines default text size
- Add a new option on which developers can specify if container's background exists
- Add more getters for GText properties
- Include the implementation of a movable dialog

### Changed

- Update all classes that defines colors to have a new attribute of type GColor
- Improve setters for classes that has a color in order to define it with variable array
- Set a GDialog as an abstraction that does not customize bottom area
- Modify the add method for menus to set the position of an option based on an offset

### Removed

- Delete the methods of a dialog that allow to set a content at bottom area

### Fixed

- Fix some documentation for pos and methods
- Fix comments at Globals to have javadoc format

## [1.0.2] - 14/03/2022

### Added

- Add a panel that can be moved and can have an image and a text or just text
- Include an example to test dialog implementation
- Include a new method to get number of components at GContainer

### Changed

- Remove sound implementation at horizontal and vertical menus

### Removed

- Delete all the javadoc files and web site view

### Fixed

- GImage check if path is null to avoid null exceptions

## [1.0.1] - 14/03/2022

### Added

- Include variables to manage dialogs at Globals
- Include horizontal and vertical menu
- Add an example to test menu implementation

### Changed

- Update classes with more documentation but still not complete

## [1.0.0] - 14/03/2022

### Added

- Folder with images and sounds used at gBuild
- Include constants at Globals for library images
- Include a class which is the abstraction of a gBuild component
- Add some basic components for text and image
- Include the implementation of a container of components
- Include a menu abstraction component and an option as its basic unit
- Include one example at examples folder to test GText and GContainer
- Include uncompleted documentation at some classes

### Changed

- Update the javadoc based on new classes

## [0.0.4] - 14/03/2022

### Added

- Template for Javadoc located at web/apidoc
- Source template with root path src/main/java/api/gbuild
- Declaration of the class Globals

### Changed

- Keep updating the web folder modifying the template

## [0.0.3] - 14/03/2022

### Added

- Maven configuration at pom.xml

### Changed

- Update .gitignore to avoid Processing, and Maven files

### Removed

- Remove sounds library at the folder lib

## [0.0.2] - 14/03/2022

### Added

- Include the file "LICENSE" with MIT license

### Changed

- Include sounds library at lib folder
- Customize the content of the template for web
- Update README.md to only have how to install the library

### Removed

- Remove unused folders such as .github, resources, data, and src
- Remove unused files such as .classpath, .project, license.txt

## [0.0.1] - 14/03/2022

### Added

- Initial release with Eclipse template