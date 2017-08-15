# Kaeon FUSION

Kaeon FUSION is the de-facto standard dialect of the FUSION system.

## Functionality

Kaeon FUSION has no inherent functionality save for the ability to expand its functionality at runtime.
Functionality for Kaeon FUSION is programmed into units called interfaces.

If an interface is availiable within the environment Kaeon FUSION is running in,
it can be incorporated with an element whose content matches the name of the interface nested within an element containing the content "Use".

For example:

    Use: Standard

will incorporate the standard interface if it is available.