# Cargo Management System

## Overview
The Cargo Management System is a Java-based application designed for managing cargo specifically for Boeing 737 and 767 aircraft. It facilitates the processing of cargo data, including loading, weight checking, and outputting cargo details.

## Modules

### `main.java.models.Cargo` (Abstract Class)
#### Purpose
Serves as the base class for different types of cargo, defining common properties and methods.
#### Key Features
- Abstract method `checkMaxWeight` for implementation in derived classes.
- Method `output` for printing cargo details.

### `main.java.models.Boeing737`
#### Purpose
Represents cargo specific to Boeing 737 aircraft, extending the `Cargo` class.
#### Key Features
- Implements `checkMaxWeight` to comply with Boeing 737's weight limits.
- Maintains total weight of Boeing 737 cargo.

### `main.java.models.Boeing767`
#### Purpose
Similar to `Boeing737`, but tailored for Boeing 767 aircraft.
#### Key Features
- Specific `checkMaxWeight` method for Boeing 767's weight limitations.
- Tracks total weight of Boeing 767 cargo.

### `main.java.utils.CargoUtility`
#### Purpose
Provides utility methods for the cargo management system.
#### Key Features
- `readInput` for reading and processing cargo data from user input.
- Validation methods like `checkPlaneType`, `checkUldType`, and `checkAbbreviation`.
- `load737` and `load767` for adding cargo to respective lists.
- `printCargo` to display cargo details.

### `main.java.main.CargoManagement`
#### Purpose
Main class and entry point of the application.
#### Functionality
- Initiates cargo processing by invoking `CargoUtility.readInput`.

## Usage
Run the `main` method in `main.java.main.CargoManagement` and follow prompts to input the data file name.

## Contributing
Contributions to the Cargo Management System are welcome. Adhere to coding standards and submit pull requests for changes.

## Author
Kevin Rutledge

## Last Edit Date
December 24, 2023
