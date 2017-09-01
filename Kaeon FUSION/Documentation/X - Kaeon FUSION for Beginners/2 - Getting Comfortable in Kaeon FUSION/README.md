[Home](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/Kaeon%20FUSION/Documentation/X%20-%20Kaeon%20FUSION%20for%20Beginners/README.md) /
[Previous](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/Kaeon%20FUSION/Documentation/X%20-%20Kaeon%20FUSION%20for%20Beginners/1%20-%20Your%20First%20Program/README.md) /
[Next](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/Kaeon%20FUSION/Documentation/X%20-%20Kaeon%20FUSION%20for%20Beginners/3%20-%20Advanced%20Kaeon%20FUSION/README.md)

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

[7 - Input and Output](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/Kaeon%20FUSION/Documentation/X%20-%20Kaeon%20FUSION%20for%20Beginners/2%20-%20Getting%20Comfortable%20in%20Kaeon%20FUSION/README.md#7---input-and-output)

[8 - Lists](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/Kaeon%20FUSION/Documentation/X%20-%20Kaeon%20FUSION%20for%20Beginners/2%20-%20Getting%20Comfortable%20in%20Kaeon%20FUSION/README.md#8---lists)

[9 - Functions](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/Kaeon%20FUSION/Documentation/X%20-%20Kaeon%20FUSION%20for%20Beginners/2%20-%20Getting%20Comfortable%20in%20Kaeon%20FUSION/README.md#9---functions)

[10 - Objects](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/Kaeon%20FUSION/Documentation/X%20-%20Kaeon%20FUSION%20for%20Beginners/2%20-%20Getting%20Comfortable%20in%20Kaeon%20FUSION/README.md#10---objects)

[11 - Errors](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/Kaeon%20FUSION/Documentation/X%20-%20Kaeon%20FUSION%20for%20Beginners/2%20-%20Getting%20Comfortable%20in%20Kaeon%20FUSION/README.md#11---errors)

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

_Note: Kaeon FUSION allows you to assign any value to any variable. However, there are some languages that force you to give each variable a type, meaning that they can only store values that match that type. If a language does do this, it is called a statically typed language. If, like Kaeon FUSION, it does not, it is called a dynamically typed language._

## 2 - Scope

Information stored within a variable can only be accessed by its sibling and children of said siblings.
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

### Global Variables

Declaring a variable within a global command, like this:

    Global: my variable: my value

will prevent the variable from disappearing if the flow of the program moves outside the script,
like when using a function,
which we'll get to later.

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

<!--

### String Operations




-->

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
    
    Log Line: Equal: 10, Add: 5, 5

    Log Line: Greater: 10, 10
    Log Line: Less: 5, 10

    Log Line: Greater or Equal: 10, 10
    Log Line: Less or Equal: 15, 10

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

## 7 - Input and Output

A typical program operates in a cycle of processing information and relaying said information to the user.
A program is pointless if the user has no way to communicate with it.

For all of our examples thus far,
we've been using the Log Line command to display information.
The Kaeon FUSION Standard Interface provides another command called Log.
The difference between Log and Log Line is that Log Line creates a new line after it displays the information given to it,
whereas Log does not.

For example, running:

    Use: Standard

    Log: a
    Log Line: b
    Log Line: c
    Log: d
    Log: e

will display:

    ab
    c
    de

Of course,
it is a given that some programs must take information from the user as well.
The input command allows for this.
It works like the log command,
except after displaying the data it is given it will prompt the user to enter a string.
It will return whatever the user enters.

For example, running:

    Use: Standard
    
    user input: Input: "Enter a word or phrase: "
    Log Line: user input

will prompt the user with the prompt "Enter a word or phrase: ".
If the user enters "I love Kaeon FUSION." at the prompt,
the program will display:

    I love Kaeon FUSION.

### Files

The Kaeon FUSION Standard Interface can read from and write to files using the open and save commands.
For the sake of the following examples we'll assume you're running Kaeon FUSION from Kaeon Dev.

The open command has a child that returns a string.
It will search the folder surrounding the environment Kaeon FUSION is running in for a file that matches the string.
If it finds such a file it will return the contents of the file as a string.
If it does not find the file in the local folder,
it will check to see if the string is a URL.
If it is,
it will search the internet for the file.

For example,
if you had a file called "My File.txt" next to Kaeon Dev.jar,
which had the content:

    abc
    123

then running:

    Use: Standard
    
    Log Line: Open: My File.txt

would display:

    abc
    123

The save command will have two children that each return strings.
It will create a file with the name of the second string and write the first string to it.

For example, running:

    Use: Standard

    Save
    
    	-
    		abc
    		123
    	-

    	My File.txt

will create a file next to Kaeon Dev.jar called My File.txt with the content:

    abc
    123

## 8 - Lists

So we know that we can use variables to store data,
but what if we wanted to store multiple values in the same place?
For that we can use lists.

A list is a value that can store other values.
The Kaeon FUSION Standard Interface provides command both for creating lists and for manipulating values in them.

When displayed,
a list will be presented as having all of the values inside of it placed between square brackets ans separated by commas.

The list command can be used for creating a list.
It can have an indefinite number of children.
When run it assembles all of the values returned to it into a list and returns the list.

For example, running:

    Use: Standard
    
    my list: List: a, b, c
    Log Line: my list

will display:

    [a, b, c]

You can even place lists inside other lists.

For example, running:

    Use: Standard
    
    my list: List: a, b, c, List: 1, 2, 3
    Log Line: my list

will display:

    [a, b, c, [1, 2, 3]]

### List Operations

You can use the at command to retrieve a specific value from within a list.
The at command takes both a list and a number specifying the position,
or the index,
of the value you want to retrieve.

For example, running:

    Use: Standard
    
    my list: List: a, b, c

    Log Line: At: my list, 1
    Log Line: At: my list, 2
    Log Line: At: my list, 3

will display:

    1
    2
    3

You can use the set command to replace a value in a list.
The set command takes a list, a number specifying the position,
or the index,
of the value you want to change,
and a value to place at that location.

For example, running:

    Use: Standard
    
    my list: List: a, b, c

    Log Line: my list
    Set: my list, 2, d
    Log Line: my list

will display:

    [a, b, c]
    [a, d, c]

You can use the insert command to place a value in a list without overriding anything.
The set command takes a list, a number specifying the position,
or the index,
of where you want to insert your value,
and a value to insert at that location.

For example, running:

    Use: Standard
    
    my list: List: a, b, c

    Log Line: my list
    Insert: my list, 2, d
    Log Line: my list

will display:

    [a, b, c]
    [a, d, b, c]

You can use the remove command to remove a value from a list.
The remove command takes a list and a number specifying the position,
or the index,
of of the value you want to remove.

For example, running:

    Use: Standard
    
    my list: List: a, b, c

    Log Line: my list
    Remove: my list, 2
    Log Line: my list

will display:

    [a, b, c]
    [a, c]

_Note: In Kaeon FUSION, list indexes start at one. However, in most other languages, they start at zero._

## 9 - Functions

A function is a chunk of code that can be stored and reused throught the program.
Every time you run a function,
you can give it information called arguments,
and after it finishes running the function can return a value.
In other words,
a making a function is like making a command out of other commands.

Functions are like variables,
in that they have an alias to identify them.
In addition,
the same scope rules that apply to variables apply to functions.

The Kaeon FUSION Standard Interface allows you to create functions using the define command.
Every child of the define command will be made into a function within the local scope,
with said child being the alias of the function and the children of the child making up the function itself.

For example:

    Use: Standard
    
    Define

    	foo

    		Log Line: abc
    		Log Line: 123

    	bar

    		#[ Because what we're printing is the same as the name of our function,
    		   we surround it in quotation marks. Otherwise, we'd be running the
    		   function from within itself, which would create an infinite loop. ]#

    		Log Line: "bar"

_Note: When demonstrating functions to newcomers, it is tradition to name the functions "foo", "bar", and "baz"._

Once a function is defined,
it may be used by using its alias as a command.

For example, running:

    Use: Standard
    
    Define

    	foo

    		Log Line: abc
    		Log Line: 123

    	bar

    		Log Line: "bar"

    foo
    foo
    bar

would display:

    abc
    123
    abc
    123
    bar

Any values returned to the alias by its children will be passed as arguments to the function.
Said arguments may be accessed from within the function as a list using the arguments command.

For example, running:

    Use: Standard
    
    Define

    	foo

    		Log Line: At { Arguments, 2 }, At { Arguments, 1 }

    foo: abc, 123

would display:

    123abc

You can make a function return a value by using the return command within it,
and nesting any value you want to return beneath the return command.
Once a return command is used within a function,
the function will stop.

For example, running:

    Use: Standard
    
    Define

    	foo

    		Log Line: At { Arguments, 2 }, At { Arguments, 1 }

    	bar
    		Return: foobar

    foo: abc, bar

would display:

    foobarabc

<!--

### Recursion

-->

## 10 - Objects

After running a function,
the variable and other functions declared within it may be stored and reused.
The stored state of the function is called an object.

Objects are a useful way of abstracting data.
For example,
we could have a function called "Dog" that declares variables analogous to the attributes of a dog,
like name,
age,
weight,
etc,
and it could have functions within it that relect things that dogs do,
like eat,
bark,
etc that can be affected by the variables.

If a function is called from within a new command,
the new command will return its state which can be stored inside a variable.
Its state can be brought into the local scope by nesting it within an in command.
This should always be done within a scope command.
The return command can be used after an in command to return something from within an object to the scope outside the object.
If being used as an object,
all variables in the function should be marked as global.

For example, running:

    Use: Standard

    Define: dog

    	Global: name: At: Arguments, 1
    	Global: age: 1
    	Global: weight: 5
    
    	Define: eat
    
    		weight: Add: weight, At: Arguments, 1
    	
    	Define: bark
    
    		Return: woof woof
    
    my dog: New: dog: Fiddo
    my other dog: New: dog: Fluffy

    food: 1

    Scope: In { my Dog } Log Line: name, " goes ", bark, .
    Scope: In { my Dog } eat: food
    Scope: In { my dog } Log Line: name, " weighs ", weight, " pounds."
    Scope: In { my dog } Log: name; Log: " is friends with "; In { my other dog } Log: name, .;

would display:

    Fiddo goes woof woof.
    Fiddo weighs 6 pounds.
    Fiddo is friends with Fluffy.

_Note: Most programming languages don't allow you to make objects out of functions. They have an entirely seperate convention called classes that allow you to define objects._

<!--

### Shallow Copy vs Deep Copy

-->

## 11 - Errors

If you give a command data is isn't programmed to handle,
something that would cause most other programming languages to crash,
the Kaeon FUSION Standard Interface will stop running any commands after that,
unless you use a catch command,
which will allow the program to keep moving normally.
Any commands nested within a catch command will only execute if the respective catch command was triggered.

For example, running:

    Use: Standard

    Log Line: Add: bad input
    Log Line: abc

    Catch: Log Line: Something went wrong.

    Log Line: 123

    Catch: Log Line: Something went wrong.

will display:

    Something went wrong.
    123