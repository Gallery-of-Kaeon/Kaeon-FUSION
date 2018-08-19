[Kaeon FUSION Home](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/README.md)

# Kaeon Origin

Kaeon Origin is a simple IDE for Kaeon FUSION Development.

## Installation

To begin, download Kaeon Origin:

[Kaeon Origin](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/raw/master/Kaeon%20FUSION/IDE/Application/Kaeon%20Origin.zip)

The download link will come as a zip file containing the Kaeon Origin IDE as well as various modules for Kaeon FUSION,
including the standard interface,
the stack interface,
the standard ONE+ directives,
the super mode directive,
and the ONE LISP syntax.

Each interface comes in a JAR (Java ARchive) file,
which must be placed in the same directory as Kaeon Origin in order for Kaeon Origin to use them.

Kaeon Origin runs on Java,
so be sure to have Java installed before you begin. You can install Java at:

[Java Download](https://www.java.com/en/download/)

## How to Use

You can open the IDE in GUI mode either by opening the "Kaeon Origin.jar" file in the file explorer or by running it from the command line with no arguments.

### GUI Mode

After opening Kaeon Origin in GUI mode, you will see open and save options at the top.

Beneath the two buttons is a blank text area which can be typed into.
This is where you will write your Kaeon FUSION code.

Beneath the text area is a "Run" button,
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

To open Kaeon Origin in GUI mode:

    java -jar "Kaeon Origin.jar"

To run a Kaeon FUSION script in the command line:

    java -jar "Kaeon Origin.jar" -r "My Kaeon FUSION.op"

To run a Kaeon FUSION script in the command line with arguments:

    java -jar "Kaeon Origin.jar" -r "My Kaeon FUSION.op" -a "Argument 1" "Argument 2" "etc"

## Source Code

The source code and references to the dependecies of Kaeon Origin can be found [here](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/tree/master/Kaeon%20FUSION/IDE/Source).