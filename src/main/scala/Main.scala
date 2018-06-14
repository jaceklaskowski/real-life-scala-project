import com.typesafe.scalalogging.LazyLogging

object Main extends App with LazyLogging {

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
    logger.error("Something went wrong. Exiting")
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

  logger.debug(s">>> Files under ${_path} directory:")
  val filesCount = files.length
  if (filesCount == 0) {
    logger.info(_noFilesMsg)
    sys.exit(0)
  } else {
    files.foreach(println)
  }

  logger.debug(">>> Loading the content of the files")
  // Q: What to do with files with incorrect headers?
  // Q: What if you read 1MM of files and 1MM+1 file is corrupted?
  import java.nio.file.Path
  files
    .flatMap { p: Path => FileContent.fromFile(p) } // <-- skips files when fromFile == None
    .sortBy(fc => fc.header.fileNumber)
    .foreach(println)

  logger.info("\n >>> The app worked fine...you can continue")

}

case class MyConfig(
  // --path
  // -p
  path: String = "src/test/resources/",

  // --no-files-msg
  // -m
  noFilesMsg: String = "No files")
