[Kaeon FUSION Home](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/README.md)

# Why use Kaeon FUSION?

## Why is Kaeon FUSION called "the first ever universal programming language"?

Admittedly,
our slogan: "the first ever universal programming language",
is more a marketting strategy than a technical descriptor,
but we do indeed stand behind it.

We call Kaeon FUSION a universal programming language because the interpreter can alter itself at runtime to take on new functionality,
and because the tree based structure of Kaeon FUSION allows it to be cross compiled into any other language.
We call it the "first ever",
because to our knowledge no other language posseses these features to the degree that Kaeon FUSION does.

To put it another way,
one might say that Kaeon FUSION is,
in essence,
what LISP should have been.

LISP provides an environment upon which any sort of language can be defined to suit just about any purpose,
thus making it a popular platform for domain specific languages,
or DSLs.
However,
you can't necessarily have two LISP DSLs coexist in the same environment.
For example,
you can't have Common LISP code,
Scheme code,
and Clojure code interwoven between each other without some sort of crazy hack.

In Kaeon FUSION,
every DSL comes packaged as an interface,
which instead of defining the rules at the start of runtime,
alters the rules mid runtime.
There is also no prohibition on using mutiple interfaces at once.

In this sense,
one could think of Kaeon FUSION as the smart phone of languages,
a blank slate whose functionality is defined by the apps,
or interfaces,
that are installed on it.
In this analogy,
LISP would be like a smart phone that could only have one app installed at a time.

To summarize:
in LISP,
DSLs become seperate languages that are not necessarily compatible with one another,
but in Kaeon FUSION,
they become plugins for a single langauge and are always compatible with one another.

And thus Kaeon FUSION can unite all development tasks under a single environment and a single syntax,
becoming a "universal" language.

So that begs the question,
why didn't we build Kaeon FUSION on LISP syntax?
As we mentioned [in our FAQ section](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/README.md#is-kaeon-fusion-a-lisp),
Kaeon FUSION is not technically a LISP because it stores data as a doubly linked tree instead of a singly linked list,
and so we required a syntax suited to trees rather than lists.
Additionally,
and this might be subjective,
we found the syntax of (L(I(S(P)))) to be rather... unaesthetic.

## What can Kaeon FUSION do that other languages cannot?

Often when we present Kaeon FUSION,
we are asked what it brings to the table.

Most successful programming languages were created to provide functionality that did not exist in other languages at the time of their creation,
such as Java with the JVM,
JavaScript with the ability to run natively in the browser,
C with the ability to efficiently compile to assembly for almost any system,
etc.

Kaeon FUSION,
being the blank slate that it is,
does not provide any features that do not currently exist in other languages.
Rather,
it establishes a platform upon which the features of all currently existing languages can be transferred to and coexist within,
and upon which novel functionality can be implemented in the future,
foregoing the need to engineer a whole new language from scratch to support said functionality.

Other languages run into roadblocks when attemping to continually integrate new functionality as they attempt to stay relevant,
leading to their eventual replacement.
Kaeon FUSION,
by design,
will never face such an issue.

That said,
in order to push such a platform,
it's ideal to ship it with some sort of "killer app".
At the moment,
we are in the process of developing an interface called the Stack interface that will allow Kaeon FUSION code to be cross compiled into C,
C++,
Java,
C#,
JavaScript,
PHP,
Python,
Swift,
XML,
JSON,
HTML,
CSS,
and SQL.
The XML,
JSON,
HTML,
CSS,
and SQL cross compilation will require code to be written using unique dialects,
but code for the rest of the available languages may be written as if it were being written for the standard interface,
and flags in the form of meta commands may be used to activate features specific to certain languages such as strong typing.
Thus,
the stack interface will make it possible to develop almost anything with Kaeon FUSION alone.
We are also in the process of designing a ONE based file format called [Kaeon ACE](https://github.com/Gallery-of-Kaeon/Kaeon-ACE/blob/master/README.md) that will serve as an interchange format for game engines,
a Kaeon FUSION interface for building the format to various game engines,
and a custom game engine designed to handle the format natively.