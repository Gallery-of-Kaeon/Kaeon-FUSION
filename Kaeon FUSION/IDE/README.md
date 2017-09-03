[Kaeon FUSION Home](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/README.md)

# Kaeon Dev

Kaeon Dev is a simple IDE for Kaeon FUSION Development.

## Installation

To begin, download Kaeon Dev:

[Kaeon Dev](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/raw/master/Kaeon%20FUSION/IDE/Application/Kaeon%20Dev.jar)

Be sure to have Java installed before you begin. You can install Java at:

[Java Download](https://www.java.com/en/download/)

After installing Java and downloading the "Kaeon Dev.jar" file,
place the "Kaeon Dev.jar" file in the root directory of your project.

## Interfaces

Kaeon Dev has the [standard](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/Kaeon%20FUSION/Documentation/3%20-%20Standard%20Interface/README.md),
[web](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/Kaeon%20FUSION/Documentation/4%20-%20The%20Web%20and%20Machine%20Interfaces/1%20-%20Web/README.md),
and [machine](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/Kaeon%20FUSION/Documentation/4%20-%20The%20Web%20and%20Machine%20Interfaces/2%20-%20Machine/README.md) interfaces built into it,
and will automatically include the standard interface when running Kaeon FUSION code.
Additional interface may be packaged as jars,
and made available to Kaeon Dev by placing the jar containing the interface next to the Kaeon Dev jar.

## How to Use

You can open the IDE in GUI mode either by opening the "Kaeon Dev.jar" file in the file explorer or by running it from the command line with no arguments.

### GUI Mode

After opening Kaeon Dev in GUI mode, you will see open and save options at the top.

Beneath the two buttons is a blank text area which can be typed into.
This is where you will write your Kaeon FUSION code.

Beneath the text area is a "Run" button,
a "Show ONE" button,
and another blank text area which cannot be edited.
Clicking the "Run" option will execute the Kaeon FUSION script written into the upper text area and log the results to the lower text area.

For example, if you type:

    Use: Standard
    
    Log Line: "Hello, world!"

into the upper text area and then click "Run", then:

    Hello, world!

will be logged to the lower text area.

Clicking the "Show ONE" button will translate any ONE+ written in the upper text area to ONE and show said ONE in the lower text area.

### Command Line Mode

The following commands work on both UNIX based systems and on Windows:

To open Kaeon Dev in GUI mode:

    java -jar "Kaeon Dev.jar"

To run a Kaeon FUSION script in the command line:

    java -jar "Kaeon Dev.jar" "My Kaeon FUSION.op"

To run a Kaeon FUSION script in the command line with arguments:

    java -jar "Kaeon Dev.jar" "My Kaeon FUSION.op" "Argument 1" "Argument 2" "etc"

## Source Code

The source code and references to the dependecies of Kaeon Dev can be found [here](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/tree/master/Kaeon%20FUSION/IDE/Source).