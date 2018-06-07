[Home](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/tree/master/Kaeon%20FUSION/Documentation/README.md) /
[Back](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/tree/master/Kaeon%20FUSION/Documentation/1%20-%20Foundations/README.md) /
[Previous](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/tree/master/Kaeon%20FUSION/Documentation/1%20-%20Foundations/1%20-%20ONE/README.md) /
[Next](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/tree/master/Kaeon%20FUSION/Documentation/1%20-%20Foundations/2%20-%20ONE%2B/1%20-%20Standard%20ONE%2B%20Directives/README.md)

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

The proper file extension for a ONE+ file is ".op".

## Sub-Topics

[1 - Standard ONE+ Directives](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/tree/master/Kaeon%20FUSION/Documentation/1%20-%20Foundations/2%20-%20ONE%2B/1%20-%20Standard%20ONE%2B%20Directives/README.md)

## Indentation

While ONE only allows the use of tabs for indentation,
ONE+ allows either tabs or an arbitrary number of spaces.
However, the use of indentation must be consistent throughout the file.

## Element Definitions

ONE+ allows elements to be defined outside of element blocks.
A non-blank line containing no token characters written will be converted into an element block.
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

## Multiple Element Definitions

It is also possible to encode multiple elements into a single line.
Such a line is called a "multiple element definition".
Various token characters may be used to separate the elements.
The token character used determines how the nest level is affected.

When nesting a child element beneath a multiple element line,
the element that will become the parent is the most recent element at the nest level of the end of the line.

The token characters used in multiple element definitions are as follows:

, - Does not affect nest level
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

### Example 6

    a: b
    	c

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

### Example 7

    a { b: c }
    	d

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

## Escape Sequences

The effect of any token character can be negated by preceding it with a tilde.
Likewise,
the negating effect of a tilde can itself be negated with a preceding tilde.
The negating tilde will not be encoded into the element.

If the letter n is preceded by a tilde,
it will be encoded as a new line.
If the letter t is preceded by a tilde,
it will be encoded as a tab.

If a string is placed between two single quotes,
the effects of all token characters between them will be negated.
The single quotes will not be encoded into the string.

Double quotes have the same negating effect as single quotes,
but double quotes will be encoded into the string.

In addition,
no token character will take effect if placed inside an element block.

### Example 1

    a~: b

Becomes:

    -
    	a: b
    -

### Example 2

    a~~: b

Becomes:

    -
    	a~
    -
    	-
    		b
    	-

### Example 3

    'a: b'

Becomes:

    -
    	a: b
    -

### Example 4

    "a: b"

Becomes:

    -
    	"a: b"
    -

### Example 5

    hello~n~tworld

Becomes:

    -
    	hello
    		world
    -

### Example 6

    -
    	a: hello~n~tworld
    -

Becomes:

    -
    	a: hello~n~tworld
    -

## Comments

Any part of a line preceded by a pound sign will be commented out.
A pound sign followed by an open square bracket dictates the start of a comment block.
A comment block is closed with a closed square bracket followed by a pound sign.

### Example 1

    hello # world

Becomes:

    -
    	hello
    -

### Example 2

    hello #[
    abc
    123
    xyz ]#
    world

Becomes:

    -
    	hello
    -
    -
    	world
    -

## Directives

Directives are preprocessor scripts for ONE+.

Any element defined in a multiple element line and encased in square brackets constitutes a directive call.
Any children of such a directive call in the same square brackets constitute the directive call's header.
Any other children a directive call may have constitute the directive call's body.

By convention,
the content of a directive call is written entirely in upper case.

For example:

    [DIRECTIVE NAME: header argument 1, header argument 2, header argument n]
    
    	body argument 1
    	body argument 2
    	body argument n

### The Use Directive

Directives come packaged in modules stored in the same environment as the ONE+ parser.

The only directive automatically usable by ONE+ is the use directive,
which allows other directive to be made usable.

The use directive call contains the content "USE",
and all of the elements in its header contain the names of modules containing directives.

For example,
you can use the hypothetical directive call "FOO" which is stored in the hypothetical directive module "bar" using the following code:

    [USE: bar]
    
    [FOO]

## Alternate Syntaxes

If you wish to use a different syntax altogether to encode a ONE document,
ONE+ allows for this using alternate syntaxes.

Alternate syntaxes,
like directives,
come packaged in modules stored in the same environment as the ONE+ parser.

To use an alternate syntax,
make sure the first line of your file starts with "-[" and ends with "]".

The text between the two brackets should contain the name of the desired dialect.
Any text following the first line will be encoded as ONE data based on the specified dialect.

For example,
our implementation of ONE+ comes with an alternate syntax called ONE LISP,
which as the name implies,
looks like LISP.

If you were to compile the following code to ONE:

    -[ONE LISP]

    (a 1 2 3)
    (foo (bar 4) (baz 5))

It would produce the following:

    -
    	a
    -
    	-
    		1
    	-
    	-
    		2
    	-
    	-
    		3
    	-
    -
    	foo
    -
    	-
    		bar
    	-
    		-
    			4
    		-
    	-
    		baz
    	-
    		-
    			5
    		-

<div align="right"><p>

<a href="https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/tree/master/Kaeon%20FUSION/Documentation/README.md">Home</a> / 
<a href="https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/tree/master/Kaeon%20FUSION/Documentation/1%20-%20Foundations/README.md">Back</a> / 
<a href="https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/tree/master/Kaeon%20FUSION/Documentation/1%20-%20Foundations/1%20-%20ONE/README.md">Previous</a> / 
<a href="https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/tree/master/Kaeon%20FUSION/Documentation/1%20-%20Foundations/2%20-%20ONE%2B/1%20-%20Standard%20ONE%2B%20Directives/README.md">Next</a>

</p></div>