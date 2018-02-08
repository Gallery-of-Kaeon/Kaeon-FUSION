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

## Kaeon FUSION Example

Here's hello world,
written using [ONE](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/Kaeon%20FUSION/Documentation/1%20-%20Foundations/1%20-%20ONE/README.md):

    -
    	Use
    -
    	-
    		Standard
    	-
    -
    	Log Line
    -
    	-
    		Hello, world!
    	-


and here's the solution to [Euler problem #1](https://projecteuler.net/problem=1),
written using [ONE+](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/Kaeon%20FUSION/Documentation/1%20-%20Foundations/2%20-%20ONE%2B/README.md):

    Use: Standard

    sum: 0

    i { 3 } Scope
	
    	Scope
		
    		Break: Not: Or
    			Equal: 0, Modulus: i, 3
    			Equal: 0, Modulus: i, 5
		
    		sum: Add: sum, i

    	i: Add: i, 1
    	Loop: Less: i, 1000

    Log Line: sum

and here's the same code as above with the Super Mode preprocessor:

	[USE: SUPER] [SUPER]

	sum = 0

	for i from 3 to 999 do
	
		if i % 3 == 0 or i % 5 == 0 do
			sum += i

	print sum

_Super Mode is in beta, and cannot support the above example at this time._

Click [here](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/tree/master/Kaeon%20FUSION/Samples) for all of our available sample code.

## Contact info

For any questions or comments, please email the following address: kaeon.ace@gmail.com

## FAQs

### How can I try it out?

We've provided a simple IDE called [Kaeon Origin](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/Kaeon%20FUSION/IDE/README.md) to get you started.

### Why should I use Kaeon FUSION?

We have a lot to say on this topic,
so click [here](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/tree/master/Kaeon%20FUSION/Documentation/X%20-%20Why%20use%20Kaeon%20FUSION/README.md) for our two cents on it.

### What's the difference between an interface and a library?

An interface is a module for the Kaeon FUSION interpreter that alters the properties of the language at runtime,
doing anything from adding new commands to adding new back end properties.

In the context of Kaeon FUSION,
a library is a set of functions written for the Kaeon FUSION standard interface that can be shared across Kaeon FUSION files.

### Can I write my own interfaces?

Yes, third parties can write their own Kaeon FUSION interfaces using our [Kaeon FUSION Interface Development Framework](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/Kaeon%20FUSION/Module%20Development%20Framework/Interfaces/README.md).

Third parties may also write their own ONE+ directives using our [ONE+ Directive Development Framework](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/Kaeon%20FUSION/Module%20Development%20Framework/Directives/README.md).

### Is Kaeon FUSION a LISP?

Despite the similarites Kaeon FUSION is not in the LISP family,
as even though it uses a code as data paradigm,
said data is represented in a doubly linked tree structure as opposed to a singly linked list structure.
This was done both for philosophical and practical reasons.
Philosophical because we felt that a tree better represented the relationship between operators and their arguments,
as the separation between them is explicit rather than implicit as is the case with LISP,
and practical because having the nodes doubly linked makes it easier to implement commands that are aware of their surrounding commands.

### Is Kaeon FUSION bound to the JVM?

We wrote our implementation of Kaeon FUSION in Java because we saw Java as the most effective way to quickly and efficiently get the project off the ground.
However,
an implementation of Kaeon FUSION can be written in any language and for any platform,
as long as it conforms to the rules laid out in the specification.

### Can the Java implementation of the Kaeon FUSION interpreter be embedded into a larger Java project?

Yes,
this is possible.
Refer to the [APIs](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/tree/master/Kaeon%20FUSION/APIs) section for the necessary JAR files.
All of them except for the updater API will be necessary.

Here's an example of how one would start the interpreter from Java:

    new KaeonFUSION().process(ONEPlus.parseONEPlus(IO.openAsString("My Code.op")));

The Java implementation of the Kaeon FUSION interpreter is built on Philosopher's Stones,
and the KaeonFUSION object,
which serves as the core of the interpreter,
is an extension of the Philosopher's Stone.
You can read about Philosopher's Stones [here](https://github.com/Gallery-of-Kaeon/Philosophers-Stone/blob/master/README.md).
It is important to understand Philosopher's Stones in order to take full advantage of the APIs.

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

## Disclaimer

It is important to note that the contents of this repository are aimed at those who have a strong background in computer science.
If you're a newcomer to programming in general,
we recommend you check out our [Kaeon FUSION for Beginners](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/tree/master/Kaeon%20FUSION/Documentation/X%20-%20Kaeon%20FUSION%20for%20Beginners/README.md) resource.

As of Novemeber 13, 2017,
the implementations for Kaeon FUSION and its standard interface are stable.
However,
the Kaeon Origin IDE and the stack interface are still in beta.
None of the standard ONE+ have implemantations yet,
the current implementation of the stack interface only provides the HTML and CSS dialects,
and Super Mode is in beta.
It should also be noted that all of our implementations were developed and tested on an unmodified Lenovo IdeaPad P500 laptop running Windows 10.

Kaeon FUSION,
being a purely interpreted language,
is not very fast.
However,
we do intend to optomize our interpreter in the near future to be much faster than it currently is.
In addition,
the Stack interface will allow Kaeon FUSION to be cross compiled into much faster languages,
allowing Kaeon FUSION to be used even where speed is an issue.

As of November 5, 2017,
the Kaeon Origin IDE automatically notifies the user when updates are available and offers them the option to have the updates installed automatically.
If you downloaded Kaeon Origin prior to November 5, 2017,
we recommend that you re-download it.

Finally,
we plan on releasing a more advanced version of our IDE by early 2018 to make up for the current version's shortcomings.