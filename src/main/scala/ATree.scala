import scala.collection.mutable.ListBuffer
import scala.annotation.varargs

  class ANode(
      _expression: String,
      _parentNode: Array[ANodeRoot] = null,
      _result: Boolean = false
  ) {
    var expression: String = _expression
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
  class ANodeRoot(
      _expression: String,
      _parentNode: ANodeRoot = null,
      _result: Boolean = false
  ) {
    var expression: String = _expression
    // var parentNode: ANode = _parentNode
    var parentNode = _parentNode
    var result: Boolean = _result
    var operandArr: Array[Boolean] =
      new Array[Boolean](expression.replace("^", "").length())
    var operandArrindx: Int = -1
    def evaluate = {
      if (operandArrindx == expression.replace("^", "").length() - 1) {
        if (expression.contains("^")) {
          if (operandArr.contains(false)) {
            operandArrindx = 0
            result = false
            propagate(false)
            // operandArrindx = 0
            false
          } else {
            operandArrindx = 0
            result = true
            propagate(true)
            // operandArrindx = 0
            true
          }
        }
      } else {
        "Not yet"
      }
    }
    def propagate(toSend: Boolean): Unit = {
      if (!(parentNode == null)) {
        // parentNode.operandArr(parentNode.operandArrindx) = toSend

        for (
          i <- parentNode.operandArrindx + 1 to parentNode.operandArrindx + expression
            .replace("^", "")
            .length()
        ) {
          parentNode.operandArr(i) = toSend
        }
        parentNode.operandArrindx += expression.replace("^", "").length()
        parentNode.evaluate
      }
      // parentNode.evalaute
    }
  }
class ATree{
  var tree = new ListBuffer[ANode]()
  def constructTree(list :Array[ANode]) ={
    
  for(i <- 0 to list.size -1) {
   tree += list(i)
  }
  tree.toList
  }
  
  
}

  
  




  

object arrnodetest extends App {
  val tree = new ATree()
  val Node_A = new ANode("A", Array(new ANodeRoot("A^B", new ANodeRoot("A^B^C", _result = false),_result = false), new ANodeRoot("A^F",_result = false)) ,_result = false)
  val Node_B = new ANode("B", Array(Node_A.parentNode(0)), false)
  val Node_C = new ANode("C", Array(Node_B.parentNode(0).parentNode), false)
  val Node_D = new ANode("D", Array(new ANodeRoot("D^E", new ANodeRoot("A^B^C^D^E", _result = false), false)), false)
  val Node_E = new ANode("E", Array(Node_D.parentNode(0)), false)
  val Node_F = new ANode("F", Array(Node_A.parentNode(1)), false)
  Node_A.parentNode(0).parentNode.parentNode = Node_E.parentNode(0).parentNode
  Node_A.ChangeResult(true)
  Node_B.ChangeResult(true)
  Node_C.ChangeResult(true)
  Node_D.ChangeResult(true)
  Node_E.ChangeResult(true)
  Node_F.ChangeResult(true)
  val nodeList = new Array[ANode](6)
  nodeList(0) = Node_A
  nodeList(1) = Node_B
  nodeList(2) = Node_C
  nodeList(3) = Node_D
  nodeList(4) = Node_E
  nodeList(5) = Node_F
  tree.constructTree(nodeList)
  tree.tree.foreach(x => println(x.expression))
  val a = tree.tree(0)
  // while(a.parentNode != null) {
  //   var curr = a.parentNode
  //   println(a.expression + ":" + a.result)
  //   curr = a.parentNode
  // }
  val b = tree.tree(0).parentNode(0).parentNode.parentNode
  println(b.expression + ": " + b.result)
  

}
