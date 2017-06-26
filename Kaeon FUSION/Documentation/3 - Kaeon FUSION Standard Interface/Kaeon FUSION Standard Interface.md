# Kaeon FUSION Standard Interface

The Kaeon FUSION Standard Interface provides commands for console IO,
arithmetic,
logic,
list operations,
string operations,
file IO,
flow control,
threading,
metaprogramming,
and object orientation.

As such, one might consider the functionality it provides to be the flagship functionality of Kaeon FUSION.

The interface is activated using the following command:

    Use: Standard

## Data Types

Certain types of string literals can function as primitives for certain standard interface commands.

### Numbers

A number string must consist entirely of digits and no more than one period.
If the period is present, it must not be the last character in the string.

### Booleans

A boolean string may either take the form of "True" or "False".
Case is irrelevant.

### Null

Running the command "Null" returns a null value.

## Indexes

Indexes for list and string operations in the Kaeon FUSION Standard Interface start at 1.

## Console IO Commands

### Log

The Log command will print every value returned by its children to the console in order.

For example:

    Log: a, b, c
    Log: x, y, z

will log:

    abcxyz

to the console.

### Log Line

The Log Line command will print every value returned by its children to the console in order,
plus a new line character.

For example:

    Log Line: a, b, c
    Log Line: x, y, z

will log:

    abc
    xyz
    

to the console.

### Input

The Input command will print every value returned by its children to the console in order,
prompt the user for string input,
and return the given response.

For example:

    x: Input: "Enter a number: "

if the user types in "5" at the prompt,
the value "5" will be assigned to the variable x.

## Arithmetic Commands

### Add

The Add command takes two string values in the form of numbers.
It returns the sum of the two numbers as a string.

For example:

    Add: 1, 2

will return the value "3".

### Subtract

The Subtract command takes two string values in the form of numbers.
It returns the difference of the two numbers as a string.

For example:

    Subtract: 2, 1

will return the value "1".

### Multiply

The Multiply command takes two string values in the form of numbers.
It returns the product of the two numbers as a string.

For example:

    Multiply: 5, 2

will return the value "10".

### Divide

The Divide command takes two string values in the form of numbers.
It returns the quotient of the two numbers as a string.

For example:

    Divide: 10, 2

will return the value "5".

### Modulus

The Modulus command takes two string values in the form of numbers.
It returns the remainder of the two numbers as a string.

For example:

    Modulus: 10, 3

will return the value "1".

## Logic Commands

### And

The And command takes two boolean strings and returns "True" if they are both "True",
and returns "False" otherwise.

For example:

    And: True, True

will return "True", and:

    And: True, False

will return "False".

### Or

The Or command takes two boolean strings and returns "True" if either of them are "True",
and returns "False" otherwise.

For example:

    Or: True, False

will return "True", and:

    Or: False, False

will return "False".

### Exclusive Or

The Exclusive Or command takes two boolean strings and returns "True" if only one of them is "True",
and returns "False" otherwise.

For example:

    Exclusive Or: True, False

will return "True", and:

    Exclusive Or: True, True

will return "False".

### Not

The Not command takes one boolean string and returns the opposite of its input.

For example:

    Not: False

will return "True", and:

    Not: True

will return "False".

### Equal

The Equal command takes two strings and returns "True" if they are both the same,
and returns "False" otherwise.

For example:

    Equal: hello, hello

will return "True", and:

    Equal: hello, goodbye

will return "False".

### Greater

The Greater command takes two number strings and returns "True" if the first number is greater than the second,
and returns "False" otherwise.

For example:

    Greater: 2, 1

will return "True", and:

    Greater: 1, 2

will return "False".

### Greater or Equal

The Greater command takes two number strings and returns "True" if the first number is greater than or equal to the second,
and returns "False" otherwise.

For example:

    Greater or Equal: 2, 1

will return "True", and:

    Greater or Equal: 1, 2

will return "False".

### Less

The Less command takes two number strings and returns "True" if the first number is less than the second,
and returns "False" otherwise.

For example:

    Less: 1, 2

will return "True", and:

    Less: 2, 1

will return "False".

### Less or Equal

The Less command takes two number strings and returns "True" if the first number is less than or equal to the second,
and returns "False" otherwise.

For example:

    Less or Equal: 1, 2

will return "True", and:

    Less or Equal: 2, 1

will return "False".

## List Operation Commands

### List

The List command places all of the values returned by its children into a list and returns it.

For example:

    List: 1, 2, 3

will return the list [1, 2, 3].

### Size

The Size command takes a list and returns the size of it.

For example:

    Size: List: 1, 2, 3

will return "3".

### At

The At command takes a list and an integer and returns the value in the list at the given integer.

For example:

    At: List { 4, 5, 6 } 2

will return "5".

### Append

The Append command takes a list and a value and appends the value to the list.

For example:

    my list: List: 1, 2, 3
    Append: my list, 4

will append "4" to my list, resulting in my list's content being [1, 2, 3, 4].

### Set

The Set command takes a list,
an integer,
and a value.
It sets the value of the list at the index specified my the integer to the given value.
If the index is greater than the langht of the list,
null values will be appended to the list until it reaches the rquired size.

For example:

    my list: List: 1, 2, 3
    Set: my list, 2, 4

