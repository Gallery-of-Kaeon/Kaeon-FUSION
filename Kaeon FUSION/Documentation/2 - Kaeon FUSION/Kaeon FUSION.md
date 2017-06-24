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