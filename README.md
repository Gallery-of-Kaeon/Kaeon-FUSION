<h1 align="center">Kaeon FUSION</h1>
<h2 align="center">The First Ever Universal Programming Language</h2>

<p align="center">
	<img src="https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/raw/master/Kaeon%20FUSION/Logo/Kaeon%20FUSION%20Logo.png" width="300px" height="300px"/>
</p>

    Use: Standard; Log Line: Open: Pitch.txt

In software engineering,
it is a commonly held belief that it is inherently necessary to use multiple tools to cover the vast array of tasks that the discipline entails.
However,
we disagree with this sentiment.

The sheer,
ever-growing scale of the toolset required by software developers is a burden that should have been dealt with long ago.
To free developers from the bondage of incomplete tools,
we have created Kaeon FUSION:
the first ever universal programming language.
What this means,
is that any software project can be done entirely in Kaeon FUSION.

Kaeon FUSION achieves its claim through its syntax which allows any hierarchy of data to function as code and through its unique ability to increase what it's capable of as it runs.
At the same time,
its minimalist design ensures that both newcomers and veterans can easily learn to use it.

## Contents of this repository

This repository contains the specification,
documentation,
sample code,
APIs,
an IDE,
a plugin development framework for said IDE,
interfaces,
and the specification for Kaeon FUSION.

### Direct Links

[Online](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/tree/master/Kaeon%20FUSION/Documentation/README.md) and [PDF](https://drive.google.com/open?id=1S4wWCpNN6BLdFOssHsFn9EQ-xUlqJoan) Documentation

[Samples](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/tree/master/Kaeon%20FUSION/Samples)

[APIs](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/tree/master/Kaeon%20FUSION/APIs)

[IDE](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/tree/master/Kaeon%20FUSION/IDE/README.md)

[Interface](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/Kaeon%20FUSION/Module%20Development%20Framework/Interfaces/README.md) and [Directive](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/Kaeon%20FUSION/Module%20Development%20Framework/Directives/README.md) Development Frameworks

[Interfaces](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/tree/master/Kaeon%20FUSION/Interfaces)

[Specification](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/tree/master/Kaeon%20FUSION/Specification)

## Disclaimer

It is important to note that the contents of this repository are aimed at those who have a strong background in computer science.
If you're a newcomer to programming in general,
we recommend you check out our [Kaeon FUSION for Beginners](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/tree/master/Kaeon%20FUSION/Documentation/X%20-%20Kaeon%20FUSION%20for%20Beginners/README.md) resource.

As of Novemeber 13, 2017,
the implementations for Kaeon FUSION and its standard interface are stable.
However,
the Kaeon Origin IDE,
several directives,
as well as the web and machine interfaces are still in beta.

As of November 5, 2017,
the Kaeon Origin IDE automatically notifies the user when updates are available and offers them the option to have the updates installed automatically.
If you downloaded Kaeon Origin prior to November 5, 2017,
we recommend that you re-download it.

Our beta implementation does not currently include implementations for any ONE+ directives or the stack interface.
It should also be noted that the Kaeon Origin IDE was developed and tested on a windows computer.
To be specific:
a Lenovo IdeaPad P500 laptop.
Some features of the IDE may malfunction on Unix based operating systems including Mac and Linux.
We will resolve these issues as soon as we can.

Finally,
we plan on releasing a more advanced version of our IDE by early 2018 to make up for the current version's shortcomings.

## Contact info

For any questions or comments, please email the following address:

kaeon.ace@gmail.com

## FAQs

### How can I try it out?

We've provided a simple IDE called [Kaeon Origin](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/Kaeon%20FUSION/IDE/README.md) to get you started.

### What's the difference between an interface and a library?

An interface is a module for the Kaeon FUSION interpreter that alters the properties of the language at runtime,
doing anything from adding new commands to adding new back end properties.

In the context of Kaeon FUSION,
a library is a set of functions written for the Kaeon FUSION standard interface that can be shared across Kaeon FUSION files.

### Can I write my own interfaces?

Yes, third parties can write their own Kaeon FUSION interfaces using our [Kaeon FUSION Interface Development Framework](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/Kaeon%20FUSION/Module%20Development%20Framework/Interfaces/README.md).

Third parties may also write their own ONE+ directives using our [ONE+ Directive Development Framework](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/Kaeon%20FUSION/Module%20Development%20Framework/Directives/README.md).

### The first ever univeral programming language? Really?

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

### What can Kaeon FUSION do that other languages cannot?

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

### Is Kaeon FUSION bound to the JVM?

We wrote our implementation of Kaeon FUSION in Java because we saw Java as the most effective way to quickly and efficiently get the project off the ground.
However,
an implementation of Kaeon FUSION can be written in any language and for any platform,
as long as it conforms to the rules laid out in the specification.

### How long has Kaeon FUSION been in development?

At the time of writing,
Kaeon FUSION has officially been in development for about eleven months,
and was built upon things that were in development for around one and a half years before the project began.

### How often will Kaeon FUSION be updated?

You should expect to see several minor updates per month,
with major updates occurring between once per month and once every three months.

### What should I expect to see in the next major updates?

At the time of writing,
our main focus is on stabilizing the specification and our implementations for the standard ONE+ directives,
super mode, and the stack interface,
as well as improving the quality of the Kaeon Origin IDE.

### I would like to use Kaeon FUSION in a commercial project. Who do I make the check out to?

Kaeon FUSION is licensed under the [Apache License 2.0](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/LICENSE.md).
Therefore,
the contents of this repository are free for everyone to use and make modifications to.

### How do you pronounce "Kaeon"?

"KAI-on".

### Who are you?

The Kaeon FUSION language is one of many projects under the Kaeon name.
The Kaeon projects are at the time of writing primarily developed and managed by [Jesse Dinkin](https://www.linkedin.com/in/jesse-dinkin-6b9135100/).

### Does Kaeon FUSION have a mascot?

The mascot of all projects under the Kaeon name is the ACE.
The female version of the character is shown on the profile picture of the Gallery-of-Kaeon github account.
The male version of the character is shown [here](https://drive.google.com/open?id=0B7qYhFZP1C70Q3cxQjREYUdETHM).

### Can I join you?

Short answer:
Yes and no.

Long answer:
You're free to write third party interfaces for Kaeon FUSION,
write your own implementation of Kaeon FUSION,
publish your own resources for Kaeon FUSION,
develop successor products to Kaeon FUSION,
etc.
So if you think you can do our job better than we can,
you've got our blessings.
However,
we have a very specific vision for what we want Kaeon FUSION to be,
and therefore we aren't likely to take on anyone who we don't know extremely well.
If we feel you'd make a good addition to our team,
we'll come to you.
However,
if you have something you feel that we need to know,
we'll hear you out.