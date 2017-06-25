# Kaeon FUSION

Kaeon FUSION is a dialect of the FUSION system.
It provide a relativley minialistic set of commands to work with at start up,
but one of the commands: the use command,
allows more commands to be integrated at runtime.

Thus, it's funtionality can be expanded to account for any development task.

## Basics

### Literals

An element with no children that does not match the name of a command or variable will be interpreted as a string literal.
In addition, any element whose content is encased in double quotes will return its content as a string literal,
minus the double quotes.

For example:

    Hello world

and:

    "Hello world"

will both return the same literal: the string "Hello world".

### Variables

An element with one child that does not match the name of a command will assign the value returned by its child to a variable with the alias being the element's content.

For example:

    x: Hello

will assign the string "Hello" to the variable "x", and:

    x: Goodbye

will overwrite the value stored in "x" with the string "Goodbye".

### Function Definitions

A function definition begins with the "Define" command.
Nested with the "Define" command is a string containing the name of the function.
Nested underneath the name of the function is the content of the function.

For example:

    Define: foo
        
        Command 1: Command Argument 1
        Command 2: Command Argument 1, Command Argument 2

### Function Calls

A defined function can be called by placing its name inside an element and nesting any arguments to be passed to the function within the element.

For example:

    Define: foo
        
        Command 1: Command Argument 1
        Command 2: Command Argument 1, Command Argument 2
    
    foo: Function Argument

### Core Commands

#### The Use Command

The Use command allows the user to add new commands to Kaeon FUSION's functionality by specifying the name of an interface.

For example:

    Use: Standard

will allow access to all of the commands in the standard interface,
such as commands for arithmetic, logic, and file IO, and more.

#### The Catch Command

The catch command will allow the program to instantly recover from a thrown exception.
If at any point an exception is thrown,
all subsequent commands will fail to activate until a catch command is reached.
The children of a catch command will be activated if and only if it catches an exception.

For example:

    Faulty Command: Bad Argument
    Normal Command 1
    Catch: Recover Command 1
    Normal Command 2
    Catch: Recover Command 2

Let's say that "Faulty Command" throws an exception due to "Bad Argument".
"Normal Command 1" will then be passed over,
and "Catch" will activate.
As a result,
both "Recover Command 1" and "Normal Command 1" will run.
However, "Recover Command 2" will not run as the exception has already been caught.

#### The Arguments Command

It is possible to execute a Kaeon FUSION script or a Kaeon FUSION function with arguments.
The "Arguments" command returns a list containing the arguments that were passed to the script or function.

For example:

    Use: Standard
    
    Define: foo
        
        #[ The "Log Line" command comes from the standard interface,
           and prints the values returned by its children to the console. ]#

        Log Line: Arguments
    
    foo: 1, 2, 3

will print to the console:

    [1, 2, 3]

#### The Return Command

It is possible for a Kaeon FUSION script or a Kaeon FUSION function to be called externally.
The "Return" command will terminate the currently executing function or script and return the value returned by its first child to the caller.

For example:
    
    Define: foo
        Return: y
    
    x: foo

will assign the value "y" to the variable "x".