will replace "2" with "4" in my list,
resulting in my list's content being [1, 4, 3],
and:

    my list: List: 1, 2, 3
    Set: my list, 5, 4

will place a null value at position 4 and append "4" to my list,
resulting in my list's content being [1, 2, 3, null, 4].

### Insert

The Insert command takes a list,
an integer,
and a value.
It appends the given value to the list at the index specified my the integer.
If the index is greater than the langht of the list,
null values will be appended to the list until it reaches the rquired size.

For example:

    my list: List: 1, 2, 3
    Insert: my list, 2, 4

will insert "4" at index 2 in my list,
resulting in my list's content being [1, 4, 2, 3],
and:

    my list: List: 1, 2, 3
    Insert: my list, 5, 4

will place a null value at position 4 and append "4" to my list,
resulting in my list's content being [1, 2, 3, null, 4].

### Remove

The Remove command takes a list and an integer.
It removes the value in the list at the specified integer and returns the value.

For example:

    my list: List: 1, 2, 3
    x: Remove: my list, 2

will remove "2" from my list,
resulting in my list's content being [1, 3],
and assign "2" to x.

## String Operation Commands

### Length

The Length command takes a string and returns its length.

For example:

    Length: hello

will return "5".

### Concatenate

The Concatenate command takes multiple strings,
concatenates them,
and returns the concatenated string. 

For example:

    Length: abc, xyz, 123

will return "abcxyz123".

### Character

The Character command takes a string and an integer and returns the character in the string at the index specified by the integer.

For example:

    Character: abc, 2

will return "b".

### Character

The Character command takes a string and two integers and returns the sequence of characters in the string from the index specified by the first integer to the index specified by the second integer.

For example:

    Character: hello, 3, 5

will return "llo".

## File IO Commands

### Open

The Open command takes a string and returns the contents of the file located at the path specified by the string.

For example, if you have a file called "My File.txt" and it contains the following content:

    abc
    xyz
    123

then:

    Open: My File.txt

will return "abc\nxyz\n123".

### Save

The Save command takes two string arguments.
It writes the contents of the first dtring to the file at the path specified by the second string.

For example:

    Save: hello, My File.txt

will create a file called "My File.txt" with the content "hello".

## Flow Control Commands

### Scope

The Scope command perfoms no operations.
It merely serves to establish an isolated scope in which its child commands proceed to execute.

For example:

    Scope
    
    	# Code

### Break

The Break command stops the execution of all commands in its scope and causes FUSION to bubble up.
It has the option of take a boolean as an argument.
If the boolean value is "False" the Break command will not take effect.

For example in:
    
    Scope { Break }
    	Log Line: Success

or:

    x: hello
    
    Scope { Break: Equal: x, hello }
    	Log Line: Success

nothing will print to the console.

### Else

An Else command will only allow its child commands to execute if the most recently used Break command used since the most recently used Else command activated.

For example:

    x: hello
    
    Scope { Break: Not: Equal: x, hello }
    	Log Line: Success
    
    Else
    	Log Line: Failure

will log "Success" to the console, and:

    x: hello
    
    Scope { Break: Equal: x, hello }
    	Log Line: Success
    
    Else
    	Log Line: Failure

will log "Failure" to the console.

### Loop

The Loop command jumps to the first child command of its parent command.
It has the option of take a boolean as an argument.
If the boolean value is "False" the Break command will not take effect.

For example:

    i { 0 } Scope
    
    	Log Line: i
    
    	i: Add: i, 1
    	Loop: Less: i, 10

will print:

    0
    1
    2
    3
    4
    5
    6
    7
    8
    9

to the console.

### Throw

The Throw command will automaticalliy throw an exception.

For example:

    Log Line: abc
    Throw
    Log Line: xyz
    Catch: Log Line: Oops
    Log Line: 123

will print:

    abc
    Oops
    123

to the console.

### Exit

The Exit command will immediately stop the execution of Kaeon FUSION.

## Threading Commands

### Split

The Split command will create execute its children on a new thread.

For example:

    Split
    
    	i { 0 } Scope

    		Log Line: i

    		i: Add: i, 1
    		Loop: Less: i, 5
    
    i { 5 } Scope

    	Log Line: i

    	i: Add: i, 1
    	Loop: Less: i, 10

will print:

    0
    5
    1
    6
    2
    7
    2
    8
    4
    9

to the console.

### Wait

The wait command will take a number a pause the current thread for that many seconds.

## Metaprogramming Commands

### Execute

The Execute command takes a string and executes it as Kaeon FUSION code.

For example, if we have a file called "My Code.op" with the content:

    Log Line: Hello

then:

    Execute: Open: My Code.op

will print "Hello" to the console.

### Run

The Run command takes a string and passes it to the command line.

On windows,
you can open up notepad with the following Kaeon FUSION code:

    Run: Notepad

## Object Orientation

Kaeon FUSION does not support classes,
but object orientation can be achieved by using functions as objects.

The state of the function can be stored within a function and reused using the In command.

The In command will take the stored state of a function and execute all of the commands following it within the stored state.
For this reason,
the In command should always be nested inside another command.
The In command will also return a value if the Return command is used within it.

For example:

    Define: foo
        
    	x: At: Arguments, 1
    	y: 10

    my foo: foo: 5

    Scope { In: my foo }
    	z: Add: x, y

    Log Line: In { my foo } Return: z

will print "15" to the console.