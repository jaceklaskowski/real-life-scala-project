
object Main extends App {

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
  val files = Files.list(dir).iterator().asScala.toList

  // Now you're in Scala Collection API world
  // Iterator[Path] <-- this is Scala! (not Sparta :))
  println(s">>> Files under $path directory:")
  // one should never use an iterator after calling a method on it.
  // Iterator is a one-pass data structure
  // Right after you consumed elements of it, you won't have them available again
  val filesCount = files.length
  if (filesCount == 0) {
    println("No files")
  } else {
    files.foreach(println)
  }

  // Two uses of _ (underscore) in Scala:
  // 1. eta-expansion to convert a method into a function
  // 2. a shortcut to reference input arguments in a function

  println(">>> Loading the content of the files")
  files
    .map(_.toString) // equivalent to whatever => whatever.toString
                     // one underscore == one input parameter
    .map(Utils.loadFile)
    .foreach(println)
}
