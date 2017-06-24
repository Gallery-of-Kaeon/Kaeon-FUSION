# ONE+

While ONE is versatile and serves as a solid foundation for the FUSION system,
it is quite cumbersome to write it by hand.
ONE+ was created to compensate for this.

ONE+ provides users with more options for encoding and nesting elements.
While there is only one way to express any given document in ONE,
a single ONE document can be expressed in many different ways in ONE+.
No matter how a ONE+ document is written,
it will convert to ONE before being processed.

Because ONE+ is not only an abstraction of ONE but also a superset,
any text that is valid in ONE is valid in ONE+.

However, unlike ONE,
ONE+ does make use of token characters and thus requires escape sequences in certain situations.

## Indentation

While ONE only allows the use of tabs for indentation,
ONE+ allows either tabs or an arbitrary number of spaces.
However, the use of indentation must be consistent throughout the file.

## Element Definitions

ONE+ allows elements to be defined outside of element blocks.
A non-blank line contianing no token characters written will be converted into an element block.
Any leading or trailing whitespace will be removed.

For example:

    abc
    	xyz

    123

will be converted to:

    -
    	abc
    -
    	-
    		xyz
    	-
    -
    	123
    -

## Multiple Element Definitons

It is also possible to encode multiple elements into a single line.
Such a line is called a "multiple element definition"
Various token characters may be used to separate the elements.
The token character used determines how the nest level is affected.

The token characters used in multiple element definitions are as follows:

, - Does not affect nest level  
| - Same as ','  
: - Increments nest level  
; - Decrements nest level  
( - Stores but does not affect nest level  
) - Restores nest level to that stored by corresponding '('  
{ - Stores and increments nest level  
} - Restores nest level to that stored by corresponding '{'  

### Example 1

    a, b, c

Becomes:

    -
    	a
    -
    -
    	b
    -
    -
    	c
    -

### Example 2

    a: b, c

Becomes:

    -
    	a
    -
    	-
    		b
    	-
    	-
    		c
    	-

### Example 3

    a: b; c

Becomes:

    -
    	a
    -
    	-
    		b
    	-
    -
    	c
    -

### Example 4

    a ( b: c ) d

Becomes:

    -
    	a
    -
    -
    	b
    -
    	-
    		c
    	-
    -
    	d
    -

### Example 5

    a { b: c } d

Becomes:

    -
    	a
    -
    	-
    		b
    	-
    		-
    			c
    		-
    -
    	d
    -