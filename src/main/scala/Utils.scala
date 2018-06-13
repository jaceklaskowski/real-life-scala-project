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
  type HeaderV2 = String // <-- any String can be a Header
  type Lines = Iterator[String] // we do not enforce proper types for our "metadata"
  // you'll be using it very rarely
  def headerAndLinesV2(content: Iterator[String]): (HeaderV2, Lines) = {
    headerAndLinesV1(content)
  }

  // How to enforce types so headerAndLinesV3 would only return Headers not Strings?
  // So, Strings != Headers (like Longs != Ints)
  // hint: Scala is an object-oriented programming language
  case class Header(line: String) {
    val prefix = "file number: "
    val fileNumber = line.substring(prefix.length).toLong

  } // <-- Header contains a String = a "has-a" relationship

  // we're now to replace a tuple to a concrete type that says something about the fields
  case class FileContent(header: Header, lines: Iterator[String])
  object FileContent { // <-- a companion object
    // a factory method to create objects of the companion class
    def apply(lines: Iterator[String]): FileContent = Utils.headerAndLinesV3(lines)
  }

  def headerAndLinesV3(content: Iterator[String]): FileContent = {
    val (header, lines) = headerAndLinesV2(content)
    FileContent(Header(header), lines)
  }
}
