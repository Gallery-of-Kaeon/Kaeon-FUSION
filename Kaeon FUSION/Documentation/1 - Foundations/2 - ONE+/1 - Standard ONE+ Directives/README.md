[Home](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/tree/master/Kaeon%20FUSION/Documentation/README.md) /
[Back](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/tree/master/Kaeon%20FUSION/Documentation/1%20-%20Foundations/2%20-%20ONE%2B/README.md) /
[Previous](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/tree/master/Kaeon%20FUSION/Documentation/1%20-%20Foundations/2%20-%20ONE%2B/README.md) /
[Next](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/tree/master/Kaeon%20FUSION/Documentation/1%20-%20Foundations/3%20-%20FUSION/README.md)

# Standard ONE+ Directives

The standard ONE+ directives come with our implementation of Kaeon FUSION.
They are packaged in a module with the alias "Standard",
and may be used after using the following directive call:

    [USE: Standard]

## DEFINE

The define directive allows a series of elements to be stored and reused.

The directive call contains the content "DEFINE".
The directive call's header contains a single element specifying the alias of the defined elements,
and the defined elements are located within the directive call's body.

For example:

    [USE: Standard]

    [DEFINE: List]
    	
    	abc
    	xyz
    	123

## CALL

The call directive allows a series of elements defined within a using a define directive to be pasted elsewhere.

The directive call contains the content "CALL".
The directive call's header contains a single element specifying the alias of a set of elements defined by a define directive call.

For example:

    [USE: Standard]

    [DEFINE: List]
    	
    	abc
    	xyz
    	123
    
    [CALL: List]
    [CALL: List]

is analogous to the following ONE+:

    abc
    xyz
    123
    abc
    xyz
    123

## IMPORT

The import directive allows several series of elements defined in a ONE+ document using the define directive to be used in another document.

The directive call contains the content "IMPORT".
Within the directive call's header is are elements containing the filepath of a referenced ONE+ document.

When parsing the referenced files,
the parser will use the standard ONE+ directive implicitly.

For example, if the file "My Code.op" contained the following code:

    [DEFINE: List]
    	
    	abc
    	xyz
    	123

and if the file "More Code.op" contained the following code:

    [DEFINE: Set]
    	
    	def
    	456
    	789

then:

    [USE: Standard]

    [IMPORT: My File.op, More Code.op]
    
    [CALL: List]
    [CALL: Set]

is analogous to the following ONE+:

    abc
    xyz
    123
    def
    456
    789
    
## FOR

The for directive allows the same elements to be repeated multiple times.

The directive call contains the content "FOR".
The directive call's header will either have one or two elements each containing integer numbers.

If the header has one element,
the content will be pasted for as many times as is specified by the number.

If the header has two elements,
the content will be pasted for as many times as is specified by the second number minus the first number plus one.

For example:

    [USE: Standard]
    
    [FOR: 3]
    	abc, 123
    
    [FOR: 6, 7]
    	456, 789
    
is analogous to the following ONE+:

    abc
    123
    abc
    123
    abc
    123
    456
    789
    456
    789
	
# INDEX

The index directive may be nested within the body of a for directive to place they index of the loop in the document's content.

The directive call contains the content "INDEX".

For example:
    
    [USE: Standard]
    
    [FOR: 3]
    	[INDEX]
    
    [FOR: 6, 7]
    	[INDEX]
    
is analogous to the following ONE+:

    0
    1
    2
    5
    6

## IF

The if directive can be used to conditionally place content within a document.

The directive call contains the content "IF".

If the content of all of the elements in the directive call's header match,
then all of the elements in its body will be placed into the document.

For example:

    [USE: Standard]
    
    [IF: abc, abc]
    	def
    
    [IF: 123, 456]
    	789
    
is analogous to the following ONE+:

    def

<div align="right"><p>

<a href="https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/tree/master/Kaeon%20FUSION/Documentation/README.md">Home</a> / 
<a href="https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/tree/master/Kaeon%20FUSION/Documentation/1%20-%20Foundations/2%20-%20ONE%2B/README.md">Back</a> / 
<a href="https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/tree/master/Kaeon%20FUSION/Documentation/1%20-%20Foundations/2%20-%20ONE%2B/README.md">Previous</a> / 
<a href="https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/tree/master/Kaeon%20FUSION/Documentation/1%20-%20Foundations/3%20-%20FUSION/README.md">Next</a>

</p></div>