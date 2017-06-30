# Native Code Dialect

The Native Code Dialect is a dialect of ONE analogous to C.

## Literals

An element that has no children and whose content does does match any predifined alias or keyword will be interpreted as a literal.
Any literal that is not in the form of a number or a boolean will be automatically assumed to be a string literal.

The literals true and false correspond to 1 and 0 in C respectively.

For example:

    123
    abc
    true

is analogous to the following C:

    123;
    "abc"
    1;

## Variables

Variables can be declared the same way they are declared in Kaeon FUSION.
To create a variable without assigning it a value,
you nest within it an element with the content "Default".

For example:

    x: 0
    y: Default

is analogous to the following C:

    void* x = 0;
    void* y;

## Data Types

The Native Code dialect requires that data members be strongly typed.
The typing of elements is allowed through the use of the Meta command.
The children of a Meta command will specify the type of all subsequent items,
until another Meta command is used.
If a preceding Mta command is absent,
the Native Code dialect will assume the type is a void pointer.

The elements that can be used within a Meta command for strong typing in the Native Code dialect are as follows:

Native Code|C
---|---
Integer|int
Short|short
Long|long
Float|float
Double|double
Character|char
Static|static
Automatic|auto
External|extern
Volatile|volatile