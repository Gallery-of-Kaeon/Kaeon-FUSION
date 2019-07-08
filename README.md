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

[Interfaces](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/tree/master/Kaeon%20FUSION/Interfaces), [Directives](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/tree/master/Kaeon%20FUSION/Directives), and [Syntaxes](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/tree/master/Kaeon%20FUSION/Syntaxes)

[Libraries](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/tree/master/Kaeon%20FUSION/Libraries)

[Specification](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/tree/master/Kaeon%20FUSION/Specification)

_NOTE: The current documentation and sample programs are partially out of date, and much of them only apply to the Java based version of Kaeon FUSION, which is now depricated._

<h2 align="center">Contact Info</h2>

For any questions or comments, please email the following address: kaeon.ace@gmail.com

<h2 align="center">What is Kaeon FUSION?</h2>

_This part is a bit technical. If you're new to programming, we've put together a beginner's resource which you can find [here](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/Kaeon%20FUSION/Documentation/X%20-%20Kaeon%20FUSION%20for%20Beginners/README.md)._

_NOTE: The beginner's guide is partially out of date, and much of it only applies to the Java based version of Kaeon FUSION, which is now depricated._

Kaeon FUSION (pronounced "KAI-on") is an interpreted programming language designed to be able to dynamically adapt to any use case.

