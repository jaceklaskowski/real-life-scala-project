object Utils {

  // this loads the entire content of a file into memory
  // heavy on memory
  def loadFile(name: String): String = {
    import scala.io.Source
    Source.fromFile(name).mkString
  }

  // this only marks the beginning of a file
  // and can give lines one at a time / at a request
  // memory-friendly
  def readFile(name: String): Iterator[String] = {
    import scala.io.Source
    Source.fromFile(name).getLines()
  }

  // Exercise: Write a method that gives the header and the lines
  // of a file
  // what's the signature?
  def headerAndLinesV1(content: Iterator[String]): (String, Iterator[String]) = {
    val header = content.take(1).toList.head
    val lines = content
    (header, lines)
  }

  // type alias
  type Header = String // <-- any String can be a Header
  type Lines = Iterator[String] // we do not enforce proper types for our "metadata"
  // you'll be using it very rarely
  def headerAndLinesV2(content: Iterator[String]): (Header, Lines) = {
    headerAndLinesV1(content)
  }
}
