object Main extends App {

  val path = "src/test/resources/"
  import java.nio.file.{Files, Paths}
  val dir = Paths.get(path)
  import scala.collection.JavaConverters._
  val files = Files.list(dir).iterator().asScala.toList

  println(s">>> Files under $path directory:")
  val filesCount = files.length
  if (filesCount == 0) {
    println("No files")
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
