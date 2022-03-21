# CHANGELOG for gBuild

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [4.0.0] - 2022-03-19

### Added

- Add new property for components to get location without the parent
- Include message error when a property was not found
- Include tint color option for images
- Include access  to all image properties
- Font property for text components

### Changed

- Rename setProperty method to setProp
- Rename getProperty method to getProp
- Method setProperty would return a boolean to check if property was changed
- Move all menus implementations to gbuild.menu package
- Move all dialogs implementations to gbuild.dialog package
- Move all buttons implementations to gbuild.button package
- Move the rest of component to gbuild package
- Text mode and alignment would return a boolean to check if property was not changed
- Mode for buttons would return a boolean to check if property was not changed
- Space for menus would return a boolean to check if property was not changed

## [3.2.0] - 2022-03-18

### Added

- Include getProperty and setProperty to manage properties
- Constants for text properties (color, mode, aligment)
- Constants for panel properties (color, stroke, location)
- Constants for button properties (background, stroke, and color effects)
- Transparency mode for RGB color implementation
- Internal Utilities to apply fill or stroke color
- RGB colors would always return a copy
- Each component would be created with just PApplet and an optional parent
- Utility to adjust space between each option of a menu
- Stroke color for buttons

### Changed

- Dialog abstraction now allows to define it's title
- Dialogs with text as its main content allow to define the message
- Update examples of use to define components with new API

### Removed

- Delete utility to customize a text from a dialog

### Fixed

- Fix location for each option of a menu based on visible state
- Component would always clone a global color to avoid errors for reference
- Fix adjustment for location and dimension for panels
- Colors for components would consider transparency property
- Fix location calculation when component is a panel or dialog
- Fix location and dimension modification to avoid null values


## [3.1.0] - 2022-03-17

### Added

- Stroke color property for panels
- Transparency for stroke color property for panels
- Utility to customize text properties at buttons
- Facility to declare vertical space for text at a dialog
- Method to return the top of a dialog
- Utility to know if close button is been hovering
- Method to customize text mode property for buttons with text

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

## [3.0.1] - 2022-03-16

### Added

- Include package documentation
- Utility to get the absolute/relative location of a component
- Define text dimension based on its size and ascent/descent value
- Access to the properties for each component

### Changed

- Update CHANGELOG whith all the versions until this version
- Modify Maven and Processing library configuration
- Update README with information about gBuild
- Update image setter to return if it was loaded
- Better appearance for close buttons
- Documentation more complete for each source file
- Update constructors for components to be compact
- Move component abstraction to api/gbuild/component

### Removed

- Remove clone implementation for each class

### Fixed

- Adjust examples of use based on this version
- Documentation for location and dimension
- Fix drawing panels to consider transparency
- Fix location and dimension methods for panels
- Fix method to add an option at menus to consider the parent
- Fix dimension and location adjustment for dialogs

## [3.0.0] - 2022-03-16

### Added

- Constant that specifies space between options of menus
- Utilities to define transparency, and color efeccts for buttons
- Method to manage if button was been hovering by the user
- Button with no content that has color effects
- Button for a close button that has color effects
- Button which stored a text component and has color effects
- Include clone implementation for components

### Changed

- Improve documentation at each gBuild component
- Rename containers to be declared as a panel component
- Panels will update it's location and dimension based on its contents
- Button component is now an abstraction of a button
- Options for menus are now an extension of a button

### Removed

- Delete all files located at data section
- Remove constants associated to data section
- Delete utilities for text properties at buttons
- Remove dimension property for containers/panels

## [2.0.0] - 2022-03-15

### Added

- Button components which color effect when mouse is hovering
- Class for RGB colors in order to manage colors easily
- CHANGELOG file with initial template
- Constant that contains default size for texts
- Transparency option for containers
- Access to text properties
- Dialog specification which can be moved by the user

### Changed

- Dialog class is now an abstraction of a dialog component
- Option at menus are located depending on a separator
- Colors can be defined at components by a variable array
- Components with color properties use RGB color implementation

