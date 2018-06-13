import Utils.FileContent

object ParseFileApp extends App {

  // Files with a header and a body
  // a header == the first line
  // a header == "file number:[SPACE][NUMBER]"
  // Be careful: Using toList makes the data structure memory-heavy
  // toList loads the elements of an Iterator
  // it's eager!
  // Let's be clever

  // a header == the first line
  val lines = Utils.readFile("src/test/resources/100PB.txt")

  // pattern matching on assignment
  // destructure a tuple into its elements
  // What do you think should be the owner of headerAndLinesV3?
  // Whenever you see a code pattern like Utils.createABC => createABC is a constructor of ABC
  // In Java is just a ABC constructor or an ABC factory
  // In Scala you do it using object with the factory method
  // In Scala factory methods are usually (if not always) done with apply's
  // FileContent.apply == FileContent
  val FileContent(header, content) = FileContent(lines)

  println(s"header: $header")

  val fileNumber = header.fileNumber
  println(s"File number: $fileNumber")

  // How to get at the content without the header?
  // And still be lazy (!)
  // Be careful: we're losing ONE line!
  // val content = lines.drop(1)

  content.foreach(println)

  println("\n >>> The app worked fine...you can continue")
}
