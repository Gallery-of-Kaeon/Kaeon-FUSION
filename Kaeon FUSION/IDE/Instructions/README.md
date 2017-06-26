# Instructions for Using Kaeon Dev

Kaeon Dev is a simple IDE for Kaeon FUSION Development.

## Installation

To begin, download the "Kaeon Dev.jar" file from:

https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/tree/master/Kaeon%20FUSION/IDE

Be sure to have Java installed before you begin. You can install Java at:

https://www.java.com/en/download/

After installing Java and downloading the "Kaeon Dev.jar" file,
place the "Kaeon Dev.jar" file in the root directory of your project.

## Supported Interfaces

At present, Kaeon Dev supports the standard interface,
the web interface,
and the machine interface.

## How to Use

You can open the IDE in GUI mode either by opening the "Kaeon Dev.jar" file in the file explorer or by running it from the command line with no arguments.

The following commands work on both UNIX based systems and on Windows:

To open Kaeon Dev in GUI mode:

    java -jar "Kaeon Dev.jar"

To run a Kaeon FUSION script in the command line:

    java -jar "Kaeon Dev.jar" "My Kaeon FUSION.op"

To run a Kaeon FUSION script in the command line with arguments:

    java -jar "Kaeon Dev.jar" "My Kaeon FUSION.op" "Argument 1" "Argument 2" "etc"

### GUI Mode

After opening Kaeon Dev in GUI mode, you will see open and save options at the top.

Beneath the two buttons is a blank text area which can be typed into.
This is where you will write your Kaeon FUSION code.

Beneath the text area is a "Run" button and another blank text area which cannot be edited.
Clicking the "Run" option will execute the Kaeon FUSION script written into the upper text area and log the results to the lower text area.

For example, if you type:

    Use: Standard
    
    Log Line: "Hello, world!"

into the upper text area and then click "Run", then:

    Hello, world!

will be logged to the lower text area.