[Home](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/tree/master/Kaeon%20FUSION/Documentation/README.md) /
[Back](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/tree/master/Kaeon%20FUSION/Documentation/3%20-%20Standard%20Interface/README.md) /
[Previous](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/tree/master/Kaeon%20FUSION/Documentation/3%20-%20Standard%20Interface/README.md) /
[Next](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/tree/master/Kaeon%20FUSION/Documentation/4%20-%20Stack%20Interface/README.md)

# Super Mode

Super Mode is a ONE+ directive that allows the syntax code written using the Kaeon FUSION Standard Interface to resemble the syntax of a more C-like language like Python.

Super Mode can be activated by placing the following line anywhere in the document:

    [SUPER]

Activating Super Mode also automatically activates the standard interface.

## Variable Assignment

Super Mode allows the equals character to act as an infix operator for variable assignment.

For example:

    [SUPER]

    x = 0

is analogous to:

    Use: Standard

    x: 0

## Infix Notation

The Super Mode directive will allow for the use of infix notation for arithmetic and logic operations

For example:

    [SUPER]

    1 + 2
    1 - 2
    1 * 2
    1 / 2
    1 % 2
    1 > 2
    1 < 2
    1 >= 2
    1 <= 2
    1 == 2
    1 != 2
    True and False
    True or False
    True xor False

is analogous to:

    Add: 1, 2
    Subtract: 1, 2
    Multiply: 1, 2
    Divide: 1, 2
    Modulus: 1, 2
    Greater: 1, 2
    Less: 1, 2
    Greater or Equal: 1, 2
    Less or Equal: 1, 2
    Equal: 1, 2
    Not: Equal: 1, 2
    And: True, False
    Or: True, False
    Exclusive Or: True, False

## Not and Print

Super Mode allows for the use of the not command without a colon,
and allows the use of the keyword "print" to be used without a colon in substitution of the log line command.

For example:
					
    [SUPER]
			
    not True
				
is analogous to:
					
    Not: True

and:
					
    [SUPER]
			
    print hello
				
is analogous to:
					
    Log Line: hello

## Array Indexing

Super Mode allows for the use of the at character as an infix operator for list indexing.

For example:

    [SUPER]

    arguments @ 1

is analogous to:

    At: Arguments, 1

## If

Super Mode allows for the use of "If" to substitute for a scope command with a break command.

For example:

    [SUPER]

    if { my boolean }
    	# Operation

is analogous to:

    Scope { Break: Not: my boolean }
    	# Operations

## While

Super Mode allows for the use of "While" to substitute for a scope command with a break command.

For example:

    [SUPER]

    while { my boolean }
    	# Operation

is analogous to:

    Scope

    	# Operations

    	 Loop: my boolean

## For

Super Mode allows for the use of for loops in three forms:
standard for loops,
for range loops,
and for each loops.

For example:

    [SUPER]

    # Performs the operation n times
    for { n }
    	# Operation

    # prints i from x to y, inclusive
    for range { i, x, y }
    	print i

    # prints every item in my list
    for each { i, my list }
    	print i

<div align="right"><p>

<a href="https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/tree/master/Kaeon%20FUSION/Documentation/README.md">Home</a> / 
<a href="https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/tree/master/Kaeon%20FUSION/Documentation/3%20-%20Standard%20Interface/README.md">Back</a> / 
<a href="https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/tree/master/Kaeon%20FUSION/Documentation/3%20-%20Standard%20Interface/README.md">Previous</a> / 
<a href="https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/tree/master/Kaeon%20FUSION/Documentation/4%20-%20Stack%20Interface/README.md">Next</a>

</p></div>