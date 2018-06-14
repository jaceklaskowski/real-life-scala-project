case class Header(fileNumber: Long)
object Header {
  def create(line: String): Option[Header] = {
    val header = """file number: (\d+)""".r // <-- note the little .r at the end
    line match {
      case header(fn) => Some(Header(fn.toLong))
      case _ => None
    }
  }
}

case class FileContent(header: Header, lines: Iterator[String])
object FileContent {
  def apply(content: Iterator[String]): Option[FileContent] = {
    for {
      header <- Header.create(content.take(1).toList.head)
      lines = content
    } yield FileContent(header, lines)
  }

  def fromFile(name: String): Option[FileContent] = {
    import scala.io.Source
    val lines = Source.fromFile(name).getLines()
    apply(lines)
  }

  import java.nio.file.Path
  def fromFile(path: Path): Option[FileContent] = {
    fromFile(path.toString)
  }
}
