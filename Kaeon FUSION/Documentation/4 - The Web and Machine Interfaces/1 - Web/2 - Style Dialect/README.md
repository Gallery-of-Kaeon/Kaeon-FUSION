[Home](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/tree/master/Kaeon%20FUSION/Documentation) /
[Back](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/tree/master/Kaeon%20FUSION/Documentation/4%20-%20The%20Web%20and%20Machine%20Interfaces/1%20-%20Web) /
[Previous](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/tree/master/Kaeon%20FUSION/Documentation/4%20-%20The%20Web%20and%20Machine%20Interfaces/1%20-%20Web/1%20-%20Page%20Dialect) /
[Next](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/tree/master/Kaeon%20FUSION/Documentation/4%20-%20The%20Web%20and%20Machine%20Interfaces/1%20-%20Web/3%20-%20Script%20Dialect)

# Style Dialect

The Style dialect is a dialect of ONE analogous to CSS.

## Styling

To style an item within a style block,
use an element containing the item to be styled,
with a child containing the value to style it with.

There is no conversion between the Style dialect and the items that can be styled.

For example:

    color: blue

is analogous to the following CSS:

    color: blue;

## Tag Styling

The Style dialect recognizes the same keywords as tags that the page dialect does.

To style a tag,
use an element with the name of the tag and nest within it items to be styled.

For example:

    Division

    	height: 100px
    	width: 100px
    	color: blue

is analogous to the following CSS:

    div {
    	height: 100px;
    	width: 100px;
    	color: blue;
    }

## ID Styling

To style an ID,
use an element with the content "On" with a child containing the ID, and nest within ithe child the items to be styled.

For example:

    On: my_id

    	height: 100px
    	width: 100px
    	color: blue

is analogous to the following CSS:

    #my_id {
    	height: 100px;
    	width: 100px;
    	color: blue;
    }

## Class Styling

To style an class,
use an element with the content "For" with a child containing the class, and nest within ithe child the items to be styled.

For example:

    For: my_class

    	height: 100px
    	width: 100px
    	color: blue

is analogous to the following CSS:

    .my_class {
    	height: 100px;
    	width: 100px;
    	color: blue;
    }