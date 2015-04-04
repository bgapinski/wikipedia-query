## Wikipedia Query
A simple command line tool that provides basic information
(provided by Wikipedia) on a user supplied topic.

## Running
You can use [sbt](http://www.scala-sbt.org/index.html) to run
the program. It will automatically install the dependencies.

There are two ways to make queries:
* Enter `sbt run` and you will be prompted for a topic.
* Pass a command line argument into the program using something of
  the form `sbt "run <topic>"`

## About
The program is straight forward. It either reads the topic from
the command line or prompts the user if there is none. It then
attempts to connect to the relevant Wikipedia page. If it
fails to connect or encounters any other error in the process,
it will return "Not found.". Otherwise it will parse the response
to get the first paragraph from Wikipedia. We make use of the
fact that the main content of Wikipedia articles are wrapped
in a div with the id 'mw-content-text'.

This project makes use of [jsoup](http://jsoup.org/), an html
parsing library. It is a well documented, straight forward library
that addresses the tasks I needed (getting the contents of a webpage
and pulling out the first paragraph). This project also makes
use of Scala's [Try](http://www.scala-lang.org/files/archive/nightly/docs/library/index.html#scala.util.Try)
library to handle any exception we might get when connecting to
a page (such as a 404, 503, etc).
