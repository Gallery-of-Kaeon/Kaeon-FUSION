[Kaeon FUSION Home](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/README.md)

# ONE+ Directive Development

The ONE+ directive development framework allows third parties to develop directives for ONE+.

## Before you begin

The ONE+ directive development framework is based in Java,
so be sure that you have Java installed and that you're comfortable writing Java code.

## Framework Structure

The framework comes in the form of a Java project.

The project itself is a fully functioning directive module,
which provides access to one directive.
This directive,
which contains the content "TEST",
appends the string "TEST - " prior to the content of all elements within the scope of its parent.
For example,
If you were to export the project as "Test.jar",
place the jar file next to [Kaeon Origin](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/Kaeon%20FUSION/IDE/README.md),
and click the "Show ONE" button in the Kaeon FUSION settings option menu after typing in the following text:

    [USE: TEST]

    a: b

    	[TEST]

    	c: d
    		e

    	f

    g: h
	
    	[TEST]
	
    	i

The following ONE would be generated:

    -
    	a
    -
    	-
    		b
    	-
    		-
    			TEST - c
    		-
    			-
    				TEST - d
    			-
    				-
    					TEST - e
    				-
    		-
    			TEST - f
    		-
    -
    	g
    -
    	-
    		h
    	-
    		-
    			TEST - i
    		-

## How to use

First, download the code for our sample ONE+ directive [here](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/Kaeon%20FUSION/Module%20Development%20Framework/Directives/Test%20Directive/Source).

Please note that the provided code is dependent on the [ONE](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/tree/master/Kaeon%20FUSION/APIs/ONE/Library),
[ONE+](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/tree/master/Kaeon%20FUSION/APIs/ONE%2B/Library),
[Tokenizer](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/tree/master/Kaeon%20FUSION/APIs/Tokenizer/Library),
and [Philosopher's Stone](https://github.com/Gallery-of-Kaeon/Philosophers-Stone/tree/master/Philosopher's%20Stone/API/Java/Library) APIs.

You can also try out the pre-compilded sample directive [here](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/Kaeon%20FUSION/Module%20Development%20Framework/Directives/Test%20Directive/Directive/Test.jar?raw=true).

The first thing to do is to name your directive module.
For the sake of this example,
we'll name it "My Directive".

Rename the package aether_test such that the name of the package is "aether_" followed by the name of your directive module,
all lower case,
with spaces replaced with underscores.
So,
for our example,
the package will be renamed to "aether_my_directive".

Proceed to implement any directives provided by your direcitve module as extensions of the DirectiveUnit class.

Then,
in the getDirectives method of the DirectiveInterface class in the Aether package,
add a new instance of each of your directiveUnits to the directiveUnits ArrayList.
Feel free to remove the test command.

Finally,
export your interface to a jar file that shares the name of your directive module.
The jar file for this example would be named "My Directive.jar".

Place the jar file next to the Kaeon Origin.jar file and Kaeon Origin will automatically be able to access it.

## The DirectiveUnit Class Functions

### Apply

    public void apply(ArrayList<DirectiveUnit> directiveUnits, ArrayList<Directive> directives, Directive directive)

This function is called when a directive unit is triggered.
All directive objects currently in effect as well as all directives in the document and the directve that triggered the directive unit are passed to the function.
Any of the passed in values may be modified.

## The Directive Class Fields

    public Element directive

This field contains the element containing the directive.

    public ArrayList<Element> header

This field contains all of the directive element's children located in the directive element's directive definition.

    public ArrayList<Element> body

This field contains all of the directive element's children located outside of the directive element's directive definition.
