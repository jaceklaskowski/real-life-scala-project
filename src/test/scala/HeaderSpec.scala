import org.scalatest.{FlatSpec, Matchers}

class HeaderSpec extends FlatSpec with Matchers {

  "A Header" should "not be created for incorrect lines" in {
    // line has to be longer than "file number:"
    {
      val header = Header.create(line = "")
      header shouldBe None
    }
    {
      val header = Header.create(line = "<empty>")
      header shouldBe None
    }
  }

  it should "have the file number property once created" in {
    val header = Header.create(line = "file number: 100")
    header shouldBe defined
    header.get.fileNumber should be(100)
  }

  it should "only accept headers with 'file number:' prefix" in {
    val header = Header.create(line = "xxxx number: 100")
    header shouldBe empty
  }
}
