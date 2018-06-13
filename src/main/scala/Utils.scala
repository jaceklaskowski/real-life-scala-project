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

  def fromFile(name: String): FileContent = {
    import scala.io.Source
    val lines = Source.fromFile(name).getLines()
    apply(lines)
  }

  import java.nio.file.Path
  def fromFile(path: Path): FileContent = {
    fromFile(path.toString)
  }
}
