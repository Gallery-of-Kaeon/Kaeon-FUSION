<h1 align="center">This is Kaeon FUSION</h1>
<h2 align="center">A Language Without Limits!</h2>

<p align="center">
	<img src="https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/raw/master/Kaeon%20FUSION/Logo/Kaeon%20FUSION%20Logo.png" width="300px" height="300px"/>
</p>

    Use: Standard
    
    Log Line: "This is Kaeon FUSION!"

<h2 align="center">Contents of this Repository</h2>

This repository contains the specification,
documentation,
sample code,
APIs,
an IDE,
a plugin development framework for said IDE,
modules,
libraries,
and the specification for Kaeon FUSION.

### Main Page Links

[Contact Info](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/README.md#contact-info)

[What is Kaeon FUSION?](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/README.md#what-is-kaeon-fusion)

[Kaeon FUSION Examples](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/README.md#kaeon-fusion-examples)

[FAQs](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/README.md#faqs)

[Acknowledgements](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/README.md#acknowledgements)

[Disclaimer](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/README.md#disclaimer)

### Repository Links

[Online](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/tree/master/Kaeon%20FUSION/Documentation/README.md) and [PDF](https://drive.google.com/open?id=1Ut8fIiKjGFRSH0hO9KSEyIFxsP3PTXOE) Documentation

[Samples](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/tree/master/Kaeon%20FUSION/Samples)

[APIs](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/tree/master/Kaeon%20FUSION/APIs)

[IDE](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/tree/master/Kaeon%20FUSION/IDE/README.md)

[Interfaces](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/tree/master/Kaeon%20FUSION/Interfaces), [Directives](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/tree/master/Kaeon%20FUSION/Directives), and [Syntaxes](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/tree/master/Kaeon%20FUSION/Syntaxes)

[Interface](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/Kaeon%20FUSION/Module%20Development%20Framework/Interfaces/README.md), [Directive](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/Kaeon%20FUSION/Module%20Development%20Framework/Directives/README.md), and [Syntax](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/Kaeon%20FUSION/Module%20Development%20Framework/Syntaxes/README.md) Development Frameworks

[Libraries](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/tree/master/Kaeon%20FUSION/Libraries)

[Specification](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/tree/master/Kaeon%20FUSION/Specification)

<h2 align="center">Contact Info</h2>

For any questions or comments, please email the following address: kaeon.ace@gmail.com

<h2 align="center">What is Kaeon FUSION?</h2>

_This part is a bit technical. If you're new to programming, we've put together a beginner's resource which you can find [here](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/Kaeon%20FUSION/Documentation/X%20-%20Kaeon%20FUSION%20for%20Beginners/README.md)._

Kaeon FUSION (pronounced "KAI-on") is an interpreted programming language designed to be the successor to [LISP](https://en.wikipedia.org/wiki/Lisp_(programming_language)).

When a Kaeon FUSION script begins, the language itself has no functionality save for a command called "use".
The use command takes the names of plugin modules called "interfaces" for the interpreter and dynamically integrates them at runtime.

Let us elaborate a little more on this point.
When we say that the language has "no functionality",
what we mean is that the whole language is a blank slate,
lacking support for functions,
variables,
literals,
math and logic operations,
IO of any kind,
flow control structures and conditionals,
etc.

However,
as mentioned previously,
all of the aforementioned features and more can be dynamically integrated into the interpreter at any point during runtime with a single command.
Additionally, there is no limit on the number on interfaces a script can use.

Essentially,
Kaeon FUSION is more or less like LISP,
if LISP allowed the simultaneous use of multiple distinct dialects at once and allowed the dialect to be changed mid runtime.

Kaeon FUSION does differ from LISP in another significant way in that its code is abstracted as a doubly linked acyclic tree of strings as opposed to a singly linked list of strings and other such lists.
The syntax that Kaeon FUSION uses to represent this tree,
which can be used independently of Kaeon FUSION as a markup language,
is called [ONE+](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/Kaeon%20FUSION/Documentation/1%20-%20Foundations/2%20-%20ONE%2B/README.md),
and is a syntactic superset of,
but semantic equivilent to a simpler langauge called [ONE](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/Kaeon%20FUSION/Documentation/1%20-%20Foundations/1%20-%20ONE/README.md).

ONE+ bears a strong visual resemblence to YAML,
and also shares some elements of JSON and Python,
but it also supports a modular preprocessor system.
Like Kaeon FUSION's interfaces,
ONE+'s preprocessor can dynamically integrate external modules into itself during parsing,
allowing for an indefinite amount of syntactic customization.

Currently,
the two most important interfaces available for Kaeon FUSION are the standard interface and the stack interface.

The standard interface provides all of the functionality expected of a modern,
Turing complete programming language as well as support for object orientation and more.

The stack interface allows specialized ONE+ dialects to be cross compiled into other programming and markup languages,
including C, JavaScript, and machine code, as well as Java, Python, HTML, CSS, XML, JSON, and more.

Kaeon FUSION is useful in cases where new languages need to be developed,
where systems need to be dramatically modified at runtime,
or where projects are expected to have requirements that will change in unpredictable ways.
The simiarities it has to LISP also make it a prime choice for programming language research.

Of course,
it can certainly be a lot fun to play around with the dynamic aspects of the language,
if for no other reason than one's own amusement,
so it's also a great choice for hobbyists and enthusiasts.

Unfortunately, the current implementation of Kaeon FUSION has a long way to go before it's ready for use in production.
That said,
it serves as an interesting experiment demonstrating that it is possible to have a language that can dynamically adapt to any use case,
even while running.

Additionally,
ONE+ is more than ready for use as a semantically simpler and more customizable alternative to existing markup languages such as XML and JSON.

Futhermore,
all of this is open source,
so it's inevitable that Kaeon FUSION will see dramatic improvements with further investment,
Kaeon FUSION will see dramatic improvements.
And given Kaeon FUSION's dynamic nature,
there's virtually nothing about it that can't be altered if necessary,
hence the tagline "a language without limits".

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

<h2 align="center">FAQs</h2>

### How can I try it out?

We've provided a simple IDE called [Kaeon Origin](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/Kaeon%20FUSION/IDE/README.md) to get you started.

### What's the difference between an interface and a library?

An interface is a module for the Kaeon FUSION interpreter that alters the properties of the language at runtime,
doing anything from adding new commands to adding new back end properties.

In the context of Kaeon FUSION,
a library is a set of functions written for the Kaeon FUSION standard interface that can be shared across Kaeon FUSION files.

### Can I write my own interfaces?

Yes,
third parties can write their own Kaeon FUSION interfaces using our [Kaeon FUSION Interface Development Framework](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/Kaeon%20FUSION/Module%20Development%20Framework/Interfaces/README.md).

Third parties may also write their own ONE+ directives using our [ONE+ Directive Development Framework](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/Kaeon%20FUSION/Module%20Development%20Framework/Directives/README.md),
as well as their own ONE+ alternate syntaxes using out [ONE+ Alternate Syntax Development Framework](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/Kaeon%20FUSION/Module%20Development%20Framework/Syntaxes/README.md).

### Is Kaeon FUSION a LISP?

Despite the similarites Kaeon FUSION is not in the LISP family,
as even though it uses a code as data paradigm,
said data is represented in a doubly linked tree structure as opposed to a singly linked list structure.
This was done both for philosophical and practical reasons.
Philosophical because we felt that a tree better represented the relationship between operators and their arguments,
as the separation between them is explicit rather than implicit as is the case with LISP,
and practical because having the nodes doubly linked makes it easier to implement commands that are aware of their surrounding commands.

### Is Kaeon FUSION bound to a specific virtual machine?

We wrote our original implementation of Kaeon FUSION in Java because it was the language our team had the most experience in at the beginning of the project,
and because the JVM's dynamic class loader made developing the interface's ability to dynamically load at runtime far easier than it would have been in a language that compiles to native code.

However,
an implementation of Kaeon FUSION can be written in any language and for any platform,
as long as it conforms to the rules laid out in the specification.

Moving forward,
we plan to implement all of Kaeon FUSION components in JavaScript.

### Can the Kaeon FUSION interpreter be embedded into a larger project?

Yes,
this is possible.
Refer to the [APIs](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/tree/master/Kaeon%20FUSION/APIs) section for the necessary files.

Here's an example of how one would start the interpreter from JavaScript:

    (new require("./KaeonFUSION.js").KaeonFUSION()).process(require("./ONEPlus.js").readONEPlus(sourceCodeString));

And here's an example of how one would start the interpreter from Java:

    new KaeonFUSION().processKaeonFUSION(ONEPlus.parseONEPlus(sourceCodeString));

The implementation of the Kaeon FUSION interpreter is built on Philosopher's Stones,
and the KaeonFUSION object,
which serves as the core of the interpreter,
is an extension of the Philosopher's Stone.
You can read about Philosopher's Stones [here](https://github.com/Gallery-of-Kaeon/Philosophers-Stone/blob/master/README.md).
It is important to understand Philosopher's Stones in order to take full advantage of the APIs.

### How long has Kaeon FUSION been in development?

Kaeon FUSION has officially been in development since December 15, 2016,
and was built upon things that were in development for around one and a half years before the project began.

### How often will Kaeon FUSION be updated?

Due to the current state of Kaeon FUSION's development,
we cannot offer an accurate timeline for updates until further notice.

### What should I expect to see in the next major updates?

We have recently completed porting the base interpreter from Java to JavaScript.
However,
we have not ported any of the interfaces or directives,
nor have we ported Kaeon Origin.
Our current focus is on porting all that we have yet to port from Java to JavaScript.
From this point onward,
we will no longer update any of our Java implementations.

### I would like to use Kaeon FUSION in a commercial project. Who do I make the check out to?

Kaeon FUSION is licensed under the [Apache License 2.0](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/LICENSE.md).
Therefore,
the contents of this repository are free for everyone to use and make modifications to.

### Does Kaeon FUSION have a mascot?

The mascot of all projects under the Kaeon name is the ACE.
Both the male version and the female version of the character are shown on the profile picture of the Gallery-of-Kaeon github account.

### Who are you?

For now,
mostly [this person](https://www.github.com/kaeon-ace),
and [this person](https://www.github.com/jwilliams0496).

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

<h2 align="center">Acknowledgements</h2>

The stack interface makes use of the [require1k](https://github.com/Stuk/require1k) JavaScript utility.

The Kaeon Origin IDE makes use of a [swing layout](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/Kaeon%20FUSION/IDE/Source/Source/kaeon_origin/ide/utilities/web/VerticalLayout.java) developed by Colin Mummery.

<h2 align="center">Disclaimer</h2>

TL;DR: The core of Kaeon FUSION's specification is more or less complete,
but the implementation,
the documentation,
and the modular parts of the specification are works in progress.

It is important to note that the contents of this repository are aimed at those who have a strong background in computer science.
If you're a newcomer to programming in general,
we recommend you check out our [Kaeon FUSION for Beginners](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/tree/master/Kaeon%20FUSION/Documentation/X%20-%20Kaeon%20FUSION%20for%20Beginners/README.md) resource.

Whenever a change is made to Kaeon FUSION,
the specification is always updated first.
The implementation is then updated to conform to the updated specification.
As a consequence,
many parts of the documentation are often out of date,
and may take a while to catch up.

It should also be noted that prior to July 4, 2018,
all of our implementations were developed and tested on an unmodified Lenovo IdeaPad P500 laptop running Windows 10.
Since July 4, 2018 we have been using a 2018 Alienware 15 inch laptop running Windows 10.

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