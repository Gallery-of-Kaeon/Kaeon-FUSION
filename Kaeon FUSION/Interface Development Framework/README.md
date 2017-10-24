[Kaeon FUSION Home](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/README.md)

# Kaeon FUSION Interface Development Framework

The Kaeon FUSION interface development framework allows third parties to develop interfaces for Kaeon FUSION.

## Before you begin

The Kaeon FUSION interface development framework is based in Java,
so be sure that you have Java installed and that you're comfortable writing Java code.
It is also important that you are familiar with the use of the Philosopher's Stone data structure,
which you can read about [here](https://github.com/Gallery-of-Kaeon/Philosophers-Stone/blob/master/README.md).

## Framework Structure

The framework comes in the form of an Eclipse project,
although you're free to use any development environment that supports Java.

The project itself is a fully functioning interface,
albeit one that only provides a single command: "Test",
which returns the string "Test Successful".
For example,
If you were to export the project as "Test.jar",
place the jar file next to [Kaeon Origin](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/Kaeon%20FUSION/IDE/README.md),
and run the following Kaeon FUSION code:

    Use: Standard, Test
    Log Line: Test

The program's console would display:

    Test Successful

## How to use

First, download the Kaeon FUSION interface development framework [here](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/Kaeon%20FUSION/Interface%20Development%20Framework/Test%20Interface.zip?raw=true).

The first thing to do is to name your interface.
For the sake of this example,
we'll name it "My Interface".

Rename the package aether_test such that the name of the package is "aether_" followed by the name of your interface,
all lower case,
with spaces replaced with underscores.
So,
for our example,
the package will be renamed to "aether_my_interface".

Proceed to implement any commands provided by your interface as extensions of the FUSIONUnit class.

Then,
in the FUSIONInterface class in the renamed Aether package,
override the addInterface function.
The FUSION object passed in is a Philosopher's Stone.
Attach any command you've implemented to the FUSION object in a public mutual connection,
and connect the FUSION object to any other Philosopher's Stone your interface works with using a one-way public connection.
Feel free to remove the test command.

Finally,
export your interface to a jar file that shares the name of your interface.
The jar file for this example would be named "My Interface.jar".

Place the jar file next to the Kaeon Origin.jar file and Kaeon Origin will automatically be able to access it.

## The FUSIONUnit Class Functions

### public boolean deny(Element element)

If this function returns true,
the command will be bypassed entirely by all FUSION units.

### public boolean verify(Element element)

If this command returns true,
this fusion will act on the element passed in.

### public int getPriority(Element element)

The position of the element passed in may change based on the integer returned by this function.
The lower the number, the higher the priority.
It returns zero by default.

### public boolean trickleDown(Element element)

If this function returns false,
the children of the passed in Element will be bypassed.

### public Object process(Element element, ArrayList<Object> processed)

Whatever this function returns will be used as an argument when processing the parent of the given element.

### public Element jump(Element element, ArrayList<Object> processed)

If this function does not return null,
the FUSION interpreter will jump to the returned element.

### public int changeDepth(Element element, ArrayList<Object> processed, int currentDepth)

This function may modify the depth value used by the FUSION interpreter.
It will return the currentDepth value by default.

### public void handleError(Element element)

This function is called whenever any FUSION unit throws an exception.