# Kaeon FUSION

Kaeon FUSION is the very first programming language created soley to work in place of any other programming language.
It allows this by using a syntax called ONE+ that can generate any abstract syntax tree and by allowing the functionality of the language to be encoded into interchangable modules which can be incorporated at runtime,
thereby making the functionality of the language indefinitely extensible.

Kaeon FUSION has sucessfully been used for general purspose programming,
scripting,
low level programming,
and web development.
In theory,
it can be extended to work with any domain,
and is particularly useful for metaprogramming.

## Contents of this repository

This repository contains documentation, APIs, an IDE, and sample code for Kaeon FUSION.

### Direct Links

[Documentation](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/tree/master/Kaeon%20FUSION/Documentation/README.md)

[Samples](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/tree/master/Kaeon%20FUSION/Samples)

[IDE](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/tree/master/Kaeon%20FUSION/IDE/README.md)

[API](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/tree/master/Kaeon%20FUSION/API)

## Disclaimer

Kaeon FUSION is more than usable in its present form.
However,
we are still working on improving the documentation,
on ironing out bugs in the interpreter,
and on new features.
Therefore,
one should consider the project to be in open alpha.
If you have an issue with any of the provided resources,
please let us know.

## Contact info

For any questions or comments, please email the following address:

kaeon.ace@gmail.com

## FAQs

### How can I try it out?

Implement it yourself.

Just kidding, we've provided a simple IDE called [Kaeon Dev](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/Kaeon%20FUSION/IDE/README.md) to get you started.

Now get coding!

### The documentation describes four different things: ONE, ONE+, FUSION, and Kaeon FUSION. What's that about?

Unlike most other languages,
Kaeon FUSION actually borrows its syntax from a language called ONE.
ONE is a very simple markup language that allows the user to define a tree of strings.
ONE+ is a superset of ONE that makes it easier to write large amounts of ONE markup by hand.
FUSION is a system that allows a ONE document to be interpreted as code,
and Kaeon FUSION is the de facto standard dialect of FUSION.

### What's the difference between an interface and a library?

In the context of Kaeon FUSION,
a library is a set of functions written in Kaeon FUSION that can be shared across Kaeon FUSION files.
An interface is a set of Kaeon FUSION commands not present in the defualt Kaeon FUSION language, but can be added in at runtime.
They are written in the same language as the interpreter,
and therefore for our implementation they are written in Java.

### Can I write my own interfaces?

Yes, third parties can write their own Kaeon FUSION interfaces using our [Kaeon FUSION Interface Development Framework](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/Kaeon%20FUSION/API/Interface%20Development%20Framework/README.md).

### Isn't the control flow of Kaeon FUSION similar to LISP? How is Kaeon FUSION different from LISP?

The main differences between Kaeon FUSION and LISP are that Kaeon FUSION uses doubly linked trees instead of singly linked lists,
boasts a far nicer syntax,
and has its functionality distributed across interchangable modules instead of being inherent to a specific dialect.

### You say this language can theoretically do anything?

Kaeon FUSION can expand its functionality at runtime through modular interfaces.
Interfaces can add new commands and provide access to back end systems.
Some interfaces allow you to cross compile ONE markup nested within function definitions into other languages,
allowing for any sort of software development using one syntax and one environment.
At the moment, the available interfaces allow for the generation of C,
HTML,
CSS,
JavaScript,
PHP,
and SQL source code.
Due to the modular nature of the Kaeon FUSION,
there is no limit to what sort functionality can be implemented through interfaces.
What's available now is only the tip of the iceberg.

### I'm already comfortable with a certain set of tools. Why should I learn Kaeon FUSION?

Whatever domain you're already working in,
Kaeon FUSION either does or will in the near future have support for it.
If you suddenly decide to branch out into a new domain or your employer demands you to work in an unfamiliar domain,
Kaeon FUSION would make the switch easier.
All you would have to do is find the right interface or library for it.
You would never have to learn a new syntax or environment.

### Give me a use case

Let's say you're managing a video game studio.
You can write your game engine in Kaeon FUSION by using an interface that generates C.
You can use ONE to create custom file and metadata formats for your engine,
and use Kaeon FUSION with the standard interface as a scripting langauge.
You can automate builds and manage assets using scripts written with the standard interface.
And you can even build a website to market your game using an interface that generates HTML and CSS.
Never once would you have to use more than one syntax or environment,
and the skillsets of your entire team would be transferable.

### The documentation says I can do something but I can't make it work, what gives?

Kaeon FUSION as a language is defined by it's specification.
As such, the documentation may be updated before the implementation is.
If you encounter an inconsistency or any other problem, please let us know.
We'll dispatch a team of highly trained rubber duckies to deal with with it immediately.

### How often will Kaeon FUSION be updated?

At the time of writing,
Kaeon FUSION has officially been in development for about five months,
and was built upon things that were in development for about one year before the project began.
You should expect to see several minor updates per month,
with major updates occuring between once per month and once every three months.

### What should I expect to see in the next major updates?

At the time of writing,
our main focus is on optomizing the perfomance of scripts written using the standard interface and on polishing the web development and machine code interfaces.
We also intend to redevelop our IDE with a far more sophisticated UI.

### I would like to use Kaeon FUSION in a commercial project. Who do I make the check out to?

Kaeon FUSION is licensed under the Apache License 2.0.
For all intents and purposes,
the contents of this repository are basically public domain,
and you are free to do as you please with it,
no strings attached.