import scala.io.Source
import collection.mutable
import scala.reflect.ClassTag
import scala.annotation.varargs
import Array._
import scala.util.control.Breaks._

val filename1 = "/home/pcdm/hann/play-scala/play-scala-seed/Mark.txt"
val txt1 = Source.fromFile(filename1).mkString
println(txt1)