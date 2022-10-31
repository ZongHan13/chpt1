

class ANode(_expression : String, _parentNode: ANodeRoot = null, _result:Boolean = false){
 var expression:String = _expression
 var parentNode: ANodeRoot = _parentNode
 var result: Boolean = _result

  def ChangeResult(_result: Boolean) = {
    result = _result
    propagate(result)
  }
  def propagate(toSend: Boolean) = {
    parentNode.operandArrindx += 1
    parentNode.operandArr(parentNode.operandArrindx) = toSend
    parentNode.evalaute

  }
}
class ANodeRoot(_expression : String, _parentNode: ANodeRoot = null ,_result:Boolean = false){
 var expression:String = _expression
 //var parentNode: ANode = _parentNode
 var parentNode = _parentNode
 var result: Boolean = _result
 var operandArr: Array[Boolean] = new Array[Boolean](expression.replace("^","").length())
 var operandArrindx :Int = -1
 def evalaute = {
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
  parentNode.evalaute
  }
 }
}
object nodetest extends App {
  val Node_A = new ANode("A", new ANodeRoot("A^B", new ANodeRoot("A^B^C", _result = false) ,_result = false), false)
  val Node_B = new ANode("B", Node_A.parentNode, false)
  val Node_C = new ANode("C", Node_B.parentNode.parentNode, false)
  val Node_D = new ANode("D", new ANodeRoot("D^E", new ANodeRoot("A^B^C^D^E", _result = false), false), false)
  val Node_E = new ANode("E", Node_D.parentNode, false)
  Node_A.parentNode.parentNode.parentNode = Node_E.parentNode.parentNode
  //println(Node_A.parentNode.expression)
  //println(Node_A.parentNode == Node_B.parentNode)
  Node_A.ChangeResult(true)
  Node_B.ChangeResult(true)
  Node_C.ChangeResult(true)
  Node_D.ChangeResult(true)
  Node_E.ChangeResult(true)
  
  //Node_B.parentNode.evalaute
  //Node_C.parentNode.evalaute
  //Node_D.parentNode.evalaute
  //Node_A.parentNode.parentNode.parentNode.evalaute
  //Node_E.parentNode.parentNode.evalaute
  println(Node_B.parentNode.expression+ ": " + Node_B.parentNode.result)
  //println(Node_A.parentNode.parentNode.expression)
  println(Node_C.parentNode == Node_A.parentNode.parentNode & Node_C.parentNode == Node_B.parentNode.parentNode)
  println(Node_A.parentNode.expression + ":" + Node_A.parentNode.result)
  println(Node_C.parentNode.expression +": "  + Node_C.parentNode.result)
  //println("A^B^C".replace("^","").length())
  //println(Node_A.parentNode.parentNode.operandArr(2))
  println(Node_D.result)
  println(Node_D.parentNode.expression + ": " + Node_D.parentNode.result)
  //println(Node_E.parentNode == Node_D.parentNode)
  println(Node_E.parentNode.parentNode.expression + ": " + Node_E.parentNode.parentNode.result)
  Node_E.parentNode.parentNode.operandArr.foreach(x => println(x))
}
