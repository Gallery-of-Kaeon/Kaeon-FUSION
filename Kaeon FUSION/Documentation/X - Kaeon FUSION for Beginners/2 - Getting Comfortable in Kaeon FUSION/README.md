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
but to ensure there is no confusion,
we will also show all examples in ONE as well.

After each section,
we recommend that you experiment with the subject the section covered before moving onto the next section.

Now let's get going!

## Contents

[1 - Variables](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/Kaeon%20FUSION/Documentation/X%20-%20Kaeon%20FUSION%20for%20Beginners/2%20-%20Getting%20Comfortable%20in%20Kaeon%20FUSION/README.md#1---variables)

[2 - Scope](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/Kaeon%20FUSION/Documentation/X%20-%20Kaeon%20FUSION%20for%20Beginners/2%20-%20Getting%20Comfortable%20in%20Kaeon%20FUSION/README.md#2---scope)

<!--

[3 - Strings and Data types](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/Kaeon%20FUSION/Documentation/X%20-%20Kaeon%20FUSION%20for%20Beginners/2%20-%20Getting%20Comfortable%20in%20Kaeon%20FUSION/README.md#3---strings-and-data-types)

[4 - Math](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/Kaeon%20FUSION/Documentation/X%20-%20Kaeon%20FUSION%20for%20Beginners/2%20-%20Getting%20Comfortable%20in%20Kaeon%20FUSION/README.md#math)

[5 - Logic](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/Kaeon%20FUSION/Documentation/X%20-%20Kaeon%20FUSION%20for%20Beginners/2%20-%20Getting%20Comfortable%20in%20Kaeon%20FUSION/README.md#logic)

[6 - Input and Output](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/Kaeon%20FUSION/Documentation/X%20-%20Kaeon%20FUSION%20for%20Beginners/2%20-%20Getting%20Comfortable%20in%20Kaeon%20FUSION/README.md#input-and-output)

[7 - Flow Control](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/Kaeon%20FUSION/Documentation/X%20-%20Kaeon%20FUSION%20for%20Beginners/2%20-%20Getting%20Comfortable%20in%20Kaeon%20FUSION/README.md#flow-control)

[8 - Lists](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/Kaeon%20FUSION/Documentation/X%20-%20Kaeon%20FUSION%20for%20Beginners/2%20-%20Getting%20Comfortable%20in%20Kaeon%20FUSION/README.md#lists)

[9 - Functions](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/Kaeon%20FUSION/Documentation/X%20-%20Kaeon%20FUSION%20for%20Beginners/2%20-%20Getting%20Comfortable%20in%20Kaeon%20FUSION/README.md#functions)

[10 - Objects](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/Kaeon%20FUSION/Documentation/X%20-%20Kaeon%20FUSION%20for%20Beginners/2%20-%20Getting%20Comfortable%20in%20Kaeon%20FUSION/README.md#objects)

-->

## 1 - Variables

A variable stores data for use later in the program.
A variable has an alias,
which is a string that it can be referenced by,
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

<!--

## 3 - Strings and Data Types



## 4 - Math



## 5 - Logic



## 6 - Flow Control



## 7 - Input and Output



### Files



## 8 - Lists



## 9 - Functions



## 10 - Objects



### Shallow Copy vs Deep Copy

-->