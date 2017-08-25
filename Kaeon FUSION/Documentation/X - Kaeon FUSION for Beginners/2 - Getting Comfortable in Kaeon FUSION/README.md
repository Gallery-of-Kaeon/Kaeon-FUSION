# Getting Comfortable in Kaeon FUSION

Alright,
you've made it this far!
Give yourself a nice pat on the back.

Now that you understand the basics of how Kaeon FUSION works,
you ought to know how to really take advantage of its power.

As such,
this section will cover the how to effectively write code using the standard interface.
We will also delve into programming topics that apply regardless of what language you use.

Our examples will be written in ONE+,
but to ensure there is no confusion,
we will also show all examples in ONE as well.

After each section,
we recommend that you experiment with the subject the section covered before moving onto the next section.

Now let's get going!

## Variables

A variable is an object that stores data for use later in the program.
A variable has an alias,
which is a string value that it can be referenced by,
and a value,
which is a reference to data somewhere in the computer's memory.

The process of creating a new variable is called declaration.
To declare a new variable,
use an element containing a string that Kaeon FUSION does not recognize to serve as the alias (so you could your a variable x, but not Log Line),
and nest within it a command that returns the value you want you variable to store.

_Note: To nest an element means to make it a child of another element._

For example,
you can declare a variable called x with the value 5 like this:

    Use: Standard

    x: 5

which in ONE is:

    -
    	Use
    -
    	-
    		Standard
    	-
    -
    	x
    -
    	-
    		5
    	-

After the variable has been declared,
you can get the value stored inside it by using an element containing its alias,
and you can change the value stored inside it by nesting a command that returns the new value inside a command that contains the alias of the variable.

For example, if you run the following code:

    Use: Standard

    Log Line: x

    x: 5
    Log Line: x

    x: 10
    Log Line: x

which in ONE is:

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
    		x
    	-
    -
    	x
    -
    	-
    		5
    	-
    -
    	Log Line
    -
    	-
    		x
    	-
    -
    	x
    -
    	-
    		10
    	-
    -
    	Log Line
    -
    	-
    		x
    	-

then the program will display:

    x
    5
    10