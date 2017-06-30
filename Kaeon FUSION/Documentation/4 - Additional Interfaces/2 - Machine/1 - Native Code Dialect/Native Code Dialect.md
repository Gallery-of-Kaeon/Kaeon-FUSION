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