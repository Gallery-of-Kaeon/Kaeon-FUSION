[Home](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/tree/master/Kaeon%20FUSION/Documentation/README.md) /
[Back](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/tree/master/Kaeon%20FUSION/Documentation/4%20-%20The%20Web%20and%20Machine%20Interfaces/1%20-%20Web/README.md) /
[Previous](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/tree/master/Kaeon%20FUSION/Documentation/4%20-%20The%20Web%20and%20Machine%20Interfaces/1%20-%20Web/2%20-%20Style%20Dialect/README.md) /
[Next](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/tree/master/Kaeon%20FUSION/Documentation/4%20-%20The%20Web%20and%20Machine%20Interfaces/1%20-%20Web/4%20-%20Process%20Dialect/README.md)

# Script Dialect

The Script dialect is a dialect of ONE ananlogous to JavaScript.

## Literals

An element that has no children and whose content does does match any predifined alias or keyword will be interpreted as a literal.
Any literal that is not in the form of a number or a boolean will be automatically assumed to be a string literal.

For example:

    123
    abc
    true

is analogous to the following JavaScript:

    123;
    "abc";
    true;

## Variables

Variables can be declared the same way they are declared in Kaeon FUSION.

For example:

    x: 0
    Global: y: abc

is analogous to the following JavaScript:

    var x = 0;
    y = "abc";

## Math and Logic Operations

Math and logic operations work the same way they do in Kaeon FUSION.

For example:

    Add: 1, 2
    Add: 1, Divide: 10, 2
    Greater: 2, 1
			
is analogous to the following JavaScript:
				
    (1 + 2);
    (1 + (10 / 2));
    (2 > 1);

## Lists

List operations work the same way they do in Kaeon FUSION.

For example:

    x: List: abc, 123, true
    y: At: x, 1
    Set: List, 1, xyz

is analogous to the following JavaScript:

    var x = {"abc", 123, true};
    var y = x[(1) - 1]
    x[(1) - 1] = "xyz";

## Functions

If an element references another element contianing Script dialect code,
the reference element will be converted to a function that takes a list of objects as its only argument.

The return command may be used withing such a function the same way it is used in Kaeon FUSION.

For example:

    foo: Return: Add
    	At: Arguments, 1
    	At: Arguments, 2

is analogous to the following JavaScript:

    function foo(arguments) {
    	return arguments[1] + arguments[2];
    }

If the foo function from the previous example is visible,
it can be called using the following command:

    foo: arg1, arg2, etc

which is anaolgous to the following JavaScript:

    foo({"arg1", "arg2", "etc"});

To call a function defined by the host environment,
nest an element with the value "Scope" within an in command which itself is nested within a scope command.

For example:

    Scope { In: Default }
    	alert: Hello

is analogous to the following JavaScript:

    alert("Hello");

## Flow Control

Flow control works the same way in the Script dialect as it does in Kaeon FUSION.

However,
else commands will only work if the corresponding break command is the first child of its scope,
and break and loop commands will only work inside of scope commands.

For example:

    Scope { Break: true }
    	# code

    Else:
    	# code

    Scope

    	# code

    	Loop: false
    	Break: true

is analogous to the following JavaScript:

    if(true) {
    	// code
    }

    else {
    	// code
    }

    do {

    	// code

    	if(false)
    		continue;

    	if(true)
    		break;
    } while(false);

## Objects

To work within the scope of an object,
nest the name of the object within an in command which itself is nested within a scope command.

Once in the scope of an object,
you can call a function using an element with the name of the function,
or return a value using the return command.

For example:

    Scope { In: console }
    	log: Hello

    x: Scope { In: obj } Return: val

is analogous to the following JavaScript:

    console.log("Hello");
    var x = obj.val;