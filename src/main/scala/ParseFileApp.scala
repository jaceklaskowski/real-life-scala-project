object ParseFileApp extends App {

  val fc = FileContent.fromFile("src/test/resources/100PB.txt")
  println(s"header: ${fc.get.header}")

  println(s"File number: ${fc.get.header.fileNumber}")

  fc.get.lines.foreach(println)

  println("\n >>> The app worked fine...you can continue")
}
