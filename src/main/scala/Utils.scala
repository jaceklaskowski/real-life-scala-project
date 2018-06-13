object Utils {

  // this loads the entire content of a file into memory
  // CAUTION: heavy on memory
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

  case class Header(line: String) {
    val prefix = "file number: "
    val fileNumber = line.substring(prefix.length).toLong
  }

  case class FileContent(header: Header, lines: Iterator[String])
  object FileContent {
    def apply(content: Iterator[String]): FileContent = {
      val header = Header(content.take(1).toList.head)
      val lines = content
      FileContent(header, lines)
    }
  }
}
