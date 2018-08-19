[Kaeon FUSION Home](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/README.md)

# ONE+ Alternate Syntax Development

The ONE+ alternate syntax development framework allows third parties to develop alternate syntaxes for ONE+.

## Before you begin

The ONE+ alternate syntax development framework is based in Java,
so be sure that you have Java installed and that you're comfortable writing Java code.

## Framework Structure

The framework comes in the form of an Java project.

The project itself is a fully functioning syntax,
which seprerates the content of a document into sibling elements using commas and new line as the delimiters,
cropping any leading or trailing whitespace from said values and discarding any values consisting of only whitespace.
For example,
If you were to export the project as "Test.jar",
place the jar file next to [Kaeon Origin](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/Kaeon%20FUSION/IDE/README.md),
and convert the following document to ONE:

    -[TEST]

    abc: 123, def: 456
    ghi: 789, j: 0

this would be the result:

    -
    	abc: 123
    -
    -
    	def: 456
    -
    -
    	ghi: 789
    -
    -
    	j: 0
    -

## How to use

First, download the code for our sample ONE+ alternate syntax [here](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/Kaeon%20FUSION/Module%20Development%20Framework/Syntaxes/Test%20Syntax/Source).

Please note that the provided code is dependent on the [ONE](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/tree/master/Kaeon%20FUSION/APIs/ONE/Library),
[ONE+](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/tree/master/Kaeon%20FUSION/APIs/ONE%2B/Library),
[Tokenizer](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/tree/master/Kaeon%20FUSION/APIs/Tokenizer/Library),
and [Philosopher's Stone](https://github.com/Gallery-of-Kaeon/Philosophers-Stone/tree/master/Philosopher's%20Stone/API/Java/Library) APIs.

You can also try out the pre-compiled sample syntax [here](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/Kaeon%20FUSION/Module%20Development%20Framework/Syntaxes/Test%20Syntax/Syntax/Test.jar?raw=true).

The first thing to do is to name your syntax.
For the sake of this example,
we'll name it "My Syntax".

Rename the package aether_test such that the name of the package is "aether_" followed by the name of your interface,
all lower case,
with spaces replaced with underscores.
So,
for our example,
the package will be renamed to "aether_my_syntax".

Rename the test package and the Test class to reflect the name of your syntax,
and rewrite the parse function in the renamed Test class to parse the document,
which is passed to the function as a string, into a ONE element.