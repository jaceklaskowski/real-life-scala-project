object ParseFileApp extends App {

  val fc = FileContent.fromFile("src/test/resources/100PB.txt")
  println(s"header: ${fc.header}")

  println(s"File number: ${fc.header.fileNumber}")

  fc.lines.foreach(println)

  println("\n >>> The app worked fine...you can continue")
}
