case class Header(fileNumber: Long)
object Header {
  def apply(line: String): Option[Header] = {
    val prefix = "file number: "
    if (line.startsWith(prefix)) {
      import scala.util.Try
      Try(line.substring(prefix.length).toLong).toOption.map { fileNumber =>
        Header(fileNumber)
      }
    } else None
  }
}

case class FileContent(header: Header, lines: Iterator[String])
object FileContent {
  def apply(content: Iterator[String]): Option[FileContent] = {
    for {
      header <- Header(content.take(1).toList.head)
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
