# Interfaces and the Use Command

Kaeon FUSION only has one command at runtime:
the use command.
The sole purpose of this command is to allow additional functionality to be added to the language at runtime through interfaces.

## Using the Use Command

If an interface is availiable within the environment Kaeon FUSION is running in,
it can be incorporated with an element whose content matches the name of the interface nested within an element containing the content "Use".

For example:

    Use: Standard

will incorporate the standard interface if it is available.