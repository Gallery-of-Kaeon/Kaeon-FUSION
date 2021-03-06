[Home](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/Kaeon%20FUSION/Documentation/Guide/X%20-%20Kaeon%20FUSION%20for%20Beginners/README.md) /
[Previous](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/Kaeon%20FUSION/Documentation/Guide/X%20-%20Kaeon%20FUSION%20for%20Beginners/2%20-%20Getting%20Comfortable%20in%20Kaeon%20FUSION/README.md)

# Advanced Kaeon FUSION

Now that you're comfortable with the basics,
it's time to learn about some of the more advnaced things you can do with the Kaeon FUSION Standard Interface.

## Contents

[1 - Concurrency and Time](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/Kaeon%20FUSION/Documentation/Guide/X%20-%20Kaeon%20FUSION%20for%20Beginners/3%20-%20Advanced%20Kaeon%20FUSION/README.md#1---concurrency-and-time)

[2 - Metaprogramming](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/Kaeon%20FUSION/Documentation/Guide/X%20-%20Kaeon%20FUSION%20for%20Beginners/3%20-%20Advanced%20Kaeon%20FUSION/README.md#2---metaprogramming)

[3 - Building](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/Kaeon%20FUSION/Documentation/Guide/X%20-%20Kaeon%20FUSION%20for%20Beginners/3%20-%20Advanced%20Kaeon%20FUSION/README.md#3---building)

## 1 - Concurrency and Time

A program does one thing and then moves onto the next.
A simple enough principle to understand,
but often inconvenient.
The fact is that there are several situation that can arise in programming that require the program to do multiple things at the same time.

The Kaeon FUSION Standard Interface allows you to split the flow of the program using the split command.
Children of the split command will execute while the rest of moves on.

For example, running:

    Use: Standard

    Split
	
    	i { 1 } Scope
		
    		Log Line: i
		
    		i: Add: i, 1
    		Loop: Less or Equal: i, 5

    i { 6 } Scope
	
    	Log Line: i
	
    	i: Add: i, 1
    	Loop: Less or Equal: i, 10

will display:

    1
    6
    2
    7
    3
    8
    4
    9
    5
    10

_Note: When dealing with two things running at once, it's hard to pinpoint exactly what will display first, so what displays when you run the above code may vary slightly._

In addition,
sometimes it is necessary to pause the flow of a program.
You can do this using the wait command.
The wait command has a child that returns a number,
and it will pause the program for the number of seconds that was returned to it.

For example, running:

    Use: Standard

    Log Line: a
    Wait: 1

    Log Line: b

will display:

    a

and pause for 1 second.
Then,
the program will display:

    a
    b

It is also useful to use the time command to get the number of seconds on the computer's clock.

For example, running:

    Use: Standard

    start: Time
    Wait: 1
    end: Time

    Log Line: Subtract: end, start

will display:

    1

## 2 - Metaprogramming

If you make a string containing Kaeon FUSION code,
you can run that code using the Kaeon FUSION Standard Interface's execute command.

For example,
if you have a file called "My File.txt" which contains:

    Log Line: "Hello, world!"

then running:

    Use: Standard

    Execute: Open: My File.txt

will display:

    Hello, world!

In addition,
you can run command line arguments using the run command.

For example,
if you're on Windows,
running:

    Use: Standard

    Run: Notepad

will open the Notepad application.

## 3 - Building

Perhaps the most important thing you can do using the Kaeon FUSION Standard Interface is generate code in other languages without actually having to write any code in other languages.

Different dialects of ONE that correspond to different development domains can be provided by other interfaces,
like the [stack interface](https://github.com/Gallery-of-Kaeon/Kaeon-FUSION/blob/master/Kaeon%20FUSION/Documentation/Guide/4%20-%20Stack%20Interface/README.md).
Using these dialects,
you can write ONE+ code that doesn't do anything in Kaeon FUSION but can work with Kaeon FUSION to build something like a website.

The first step is to define a function and write ONE+ code within the function that corresponds to the desired dialect.
Then,
use the call command.
The call command should have one child with the content "Build",
a second child specifying the name of the desired dialect,
a third that returns a list of strings written in said dialect,
and an optional fourth that specifies additional information.

For example, running:

    Use: Standard, Stack

    My Website

    	-
    		Head: Title: My Website
    		Text: 'Hello, world!'
    	-

    Call: Build, XML, List { My Website }, List { "", List { }, HTML }

_HTML is the language used to define what a website looks like. It is an extended form of a langauge called XML, which is just used to store data._

will generate an html file in the local directory containing the following html code:

    <!DOCTYPE html>
    <html>
    	<head>
    		<title>
    			My Website
    		</title>
    	<head>
    	<body>
    		<p>
    			Hello, world!
    		</p>
    	</body>
    </html>

If opened in a browser,
the tab the website is open in will have the title "My Website",
and the page itself will have the text "Hello, world!" in the upper left corner.

_Further documentation on the stack interface will be coming soon._