When a Kaeon FUSION script begins, the language itself has no functionality save for a command called "use".
The use command takes the names of plugin modules for the interpreter called "interfaces" and dynamically integrates them at runtime,
thus its primary paradigm could most accurately be described as "purely reflective".

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
Kaeon FUSION is sort of like [LISP](https://en.wikipedia.org/wiki/Lisp_(programming_language)) with respect to serving as a platform for domain specific languages (DSLs),
but where multiple DSLs may be used at once and may be changed mid runtime.

Another similarity that Kaeon FUSION shares with LISP is that it uses a code-as-data / data-as-code paradigm,
though unlike LISP its code is abstracted as a doubly linked acyclic tree of strings as opposed to a singly linked list of strings and other such lists.
The syntax that Kaeon FUSION uses to represent this tree,
which can be used independently of Kaeon FUSION as a markup language,
is called [ONE+](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/Kaeon%20FUSION/Documentation/1%20-%20Foundations/2%20-%20ONE%2B/README.md),
and is a syntactic superset of,
but semantic equivalent to a simpler langauge called [ONE](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/Kaeon%20FUSION/Documentation/1%20-%20Foundations/1%20-%20ONE/README.md).

ONE+ bears a strong visual resemblance to YAML,
and shares some elements of JSON and Python,
but it also supports a modular preprocessor system.
Like Kaeon FUSION's interfaces,
ONE+'s preprocessor can dynamically integrate external modules into itself during parsing,
allowing for an indefinite amount of syntactic customization.

Currently,
the most important interface available for Kaeon FUSION is the standard interface.

The standard interface provides all of the functionality expected of a modern,
Turing complete programming language as well as support for object orientation and more.

Though Kaeon FUSION can in theory adapt to just about anything,
its most obvious applications are where new languages need to be developed,
where systems need to be dramatically modified at runtime,
where projects are expected to have requirements that will change in unpredictable ways,
for programming language research,
for use as a scripting language,
and for use as a transpilation target.

Of course,
it can certainly be a lot fun to play around with the dynamic aspects of the language,
if for no other reason than one's own amusement,
so it's also a great choice for hobbyists and enthusiasts.

Unfortunately, the current implementation of Kaeon FUSION has a long way to go before it's ready for use in production.
That said,
it serves as a powerful proof of concept demonstrating that it is possible to have a language that can dynamically alter its own properties and paradigms,
and can thereby adapt to any use case,
whether during a program's development or while said program is running.

Additionally,
ONE+ is more than ready for use as a semantically simpler and more customizable alternative to existing markup languages such as XML and JSON.

Furthermore,
all of this is open source,
so it's inevitable that Kaeon FUSION will see dramatic improvements with further investment.
And given Kaeon FUSION's dynamic nature,
there's virtually nothing about it that can't be altered if necessary,
hence the tagline "a language without limits".

<h2 align="center">Kaeon FUSION Examples</h2>

Here's the solution to [Euler problem #1](https://projecteuler.net/problem=1),
written in [ONE+](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/Kaeon%20FUSION/Documentation/1%20-%20Foundations/2%20-%20ONE%2B/README.md),
the markup language Kaeon FUSION uses as its syntax,
and with the Super Mode directive acting as a preprocessor, causing the syntax to superficially resemble Python:

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

A JavaScript based interpreter for Kaeon FUSION comes with the Kaeon United framework,
which you can find [here](https://github.com/Gallery-of-Kaeon/Kaeon-United).

The Java version of Kaeon FUSION,
which is now depricated,
has a legacy IDE called Kaeon Origin,
which you can find [here](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/Kaeon%20FUSION/IDE/README.md).

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

_NOTE: These are for the Java version of Kaeon FUSION, which is now depricated._

### Is Kaeon FUSION a LISP?

Despite the superficial similarites Kaeon FUSION is not in the LISP family,
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

Our Java implementation of Kaeon FUSION is now depricated,
and moving forward,
we plan to implement all of Kaeon FUSION components in JavaScript.

### Can the Kaeon FUSION interpreter be embedded into a larger project?

Yes,
this is possible.
Refer to the [APIs](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/tree/master/Kaeon%20FUSION/APIs) section for the necessary files.

Here's an example of how one would start the interpreter from JavaScript:

	let sourceCodeString = "# my source code";

    (new require("./KaeonFUSION.js").KaeonFUSION()).process(require("./ONEPlus.js").readONEPlus(sourceCodeString));

The implementation of the Kaeon FUSION interpreter is built on Philosopher's Stones.
You can read about Philosopher's Stones [here](https://github.com/Gallery-of-Kaeon/Philosophers-Stone/blob/master/README.md).
It is important to understand Philosopher's Stones in order to take full advantage of the APIs.

### How long has Kaeon FUSION been in development?

Kaeon FUSION has officially been in development since December 15, 2016,
and was built upon things that were in development for around one and a half years before the project began.

### What's next for Kaeon FUSION?

We are currently focusing on bringing Kaeon FUSION's JavaScript implementation up to date with the current spcification as well as updating the modular components of the specification itself.
We are also looking into how to improve the interpreter's performance.

Furthermore, we are currently working on re-writing outdated portions of the documentation.

### I would like to use Kaeon FUSION in a commercial project. Who do I make the check out to?

Kaeon FUSION is licensed under the [Apache License 2.0](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/LICENSE.md).
Therefore,
the contents of this repository are free for everyone to use and make modifications to.

### Any fun facts about Kaeon FUSION?

Kaeon FUSION actually has a mascot, as do all projects under the Kaeon name:
the ACE.
Both the male version and the female version of the character are shown on the profile picture of the Gallery-of-Kaeon github account.

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

The Java version of the stack interface makes use of the [require1k](https://github.com/Stuk/require1k) JavaScript utility.

The Kaeon Origin IDE makes use of a [swing layout](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/Kaeon%20FUSION/IDE/Source/Source/kaeon_origin/ide/utilities/web/VerticalLayout.java) developed by Colin Mummery.

<h2 align="center">Disclaimer</h2>

The core of Kaeon FUSION's specification is more or less complete,
but the implementation,
the documentation,
and the modular parts of the specification are works in progress.

Kaeon FUSION is officially defined by it's specification,
and any implentation,
ours or otherwise,
is valid insofar as it conforms to the specification.

The specification itself is composed of several documents written in either ONE or ONE+,
the same markup formats Kaeon FUSION itself uses.
These documents are arranged into numbered folders,
and folders with lower numbers take precedence over folders with higher numbers,
so if there is any inconsistency in the specification,
one document can overrule another based on its placement.

The specification can be updated at any time,
in any way,
and for any reason,
and thus any existing implementation or documentation will have to pivot in order to stay up to date.