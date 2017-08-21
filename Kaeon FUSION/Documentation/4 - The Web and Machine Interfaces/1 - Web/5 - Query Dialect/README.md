# Query Dialect

The Query dialect is a dialect of ONE analogous to SQL.

## Queries

Query elements are listed from the inside out,
and their children act as their contents.

For example:

    Where: condition
    Select: *

is anaologous to the following SQL:

    SELECT * WHERE condition;

## Scope

Queries can be isolated from one another by nesting them within a scope command.

For example:

    Scope

    	Where: condition_1
    	Select: name_1

    Scope

    	Where: condition_2
    	Select: name_2

is analogous to the following SQL:

    SELECT name_1 WHERE condition_1;
    SELECT name_2 WHERE condition_2;

## Split

Queries can be split using the split command.

For example:

    Where: condition

    Split
    	Select: name_1

    Split
    	Select: name_2

is analogous to the following SQL:

    SELECT name_1 WHERE condition;
    SELECT name_2 WHERE condition;