### Removed

- Dialog abstraction does not allow to define the content for bottom

### Fixed

- Fix documentation for location and dimension utilities
- Javadoc format at globals section

## [1.0.2] - 2022-03-14

### Added

- Dialog that can store a text and an optional image
- Example of use that test dialog gBuild components
- Utility for containers to know how many components are stored

### Removed

- Delete sound effects at menus when user is hovering an option
- Remove Javadoc and web sections

### Fixed

- Avoid null exceptions for images gBuild components

## [1.0.1] - 2022-03-14

### Added

- Constants to manage gBuild dialogs
- Implementation for horizontal and vertical menus
- Include a new example of use that tests menu components

### Changed

- Improve documentation for some classes

## [1.0.0] - 2022-03-14

### Added

- Declaration of a gBuild component
- Support for text and image gBuild components
- Container component to support UI panels
- Menu abstraction and declaration of an option
- Public Constants at a class to customize data path
- Initial path to store images and sounds of gBuild
- One example of use that tests text and container components
- Uncompleted documentation for each new class

### Changed

- Update Javadoc section

## [0.0.4] - 2022-03-14

### Added

- Include Javadoc template at web section
- Include initial source path for gBuild API

### Changed

- Improve web section customizing default values

## [0.0.3] - 2022-03-14

### Added

- Configuration to execute Java project with Maven

### Changed

- Ignore Processing and Maven files at .gitignore

### Removed

- Remove [Sound](https://processing.org/reference/libraries/sound/) dependency
- Remove sounds library at the folder lib

## [0.0.2] - 2022-03-14

### Added

- Include [Sound library](https://processing.org/reference/libraries/sound/) as a dependency

### Changed

- Set this project with MIT license
- Update README to only have how to install the library
- Customize web template to have information about gBuild

### Removed

- Remove files related to Eclipse (.classpath, .project)
- Delete unused folders (.github, resources, data, and src)

## [0.0.1] - 2022-03-14

### Added

- Fork the repository [Processing library template](https://github.com/processing/processing-library-template)


[0.0.1]: https://github.com/losedavidpb/gbuild-for-processing/commit/9544858aa9559ef5e9d11df9e662e0445da98f99
[0.0.2]: https://github.com/losedavidpb/gbuild-for-processing/commit/a1cd7b6e637732b63c94898a899dc50dc1d70b03
[0.0.3]: https://github.com/losedavidpb/gbuild-for-processing/commit/fd1304baeed41da22849f6971390a821758f453d
[0.0.4]: https://github.com/losedavidpb/gbuild-for-processing/commit/47f9f10e6b5a0b05957df5b5cdadf9d7aef8c1b2
[1.0.0]: https://github.com/losedavidpb/gbuild-for-processing/commit/a6860b36744611c2df48b609b464cc1c92f4588b
[1.0.1]: https://github.com/losedavidpb/gbuild-for-processing/commit/56d81b03c2721d8bb6682b635ec8735c0f187bc0
[1.0.2]: https://github.com/losedavidpb/gbuild-for-processing/commit/7b95cd05a9ff7298791f837822fc26d20b59417a
[2.0.0]: https://github.com/losedavidpb/gbuild-for-processing/commit/703032c8c13e9e45122385f6045d6c003cf7b677
[3.0.0]: https://github.com/losedavidpb/gbuild-for-processing/commit/8719e2ce8172c01f53b5ddcebeca77b1944344f6
[3.0.1]: https://github.com/losedavidpb/gbuild-for-processing/commit/03583f82e635134e4b290f7eae7f7fb7e8bec0e6
[3.1.0]: https://github.com/losedavidpb/gbuild-for-processing/commit/45585ab656d7005e225154598a1b817f74562dab
[3.2.0]: https://github.com/losedavidpb/gbuild-for-processing/commit/f8c7ea3a6d202273f7de00e3ee5c7d4950e4b295
[4.0.0]: 
