# Kaeon FUSION Standard Interface

The Kaeon FUSION Standard Interface provides commands for console IO,
arithmetic,
logic,
list operations,
string operations,
control flow,
file IO,
and multithreadhing.

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

## Commands

### Console IO

#### Log

The Log command will print every value returned by its children to the console in order.

For example:

    Log: a, b, c
    Log: x, y, z

will log:

    abcxyz

to the console.

#### Log Line

The Log Line command will print every value returned by its children to the console in order,
plus a new line character.

For example:

    Log Line: a, b, c
    Log Line: x, y, z

will log:

    abc
    xyz
    

to the console.

#### Input

The Input command will print every value returned by its children to the console in order,
prompt the user for string input,
and return the given response.

For example:

    x: Input: "Enter a number: "

if the user types in "5" at the prompt,
the value "5" will be assigned to the variable x.

### Arithmetic

#### Add

The Add command takes two string values in the form of numbers.
It returns the sum of the two numbers as a string.

For example:

    Add: 1, 2

will return the value "3".

#### Subtract

The Subtract command takes two string values in the form of numbers.
It returns the difference of the two numbers as a string.

For example:

    Subtract: 2, 1

will return the value "1".

#### Multiply

The Multiply command takes two string values in the form of numbers.
It returns the product of the two numbers as a string.

For example:

    Multiply: 5, 2

will return the value "10".

#### Divide

The Divide command takes two string values in the form of numbers.
It returns the quotient of the two numbers as a string.

For example:

    Divide: 10, 2

will return the value "5".

#### Modulus

The Modulus command takes two string values in the form of numbers.
It returns the remainder of the two numbers as a string.

For example:

    Modulus: 10, 3

will return the value "1".

### Logic

#### And

The And command takes two boolean strings and returns "True" if they are both "True",
and returns "False" otherwise.

For example:

    And: True, True

will return "True", and:

    And: True, False

will return "False".

#### Or

The Or command takes two boolean strings and returns "True" if either of them are "True",
and returns "False" otherwise.

For example:

    Or: True, False

will return "True", and:

    Or: False, False

will return "False".

#### Exclusive Or

The Exclusive Or command takes two boolean strings and returns "True" if only one of them is "True",
and returns "False" otherwise.

For example:

    Exclusive Or: True, False

will return "True", and:

    Exclusive Or: True, True

will return "False".

#### Not

The Not command takes one boolean string and returns the opposite of its input.

For example:

    Not: False

will return "True", and:

    Not: True

will return "False".

#### Equal

The Equal command takes two strings and returns "True" if they are both the same,
and returns "False" otherwise.

For example:

    Equal: hello, hello

will return "True", and:

    Equal: hello, goodbye

will return "False".

#### Greater

The Greater command takes two number strings and returns "True" if the first number is greater than the second,
and returns "False" otherwise.

For example:

    Greater: 2, 1

will return "True", and:

    Greater: 1, 2

will return "False".

#### Greater or Equal

The Greater command takes two number strings and returns "True" if the first number is greater than or equal to the second,
and returns "False" otherwise.

For example:

    Greater or Equal: 2, 1

will return "True", and:

    Greater or Equal: 1, 2

will return "False".

#### Less

The Less command takes two number strings and returns "True" if the first number is less than the second,
and returns "False" otherwise.

For example:

    Less: 1, 2

will return "True", and:

    Less: 2, 1

will return "False".

#### Less or Equal

The Less command takes two number strings and returns "True" if the first number is less than or equal to the second,
and returns "False" otherwise.

For example:

    Less or Equal: 1, 2

will return "True", and:

    Less or Equal: 2, 1

will return "False".

### List Operations

Indexes for list operations in the Kaeon FUSION Standard Interface start at 1.

#### List

The List command places all of the values returned by its children into a list and returns it.

For example:

    List: 1, 2, 3

will return the list [1, 2, 3].

#### Size

The Size command takes a list and returns the size of it.

For example:

    Size: List: 1, 2, 3

will return "3".

#### At

The At command takes a list and an integer and returns the value in the list at the given integer.

For example:

    At: List { 4, 5, 6 } 2

will return "5".

#### Append

The Append command takes a list and a value and appends the value to the list.

For example:

    my list: List: 1, 2, 3
    Append: my list, 4

will append "4" to my list, resulting in my list's content being [1, 2, 3, 4].

#### Set

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

#### Insert

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

#### Remove

The Remove command takes a list and an integer.
It removes the value in the list at the specified integer and returns the value.

For example:

    my list: List: 1, 2, 3
    x: Remove: my list, 2

will remove "2" from my list,
resulting in my list's content being [1, 3],
and assign "2" to x.