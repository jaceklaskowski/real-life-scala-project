import scala.io.Source

object LoadFileApp extends App {

  val file = "src/test/resources/1.txt"
  println(s"Printing lines from file $file:")
  Source
    .fromFile(file)
    .getLines()
    .foreach(println)

  // Bonus exercise
  // How to print out lines with line numbers?
  // Hint: Look at Seq scaladoc
}
