[Home](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/tree/master/Kaeon%20FUSION/Documentation/README.md) /
[Back](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/tree/master/Kaeon%20FUSION/Documentation/4%20-%20The%20Web%20and%20Machine%20Interfaces/2%20-%20Machine/README.md) /
[Previous](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/tree/master/Kaeon%20FUSION/Documentation/4%20-%20The%20Web%20and%20Machine%20Interfaces/2%20-%20Machine/README.md)

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

## Global Variables

Any variables declared within a global command will be registered as global.

For example:

    Define: foo
    
    	Global { Meta: Integer, External }
    		x: Default
    		y: Default
    
    	return: Add: x, y

is analogous to the following C:

    extern int x;
    extern int y;

    void foo() {
    	return x + y;
    }

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
Pointer|*
Address|&
List: num_1, num_n|[num_1][num_n]

For example:

    a: "hello"

    Meta: Integer

    b: 0
    c: 5

    Meta: Double

    d: 1.5

    Meta: Integer, Pointer
    e: Default

    Meta: Integer, List: 1, 2, 3
    f: Default

is analogous to the following C:

    void* a = "hello";

    int b = 0;
    int c = 5;

    double d = 1.5;

    int* e;

    int f[1][2][3];

## Math and Logic Operations

Math and logic operations work the same way they do in Kaeon FUSION.

For example:

    Add: 1, 2
    Add: 1, Divide: 10, 2
    Greater: 2, 1

is analogous to the following C:

    (1 + 2);
    (1 + (10 / 2));
    (2 > 1);

## Addresses and Pointers

Addresses and pointers to variables may be obtained by using the address or pointer commands respectively.

For example:

    Meta: Integer
    x: 0
    
    Meta: Integer, Address
    y: Address: x
    
    Meta: Integer, Pointer
    z: Pointer: x

is analogous to the following C:

    int x = 0;
    int& y = (&(x));
    int* y = (*(x));

## Size

To retrieve the size of a variable or data type,
the size command may be used.

For example:

    Meta: Integer
    x: 0

    Size: Integer
    Size: x

is analogous to the following C:

    int x = 0;

    sizeof(int)
    sizeof(x)

## Casting

To cast a variable from one data type to another,
a Cast command following a Meta command may be used.

For example:

    Meta: Double
    x: 1.5

    Meta: Integer
    y: Meta { Integer } Cast: x

is analogous to the following C:

    double x = 1.5;
    int y = ((double) x);

## Lists

The list,
at,
and set commands work the same way they do in the Kaeon FUSION Standard Interface.

For example:

    List: 1, 2, 3
    At: foo, 1
    Set: foo, 1, 5

is analogous to the following C:

    {1, 2, 3};
    foo[(1) - 1];
    foo[(1) - 1] = 5;

## Flow Control

Flow control works the same way in the Script dialect as it does in Kaeon FUSION.

However, else commands will only work if the corresponding break command is the first child of its scope, and break and loop commands will only work inside of scope commands.

For example:

    Scope { Break: true }
    	# code
    
    Else:
    	# code
    
    Scope
    
    	# code
    
    	Loop: false
    	Break: true

is analogous to the following C:

    if(1) {
    	// code
    }
    
    else {
    	// code
    }
    
    do {

    	// code

    	if(0)
    		continue;
    
    	if(1)
    		break;
    } while(0);

## Functions

Each Kaeon FUSION Definition containing code written in the Native Code dialect corresponds to a C function.
A function can be typed by preceding the element containing the function with a Meta command.
The Return command can be used to terminate the function and return a value.
Parameters for functions can be specified by nesting a takes command within a Meta command.

If a function is defined in another definition,
it can be called using an element that contains its name.
Any children nested within such an element would be registered as arguments.

The arguments of a functions may be accessed as a list using the arguments command.

For example:

    Define: foo { Integer, Takes: Integer } foo
    	Return: Multiply: At { Arguments, 1 }, 2

    Define: Meta { Integer } main

    	x: foo: 5
    	
    	Return: 0

is analogous to the following C:

    int foo(int arg1) {
    	return arg1 * 2;
    }

    int main() {

    	x = foo(5);
    
    	return 0;
    }

## Structures

Structures may be defined by placing "Structure" in the meta command before a function definition.
Once defined, structures may be used as varaible types.

To return content from within a scope, the return command may be used.

For example:

    Define: Meta { Structure } foo
    
    	Meta: Integer

    	x: Default
    	y: Default
    	z: Default

    Define: Meta { Integer } main

    	Meta: foo
    	x: Default

    	Scope { In: x }
    		Return: z

    	Return: 0

is analogous to the following C:

    struct foo {
    	int x;
    	int y;
    	int z;
    }

    int main {
	
    	struct foo x;
    	x.z;

    	return 0;
    }

## Header Files

Header files can be included with the Use command.
Their contents may be accessed using an In command containing the element "Default" nested within a Scope command.

For example:

    Define: Meta { Integer } main

    	Use: stdio.h

    	Scope { In: Default }
    		printf: "Hello, world"

    	Return: 0

is analogous to the following C:

    #include "stdio.h"

    int main() {

    	printf("Hello, world");

    	return 0;
    }