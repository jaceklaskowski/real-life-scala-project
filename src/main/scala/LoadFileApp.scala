import scala.io.Source

object LoadFileApp extends App {

  val file = "src/test/resources/1.txt"
  println(s"Printing lines from file $file:")
  Source
    .fromFile(file)
    .getLines()
    .zipWithIndex
    .map(beautify) // <-- conversion from pairs to lines with their numbers as strings
    .foreach(println) // <-- the action

  // Bonus exercise
  // How to print out lines with line numbers?
  // Hint: Look at Iterator scaladoc

  // Home exercise: Review Tuple2 scaladoc
  def beautify(tuple: (String, Int)): String = {
    val (line, number) = tuple // <-- pattern matching on assignment
    s"Line $number: $line"
  }

}
