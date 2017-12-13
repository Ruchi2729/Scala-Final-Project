import org.scalatest.{FlatSpec, Matchers}

class mapReduceSpec extends FlatSpec with Matchers {
  val dataPath="C:\\Users\\sweta\\Desktop\\export.csv"
  behavior of "Map reduce"

  it should "test total bookings  by each user" in {
    val result = MapReduce.getNumberOfBookingsForEachuser(dataPath)
    result.count() should equal(31887)
    //    result.foreach(rs => rs  should matchPattern (String,))
  }

  it should "test total bookings  in every month" in {
    val result = MapReduce.getNumberOfBookingsEveryMonth(dataPath)
    val firstResult = result.first()
    firstResult should matchPattern {
      case (a:String, b:Int) =>
    }
  }

  it should "test total bookings  in every country" in {
    val result = MapReduce.getNumberOfBookingsForEachCountry(dataPath)
    val firstResult=result.first()
    firstResult should matchPattern {
      case ("4",22225) =>
    }
  }
}