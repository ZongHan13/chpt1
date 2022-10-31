
class ANode(_expression : String, _parentNode: Array[ANodeRoot] = null, _result:Boolean = false){
 var expression:String = _expression
 var parentNode = _parentNode
 var result: Boolean = _result

  def ChangeResult(_result: Boolean) = {
    result = _result
    propagate(result)
  }
  def propagate(toSend: Boolean) = {
    parentNode.foreach(x => x.operandArrindx += 1)
    parentNode.foreach(x => x.operandArr(x.operandArrindx) = toSend)
    parentNode.foreach(x => x.evaluate)

  }
}
class ANodeRoot(_expression : String, _parentNode: ANodeRoot = null ,_result:Boolean = false){
 var expression:String = _expression
 //var parentNode: ANode = _parentNode
 var parentNode = _parentNode
 var result: Boolean = _result
 var operandArr: Array[Boolean] = new Array[Boolean](expression.replace("^","").length())
 var operandArrindx :Int = -1
 def evaluate = {
  if (operandArrindx == expression.replace("^","").length() -1) {
    if (expression.contains("^")) {
      if (operandArr.contains(false)) {
        operandArrindx = 0
        result = false
        propagate(false)
        //operandArrindx = 0
        false
      } 
      else {
        operandArrindx = 0
        result = true
        propagate(true)
        //operandArrindx = 0
        true
      }
    }
  } else {
    "Not yet"
  }
 }
 def propagate(toSend:Boolean):Unit = {
  if(!(parentNode == null)) {
  //parentNode.operandArr(parentNode.operandArrindx) = toSend
  
  for(i <- parentNode.operandArrindx+1 to parentNode.operandArrindx + expression.replace("^","").length()) {
    parentNode.operandArr(i) = toSend
  }
  parentNode.operandArrindx += expression.replace("^","").length() 
  parentNode.evaluate  
}
  //parentNode.evalaute
 }
}


object arrnodetest extends App {
  
  
  val Node_A = new ANode("A", Array(new ANodeRoot("A^B", new ANodeRoot("A^B^C", _result = false),_result = false), new ANodeRoot("A^F",_result = false)) ,_result = false)
  val Node_B = new ANode("B", Array(Node_A.parentNode(0)), false)
  val Node_C = new ANode("C", Array(Node_B.parentNode(0).parentNode), false)
  val Node_D = new ANode("D", Array(new ANodeRoot("D^E", new ANodeRoot("A^B^C^D^E", _result = false), false)), false)
  val Node_E = new ANode("E", Array(Node_D.parentNode(0)), false)
  val Node_F = new ANode("F", Array(Node_A.parentNode(1)), false)
  Node_A.parentNode(0).parentNode.parentNode = Node_E.parentNode(0).parentNode
  //println(Node_A.parentNode.expression)
  //println(Node_A.parentNode == Node_B.parentNode)
  Node_A.ChangeResult(true)
  Node_B.ChangeResult(true)
  Node_C.ChangeResult(true)
  Node_D.ChangeResult(true)
  Node_E.ChangeResult(true)
  Node_F.ChangeResult(true)
  
  //Node_B.parentNode.evalaute
  //Node_C.parentNode.evalaute
  //Node_D.parentNode.evalaute
  //Node_A.parentNode.parentNode.parentNode.evalaute
  //Node_E.parentNode(0).parentNode.evalaute
  println(Node_B.parentNode(0).expression+ ": " + Node_B.parentNode(0).result)
  //println(Node_A.parentNode(0).parentNode.expression + ": " + Node_A.parentNode(0).parentNode.result)
  //println(Node_C.parentNode(0) == Node_A.parentNode(0).parentNode & Node_C.parentNode(0) == Node_B.parentNode(0).parentNode)
  println(Node_A.parentNode(0).expression + ":" + Node_A.parentNode(0).result)
  println(Node_C.parentNode(0).expression +": "  + Node_C.parentNode(0).result)
  //println("A^B^C".replace("^","").length())
  //println(Node_A.parentNode.parentNode.operandArr(2))
  //println(Node_D.result)
  println(Node_D.parentNode(0).expression + ": " + Node_D.parentNode(0).result)
  //println(Node_E.parentNode(0) == Node_D.parentNode(0))
  println(Node_A.parentNode(0).parentNode.parentNode.expression + ": " + Node_A.parentNode(0).parentNode.parentNode.result)
  //Node_E.parentNode(0).parentNode.operandArr.foreach(x => println(x))
  
  println(Node_F.parentNode(0).expression + ":" +Node_F.parentNode(0).result)
}
