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

Documentation: [Online](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/tree/master/Kaeon%20FUSION/Documentation/README.md) /
[PDF](https://drive.google.com/open?id=1S4wWCpNN6BLdFOssHsFn9EQ-xUlqJoan)

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

Our beta implementation does not currently include implementations for any ONE+ directives.
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

### The documentation describes four different things: ONE, ONE+, FUSION, and Kaeon FUSION. What's that about?

Kaeon FUSION is not a monolithic system.

Kaeon FUSION code is encoded into documents using the [ONE](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/Kaeon%20FUSION/Documentation/1%20-%20Foundations/1%20-%20ONE/README.md) format,
which allows any abstract syntax tree to be defined.

[ONE+](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/Kaeon%20FUSION/Documentation/1%20-%20Foundations/2%20-%20ONE%2B/README.md) is a system of mnemonics that make it easier to write ONE documents by hand.

The control flow of Kaeon FUSION is managed by the [FUSION](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/Kaeon%20FUSION/Documentation/1%20-%20Foundations/3%20-%20FUSION/README.md) system,
which allows any ONE document to serve as code.

### What's the difference between an interface and a library?

An interface is a module for the Kaeon FUSION interpreter that alters the properties of the language at runtime,
doing anything from adding new commands to adding new back end properties.

In the context of Kaeon FUSION,
a library is a set of functions written for the Kaeon FUSION standard interface that can be shared across Kaeon FUSION files.

### Can I write my own interfaces?

Yes, third parties can write their own Kaeon FUSION interfaces using our [Kaeon FUSION Interface Development Framework](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/Kaeon%20FUSION/Module%20Development%20Framework/Interfaces/README.md).

Third parties may also write their own ONE+ directives using our [ONE+ Directive Development Framework](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/Kaeon%20FUSION/Module%20Development%20Framework/Directives/README.md).

### Isn't the control flow of Kaeon FUSION similar to LISP? How is Kaeon FUSION different from LISP?

The main differences between Kaeon FUSION and LISP are that Kaeon FUSION uses doubly linked trees instead of singly linked lists,
and has its functionality distributed across interchangeable modules instead of being inherent to a specific dialect.

### Why would you create a language that does everything? Normal developers get by fine without such a thing. And how is the concept of a "language that does everything" different from any turing complete language?

We would like to think that the prospect of a language that can do anything,
the proverbial "golden hammer",
is a worthy goal in and of itself.

The term "turing complete" means the language has the ability to solve any problem that can be be solved through computation.
Kaeon FUSION is turing complete when using the standard interface.
However,
software must do more than compute data.
It is often the case that specialty languages are required for templating content as is the case with HTML and CSS,
and in certain cases developers require languages to support certain paradigms and frameworks in order to produce content for certain platforms.
Due to Kaeon FUSION's ability to take on new functionality through interfaces and to support ONE based DSLs that can be cross complied to other languages,
it is able to meet these requirements in addition to being turing complete.

### I'm already comfortable with a certain set of tools. Why should I learn Kaeon FUSION? What is its niche?

Because Kaeon FUSION can incorporate new functionality as needed,
it can be relied on when other toolsets fall short.

These properties make Kaeon FUSION extremely useful for rapid prototyping,
and for projects with constantly changing specifications or constantly increasing scope.

In addition,
Kaeon FUSION serves as a solid foundation for creating domain specific languages,
and its systax works well as a base for custom file formats.
While LISP already works well for creating DSLs,
Kaeon FUSION's ability to have them implemented as interfaces allows multiple DSLs to be used in the same environment,
whereas LISP DSLs must be used separately.

### Is Kaeon FUSION bound to the JVM?

We wrote our implementation of Kaeon FUSION in Java because we saw Java as the most effective way to quickly and efficiently get the project off the ground.
However,
an implementation of Kaeon FUSION can be written in any language and for any platform,
as long as it conforms to the rules laid out in the specification.

### How often will Kaeon FUSION be updated?

At the time of writing,
Kaeon FUSION has officially been in development for about ten months,
and was built upon things that were in development for around one year before the project began.
You should expect to see several minor updates per month,
with major updates occurring between once per month and once every three months.

### What should I expect to see in the next major updates?

At the time of writing,
our main focus is on stabilizing the specification and our implementations for the standard ONE+ directives,
super mode, and the web and machine interfaces,
as well as improving the quality of the Kaeon FUSION IDE.

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