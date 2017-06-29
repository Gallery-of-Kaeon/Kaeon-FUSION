# Script Dialect

The Script dialect is a dialect of ONE ananlogous to JavaScript.

## Literals

An element that has no children and whose content does does match any predifined alias or keyword will be interpreted as a literal.
Any literal that is not in the form of a number or a boolean will be automatically assumed to be a string literal.

For example:

    123
    abc
    true

is analogous to the following JavaScript:

    123;
    "abc";
    true;

## Variables

Variables can be declared the same way they are declared in Kaeon FUSION.

For example:

    x: 0
    Global: y: abc

is analogous to the following JavaScript:

    var x = 0;
    y = "abc";

## Math and Logic Operations

Math and logic operations work the same way they do in Kaeon FUSION.

For example:

    Add: 1, 2
    Add: 1, Divide: 10, 2
    Greater: 2, 1
			
is analogous to the following JavaScript:
				
    (1 + 2);
    (1 + (10 / 2));
    (2 > 1);