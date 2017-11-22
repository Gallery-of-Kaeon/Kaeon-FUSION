[Kaeon FUSION Home](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/README.md)

# ONE+ Directive Development Framework

The ONE+ directive development framework allows third parties to develop directives for ONE+.

## Before you begin

The ONE+ directive development framework is based in Java,
so be sure that you have Java installed and that you're comfortable writing Java code.

## Framework Structure

The framework comes in the form of an Eclipse project,
although you're free to use any development environment that supports Java.

The project itself is a fully functioning directive module,
which provides access to one directive.
This directive,
which contains the content "TEST",
appends the string "TEST - " prior to the content of all elements within the scope of its parent.
For example,
If you were to export the project as "Test.jar",
place the jar file next to [Kaeon Origin](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/Kaeon%20FUSION/IDE/README.md),
and click the "Show ONE button after typing in the following text:

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

First, download the ONE+ directive development framework [here](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/Kaeon%20FUSION/Module%20Development%20Framework/Directives/Test%20Interface.zip?raw=true).

The first thing to do is to name your directive module.
For the sake of this example,
we'll name it "My Directive".

Rename the package aether_test such that the name of the package is "aether_" followed by the name of your directive module,
all lower case,
with spaces replaced with underscores.
So,
for our example,
the package will be renamed to "aether_my_directive".

Proceed to implement any directives provided by your direcitve module as extensions of the Directive class.

Then,
in the onCall method of the Aether class,
add a new instance of each of your directives to the directiveUnits ArrayList.
Feel free to remove the test command.

Finally,
export your interface to a jar file that shares the name of your directive module.
The jar file for this example would be named "My Directive.jar".

Place the jar file next to the Kaeon Origin.jar file and Kaeon Origin will automatically be able to access it.

## The Directive Class Functions

### Apply

    public void apply(ArrayList<Directive> directiveUnits, ArrayList<Element> directives, Element element)

This function is called when a directive is triggered.
All directive objects currently in effect as well as all elements in the document recognized as directives and the element that triggered the directive are passed to the function.
Any of the passed in values may be modified.