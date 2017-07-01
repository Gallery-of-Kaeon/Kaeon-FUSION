# Page Dialect

The Page dialect is a dialect of ONE analogous to HTML.

## Tags and Attributes

There is no explicit syntax for tags and attributes.
Certain keywords are automatically registered as tags or attributes and anything else is registered as raw text.
Any text surrounded by quotation marks is registered as raw text.
It is not necessary to declare a body tag in a Page document.
Anything not nested within the head tag is automatically in the body tag.

For example:

    Head: Title: My Website
    
    Text: Height { 10px }Hello

is analogous to the following HTML:

    <!DOCTYPE html>
    <html>
    	<head>
    		<title>My Website</title>
    	</head>
    	<body>
    		<p height="10px">Hello</p>
    	</body>
    </html>

## Styles, Scripts, and Processes

Code for the style,
script,
and process ONE dialects can be nested under elements named after the respective dialect.
If using the build command in Kaeon FUSION, such an element can instead have as a child the name of a function containing code written in that dialect.

## Tag Reference

The following list shows which keywords are recognized as tags and the HTML tags the correspond to:

Page | HTML
--- | ---
Acronym | abbr
Address | address
Alternate Ruby | rt
Area | area
Article | article
Aside | aside
Audio | audio
Base | base
Bi Directional Isolation | bdi
Bi Directional Override | bdo
Block Quote | blockquote
Body | body
Bold | b
Break | br
Break Opportunity | wbr
Button | button
Canvas | canvas
Caption | caption
Cell | td
Cite | cite
Code | code
Column | col
Column Group | colgroup
Data | data
Data List | datalist
Delete | del
Define Instance | dfn
Description | dd
Description List | dl
Details | details
Dialog | dialog
Division | div
Embed | embed
Emphasize | em
Fieldset | fieldset
Figure | figure
Figure Caption | figcaption
Footer | footer
Form | form
Head | head
Header | header
Header Cell | th
Heading | h1
Image | img
Import | link
Inline Frame | iframe
Input | input
Insert | ins
Italic | i
Item | li
Key Generator | keygen
Keyboard | kbd
Label | label
Legend | legend
Link | a
List | ul
Main | main
Map | map
Mark | mark
Menu | menu
Menu Item | menuitem
Metadata | meta
Meter | meter
Navigation | nav
No Ruby | rp
No Script | noscript
Object | object
Option | option
Option Group | optgroup
Ordered List | ol
Output | output
Parameter | param
Picture | picture
Preformatted | pre
Progress | progress
Quote | q
Resource | source
Ruby | ruby
Sample | samp
Section | section
Select | select
Small | small
Span | span
Strong | strong
Struck | s
Subscript | sub
Summary | summary
Superscript | sup
Table | table
Table Body | tbody
Table Foot | tfoot
Table Head | thead
Table Row | tr
Term | dt
Text | p
Text Area | textarea
Theme Change | hr
Time | time
Title | title
Track | track
Underline | u
Variable | var
Video | video

## Attribute Reference

The following list shows which keywords are recognized as tags and the HTML tags the correspond to:

Page | HTML
--- | ---
Accept | accept
Accept Charset | accept-charset
Access Key | access-key
Action | action
Alternate | alt
Asynchronous | async
Autocomplete | autocomplete
Autofocus | autofocus
Autoplay | autoplay
Challenge | challenge
Character Set | charset
Challenge | challenge
Checked | checked
Cite | cite
Class | class
Columns | cols
Column Span | colspan
Content | content
Context Menu | contextmenu
Controls | controls
Coordinates | coords
Custom Data | data-*
Data | data
Date and Time | datatime
Default | default
Defer | defer
Direction | dir
Direction Name | dirname
Disabled | disabled
Download | download
Draggable | draggable
Drop Zone | dropzone
Editable | contenteditable
Encode Type | enctype
For | for
Form | form
Form Action | formaction
Headers | headers
Height | height
Hidden | hidden
High | high
HTTP Equivalent | http-equiv
ID | id
Image Server Map | ismap
Key Type | keytype
Kind | kind
Label | label
Language | lang
Input List | klist
Loop | loop
Low | low
Maximum | max
Maximum Length | maxlength
Media | media
Method | method
Minimum | min
Multiple | multiple
Muted | muted
Name | name
No Validate | novalidate
On Abort | onabort
On After Print | onafterprint
On Before Print | onbeforeprint
On Before Unload | onbeforeunload
On Blur | onblur
On Can Play | oncanplay
On Can Play Through | oncanplaythrough
On Change | onchange
On Click | onclick
On Context Menu | oncontextmenu
On Copy | oncopy
On Cue Change | oncuechange
On Cut | oncut
On Double Click | ondbclick
On Drag | ondrag
On Drag Center | ondragcenter
On Drag Leave | ondragleave
On Drag Over | ondragover
On Drag Start | ondragstart
On Drop | ondrop
On Duration Change | ondurationchange
On Emptied | onemptied
On Ended | onended
On Error | onerror
On Focus | onfocus
On Hash Change | onhashchange
On Input | oninput
On Invalid | oninvalid
On Key Down | onkeydown
On Key Press | onkeypress
On Key Up | onkeyup
On Load | onload
On Loaded Data | onloadeddata
On Loaded Metadata | onloadedmetadata
On Load Start | onloadstart
On Mouse Down | onmousedown
On Mouse Move | onmousemove
On Mouse Out | onmouseout
On Mouse Over | onmouseover
On Mouse Up | onmouseup
On Mouse Wheel | onmousewheel
On Offline | onoffline
On Online | ononline
On Page Hide | onpagehide
On Page Show | onpageshow
On Paste | onpaste
On Pause | onpause
On Play | onplay
On Playing | onplaying
On Pop State | onpopstate
On Progress | onprogess
On Rate Change | onratechange
On Reset | onreset
On Resize | onresize
On Scroll | onscroll
On Search | onsearch
On Seeked | onseeked
On Seeking | onseeking
On Select | onselect
On Show | onshow
On Stalled | onstalled
On Storage | onstorage
On Submit | onsubmit
On Suspend | onsuspend
On Time Update | ontimeupdate
On Toggle | ontoggle
On Unload | onunload
On Volume Change | onvolumechange
On Waiting | onwaiting
On Wheel | onwheel
Open | open
Optimum | optimum
Pattern | pattern
Pattern | pattern
Pattern | pattern
Placeholder | placeholder
Poster | poster
Preload | preload
Read Only | readonly
Reference | href
Reference Language | hreflang
Relationship | rel
Required | required
Reversed | reversed
Rows | rows
Row Span | rowspan
Sandbox | sandbox
Scope | scope
Scoped | Scoped
Selected | Selected
Shape | shape
Size | size
Sizes | sizes
Span | span
Spellcheck | spellcheck
Source | src
Source Document | srcdoc
Source Language | srclang
Start | start
Step | step
Tab Index | tabindex
Target | target
Title | title
Translate | translate
Type | type
Use Map | usemap
Value | value
Width | width
Wrap | wrap