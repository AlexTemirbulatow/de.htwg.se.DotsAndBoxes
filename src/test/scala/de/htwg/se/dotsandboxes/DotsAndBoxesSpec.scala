
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class DotsAndBoxesSpec extends AnyWordSpec:
  "dotsandboxes" should {
    "have a bar as String of form 'o------o------o------o------o------o------o'" in {
      bar() should be("o------o------o------o------o------o------o\n")
    }
    "have cells as String of form \n" + 
    "'|      |      |      |      |      |      |\n" +
    " |      |      |      |      |      |      |'" in {
      cells() should be(
        "|      |      |      |      |      |      |\n" +
        "|      |      |      |      |      |      |\n")
    }
    "have a scalable bar" in {
      bar(1, 1) should be("o-o\n")
      bar(2, 2) should be("o--o--o\n")
      bar(6, 3) should be("o------o------o------o\n")
    }
    "have scalable cells" in {
      cells(1, 1, 1) should be(
        "| |\n")
      cells(2, 2, 2) should be(
        "|  |  |\n" +
        "|  |  |\n")
      cells(6, 3, 2) should be(
        "|      |      |      |\n" +
        "|      |      |      |\n")
    }
    "have a mesh in the form \n" +
      "o---o---o---o\n" +
      "|   |   |   |\n" +
      "o---o---o---o\n" +
      "|   |   |   |\n" +
      "o---o---o---o\n" +
      "|   |   |   |\n" +
      "o---o---o---o\n" in {
        mesh(3, 3, 1) should be(
            "o---o---o---o\n" +
            "|   |   |   |\n" + 
            "o---o---o---o\n" +
            "|   |   |   |\n" + 
            "o---o---o---o\n" +
            "|   |   |   |\n" + 
            "o---o---o---o\n")
      }
  }
