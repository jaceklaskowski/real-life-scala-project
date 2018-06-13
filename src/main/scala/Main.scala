
object Main extends App {

  // How to support two options + --help?

  val parser = new scopt.OptionParser[MyConfig]("runMain Main") {
    head("Main", "1.x")

    // --path src/test/resources/
    // -p src/test/resources/
    opt[String]('p', "path")
      .action((x, mc) => mc.copy(path = x))
      .text("path to the files to process")

    opt[String]('m', "no-files-msg")
      .action((x, mc) => mc.copy(noFilesMsg = x)) // FIXME Explain case class + copy constructor
      .text("message for 'No files'")

    // --help
    help("help").text("Prints help")
  }

  // this is Option type
  // no value = -1 or null ==> None
  // some value ==> Some(value)
  val myConfig = parser.parse(args, MyConfig()).getOrElse { // FIXME Explain Option type + getOrElse
    // this code is executed only when parsing fails
    println("Something went wrong. Exiting")
    sys.exit(-1) // FIXME Explain sys object
  }

  val _path = myConfig.path
  val _noFilesMsg = myConfig.noFilesMsg

  import java.nio.file.{Files, Paths}
  val dir = Paths.get(_path)
  import scala.collection.JavaConverters._
  import scala.util.Try
  // FIXME Explain Try
  // FIXME Explain Seq.empty without defining the type of elements (type inference)
  val files = Try(Files.list(dir).iterator().asScala.toList).getOrElse(Seq.empty)

  println(s">>> Files under ${_path} directory:")
  val filesCount = files.length
  if (filesCount == 0) {
    println(_noFilesMsg)
    sys.exit(0)
  } else {
    files.foreach(println)
  }

  println(">>> Loading the content of the files")
  files
    .map(FileContent.fromFile)
    .sortBy(fc => fc.header.fileNumber)
    .foreach(println)

  println("\n >>> The app worked fine...you can continue")

}

case class MyConfig(
  // --path
  // -p
  path: String = "src/test/resources/",

  // --no-files-msg
  // -m
  noFilesMsg: String = "No files")
