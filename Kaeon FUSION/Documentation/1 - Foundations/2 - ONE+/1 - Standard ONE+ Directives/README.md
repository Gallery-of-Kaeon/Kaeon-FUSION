[Home](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/tree/master/Kaeon%20FUSION/Documentation/README.md) /
[Back](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/tree/master/Kaeon%20FUSION/Documentation/1%20-%20Foundations/2%20-%20ONE%2B/README.md) /
[Previous](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/tree/master/Kaeon%20FUSION/Documentation/1%20-%20Foundations/2%20-%20ONE%2B/README.md) /
[Next](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/tree/master/Kaeon%20FUSION/Documentation/1%20-%20Foundations/3%20-%20FUSION/README.md)

# Standard ONE+ Directives

The directives usable when writing ONE+ vary by parser implementation,
but the directives described in the document should be available in the majority of implementations.

## DEFINE

The define directive allows a series of elements to be stored and reused.

The define directive begins with the element "DEFINE".
Nested within this element is the name of the defined elements.

Nested beneath the directive are the defined elements.

For example:

    [DEFINE: List]
    	
    	abc
    	xyz
    	123

## USE

The use directive allows a series of elements defined within a using a define directive to be pasted elsewhere.

The use directive begins with the element "USE".
Nested within this element is the name of the defined elements.

For example:

    [DEFINE: List]
    	
    	abc
    	xyz
    	123
    
    [USE: List]
    [USE: List]

is analogous to the following ONE+:

    abc
    xyz
    123
    abc
    xyz
    123

## IMPORT

The import directive allows several series of elements defined in a ONE+ document using the define directive to be used in another document.

The define directive begins with the element "IMPORT".
Nested within this element is the filepath of the referenced ONE+ document.

For example, if the file "My Code.op" contained the following code:

    [DEFINE: List]
    	
    	abc
    	xyz
    	123

then:

    [IMPORT: My File.op]
    
    [USE: List]
    [USE: List]

is analogous to the following ONE+:

    abc
    xyz
    123
    abc
    xyz
    123

<div align="right">

<a href="https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/tree/master/Kaeon%20FUSION/Documentation/README.md">Home</a><p> / </p>
<a href="https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/tree/master/Kaeon%20FUSION/Documentation/1%20-%20Foundations/2%20-%20ONE%2B/README.md">Back</a><p> / </p>
<a href="https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/tree/master/Kaeon%20FUSION/Documentation/1%20-%20Foundations/2%20-%20ONE%2B/README.md">Previous</a><p> / </p>
<a href="https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/tree/master/Kaeon%20FUSION/Documentation/1%20-%20Foundations/3%20-%20FUSION/README.md">Next</a>

</div>