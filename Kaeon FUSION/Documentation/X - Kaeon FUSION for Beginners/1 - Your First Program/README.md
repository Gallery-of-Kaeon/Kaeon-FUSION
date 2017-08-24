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
which means it uses special sybmols in the document to cut the document into small chunks of text.
It then rearranges these chunks into a tree,
which is usually called an "abstract syntax tree".

However,
most languages have a very strict syntax that governs which chunks of text can be placed in certain spots on the abstract syntax tree.
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
    Log Line: "Hello, world!"

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