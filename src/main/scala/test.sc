
import collection.mutable
import java.io._
private val tasks = mutable.Map[String, List[String]]("Mark" -> List("Make videos", "eat", "sleep", "code"))


//println(tasks("Mark").patch(1,"todo",3))
//println(tasks)


/*class querys(username:String) {
  val query = mutable.Map[String, mutable.Map[String,String]](username -> mutable.Map("from" -> "", "Pattern" -> "", "Value" -> ""))
  def getquery {
    println(query(username)("Value"))
  }
  def setQuery(from: String, pattern: String, value: String) {
    query(username)("from") = value
    query(username)("Pattern") = value
    query(username)("Value") = value

  }

  
}*/

//val querytest = new querys("Mark")
//val query2 = new querys("hann", "UST", "Value", "> 0.8")

//querytest.setValue("95")

//querytest.getquery


val query1 = mutable.Map[String, mutable.Map[String,String]]("Mark" -> mutable.Map("from" -> "UST", "Pattern" -> "Value", "Value" -> "< 0.8"))
query1 += ("han" -> mutable.Map("from" -> "Luna", "Pattern" -> "Up", "Value" -> "<0.8"))
println(query1)
val query2 = mutable.Map[String, String]("From"-> "", "Pattern" -> "Value", "Value" -> "< 1", "Peroid" -> "20s", "AND_OR" -> "AND")

val pw = new PrintWriter(new File("hello.txt"))
pw.write(query2.mkString(" | ").replace("->",":"))
pw.close

