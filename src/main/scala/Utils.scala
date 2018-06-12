object Utils {

  def loadFile(name: String): String = {
    import scala.io.Source
    Source.fromFile(name).mkString
  }

}
