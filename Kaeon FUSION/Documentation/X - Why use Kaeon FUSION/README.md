# Why use Kaeon FUSION?

## Why is Kaeon FUSION called "the first universal programming language"?

Okay, skeptic.
You saw through the marketing jargon and you want a more down to earth explanation.
Very well.

Kaeon FUSION does aim to be universal,
and it does this by being what LISP should have been.
What do we mean by this?

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
Because in our humble opinion,
a doubly linked tree structure makes more sense than a singly linked list structure.
Also, (L(I(S(P)))) is ugly.

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

Other languages run into roadblocks when attemping to continually integrate new functionality as they attempt to stay relavent,
leading to their eventual replacement.
Kaeon FUSION,
by design,
will never face such an issue.

That said,
in order to push such a platform,
it's ideal to ship it with some sort of "killer app".
At the moment,
we are in the process of developing an interface called the Stack interface that will allow Kaeon FUSION code from the standard interface to be cross compiled into C,
C++,
Java,
C#,
JavaScript,
Python,
Swift,
HTML,
CSS,
and SQL,
making it possible to develop almost anything with Kaeon FUSION alone.
We are also in the process of designing a ONE based file format called [Kaeon ACE](https://github.com/Gallery-of-Kaeon/Kaeon-ACE/blob/master/README.md) that will serve as an interchange format for game engines,
a Kaeon FUSION interface for building the format to various game engines,
and on a custom game engine desined to handle the format natively.