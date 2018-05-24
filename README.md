<h1 align="center">This is Kaeon FUSION</h1>
<h2 align="center">A Language Without Limits!</h2>

<p align="center">
	<img src="https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/raw/master/Kaeon%20FUSION/Logo/Kaeon%20FUSION%20Logo.png" width="300px" height="300px"/>
</p>

    Use: Standard; Log Line: "This is Kaeon FUSION!"

In software engineering,
it is a commonly held belief that it is inherently necessary to use multiple tools to cover the vast array of tasks that the discipline entails.
However,
we disagree with this sentiment.

The sheer,
ever-growing scale of the toolset required by software developers is a burden that should have been dealt with long ago.
In order to liberate developers from the bondage of incomplete tools,
we have created a language without limits.
This is Kaeon FUSION!

<h2 align="center">What is Kaeon FUSION?</h2>

Kaeon FUSION (pronounced "KAI-on") is an interpreted programming language with the unique ability to alter its properties at runtime using modules that it can load dynamically.
It's like if Java could import a module at runtime that made multiple inheritence legal,
or if C could import a module at runtime that allowed the use of C++ style classes or enabled automatic garbage collection.

It is also unique in that sits on top of a markup language called ONE+.
ONE+ allows the user to manually encode any tree of strings.
Said tree serves as the abstract syntax tree for Kaeon FUSION.
This property allows Kaeon FUSION to be easily cross compiled into other languages,
so despite being interpreted,
Kaeon FUSION can still be useful in speed critical envrionments by being cross compiled to a compiled language.
Another benefit to ONE+ is that it has a highly customizable syntax,
which allows for a wide variety of developer preferences to be accommodated.

<h2 align="center">Contents of this Repository</h2>

This repository contains the specification,
documentation,
sample code,
APIs,
an IDE,
a plugin development framework for said IDE,
interfaces,
and the specification for Kaeon FUSION.

### Direct Links

[Online](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/tree/master/Kaeon%20FUSION/Documentation/README.md) and [PDF](https://drive.google.com/open?id=1cpDkYGPyTE08rpowXH10VjTxr03_jl6b) Documentation

[Samples](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/tree/master/Kaeon%20FUSION/Samples)

[APIs](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/tree/master/Kaeon%20FUSION/APIs)

[IDE](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/tree/master/Kaeon%20FUSION/IDE/README.md)

[Interfaces](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/tree/master/Kaeon%20FUSION/Interfaces), [Directives](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/tree/master/Kaeon%20FUSION/Directives), and [Syntaxes](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/tree/master/Kaeon%20FUSION/Syntaxes)

[Interface](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/Kaeon%20FUSION/Module%20Development%20Framework/Interfaces/README.md), [Directive](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/Kaeon%20FUSION/Module%20Development%20Framework/Directives/README.md), and [Syntax](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/Kaeon%20FUSION/Module%20Development%20Framework/Syntaxes/README.md) Development Frameworks

[Specification](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/tree/master/Kaeon%20FUSION/Specification)

<h2 align="center">Kaeon FUSION Examples</h2>

Here's the solution to [Euler problem #1](https://projecteuler.net/problem=1),
written in [ONE+](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/Kaeon%20FUSION/Documentation/1%20-%20Foundations/2%20-%20ONE%2B/README.md),
the markup language Kaeon FUSION uses as its syntax,
and with the Super Mode directive acting as a preprocessor:

	[USE: SUPER] [SUPER]

	sum = 0

	for range { i, 3, 999 }
	
		if { i % 3 == 0 or i % 5 == 0 }
			sum += i

	print sum

Here's the same code as above,
also written in ONE+ but without using Super Mode:

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

Here's hello world,
written using [ONE](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/Kaeon%20FUSION/Documentation/1%20-%20Foundations/1%20-%20ONE/README.md),
the most basic form of Kaeon FUSION's syntax:

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

Click [here](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/tree/master/Kaeon%20FUSION/Samples) for all of our available sample code.

<h2 align="center">Contact Info</h2>

For any questions or comments, please email the following address: kaeon.ace@gmail.com

<h2 align="center">FAQs</h2>

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

We wrote our implementation of Kaeon FUSION in Java because it was the language our team had the most experience in at the beginning of the project,
and because the JVM's dynamic class loader made developing the interface's ability to dynamically load at runtime far easier than it would have been in a language that compiles to native code.

However,
an implementation of Kaeon FUSION can be written in any language and for any platform,
as long as it conforms to the rules laid out in the specification.

### Can the Java implementation of the Kaeon FUSION interpreter be embedded into a larger Java project?

Yes,
this is possible.
Refer to the [APIs](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/tree/master/Kaeon%20FUSION/APIs) section for the necessary JAR files.
All of them except for the updater API will be necessary.

Here's an example of how one would start the interpreter from Java:

    new KaeonFUSION().processKaeonFUSION(ONEPlus.parseONEPlus(IO.openAsString("My Code.op")));

The Java implementation of the Kaeon FUSION interpreter is built on Philosopher's Stones,
and the KaeonFUSION object,
which serves as the core of the interpreter,
is an extension of the Philosopher's Stone.
You can read about Philosopher's Stones [here](https://github.com/Gallery-of-Kaeon/Philosophers-Stone/blob/master/README.md).
It is important to understand Philosopher's Stones in order to take full advantage of the APIs.

### How long has Kaeon FUSION been in development?

At the time of writing,
Kaeon FUSION has officially been in development since the middle of February 2017,
and was built upon things that were in development for around one and a half years before the project began.

### How often will Kaeon FUSION be updated?

Due to the current state of Kaeon FUSION's development,
we cannot offer an accurate timeline for updates until further notice.

### What should I expect to see in the next major updates?

At the time of writing,
our main focus is on stabilizing the specification and our implementations for the standard ONE+ directives,
super mode, and the stack interface,
as well as improving the quality of the Kaeon Origin IDE.

### I would like to use Kaeon FUSION in a commercial project. Who do I make the check out to?

Kaeon FUSION is licensed under the [Apache License 2.0](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/LICENSE.md).
Therefore,
the contents of this repository are free for everyone to use and make modifications to.

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

<h2 align="center">Disclaimer</h2>

Tl;Dr: The core of Kaeon FUSION's specification is more or less complete,
but the modular parts of the specification,
the implementation,
and the documentation are works in progress.

It is important to note that the contents of this repository are aimed at those who have a strong background in computer science.
If you're a newcomer to programming in general,
we recommend you check out our [Kaeon FUSION for Beginners](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/tree/master/Kaeon%20FUSION/Documentation/X%20-%20Kaeon%20FUSION%20for%20Beginners/README.md) resource.

Whenever a change is made to Kaeon FUSION,
the specification is always updated first.
The implementation is then updated to conform to the updated specification.
As a consequence,
many parts of the documentation are often out of date,
and may take a while to catch up.

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

As of April 10, 2018,
the Kaeon Origin IDE requires that the update path be manually specified.
This is in case the update path changes.
You may specify the update path in the Kaeon Origin section of the options menu.
Please set it to:

    https://raw.githubusercontent.com/Gallery-of-Kaeon/Kaeon-FUSION/master/Kaeon%20FUSION/IDE/Application/Update/Update.op

On May 5, 2018,
We had to repair Kaeon Origin's updater module due to changes we made to the ONE+ parser.
If you downloaded Kaeon Origin prior to May 5, 2018, please re-download it manually.