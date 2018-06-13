import Utils.FileContent

object ParseFileApp extends App {

  val lines = Utils.readFile("src/test/resources/100PB.txt")
  val fc = FileContent(lines)
  println(s"header: ${fc.header}")

  println(s"File number: ${fc.header.fileNumber}")

  fc.lines.foreach(println)

  println("\n >>> The app worked fine...you can continue")
}
