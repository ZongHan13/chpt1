import Array._
import scala.util.control.Breaks._
val aa = Array(
  "<3",
  ">2",
  ">4",
  "<5",
  ">8",
  "<11",
  "<14",
  ">7",
  "<22",
  ">25",
  "<30",
  "<27"
)
println(aa(0).substring(1))

val bb = aa.sortWith(_.substring(1).toFloat < _.substring(1).toFloat)

println(bb.mkString(", "))

val cc: Float = 13

def greater(input_value: Float, num: Float): Boolean = {
  input_value > num

}
def smaller(input_value: Float, num: Float): Boolean = {
  input_value < num
}

println(cc)

var bigger: Array[String] = Array[String]()
var smaller: Array[String] = Array[String]()

breakable {
  for (i <- 0 to bb.length - 1) {
    bb(i).substring(0, 1) match {
      case ">" =>
        if (cc > bb(i).substring(1).toFloat == false) {
          println(i + "!")
          for (j <- i - 1 to (0, -1)) {
            if (bb(j).substring(0, 1) == ">") {
              println(bb(j))
              bigger = bigger :+ bb(j)
            }
          }

          for (k <- i to bb.length - 1) {
            if (bb(k).substring(0, 1) == "<") {
              println(bb(k))
              smaller = smaller :+ bb(k)
            }
          }
          break
        }
      case "<" =>
        if (cc < bb(i).substring(1).toFloat == true) {
          for (j <- i to (0, -1)) {
            if (bb(j).substring(0, 1) == ">") {
              println(bb(j))
              bigger = bigger :+ bb(j)
            }
          }
          // println(i +" here")
          for (k <- i to bb.length - 1) {
            if (bb(k).substring(0, 1) == "<") {
              println(bb(k))
              smaller = smaller :+ bb(k)
            }
          }
          break
        }
    }
  }
}
var output_1 = (bigger :+ ("↓" + cc.toString)) ++ smaller

println(output_1.sortBy(_.substring(1).toFloat)(Ordering[Float]).mkString(", "))
