
object ScalaApp extends App {

  // What's the next step a Scala dev *could* do to make sure the project is set up fine?
  println("It works!")

  // Print out all the files under src/test/resources
  // Please note that we skipped the round brackets at the end of toPath
  // val dir = new File("src/test/resources/").toPath
  val path = "src/test/resources/"
  import java.nio.file.{Files, Paths}
  val dir = Paths.get(path)
  import scala.collection.JavaConverters._
  // Please note the underline to denote a Scala implicit conversion
  // It's like an extension method in C#
  import scala.collection.Iterator
  import java.nio.file.Path
  val files: Iterator[Path] = Files.list(dir).iterator().asScala

  // Now you're in Scala Collection API world
  // Iterator[Path] <-- this is Scala! (not Sparta :))
  println(s"Files under $path directory:")
  val filesCount = files.length
  if (filesCount == 0) {
    println("No files")
  } else {
    files.foreach(println)
  }
}
