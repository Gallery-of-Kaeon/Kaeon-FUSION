# The Kaeon FUSION Interface Development Framework

The Kaeon FUSION Interface SDK allows third parties to develop their own interfaces for use with Kaeon FUSION.
As the current implementation of Kaeon FUSION is implemented in Java,
the framework is based in Java.

## Framework Structure

A Kaeon FUSION interface must be written as a java project that includes the utilities listed [here]() and packaged as a non-runnable jar.

The project must have a class called "KaeonFUSIONInterfaceModule" in a package called "kaeon_fusion_interface".
This class must have a method called "getInterface" with one parameter of type KaeonFUSION (provided by the aforementioned utilities).