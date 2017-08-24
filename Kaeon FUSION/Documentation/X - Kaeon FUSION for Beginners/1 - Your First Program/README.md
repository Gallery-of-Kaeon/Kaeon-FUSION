[Home](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/Kaeon%20FUSION/Documentation/X%20-%20Kaeon%20FUSION%20for%20Beginners/README.md) /
[Previous](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/Kaeon%20FUSION/Documentation/X%20-%20Kaeon%20FUSION%20for%20Beginners/README.md)

# Your First Program

It is tradition in the world of programming that when one either learns programming for the first time or learns a new language,
they write a program called "Hello World" which displays the text "Hello, world!" on the screen.

## Writing Hello World

Boot up the Kaeon Dev application and type the following into the upper text area:

    Use: Standard
    Log Line: "Hello, world!"

_Note: If this is indeed your first program, consider replacing the text between the quotation marks with something a bit more personal. Writing your first program is a special moment, so make it count!_

Once you've done this,
click the "Run" button.
The text between the quotation marks should be displayed in the lower text area.

## What just happened?

So,
assuming you performed the above instructions correctly and the text you placed between the quotation marks appeared in the lower text area,
you're probably curious about what exactly went down when you clicked the run button.

### ONE+

You may have taken note of the fact that bits of the text you wrote are separated by colons.
As you wrote your program,
you were writing in a syntax called ONE+.

Syntax refers to the system that governs how all of the symbols and words in a text document relate to each other.
The first thing any programming language does when running a program is a process called tokenization,
which means it uses special sybmols in the document to cut the document into small chunks of text called strings.
It then rearranges these strings into a tree,
which is usually called an "abstract syntax tree".

#### A tree? Like an apple tree?

In the context of computer science,
the term "tree" refers to a certain type of graph.
A graph is data structure made of little nodes.
Each node stores miscellaneous data and connections to other nodes.
A graph is a tree if none of the connections loop.

For example,
we could have a tree with seven different nodes:
A,
B,
C,
D,
E,
F,
and G.
We'll have A be connected to B and C,
we'll have B be connected to D and E,
and we'll have C be connected to F and G.
In this example we could say that A is the parent of B and C,
that B is the parent of D and E,
and that C is the parent of F and G.
Therefore,
D and E would be the children of B,
F and G would be the children of C,
and B and C would be the children of A.
However,
if any of our connections formed a loop,
our we would no longer have a tree,
just a graph.

Another thing to note is that the order in which the connections are stored within a node matters.
For instance,
if we assume A stores its connection to B before its connection to C,
we could say that B is the older sibling of C,
thereby making C the younger sibling of B.

Using this structure,
we can interpret whatever data is stored in these nodes as a hierarchy.

In ONE+,
every string created after tokenization is stored in a node called an element.

#### What's special about ONE+?

Most languages have a very strict syntax that governs which chunks of text can be placed in certain spots on the abstract syntax tree.
ONE+, on the other hand, allows you to define the tree however you want to.

In the case of our previous example,
which for the sake of example we'll assume you wrote "Hello, world!" between the quotation marks,
the tokenization process cut the text into four chunks:
"Use",
"Standard",
"Log Line",
and ""Hello, world!"".

Because "Standard" and "Hello, world!" were placed in front of "Use" and "Log Line" respectively using a colon,
"Standard" became a child of "Use" and ""Hello, world"" became a child of "Log Line".
Because they were on separate lines,
"Use" and "Log Line" became siblings of each other.

During the tokenization process,
our ONE+ document was converted into a ONE document.
ONE is a much stricter form of ONE+.
ONE takes longer to write by hand than ONE+,
but reading it makes the tree structure far more obvious.
In the case of our ONE+ document,
the corresponding ONE document looked like this:

    -
    	Use
    -
    	-
    		Standard
    	-
    -
    	Log Line
    -
    	-
    		"Hello, world!"
    	-

_Note: ONE code is still valid as ONE+. If you copy and paste the above text over your original code, it will still work!_

It is important to note the importance of surrounding the text "Hello, world!" with quotation marks.
If the text did not have a comma in it,
we could have gotten away with not using quotation marks.
However,
commas in ONE+ normally cut text into separate siblings.
If we had written our ONE+ like this:

    Use: Standard
    Log Line: Hello, world!

our ONE document would have looked like this:

    -
    	Use
    -
    	-
    		Standard
    	-
    -
    	Log Line
    -
    	-
    		Hello
    	-
    	-
    		world!
    	-

and instead of displaying:

    Hello, world!

it would have displayed:

    Helloworld!

### FUSION

After our document has been cut up and arranged into a tree,
we want our program to do something based on the information the tree contains.

Every language has its own way of doing this.
Kaeon FUSION governs how the tree is interpreted using a ruleset called the FUSION system.

FUSION will start at the first element in the document that has no parent.
So for our example it starts at Use.

If the element FUSION is currently at has children,
FUSION will shift focus to them.
If not,
FUSION will perform an operation based on the string inside the element.
If the performed operation generates a value,
FUSION will store this value in a list.

After performing an operation on an element,
if the element has any younger siblings FUSION will shift focus to the next youngest sibling,
and to the parent of the element if not.
After shifting to a parent of an element it has already performed an operation on,
it will perform an operation on the parent based on the string inside the parent and the values in its list.
It will then clear the list.

Every time FUSION performs an operation,
it may choose to jump to an element elsewhere in the document instead of proceeding to the nest sibling or parent.

Let's use this program as an example:

    -
    	A
    -
    	-
    		B
    	-
    		-
    			C
    		-
    	-
    	 	D
    	-
    -
    	JUMP
    -
    -
    	E
    -
    -
    	F
    -
    	-
    		G
    	-
    	-
    		LAND
    	-
    		-
    			H
    		-

The flow of the above program according to the rules of FUSION will be as follows:

After starting at A the process trickles down to B and then to C.
After perfomring an operation with C and generating a value,
it returns that value to B.
It then performs an operation with B and the value generated with C,
and then moves on to D.
After perfomring an operation and generating a value with D,
it performs and operation with A and the value generated with B and D.

It of course then moves to JUMP,
but after it performs an operationa and generates a value,
it jumps to LAND instead of moving to E.

After jumping to LAND,
it trickles down to H,
where it performs an operation and generates a value.
It then goes back to LAND where it performs an operation and generates a value using the value generated with H.
Finally,
it performs an operation and generates a value with F using the value generated with LAND.

Here's a nice way to visualize it:

C -> B(C) -> D -> A(B, D) -> JUMP -> H -> LAND(H) -> F(LAND)