[Home](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/Kaeon%20FUSION/Documentation/X%20-%20Kaeon%20FUSION%20for%20Beginners/README.md) /
[Previous](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/Kaeon%20FUSION/Documentation/X%20-%20Kaeon%20FUSION%20for%20Beginners/1%20-%20Your%20First%20Program/README.md)

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
but to minimize confusion,
we will also show many of our examples in ONE as well.

After each section,
we recommend that you experiment with the subject the section covered before moving onto the next section.

Now let's get going!

## Contents

[1 - Variables](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/Kaeon%20FUSION/Documentation/X%20-%20Kaeon%20FUSION%20for%20Beginners/2%20-%20Getting%20Comfortable%20in%20Kaeon%20FUSION/README.md#1---variables)

[2 - Scope](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/Kaeon%20FUSION/Documentation/X%20-%20Kaeon%20FUSION%20for%20Beginners/2%20-%20Getting%20Comfortable%20in%20Kaeon%20FUSION/README.md#2---scope)

[3 - Strings and Data types](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/Kaeon%20FUSION/Documentation/X%20-%20Kaeon%20FUSION%20for%20Beginners/2%20-%20Getting%20Comfortable%20in%20Kaeon%20FUSION/README.md#3---strings-and-data-types)

[4 - Math](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/Kaeon%20FUSION/Documentation/X%20-%20Kaeon%20FUSION%20for%20Beginners/2%20-%20Getting%20Comfortable%20in%20Kaeon%20FUSION/README.md#4---math)

[5 - Logic](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/Kaeon%20FUSION/Documentation/X%20-%20Kaeon%20FUSION%20for%20Beginners/2%20-%20Getting%20Comfortable%20in%20Kaeon%20FUSION/README.md#5---logic)

[6 - Flow Control](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/Kaeon%20FUSION/Documentation/X%20-%20Kaeon%20FUSION%20for%20Beginners/2%20-%20Getting%20Comfortable%20in%20Kaeon%20FUSION/README.md#6---flow-control)

<!--

[7 - Input and Output](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/Kaeon%20FUSION/Documentation/X%20-%20Kaeon%20FUSION%20for%20Beginners/2%20-%20Getting%20Comfortable%20in%20Kaeon%20FUSION/README.md#7---input-and-output)

[8 - Lists](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/Kaeon%20FUSION/Documentation/X%20-%20Kaeon%20FUSION%20for%20Beginners/2%20-%20Getting%20Comfortable%20in%20Kaeon%20FUSION/README.md#8---lists)

[9 - Functions](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/Kaeon%20FUSION/Documentation/X%20-%20Kaeon%20FUSION%20for%20Beginners/2%20-%20Getting%20Comfortable%20in%20Kaeon%20FUSION/README.md#9---functions)

[10 - Objects](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/Kaeon%20FUSION/Documentation/X%20-%20Kaeon%20FUSION%20for%20Beginners/2%20-%20Getting%20Comfortable%20in%20Kaeon%20FUSION/README.md#10---objects)

[11 - Errors](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/Kaeon%20FUSION/Documentation/X%20-%20Kaeon%20FUSION%20for%20Beginners/2%20-%20Getting%20Comfortable%20in%20Kaeon%20FUSION/README.md#11---errors)

-->

## 1 - Variables

A variable stores data for use later in the program.
A variable has an alias,
which is a string that it can be referenced by,
and a value,
which is a reference to data somewhere in the computer's memory.

The process of creating a new variable is called declaration.
To declare a new variable,
use an element containing a string that Kaeon FUSION does not recognize to serve as the alias (so you could name your variable x, but not Log Line),
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

## 2 - Scope

Information stored withing a variable can only be accessed by its sibling and children of said siblings.
There is a command in the standard interface called "Scope".
Scope performs no operations.
It simply serves to establish a block of code isolated from the rest of the document.
Any variable declaration nested within the scope command would have no effect on the rest of the document.

For example, if you ran the following code:

    Use: Standard

    x: 5

    Scope

    	y: 10

    	Scope

    		z: 15

    		Log Line: x
    		Log Line: y
    		Log Line: z

    	Log Line: x
    	Log Line: y
    	Log Line: z

    Log Line: x
    Log Line: y
    Log Line: z

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
    -
    	Scope
    -
    	-
    		y
    	-
    		-
    			10
    		-
    	-
    		Scope
    	-
    		-
    			z
    		-
    			-
    				15
    			-
    		-
    			Log Line
    		-
    			-
    				x
    			-
    		-
    			Log Line
    		-
    			-
    				y
    			-
    		-
    			Log Line
    		-
    			-
    				z
    			-
    	-
    		Log Line
    	-
    		-
    			x
    		-
    	-
    		Log Line
    	-
    		-
    			y
    		-
    	-
    		Log Line
    	-
    		-
    			z
    		-
    -
    	Log Line
    -
    	-
    		x
    	-
    -
    	Log Line
    -
    	-
    		y
    	-
    -
    	Log Line
    -
    	-
    		z
    	-

the program will display:

    5
    10
    15
    5
    10
    z
    5
    y
    z

## 3 - Strings and Data Types

As discussed earlier,
a command that does not match a command known by Kaeon FUSION will returns its own string as a value.
Such a command is called a literal.

However,
some characters have special effects if used in literals.

For example,
as prevously shown,
quotation marks in literals block characters between them from have any effect on ONE+,
but the quotation marks themselves disappear after that.
In addition to being useful when your string contains commas or colons,
it is also useful if you want to display something that would normally be identified as a command.
Like if you wanted to display Log Line without running it as a command.

For example, running:

    Use: Standard
    Log Line: "Log Line"

would display:

    Log Line

Backslashes also allow certain characters to be placed into strings.
A backslash followed by the letter 'n' will be interpreted as a new line,
a backslash followed by the letter 't' will be interpreted as a tab,
and a backslash followed by another backslash will be interpreted as a literal backslash.

For example: running:

    Use: Standard
    Log Line: abc\n\t\\

will display:

    abc
    	\

### String Types

Certain commands in Kaeon FUSION that take string values expect said values to be in certain formats.

For example,
math commands expect the strings given to them to be in the form of numbers.
A number string must consist entirely of digits and optionally one period,
and the last character in the string must be a digit.

Logic commands on the other hand,
expect the strings given to them to be either the word "true",
or "false".
Letter case is irrelevant.

### Null

The null value is a value used to sigify the absence of a value where there would otherwise be one.
The null value can be returned using a command containing the string null.

## 4 - Math

Basics math operations can be performed in the standard interface using its five basic math commands:
add,
subtract,
multiply,
divide,
and modulus.
Each math command has two child commands,
each of which return a number.
When activated,
a math command will perform its respective operation on the two numbers returned to it by its children and return the result.

_Note: A modulus operation takes the first number and divides it by the second number, but returns the remainder instead of the quotient._

For example, running:

    Use: Standard

    Log Line: Add: 1, 2
    Log Line: Subtract: 3, 2
    Log Line: Multiply: 2, 5
    Log Line: Divide: 9, 3
    Log Line: Modulus: 10, 4

will display:

    3
    1
    10
    3
    2

A random number between 0 and 1 can be generated by the random command.

Let's say we want to generate a random number between 5 and 10.
We could do it like this:

    Use: Standard

    min: 5; max: 10

    random number: Add: min, Multiply: Random, Subtract: max, min 

    Log Line: random number

Try running it over and over again and see what you get!

If we want the result to be a whole number,
we can just add in a modulus command:

    Use: Standard

    min: 5; max: 10

    random number: Add: min, Multiply: Random, Subtract: max, min 
    random number: Subtract: random number, Modulus: random number, 1

    Log Line: random number

_Note: While writing out math operations like this can be tedious, there is a ONE+ directive called Super Mode, which allows these operations to be written like normal math operations. You can read about it [here](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/Kaeon%20FUSION/Documentation/3%20-%20Standard%20Interface/1%20-%20Super%20Mode/README.md)._

## 5 - Logic

In programming,
logic operations deal with values of true and false.
These true or false values are called booleans.

Booleans are named after british mathematician [George Boole](https://en.wikipedia.org/wiki/George_Boole),
who created a system of processing true and false values called boolean algebra.
This system laid the mathematical foundation for how computer circuitry works.

In boolean logic,
which derives from boolean algebra,
there are four primary functions:
and,
or,
not,
and exclusive or.

The and operation takes two boolean values and returns true if both of the boolean values it was given are true,
and returns false otherwise.

The or operation takes two boolean values and returns true if at least one of the boolean values it was given are true,
and returns false otherwise.

The not operation takes one boolean values and returns true if the boolean value it was given is false,
and returns false if the boolean value it was given is true.

The exclusive or operation takes two boolean values and returns true if one but not both of the boolean values it was given are true,
and returns false otherwise.

The Kaeon FUSION Standard Interface provides these four operations as commands.

For example, running:

    Use: Standard

    Log Line: And: True, True
    Log Line: And: True, False

    Log Line: Or: True, False
    Log Line: Or: False, False

    Log Line: Not: False
    Log Line: Not: True

    Log Line: Exclusive Or: True, False
    Log Line: Exclusive Or: True, True

will display:

    True
    False
    True
    False
    True
    False
    True
    False

_Note: When calculating boolean logic, many computer scientists use charts called truth tables. You can read about them [in this article](http://www.butte.edu/resources/interim/wmwu//iLogic/3.2/iLogic_3_2.html), authored by Wu Wei-Ming in his blog "iLogic"._

### Comparison

In computer science it is also important to have operations for comparison.
Such operations take two values and return either a true or false boolean value depending on how the two values compare to one another.

Kaeon FUSION provides an equal command,
which determines if two values are eqivilent,
as well the following inequality operations,
which only work with numbers:
Greater,
Less,
Greater or Equal,
and Less or Equal.

For example, running:

    Use: Standard
    
    Equal: 10, Add: 5, 5

    Greater: 10, 10
    Less: 5, 10

    Greater or Equal: 10, 10
    Less or Equal: 15, 10

will display:

    True
    False
    True
    True
    False

_Note: As mentioned in the math section, there is a ONE+ directive called Super Mode, which allows these operations to be written in a far less verbose manner. You can read about it [here](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/Kaeon%20FUSION/Documentation/3%20-%20Standard%20Interface/1%20-%20Super%20Mode/README.md)._

## 6 - Flow Control

In all of our programs written thus far,
every command we place in our program will execute,
and after they execute,
they will not execute again.

You may recall that in the previous section:
your first program,
we discussed how certain commands could force the program to jump to another part of the program.

The Kaeon FUSION Standard Interface provides two such commands:
loop and break,
each of which have the option of having a child that returns a boolean value.
In practice,
they should almost always have such a child.
If they have no child,
they will perform their respective jump operation.
If they do have a child,
they will only jump if the child returns a true value.

The loop command jumps to the first child of its parent,
and the break command jumps to the first sibling after its parent.

It is best practice to nest them within a Scope command.

For example, running:

    Use: Standard

    x: 5

    Scope { Break: Less: x, 10 }
    	Log Line: The variable x is less than 10.

    Scope { Break: Greater or Equal: x, 10 }
    	Log Line: The variable x is greater than or equal to 10.

    i { 0 } Scope

    	Log Line: i, " alligator"

    	i: Add: i, 1
    	Loop: Less: i, x

will display:

    The variable x is less than 10.
    0 alligator
    1 alligator
    2 alligator
    3 alligator
    4 alligator

In addition,
Kaeon FUSION also has a else command,
which will only allow its children to execute if the most recently used break command had a child that returned false.

For example, running:

    Use: Standard

    x: 5

    Scope { Break: Greater or Equal: x, 10 }
    	Log Line: The variable x is greater than or equal to 10.

    Else
    	Log Line: The variable x is less than 10.

will display:

    The variable x is less than 10.

<!--

## 7 - Input and Output



### Files



## 8 - Lists



## 9 - Functions



## 10 - Objects



### Shallow Copy vs Deep Copy



## 11 - Errors



-->