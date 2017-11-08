# Kaeon FUSION

    Use: Standard; Log Line: Open: Pitch.txt

In computer programming,
it is a commonly held belief that it is inherently necessary to use multiple tools to cover the vast array of tasks that software engineering entails.
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
[PDF](https://drive.google.com/open?id=0B7qYhFZP1C70dGVfR3Iwd2ZNUGs)

[Samples](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/tree/master/Kaeon%20FUSION/Samples)

[APIs](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/tree/master/Kaeon%20FUSION/APIs)

[IDE](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/tree/master/Kaeon%20FUSION/IDE/README.md)

[Interface Deveopment Framework](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/tree/master/Kaeon%20FUSION/Interface%20Development%20Framework/README.md)

[Interfaces](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/tree/master/Kaeon%20FUSION/Interfaces)

[Specification](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/tree/master/Kaeon%20FUSION/Specification)

## Disclaimer

It is important to note that the contents of this repository are aimed at those who have a strong background in computer science.
If you're a newcomer to programming in general,
we recommend you check out our [Kaeon FUSION for Beginners](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/tree/master/Kaeon%20FUSION/Documentation/X%20-%20Kaeon%20FUSION%20for%20Beginners/README.md) resource.

As of October 21, 2017,
Kaeon FUSION is in beta.

As of November 5, 2017,
the Kaeon Origin IDE automatically notifies the user when updates are available and offers them the option to have the updates installed automatically.
If you downloaded Kaeon Origin prior to November 5, 2017,
we recommended that you re-download it.

Our beta implementation does not currently include implementations for any ONE+ directives.
In addition, due to a glitch in the ONE+ parser, we recommend that only tabs be used for indentation in Kaeon FUSION code.
The aforementioned issues should be resolved by mid December, 2017.

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
which allows any tree of strings to be defined.

[ONE+](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/Kaeon%20FUSION/Documentation/1%20-%20Foundations/2%20-%20ONE%2B/README.md) is a system of mnemonics that make it easier to write ONE documents by hand.

The control flow of Kaeon FUSION is managed by the [FUSION](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/Kaeon%20FUSION/Documentation/1%20-%20Foundations/3%20-%20FUSION/README.md) system,
which allows any ONE document to serve as code.

### What's the difference between an interface and a library?

In the context of Kaeon FUSION,
a library is a set of functions written in Kaeon FUSION that can be shared across Kaeon FUSION files.
An interface is a set of Kaeon FUSION commands not present in the default Kaeon FUSION language, but can be added in at runtime.
They are written in the same language as the interpreter,
and therefore for our implementation they are written in Java.

### Can I write my own interfaces?

Yes, third parties can write their own Kaeon FUSION interfaces using our [Kaeon FUSION Interface Development Framework](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/Kaeon%20FUSION/API/Interface%20Development%20Framework/README.md).

### Isn't the control flow of Kaeon FUSION similar to LISP? How is Kaeon FUSION different from LISP?

The main differences between Kaeon FUSION and LISP are that Kaeon FUSION uses doubly linked trees instead of singly linked lists,
boasts a far nicer syntax,
and has its functionality distributed across interchangeable modules instead of being inherent to a specific dialect.

### Why would you create a language that does everything? Normal developers get by fine without such a thing.

We would like to think that the prospect of a language that can do anything,
the proverbial "golden hammer",
is a worthy goal in and of itself.

### I'm already comfortable with a certain set of tools. Why should I learn Kaeon FUSION? What is its niche?

Because Kaeon FUSION can incorporate any functionality,
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

We wrote our implementation of Kaeon FUSION in Java because we saw Java as the most effective way to quickly and efficiently get it off the ground.
However,
an implementation of Kaeon FUSION can be written in any language and for any platform,
as long as it conforms to the rules laid out in the specification.

### How often will Kaeon FUSION be updated?

At the time of writing,
Kaeon FUSION has officially been in development for about seven months,
and was built upon things that were in development for about one year before the project began.
You should expect to see several minor updates per month,
with major updates occurring between once per month and once every three months.

### What should I expect to see in the next major updates?

At the time of writing,
our main focus is on optimizing the performance of scripts written using the standard interface and on polishing the web development and machine code interfaces.
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