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

is analogous to the following ONE:

    abc
    xyz
    123
    abc
    xyz
    123

## IMPORT

The import directive allows serveral series of elements defined in a ONE+ document using the define directive to be used in another document.

The define directive begins with the element "IMPORT".
Nested within this element is the filepath of the referrence ONE+ document.

For example, if the file "My Code.op" contained the following code:

    [DEFINE: List]
    	
    	abc
    	xyz
    	123

then:

    [IMPORT: My File.op]
    
    [USE: List]
    [USE: List]

is analogous to the following ONE:

    abc
    xyz
    123
    abc
    xyz
    123