import org.jsoup._
import scala.util.{Try, Success, Failure}

/** A simple object to make topic queries against Wikipedia. */
object WikipediaQuery {

  /** The wikipedia url where we can make our queries. */
  val wikipedia = "http://en.wikipedia.org/wiki/"

  /** Query wikipedia with the given topic and returns
   *  the first paragraph. Will return "Not found." 
   *  if there is no wikipedia article for the topic.
   *
   *  @param topic the topic to query for
   *  @return The result of the query. This will
   *          be the text of the first paragraph on
   *          success and "Not found." on a failure.
   */
  def query (topic: String): String =
    Try(Jsoup.connect(wikipedia + topic).get) match {
      case Failure(_) => "Not found."

      // Wikipedia stores the content of the article in
      // a div with the id 'mw-content-text'.
      case Success(d) => d.body.select("div#mw-content-text > p").first.text
    }

  /** Makes a query to wikipedia and prints the result.
   *
   *  Usage: This will make a query using the
   *         first command line argument or prompt
   *         the user for a topic if no arguments
   *         are present.
   */
  def main(args: Array[String]) {
    val topic = (if (args.length > 0) {
      args(0)
    } else {
      print("Please enter a search query: ")
      readLine
    }).replace(" ", "_")
    println(query(topic))
  }
}
