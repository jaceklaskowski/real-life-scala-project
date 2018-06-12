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
  // avoiding _1 and _2
  val (header, content) = Utils.headerAndLinesV1(lines)

  // Home exercise: Is Iterator.toSeq still lazy?
  println(s"header: $header")

  // time for some parsing
  // Open StringOps and find a method to strip "file number: " from a string
  // val fileNumber = header.split(": ").tail.head
  val prefix = "file number: "
  val fileNumber = header.substring(prefix.length).toLong
  println(s"File number: $fileNumber")

  // How to get at the content without the header?
  // And still be lazy (!)
  // Be careful: we're losing ONE line!
  // val content = lines.drop(1)

  content.foreach(println)
}
