class  FactoriaMemoiz {
  var cache: Map[Int,Int] = Map()

  def lookup(num: Int): Int = cache.getOrElse(num,0)

  def calcFactMemoiz(x: Int): Int = {
    if(x == 0 || x == 1) 1
    else if (lookup(x) > 0) {
      println("Peforming lookup")
      lookup(x)
    } else {
      println("Peformnig calc")
      val factorial =  calcFactMemoiz((x-2))+ calcFactMemoiz((x -1))
      cache += x -> factorial
      factorial
    }
  }
}
  object FactoriaMemoizApp extends App {
    val factMem = new FactoriaMemoiz()
    println(factMem.calcFactMemoiz(5))
    println(factMem.calcFactMemoiz(6))
    

  }


