# Kaeon FUSION

Kaeon FUSION is the very first programming language created to work in place of any other programming language.

In layman's terms,
it's a programming language that can do anything.
And it's easy to learn too, even if you've never programmed before!

Kaeon FUSION has successfully been used for general purpose programming,
scripting,
low level programming,
and web development.
In theory,
it can be extended to work with any domain,
and is particularly useful for metaprogramming.

Kaeon FUSION allows this vast array of coverage by using a syntax called ONE+ that can generate any abstract syntax tree and by allowing the functionality of the language to be encoded into interchangeable modules which can be incorporated at runtime,
thereby making the functionality of the language indefinitely extensible.

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

Also,
it is important to note that the contents of this repository are aimed at those who have a strong background in computer science.
If you're a newcomer to programming in general,
we recommend you check out our [Kaeon FUSION for Beginners](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/tree/master/Kaeon%20FUSION/Documentation/X%20-%20Kaeon%20FUSION%20for%20Beginners/README.md) resource.

## Contact info

For any questions or comments, please email the following address:

kaeon.ace@gmail.com

## FAQs

### How can I try it out?

Implement it yourself.

Just kidding, we've provided a simple IDE called [Kaeon Dev](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/Kaeon%20FUSION/IDE/README.md) to get you started.

Now get coding!

### The documentation describes four different things: ONE, ONE+, FUSION, and Kaeon FUSION. What's that about?

Kaeon FUSION is not a monolithic system.

Kaeon FUSION code is encoded into documents using the [ONE](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/Kaeon%20FUSION/Documentation/1%20-%20Foundations/1%20-%20ONE/README.md) format,
which allows any tree of strings to be defined.

[ONE+](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/Kaeon%20FUSION/Documentation/1%20-%20Foundations/2%20-%20ONE%2B/README.md) is a system of mneumonics that make it easier to write ONE documents by hand.

The control flow of Kaeon FUSION is managed by the [FUSION](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/Kaeon%20FUSION/Documentation/1%20-%20Foundations/3%20-%20FUSION/README.md) system,
which allows any ONE document to serve as code.

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
and has its functionality distributed across interchangeable modules instead of being inherent to a specific dialect.

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

### Is Kaeon FUSION bound to the JVM?

We wrote our implementation of Kaeon FUSION in Java because we saw java as the most effective way to quickly and efficiently get it off the ground.
However,
an implementation of Kaeon FUSION can be written in any language and for any platform,
as long as it conforms to the rules laid out in the documentation.

### How often will Kaeon FUSION be updated?

At the time of writing,
Kaeon FUSION has officially been in development for about seven months,
and was built upon things that were in development for about one year before the project began.
You should expect to see several minor updates per month,
with major updates occuring between once per month and once every three months.

### What should I expect to see in the next major updates?

At the time of writing,
our main focus is on optomizing the perfomance of scripts written using the standard interface and on polishing the web development and machine code interfaces.
We also intend to redevelop our IDE with a far more sophisticated UI.

### I would like to use Kaeon FUSION in a commercial project. Who do I make the check out to?

Kaeon FUSION is licensed under the [Apache License 2.0](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/LICENSE.md).
For all intents and purposes,
the contents of this repository are basically public domain,
and you are free to do as you please with it,
no strings attached.

### How do you pronounce "Kaeon"?

"KAI-on".

### Who are you?

The Kaeon FUSION language is one of many projects under the Kaeon name.
The Kaeon projects are at the time of writing primarily developed and managed by [Jesse Dinkin](https://www.linkedin.com/in/jesse-dinkin-6b9135100/).

### Can I join you?

Short answer:
Sorry, but unfortunately not.

Long answer:
You're free to write third party interfaces for Kaeon FUSION,
write your own implementaion of Kaeon FUSION,
publish your own resources for Kaeon FUSION,
develop successor products to Kaeon FUSION,
etc.
So if you think you can do our job better than we can,
you've got our blessings.
However,
we have a very specific vision for what we want Kaeon FUSION to be,
and therefore can't afford to take on anyone who we don't know extremely well.
If we feel you'd make a good addition to our team,
we'll come to you.